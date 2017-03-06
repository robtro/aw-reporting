// Copyright 2016 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.api.ads.adwords.awreporting.processors.file;

import com.google.api.ads.adwords.awreporting.model.csv.AwReportCsvReader;
import com.google.api.ads.adwords.awreporting.model.csv.CsvParserIterator;
import com.google.api.ads.adwords.awreporting.model.csv.CsvReportParsingException;
import com.google.api.ads.adwords.awreporting.model.csv.ModifiedCsvToBean;
import com.google.api.ads.adwords.awreporting.model.csv.ReportEntityMappingStrategy;
import com.google.api.ads.adwords.awreporting.model.entities.DateRangeAndType;
import com.google.api.ads.adwords.awreporting.model.entities.Report;
import com.google.api.ads.adwords.awreporting.model.persistence.EntityPersister;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This {@link Runnable} implements the core logic to parse the rows in the CSV file for the
 * report type, and persists the beans into the database.
 *
 * @param <R> type of sub Report.
 */
public class FileRunnableProcessor<R extends Report> implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(FileRunnableProcessor.class);

  private final String topCustomerId;
  private final File file;
  private final boolean fileDownloadedByAPI;
  private final ModifiedCsvToBean<R> csvToBean;
  private final ReportEntityMappingStrategy<R> mappingStrategy;
  private final DateRangeAndType dateRangeAndType;
  private final EntityPersister entityPersister;
  private final int reportRowsSetSize;

  /**
   * @param topCustomerId the top customer account id.
   * @param file the CSV file.
   * @param fileDownloadedByAPI whether the report files are downloaded by AWAPI (no header/summary)
   *     or provided by user (via csvReportFile option).
   * @param csvToBean the {@code CsvToBean} for handling different formats from reports.
   * @param mappingStrategy the mapping strategy to convert report CSV file into Java beans using
   *     annotations.
   * @param dateRangeAndType the date range and type.
   * @param entityPersister the bean to persist report to database.
   * @param reportRowsSetSize the size of the set parsed that will be persisted to the database.
   */
  public FileRunnableProcessor(
      String topCustomerId,
      File file,
      boolean fileDownloadedByAPI,
      ModifiedCsvToBean<R> csvToBean,
      ReportEntityMappingStrategy<R> mappingStrategy,
      DateRangeAndType dateRangeAndType,
      EntityPersister entityPersister,
      int reportRowsSetSize) {
    
    this.topCustomerId = Preconditions.checkNotNull(topCustomerId, "topCustomerId cannot be null");
    this.file = Preconditions.checkNotNull(file, "file cannot be null");
    this.fileDownloadedByAPI = fileDownloadedByAPI;
    this.csvToBean = Preconditions.checkNotNull(csvToBean, "csvToBean cannot be null");
    this.mappingStrategy =
        Preconditions.checkNotNull(mappingStrategy, "mappingStrategy cannot be null");
    this.dateRangeAndType =
        Preconditions.checkNotNull(dateRangeAndType, "dateRangeAndType cannot be null");
    this.entityPersister =
        Preconditions.checkNotNull(entityPersister, "entityPersister cannot be null");
    this.reportRowsSetSize = reportRowsSetSize;
    Preconditions.checkArgument(reportRowsSetSize > 0, "reportRowsSetSize must be > 0");
  }

  /**
   * Executes the API call to download the report that was given when this {@code Runnable} was
   * created.
   *
   * The download blocks this thread until it is finished, and also does the file copying.
   *
   * There is also a retry logic implemented by this method, where the times retried depends on the
   * value given in the constructor.
   *
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    try {
      logger.debug("Starting parse of report rows...");
      AwReportCsvReader csvReader = createCsvReader();
      CsvParserIterator<R> reportRowsList = csvToBean.lazyParse(mappingStrategy, csvReader);
      logger.debug("... success.");

      logger.debug("Starting report persistence...");
      List<R> reportBuffer = Lists.newArrayListWithCapacity(reportRowsSetSize);
      while (reportRowsList.hasNext()) {
        R report = reportRowsList.next();

        // Getting Account Id from File Name for reports that do not have Client Customer Id
        if (report.getCustomerId() == null) {
          String filename = file.getName();
          if (filename.contains("-") && filename.split("-") != null
              && filename.split("-").length >= 2 && filename.split("-")[1].matches("\\d*")) {
            report.setCustomerId(Long.parseLong(file.getName().split("-")[1]));
          } else {
            throw new IllegalArgumentException(
                "Unknown customer id for report "
                    + report.getClass().getSimpleName()
                    + " file "
                    + file.getName());
          }
        }

        report.setTopCustomerId(Long.parseLong(topCustomerId.replaceAll("-", "")));
        report.setDateRangeType(dateRangeAndType.getTypeStr());
        report.setStartDate(dateRangeAndType.getStartDateStr());
        report.setEndDate(dateRangeAndType.getEndDateStr());
        report.setRowId();

        reportBuffer.add(report);

        if (reportBuffer.size() >= reportRowsSetSize) {
          entityPersister.persistReportEntities(reportBuffer);
          reportBuffer.clear();
        }
      }
      if (reportBuffer.size() > 0) {
        entityPersister.persistReportEntities(reportBuffer);
      }
      logger.debug("... success.");
    } catch (IOException | CsvReportParsingException e) {
      logger.error("Error processing file: " + file.getAbsolutePath(), e);
    } catch (RuntimeException e) {
      logger.error("Runtime error processing file: " + file.getAbsolutePath(), e);
    }
  }

  /**
   * Creates the proper {@link AwReportCsvReader} to parse the AW reports.
   *
   * @return the {@code AwReportCsvReader}
   * @throws IOException in case the file is failed to open or parse as CSV.
   */
  private AwReportCsvReader createCsvReader() throws IOException {
    logger.debug("Creating CSVReader for file: " + file.getAbsolutePath());

    InputStreamReader reader =
        new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

    // File downloaded by API means no header / summary lines.
    boolean hasHeaderSummary = !fileDownloadedByAPI;
    return new AwReportCsvReader(reader, hasHeaderSummary, hasHeaderSummary);
  }
}
