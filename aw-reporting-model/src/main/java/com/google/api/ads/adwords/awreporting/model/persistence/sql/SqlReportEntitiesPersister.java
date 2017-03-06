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

import com.google.api.ads.adwords.awreporting.model.entities.Report;
import com.google.api.ads.adwords.awreporting.model.persistence.EntityPersister;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the basic implementation of the persistence layer to communicate with a SQL database.
 * The communication is done using a generic {@link SessionFactory}.
 */
@Component
@Qualifier("sqlEntitiesPersister")
public class SqlReportEntitiesPersister implements EntityPersister {

  private static final int BATCH_SIZE = 50;

  private int batchSize = BATCH_SIZE;
  private SessionFactory sessionFactory;

  /**
   * Constructor.
   *
   * @param sessionFactory the session factory to communicate with the DB.
   */
  @Autowired
  public SqlReportEntitiesPersister(SessionFactory sessionFactory) {
    this.sessionFactory =
        Preconditions.checkNotNull(sessionFactory, "SessionFactory can not be null");
  }

  /**
   * Persists all the given entities into the DB configured in the {@code SessionFactory}.
   */
  @Override
  @Transactional
  public void persistReportEntities(List<? extends Report> reportEntities) {
    int batchFlush = 0;
    Session session = sessionFactory.getCurrentSession();

    for (Report report : reportEntities) {
      report.setRowId();
      session.saveOrUpdate(report);
      batchFlush++;

      if (batchFlush == batchSize) {
        session.flush();
        session.clear();
        batchFlush = 0;
      }
    }

    if (batchFlush > 0) {
      session.flush();
      session.clear();
    }
  }

  @Override
  @Transactional
  public <T> T save(T t) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(t);
    return t;
  }

  private <T> Criteria createCriteria(Class<T> classT) {
    Session session = sessionFactory.getCurrentSession();
    return session.createCriteria(classT);
  }

  @SuppressWarnings("unchecked")
  @Override
  @Transactional(readOnly = true)
  public <T, V> List<T> get(Class<T> classT, String key, V value) {
    Criteria criteria = createCriteria(classT);
    criteria.add(Restrictions.eq(key, value));
    return criteria.list();
  }

  @SuppressWarnings("unchecked")
  @VisibleForTesting
  <T extends Report> List<T> listReports(Class<T> classT) {
    Criteria criteria = createCriteria(classT);
    return criteria.list();
  }

  @VisibleForTesting
  protected void setBatchSize(int batchSize) {
    Preconditions.checkArgument(batchSize > 0, "batchSize <= 0");
    this.batchSize = batchSize;
  }
}
