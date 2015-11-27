package com.google.api.ads.adwords.awreporting.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.api.ads.adwords.jaxws.v201509.cm.ReportDefinitionField;

public class ReportDefinitionData {
  private static final String MONEY_FILED_NAME = "Money";
  
  // raw data
  private Map<String, ReportDefinitionField> reportDefinitionFieldMap;
  
  // derived data
  // display name -> field name mapping
  private Map<String, String> displayName2FieldNameMap;
  // all fields of type Money
  private Set<String> moneyFields;
  
  public ReportDefinitionData(List<ReportDefinitionField> reportDefinitionFields) {
    final int size = reportDefinitionFields.size();
    reportDefinitionFieldMap = new HashMap<String, ReportDefinitionField>(size);
    displayName2FieldNameMap = new HashMap<String, String>(size);
    moneyFields = new HashSet<String>();
    
    for (ReportDefinitionField field : reportDefinitionFields) {
      reportDefinitionFieldMap.put(field.getFieldName(), field);
      displayName2FieldNameMap.put(field.getDisplayFieldName(), field.getFieldName());
      
      if (field.getFieldType().equals(MONEY_FILED_NAME)) {
        moneyFields.add(field.getFieldName());
      }
    }
  }
  
  // Get all field names of this report type
  public Set<String> getFieldNames() {
    return reportDefinitionFieldMap.keySet();
  }
  
  // Look up the display name from the field name
  public String getDisplayName(String fieldName) {
    if (!reportDefinitionFieldMap.containsKey(fieldName)) {
      return null;
    }
    
    return reportDefinitionFieldMap.get(fieldName).getDisplayFieldName();
  }
  
  // Look up the field name from the display name
  public String getFieldName(String displayName) {
    return displayName2FieldNameMap.get(displayName);
  }
  
  // Look up the field type from the field name
  public String getFieldType(String fieldName) {
    if (!reportDefinitionFieldMap.containsKey(fieldName)) {
      return null;
    }
    
    return reportDefinitionFieldMap.get(fieldName).getFieldType();
  }
}
