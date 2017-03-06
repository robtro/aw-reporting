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

import com.opencsv.bean.CsvToBean;
import com.google.api.ads.adwords.awreporting.model.entities.Report;
import com.google.api.ads.adwords.awreporting.model.util.BigDecimalUtil;
import com.google.api.ads.adwords.awreporting.model.util.StringsUtil;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

/**
 * Modified CSV to Bean converter to handle the different number formats from the reports.
 *
 * @param <T> type of sub Report.
 */
public class ModifiedCsvToBean<T extends Report> extends CsvToBean<T> {
  private static final Logger logger =
      Logger.getLogger(ModifiedCsvToBean.class.getCanonicalName());

  /**
   * Parses the CSV lazily, letting the client class decide when getting new elements.
   *
   * @param mapper the mapping strategy for the bean
   * @param csvReader the CSV file reader
   * @return the Iterator to go over the CSV elements
   * @throws CsvReportParsingException error on parsing from the csvReader
   */
  public CsvParserIterator<T> lazyParse(
      ReportEntityMappingStrategy<T> mapper, AwReportCsvReader csvReader)
      throws CsvReportParsingException {
    try {
      mapper.processHeader(csvReader.getColumnNames());
      return new CsvParserIterator<T>(mapper, csvReader, this);
    } catch (IOException e) {
      throw new CsvReportParsingException("Error parsing CSV: failed to read the header line.", e);
    }
  }

  /**
   * Creates a single object from a line from the CSV file.
   *
   * @param mapper the mapping strategy for the bean
   * @param line array of Strings from the CSV file
   * @return Object containing the values
   * @throws CsvReportParsingException error on parsing the line
   */
  protected T processLine(ReportEntityMappingStrategy<T> mapper, String[] line)
      throws CsvReportParsingException {
    T bean = mapper.createBean();

    for (int col = 0; col < line.length; col++) {
      PropertyDescriptor prop = mapper.findDescriptor(col); // Return value is not null.
      String value = line[col];

      if (StringsUtil.isEmptyValue(value)) {
        value = null;
      } else {
        value = trimIfPossible(value, prop);

        // Remove dashes inside a number format (such as for CID "xxx-xxx-xxxx").
        if (isNumberTypeProperty(prop)) {
          value = StringsUtil.removeDashes(value);
        }
      }

      try {
        Object obj = convertValue(value, prop);

        // Convert Money values to regular Decimals by dividing by a Million
        if (mapper.isMoneyField(prop.getName())) {
          obj = BigDecimalUtil.parseFromMoneyString((String) obj);
        }

        prop.getWriteMethod().invoke(bean, obj);
      } catch (InstantiationException
          | IllegalAccessException
          | IllegalArgumentException
          | InvocationTargetException e) {
        String errorMsg =
            String.format(
                "Error parsing column #%d with contents: %s, and property descriptor: %s.",
                col, value, prop.toString());
        logger.severe(errorMsg);
        throw new CsvReportParsingException(errorMsg, e);
      }
    }

    return bean;
  }

  /**
   * Trims the property if it is of type String.
   *
   * @param stringValue the string value of the property.
   * @param propertyDescriptor the property descriptor.
   * @return the property value trimmed.
   */
  private static String trimIfPossible(
      String stringValue, PropertyDescriptor propertyDescriptor) {
    return isStringTypeProperty(propertyDescriptor) ? stringValue.trim() : stringValue;
  }

  private static boolean isStringTypeProperty(PropertyDescriptor propertyDescriptor) {
    return String.class.isAssignableFrom(propertyDescriptor.getPropertyType());
  }

  private static boolean isNumberTypeProperty(PropertyDescriptor propertyDescriptor) {
    return Number.class.isAssignableFrom(propertyDescriptor.getPropertyType());
  }
}
