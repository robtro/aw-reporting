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

package com.google.api.ads.adwords.awreporting.authentication;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the scope used by AwReports for all OAuth2 authenticated services.
 */
public enum OAuthScope {
  
  ADWORDS("https://adwords.google.com/api/adwords"),
    
  DRIVE("https://www.googleapis.com/auth/drive.file");
  
  private final String scope;
  
  private OAuthScope(String scope) {
    this.scope = scope;
  }
  
  /**
   * Convenience method to provide a comma separated scope list 
   * consisting of the services provided in the {@link OAuthScope}s.
   * 
   * @param scopeTypes
   * @return scopes in csv format
   */
  public static String getScopeCsv(OAuthScope... scopeTypes) {
    List<String> scope = getScopeList(scopeTypes);
    return StringUtils.join(scope, ',');
  }
  
  /**
   * Provides a scope {@link List} consisting of the services provided 
   * in the {@link OAuthScope}s.
   * 
   * @param scopeTypes
   * @return list of scopes
   */
  public static List<String> getScopeList(OAuthScope... scopeTypes) {
    List<String> scopes = new ArrayList<String>();
    
    for (OAuthScope type : scopeTypes) {
      switch (type) {
        case ADWORDS:
          scopes.add(ADWORDS.scope);
          break;
        case DRIVE:
          scopes.add(DRIVE.scope);
          break;
        default:
          throw new IllegalArgumentException("Unknown type " + type.name());
      }
    }
    
    return scopes;
  }
}
