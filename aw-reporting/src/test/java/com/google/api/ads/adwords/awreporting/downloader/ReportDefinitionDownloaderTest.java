//Copyright 2014 Google Inc. All Rights Reserved.
//
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.

package com.google.api.ads.adwords.awreporting.downloader;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.google.api.ads.adwords.jaxws.v201601.cm.ApiException_Exception;
import com.google.api.ads.adwords.jaxws.v201601.cm.ReportDefinitionField;
import com.google.api.ads.adwords.jaxws.v201601.mcm.ApiException;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.jaxb.v201601.ReportDefinitionReportType;
import com.google.api.ads.common.lib.exception.ValidationException;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;

public class ReportDefinitionDownloaderTest {

  @Spy
  private ReportDefinitionDownloader mockedReportDefinitionDownloader;

  @Before
  public void setUp() throws ValidationException {
    AdWordsSession adWordsSession =
        new AdWordsSession.Builder().withEndpoint("http://www.google.com")
            .withDeveloperToken("DeveloperToken")
            .withClientCustomerId("123")
            .withUserAgent("UserAgent")
            .withOAuth2Credential( new GoogleCredential.Builder().build())
            .build();

    mockedReportDefinitionDownloader = new ReportDefinitionDownloader();
    mockedReportDefinitionDownloader.init(adWordsSession);
    mockedReportDefinitionDownloader.setRetriesCount(5);
    mockedReportDefinitionDownloader.setBackoffInterval(0);

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetFieldsMapping()
      throws ApiException_Exception, ApiException {

    doReturn(new ArrayList<ReportDefinitionField>()).when(mockedReportDefinitionDownloader)
        .downloadReportDefinitionFields(Mockito.<ReportDefinitionReportType>anyObject());

    ReportDefinitionReportType reportType = ReportDefinitionReportType.ACCOUNT_PERFORMANCE_REPORT;
    mockedReportDefinitionDownloader.getReportDefinitionFieldsMap(reportType);

    verify(mockedReportDefinitionDownloader, times(1)).getReportDefinitionFieldsMap(reportType);
    verify(mockedReportDefinitionDownloader, times(1)).downloadReportDefinitionFields(reportType);
  }

  @Test
  public void testGetFieldsMapping_retries() throws ApiException_Exception, ApiException {

    ApiException_Exception ex = new ApiException_Exception("ApiException",
        new com.google.api.ads.adwords.jaxws.v201601.cm.ApiException());
    doThrow(ex).when(mockedReportDefinitionDownloader)
        .downloadReportDefinitionFields(Mockito.<ReportDefinitionReportType>anyObject());

    ReportDefinitionReportType reportType = ReportDefinitionReportType.ACCOUNT_PERFORMANCE_REPORT;
    try {
      mockedReportDefinitionDownloader.getReportDefinitionFieldsMap(reportType);
    } catch (ApiException e) {
      // do nothing
    }

    verify(mockedReportDefinitionDownloader, times(5)).downloadReportDefinitionFields(reportType);
    verify(mockedReportDefinitionDownloader, times(1)).getReportDefinitionFieldsMap(reportType);
  }
}
