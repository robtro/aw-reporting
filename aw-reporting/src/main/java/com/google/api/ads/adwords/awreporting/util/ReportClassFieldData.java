// Copyright 2014 Google Inc. All Rights Reserved.
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

package com.google.api.ads.adwords.awreporting.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.api.ads.adwords.awreporting.model.csv.annotation.CsvField;
import com.google.api.ads.adwords.awreporting.model.csv.annotation.MoneyField;
import com.google.api.ads.adwords.jaxws.v201603.cm.ReportDefinitionField;

public class ReportClassFieldData {
  
  private enum ReportFieldType {
    Money,
    Integer,
    Long,
    Double,
    Date,
    String  // default to string for undefined types
  }
  
  private static final Logger LOGGER = Logger.getLogger(ReportClassFieldData.class);
  
  private static final String MONEY_FIELD_NAME = "Money";
  
  private static final Map<ReportFieldType, List<String>> reportFieldTypesMap
      = new HashMap<ReportFieldType, List<String>>();
  static {
    reportFieldTypesMap.put(ReportFieldType.Money,   Arrays.asList("BigDecimal", "Long", "String"));
    reportFieldTypesMap.put(ReportFieldType.Integer, Arrays.asList("Integer", "Long"));
    reportFieldTypesMap.put(ReportFieldType.Long,    Arrays.asList("Long"));
    reportFieldTypesMap.put(ReportFieldType.Double,  Arrays.asList("BigDecimal", "Double"));
    reportFieldTypesMap.put(ReportFieldType.Date,    Arrays.asList("Date"));
    reportFieldTypesMap.put(ReportFieldType.String,  Arrays.asList("String"));
  }
  
  
  final private String fieldName;
  final private String variableName;
  
  //in Java entity class
  final private String declaredDisplayName;
  final private String declaredTypeName;
  final private boolean declaredMoneyField;
  
  // from ReportDefintionService
  final private String definedDisplayName;
  final private String definedTypeName;
  final private boolean definedMoneyField;
  
  public ReportClassFieldData(Field field, CsvField annotation, ReportDefinitionField rdf)
  {    
    this.fieldName    = annotation.reportField();
    this.variableName = field.getName();
    
    this.declaredDisplayName = annotation.value();
    this.declaredTypeName    = field.getType().getSimpleName();
    this.declaredMoneyField  = field.isAnnotationPresent(MoneyField.class);
    
    // may have extra fields declared in entity class (which are not found in ReportDefinitionService)
    this.definedDisplayName = rdf == null ? null : rdf.getDisplayFieldName();
    this.definedTypeName    = rdf == null ? null : rdf.getFieldType();
    this.definedMoneyField  = rdf == null ? false : rdf.getFieldType().equals(MONEY_FIELD_NAME);
  }
  
  public String getFieldName() {
    return fieldName;
  }
  
  public String getVariableName() {
    return variableName;
  }
  
  public String getDeclaredDisplayName() {
    return declaredDisplayName;
  }
  
  public String getDeclaredTypeName() {
    return declaredTypeName;
  }
  
  public boolean getDeclaredMoneyField() {
    return declaredMoneyField;
  }
  
  public String getDefinedDisplayName() {
    return definedDisplayName;
  }
  
  public String getDefinedTypeName() {
    return definedTypeName;
  }
  
  public boolean getDefinedMoneyField() {
    return definedMoneyField;
  }
  
  /**
   * Check whether the declared display name (from Java entity class) matches
   * the defined display name (from ReportDefinitionService)
   */
  public boolean checkMatchedDisplayName() {
    return declaredDisplayName.equals(definedDisplayName);
  }
  
  /**
   * Check whether the declared @MoneyField annotation (from Java entity class) matches
   * the defined Money type (from ReportDefinitionService)
   */
  public boolean checkMatchedMoneyType() {
    return declaredMoneyField == definedMoneyField;
  }
  
  /**
   * Check whether the declared field type (in the Java entity class) conforms to 
   * the defined type name (retrieved from ReportDefintionService).
   * 
   * @param fieldData the ReportCsvFieldData object of the field
   * @param fieldTypaName the defined type name of the field (from ReportDefinitionService)
   */
  public boolean checkFieldType() {
    ReportFieldType fieldType = null;
    try {
      fieldType = ReportFieldType.valueOf(definedTypeName);
    } catch (IllegalArgumentException e) {
      LOGGER.debug("No field type mapping for \"" + definedTypeName + "\", fall back to String");
      fieldType = ReportFieldType.String;
    }
    
    return reportFieldTypesMap.get(fieldType).contains(declaredTypeName);
  }
}
