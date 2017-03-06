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

package com.google.api.ads.adwords.awreporting.model.persistence.sql;

import com.google.api.ads.adwords.awreporting.model.entities.AuthToken;
import com.google.api.ads.adwords.awreporting.model.persistence.AuthTokenPersister;
import com.google.common.base.Preconditions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * The SQL implementation of the token persister.
 */
@Component
public class SqlAuthTokenPersister implements AuthTokenPersister {

  private SessionFactory sessionFactory;

  @Autowired
  public SqlAuthTokenPersister(SessionFactory sessionFactory) {
    this.sessionFactory =
        Preconditions.checkNotNull(sessionFactory, "SessionFactory can not be null");
  }

  @Override
  @Transactional
  public void persistAuthToken(AuthToken authToken) {
    Session session = this.sessionFactory.getCurrentSession();
    session.saveOrUpdate(authToken);
  }
  
  @Override
  @Transactional
  public AuthToken getAuthToken(String topManagerAccountId) {
    Session session = this.sessionFactory.getCurrentSession();
    return (AuthToken) session.get(AuthToken.class, topManagerAccountId);
  }
}
