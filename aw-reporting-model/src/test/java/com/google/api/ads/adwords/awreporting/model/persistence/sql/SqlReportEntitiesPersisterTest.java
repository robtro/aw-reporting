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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.google.api.ads.adwords.awreporting.model.entities.AccountPerformanceReport;
import com.google.api.ads.adwords.awreporting.model.entities.DateRangeAndType;
import com.google.common.collect.Lists;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Test case for the {@code SqlReportEntitiesPersister} class.
 */
@RunWith(JUnit4.class)
public class SqlReportEntitiesPersisterTest {

  private SqlReportEntitiesPersister reportEntitiesPersister;

  @Mock private Session session;

  @Mock private SessionFactory sessionFactory;

  @Mock private Criteria criteria;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
    Mockito.when(session.createCriteria(AccountPerformanceReport.class)).thenReturn(criteria);

    reportEntitiesPersister = new SqlReportEntitiesPersister(sessionFactory);
  }

  /**
   * Tests the persistence and retrieval of Report Entities.
   */
  @Test
  public void testPersistence() {
    AccountPerformanceReport report = new AccountPerformanceReport(123L, 456L);
    report.setAccountDescriptiveName("testAccount");

    LocalDate today = LocalDate.now();
    DateRangeAndType dateRange = DateRangeAndType.fromValues(today, today, null);
    report.setDateRangeType(dateRange.getTypeStr());
    report.setStartDate(dateRange.getStartDateStr());
    report.setEndDate(dateRange.getEndDateStr());

    report.setRowId();
    List<AccountPerformanceReport> reportList = Lists.newArrayList();
    reportList.add(report);
    reportEntitiesPersister.persistReportEntities(reportList);

    Mockito.when(reportEntitiesPersister.listReports(AccountPerformanceReport.class))
        .thenReturn(reportList);
    List<AccountPerformanceReport> reportAccountList =
        reportEntitiesPersister.listReports(AccountPerformanceReport.class);
    assertReportEntities(reportAccountList, 123L, 456L, "testAccount");

    report = new AccountPerformanceReport(789L, 456L);
    report.setAccountDescriptiveName("updatedTestAccount");
    report.setDateRangeType(dateRange.getTypeStr());
    report.setStartDate(dateRange.getStartDateStr());
    report.setEndDate(dateRange.getEndDateStr());
    
    reportList = Lists.newArrayList();
    reportList.add(report);
    reportEntitiesPersister.persistReportEntities(reportList);

    Mockito.when(reportEntitiesPersister.listReports(AccountPerformanceReport.class))
        .thenReturn(reportList);

    reportAccountList = reportEntitiesPersister.listReports(AccountPerformanceReport.class);
    assertReportEntities(reportAccountList, 789L, 456L, "updatedTestAccount");
  }


  private void assertReportEntities(List<AccountPerformanceReport> reportAccountList,
      long expectedTopCustomerId, long expectedCustomerId, String expectedAccountDescriptiveName){
    assertNotNull("Report account list should not be null", reportAccountList);
    assertEquals("Expecting matching report list size", 1, reportAccountList.size());
    assertTrue("Expecting matching top customer ID",
        reportAccountList.get(0).getTopCustomerId().equals(expectedTopCustomerId));
    assertTrue("Expecting matching customer IDs",
        reportAccountList.get(0).getCustomerId().equals(expectedCustomerId));
    assertTrue("Expecting matching account descriptions",
        reportAccountList.get(0).getAccountDescriptiveName()
        .equals(expectedAccountDescriptiveName));
  }
}
