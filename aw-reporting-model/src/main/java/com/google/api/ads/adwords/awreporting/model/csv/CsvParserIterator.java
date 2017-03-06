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

package com.google.api.ads.adwords.awreporting.model.csv;

import com.google.api.ads.adwords.awreporting.model.entities.Report;
import com.google.common.collect.AbstractIterator;

/**
 * Iterator to read one line at a time from the CSV file.
 *
 * @param <T> type of class to map.
 */
public class CsvParserIterator<T extends Report> extends AbstractIterator<T> {
  private final ReportEntityMappingStrategy<T> mapper;
  private final AwReportCsvReader csvReader;
  private final ModifiedCsvToBean<T> csvToBean;

  /**
   * @param mapper the mapping strategy for the bean.
   * @param csvReader the CSV file reader.
   * @param csvToBean the modified implementation of the {@code CsvToBean} to access the parsing
   *     methods
   */
  public CsvParserIterator(
      ReportEntityMappingStrategy<T> mapper,
      AwReportCsvReader csvReader,
      ModifiedCsvToBean<T> csvToBean) {
    this.mapper = mapper;
    this.csvReader = csvReader;
    this.csvToBean = csvToBean;
  }

  /**
   * @see com.google.common.collect.AbstractIterator#computeNext()
   */
  @Override
  protected T computeNext() {
    String[] line = csvReader.readNext();
    
    try {
      return (line == null) ? endOfData() : csvToBean.processLine(mapper, line);
    } catch (CsvReportParsingException e) {
      // AbstractIterator.computNext() requires to throw RuntimeException when any unrecoverable
      // error happens. As this is the only place for using an unchecked exception, we don't bother
      // defining a specific class for it.
      throw new RuntimeException("CsvParserIterator.computeNext() failed.", e);
    }
  }
}
