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

package com.google.api.ads.adwords.awreporting.model.entities;

import com.google.api.ads.adwords.awreporting.model.persistence.mongodb.MongoEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** POJO to store OAuth credentials. */
@Entity
@Table(name = "AW_AuthToken")
public class AuthToken implements MongoEntity {

  /** Manager account id used as key to retrieve AUTH_TOKEN. */
  public static final String TOP_MANAGER_ACCOUNT_ID = "topManagerAccountId";

  @Id
  @Column(name = "TOP_MANAGER_ACCOUNT_ID")
  private String topManagerAccountId;

  @Column(name = "TOP_MANAGER_ACCOUNT_NAME")
  private String topManagerAccountName;

  @Column(name = "AUTH_TOKEN", length = 1024)
  private String authToken;

  @Column(name = "SCOPE", length = 1024)
  private String scope;

  /**
   * Constructor to satisfy Hibernate.
   */
  AuthToken() {}

  /**
   * Constructor for AuthToken class.
   *
   * @param topManagerAccountId the top level manager account ID
   * @param topManagerAccountName the top level account name
   * @param authToken the authentication Token
   * @param scope the scope of the authenticaion Token
   */
  public AuthToken(String topManagerAccountId, String topManagerAccountName, String authToken,
      String scope) {
    this.topManagerAccountId = topManagerAccountId;
    this.topManagerAccountName = topManagerAccountName;
    this.authToken = authToken;
    this.scope = scope;
  }

  @Override
  public String getRowId() {
    return topManagerAccountId;
  }

  public String getTopManagerAccountId() {
    return topManagerAccountId;
  }

  void setTopManagerAccountId(String topManagerAccountId) {
    this.topManagerAccountId = topManagerAccountId;
  }

  public String getTopManagerAccountName() {
    return topManagerAccountName;
  }

  void setTopManagerAccountName(String topManagerAccountName) {
    this.topManagerAccountName = topManagerAccountName;
  }

  public String getAuthToken() {
    return authToken;
  }

  void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public String getScope() {
    return scope;
  }

  void setScope(String scope) {
    this.scope = scope;
  }
}
