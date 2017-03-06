
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

package com.google.api.ads.adwords.awreporting.downloader;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.api.ads.adwords.awreporting.ReportProcessingException;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.client.AdWordsSession.ImmutableAdWordsSession;
import com.google.api.ads.adwords.lib.jaxb.v201702.ReportDefinition;
import com.google.api.ads.adwords.lib.utils.ReportDownloadResponse;
import com.google.api.ads.adwords.lib.utils.ReportDownloadResponseException;
import com.google.api.ads.adwords.lib.utils.ReportException;
import com.google.api.ads.common.lib.exception.ValidationException;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import java.io.File;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/** Test case for the {@code CallableReportDownloader} class. */
@RunWith(JUnit4.class)
public class CallableReportDownloaderTest {

  private static final int RETRIES_COUNT = 5;
  private static final int BACKOFF_INTERVAL = 0;

  private CallableReportDownloader callableReportDownloader;

  private ImmutableAdWordsSession adWordsSession;

  private ReportDefinition reportDefinition = new ReportDefinition();

  @Before
  public void setUp() throws ValidationException {

    adWordsSession =
        new AdWordsSession.Builder()
            .withEndpoint("http://www.google.com")
            .withDeveloperToken("DeveloperToken")
            .withClientCustomerId("123")
            .withUserAgent("UserAgent")
            .withOAuth2Credential(new GoogleCredential.Builder().build())
            .buildImmutable();

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testRun() throws ReportProcessingException {

    final ReportDownloadResponse mockReportDownloadResponse = mock(ReportDownloadResponse.class);
    when(mockReportDownloadResponse.getInputStream()).thenReturn(null);

    callableReportDownloader =
        new CallableReportDownloader(
            RETRIES_COUNT, BACKOFF_INTERVAL, adWordsSession, reportDefinition) {
          @Override
          File handleReportStreamResult(InputStream reportStream) throws ReportProcessingException {
            return new File("");
          }

          @Override
          ReportDownloadResponse downloadReport()
              throws ReportException, ReportDownloadResponseException {
            return mockReportDownloadResponse;
          }
        };

    callableReportDownloader.call();
    verify(mockReportDownloadResponse, times(1)).getInputStream();
  }

  @Test
  public void testRun_retries() throws ReportException, ReportDownloadResponseException {

    final ReportException ex = new ReportException("UnitTest Retryable Exception");

    final CallableReportDownloader mockCallableReportDownloader =
        Mockito.mock(CallableReportDownloader.class);

    callableReportDownloader =
        new CallableReportDownloader(
            RETRIES_COUNT, BACKOFF_INTERVAL, adWordsSession, reportDefinition) {
          @Override
          ReportDownloadResponse downloadReport()
              throws ReportException, ReportDownloadResponseException {
            mockCallableReportDownloader.downloadReport();
            throw ex;
          }
        };

    try {
      callableReportDownloader.call();
      fail("Expected ReportProcessingException");
    } catch (ReportProcessingException e) {
      verify(mockCallableReportDownloader, times(RETRIES_COUNT)).downloadReport();
    }
  }

  /**
   * Test for DetailedReportDownloadResponseExceptions
   *
   * <p>A DetailedReportDownloadResponseException breaks the retries, typically is an invalid field
   * in the definition.
   */
  @Test
  public void testRun_retriesStop()
      throws ReportException, ReportDownloadResponseException, ReportProcessingException {

    final RuntimeException ex = new RuntimeException("UnitTest Non-Retryable Server Error");
    final CallableReportDownloader mockCallableReportDownloader =
        Mockito.mock(CallableReportDownloader.class);

    callableReportDownloader =
        new CallableReportDownloader(
            RETRIES_COUNT, BACKOFF_INTERVAL, adWordsSession, reportDefinition) {
          @Override
          ReportDownloadResponse downloadReport()
              throws ReportException, ReportDownloadResponseException {
            mockCallableReportDownloader.downloadReport();
            throw ex;
          }
        };

    try {
      callableReportDownloader.call();
      fail("Expected RuntimeException");
    } catch (RuntimeException e) {
      verify(mockCallableReportDownloader, times(1)).downloadReport();
    }
  }
}
