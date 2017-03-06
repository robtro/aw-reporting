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

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Class for reading AdWords reports (CSV format).
 *
 * <p>It handles both API downloaded reports (without header or summary), and user downloaded
 * reports (with header and summary). In order to accomplish the latter behavior,
 */
public class AwReportCsvReader {
  // The 'Total' {@code String} represents the last line of the AW Report CSV file.
  private static final String AW_REPORT_CSV_TOTAL = "total";

  private final boolean hasSummary;
  private final String[] columnNames;
  private final ListIterator<CSVRecord> records;

  /**
   * Constructs AwReportCsvReader with params of whether header / summary lines exist.
   *
   * @param reader the reader to an underlying CSV source
   * @param hasHeader whether the report has header line
   * @param hasSummary whether the report has summary line
   */
  public AwReportCsvReader(Reader reader, boolean hasHeader, boolean hasSummary)
      throws IOException {
    try (BufferedReader br = new BufferedReader(reader)) {
      if (hasHeader) {
        br.readLine();
      }

      Splitter splitter = Splitter.on(',').trimResults();
      columnNames = Iterables.toArray(splitter.split(br.readLine()), String.class);
      records = CSVFormat.RFC4180.withHeader(columnNames).parse(br).getRecords().listIterator();
    }
    
    this.hasSummary = hasSummary;
  }

  /**
   * Returns the next CSV line in the file. If the line starts with the AW last line total String,
   * then returns null.
   *
   * @return the next line in the CSV, or {@code null} in case the file has ended, or the total line
   *     was reached
   */
  public String[] readNext() {
    String[] next = null;
    if (records.hasNext()) {
      CSVRecord record = records.next();
      List<String> values = Lists.newArrayListWithCapacity(record.size());
      for (int i = 0; i < record.size(); i++) {
        values.add(record.get(i));
      }

      if (!hasSummary || !AW_REPORT_CSV_TOTAL.equalsIgnoreCase(Iterables.getFirst(values, null))) {
        next = values.toArray(new String[0]);
      }
    }
    return next;
  }
  
  /**
   * Get the column names of the CSV file, in the original order.
   */
  public String[] getColumnNames() {
    return columnNames;
  }
}
