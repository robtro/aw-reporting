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

import com.google.api.ads.adwords.awreporting.model.csv.annotation.CsvField;
import com.google.api.ads.adwords.awreporting.model.csv.annotation.MoneyField;
import com.google.api.ads.adwords.awreporting.model.entities.Report;
import com.google.common.base.Preconditions;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class describes the mapping strategy to convert CSV files into corresponding report entity
 * Java beans.
 *
 * <p>The {@code ReportEntityMappingStrategy} is expecting to map only Java beans that extend from
 * {@link Report}. A code example on how to use this mapping strategy:
 *
 * <pre>{@code
 * AwReportCsvReader csvReader = new AwReportCsvReader(new InputStreamReader(
 *    new FileInputStream("file-name"), StandardCharsets.UTF-8), false, false);
 *
 * ReportEntityMappingStrategy<AdPerformanceReport> mappingStrategy =
 *    new ReportEntityMappingStrategy<AdPerformanceReport>(AdPerformanceReport.class);
 *
 * CsvToBean<AdPerformanceReport> csvToBean = new ModifiedCsvToBean<AdPerformanceReport>();
 * CsvParserIterator<R> reportRowsList = csvToBean.lazyParse(mappingStrategy, csvReader);
 * }</pre>
 *
 * <p>The example uses a {@link AwReportCsvReader} in order to parse the CSV files from the AdWords
 * reporting API.
 *
 * @param <T> type of sub Report.
 */
public class ReportEntityMappingStrategy<T extends Report> {
  private Class<T> reportEntityClass;
  private final Map<Integer, String> csvIndexToReportNames = new HashMap<Integer, String>();
  private final Set<String> fieldsWithMoneyValues = new HashSet<String>();

  /**
   * @param reportEntityClass the {@code class} of the report entity POJO. This parameter is
   *        obligatory.
   */
  public ReportEntityMappingStrategy(Class<T> reportEntityClass) {
    this.reportEntityClass =
        Preconditions.checkNotNull(reportEntityClass, "The report entity class must be specified.");
    this.reportEntityClass = reportEntityClass;
  }

  /**
   * Process the header of the CSV file.
   *
   * This method scans the class of the bean for the proper annotations, and associates the correct
   * column index to the correct field.
   *
   * @param header the list of column names
   */
  public void processHeader(String[] header) throws IOException {
    Map<String, String> nameMapping = createNameMapping();
    for (int i = 0; i < header.length; i++) {
      csvIndexToReportNames.put(i, nameMapping.get(header[i]));
    }
  }

  /**
   * Creates the {@code Map} where the key is the name of the property in the CSV file, and the
   * value is the name of the field in the Java bean.
   *
   * @return the {@code Map} of the properties for the bean class.
   */
  private Map<String, String> createNameMapping() {
    Map<String, String> nameMapping = new HashMap<String, String>();
    Class<?> currentClass = reportEntityClass;

    while (currentClass != Object.class) {
      addNameMappingForDeclaredFields(nameMapping, currentClass);
      currentClass = currentClass.getSuperclass();
    }

    return nameMapping;
  }

  /**
   * Scans the current class' fields for the mapped properties, and associate then to the CSV file
   * columns.
   *
   * @param nameMapping the map to be filled.
   * @param currentClass the class to be scanned.
   */
  private void addNameMappingForDeclaredFields(Map<String, String> nameMapping,
      Class<?> currentClass) {
    Field[] declaredFields = currentClass.getDeclaredFields();

    for (Field field : declaredFields) {
      addNameMappingIfAnnotationPresent(nameMapping, field);
      addMoneyMappingIfAnnotationPresent(field);
    }
  }

  /**
   * Checks for the annotation, and if the annotation is present, creates the association between
   * the CSV property and the field.
   *
   * @param nameMapping the map that is being filled.
   * @param field the current field.
   */
  private void addNameMappingIfAnnotationPresent(Map<String, String> nameMapping, Field field) {
    if (field.isAnnotationPresent(CsvField.class)) {
      CsvField reportFieldAnnotation = field.getAnnotation(CsvField.class);
      String csvFieldName = reportFieldAnnotation.value();
      nameMapping.put(csvFieldName, field.getName());
    }
  }

  /**
   * Checks for the @MoneyField annotation, and if the annotation is present, puts a true value at
   * fieldsWithMoneyValues, this value will be used when parsing CSV to use BigDecimal and divide
   * by 1M.
   *
   * @param field the current field.
   */
  private void addMoneyMappingIfAnnotationPresent(Field field) {
    if (field.isAnnotationPresent(MoneyField.class)) {
      fieldsWithMoneyValues.add(field.getName());
    }
  }

  /**
   * Creates a new instance of the Java bean.
   *
   * <p>The Java bean must have a public constructor that receives no arguments in order to be
   * instantiated.
   */
  public T createBean() throws CsvReportParsingException {
    try {
      return reportEntityClass.getConstructor().newInstance();
    } catch (NoSuchMethodException
        | SecurityException
        | InstantiationException
        | IllegalAccessException
        | IllegalArgumentException
        | InvocationTargetException e) {
      throw new CsvReportParsingException(
          "Failed to instantiate " + reportEntityClass.getCanonicalName(), e);
    }
  }

  /**
   * Find the property descriptor that is referenced by the given column index.
   *
   * <p>The mapping between the indexes and the field were created when the CSV header was captured.
   */
  public PropertyDescriptor findDescriptor(int columnNumber) throws CsvReportParsingException {
    String propertyName = csvIndexToReportNames.get(columnNumber);
    if (propertyName == null) {
      throw new CsvReportParsingException(
          "Invalid column number " + columnNumber + " in map " + csvIndexToReportNames);
    }

    try {
      return new PropertyDescriptor(propertyName, reportEntityClass);
    } catch (IntrospectionException e) {
      throw new CsvReportParsingException(
          "Failed to create property descriptor for " + propertyName, e);
    }
  }

  /**
   * Checks if a field on the class has the annotations @MoneyField,
   * this value will be used when parsing CSV to use BigDecimal and divide by 1M.
   */
  public boolean isMoneyField(String field) {
    return fieldsWithMoneyValues.contains(field);
  }
}
