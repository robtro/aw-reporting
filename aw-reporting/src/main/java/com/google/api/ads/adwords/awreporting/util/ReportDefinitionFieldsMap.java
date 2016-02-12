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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.api.ads.adwords.jaxws.v201601.cm.ReportDefinitionField;

public class ReportDefinitionFieldsMap {
  private Map<String, ReportDefinitionField> reportDefinitionFieldMap;
  
  public ReportDefinitionFieldsMap(List<ReportDefinitionField> reportDefinitionFields) {
    reportDefinitionFieldMap
        = new HashMap<String, ReportDefinitionField>(reportDefinitionFields.size());
    
    for (ReportDefinitionField field : reportDefinitionFields) {
      reportDefinitionFieldMap.put(field.getFieldName(), field);
    }
  }
  
  // Get all field names of this report type
  public Set<String> getFieldNames() {
    return reportDefinitionFieldMap.keySet();
  }
  
  public ReportDefinitionField get(String fieldName) {
    return reportDefinitionFieldMap.get(fieldName);
  }
}
