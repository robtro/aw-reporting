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
  private List<ReportDefinitionField> reportDefintionFields;
  
  // derived data
  // field name -> display name mapping
  private Map<String, String> fieldName2DisplayNameMap;
  // display name -> field name mapping
  private Map<String, String> displayName2FieldNameMap;
  // all fields of type Money
  private Set<String> moneyFields;
  
  public ReportDefinitionData(List<ReportDefinitionField> reportDefinitionFields) {
    this.reportDefintionFields = reportDefinitionFields;
    displayName2FieldNameMap = new HashMap<String, String>(reportDefinitionFields.size());
    moneyFields = new HashSet<String>();
    
    for (ReportDefinitionField field : reportDefinitionFields) {
      fieldName2DisplayNameMap.put(field.getFieldName(), field.getDisplayFieldName());
      displayName2FieldNameMap.put(field.getDisplayFieldName(), field.getFieldName());
      
      if (field.getFieldType().equals(MONEY_FILED_NAME)) {
        moneyFields.add(field.getFieldName());
      }
    }
  }
  
  public Set<String> getFieldNames() {
    return fieldName2DisplayNameMap.keySet();
  }
  
  public String getDisplayName(String fieldName) {
    return fieldName2DisplayNameMap.get(fieldName);
  }
}
