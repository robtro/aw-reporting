package com.google.api.ads.adwords.awreporting.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


public class ReportClassFieldData {
  
  public enum ReportFieldType {
    Money,
    Integer,
    Long,
    Double,
    Date,
    String  // default to string for undefined types
  }
  
  private static final Logger LOGGER = Logger.getLogger(ReportClassFieldData.class);
  
  private static final Map<ReportFieldType, List<Class<?>>> reportFieldTypesMap
      = new HashMap<ReportFieldType, List<Class<?>>>();
  static {
    reportFieldTypesMap.put(ReportFieldType.Money,   Arrays.<Class<?>>asList(BigDecimal.class, Long.class, String.class));
    reportFieldTypesMap.put(ReportFieldType.Integer, Arrays.<Class<?>>asList(Integer.class, Long.class));
    reportFieldTypesMap.put(ReportFieldType.Long,    Arrays.<Class<?>>asList(Long.class));
    reportFieldTypesMap.put(ReportFieldType.Double,  Arrays.<Class<?>>asList(BigDecimal.class, Double.class));
    reportFieldTypesMap.put(ReportFieldType.Date,    Arrays.<Class<?>>asList(Date.class));
    reportFieldTypesMap.put(ReportFieldType.String,  Arrays.<Class<?>>asList(String.class));
  }
  
  
  private String fieldName;
  private String variableName;
  
  //in Java entity class
  private String declaredDisplayName;
  private Class<?> declaredType;
  
  // from ReportDefintionService
  private String definedDisplayName;
  private String definedTypeName;
  
  public ReportClassFieldData(String fieldName, String variableName, String declaredDisplayName, Class<?> declaredType,
      String definedDisplayName, String definedTypeName) {
    this.fieldName = fieldName;
    this.variableName = variableName;
    this.declaredDisplayName = declaredDisplayName;
    this.declaredType = declaredType;
    this.definedDisplayName = definedDisplayName;
    this.definedTypeName = definedTypeName;
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
  
  public Class<?> getDeclaredType() {
    return declaredType;
  }
  
  public String getDefinedDisplayName() {
    return definedDisplayName;
  }
  
  public String getDefinedTypeName() {
    return definedTypeName;
  }
  
  /*
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
    
    return reportFieldTypesMap.get(fieldType).contains(declaredType);
  }
}
