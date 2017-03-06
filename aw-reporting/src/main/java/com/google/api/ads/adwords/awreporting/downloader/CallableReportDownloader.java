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

import com.google.api.ads.adwords.awreporting.ReportProcessingException;
import com.google.api.ads.adwords.lib.client.AdWordsSession.ImmutableAdWordsSession;
import com.google.api.ads.adwords.lib.jaxb.v201702.ReportDefinition;
import com.google.api.ads.adwords.lib.utils.ReportDownloadResponse;
import com.google.api.ads.adwords.lib.utils.ReportDownloadResponseException;
import com.google.api.ads.adwords.lib.utils.ReportException;
import com.google.api.ads.adwords.lib.utils.v201702.ReportDownloader;
import com.google.api.ads.common.lib.utils.Streams;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This {@link Callable} implements the core logic to download the reporting data
 * from the AdWords API.
 */
public class CallableReportDownloader implements Callable<File> {
  private static final Logger logger = LoggerFactory.getLogger(CallableReportDownloader.class);

  private final int numAttempts;
  private final int backoffInterval;

  private final ImmutableAdWordsSession session;
  private final ReportDefinition reportDefinition;

  /**
   * @param numAttempts the number of attempts if an error occur.
   * @param backoffInterval the time to backoff (in millis) if an error occur to prevent QPS limits.
   * @param session the adwords session used for downloading report
   * @param reportDefinition the report to download
   */
  public CallableReportDownloader(int numAttempts, int backoffInterval,
      ImmutableAdWordsSession session, ReportDefinition reportDefinition) {
    this.numAttempts = numAttempts;
    this.backoffInterval = backoffInterval;
    this.session = Preconditions.checkNotNull(session, "session cannot be null.");
    this.reportDefinition = Preconditions.checkNotNull(reportDefinition,
        "reportDefinition cannot be null.");
  }

  /**
   * Downloads report from API (with retry logic) and returns a {@link File} object.
   */
  @Override
  public File call() throws ReportProcessingException {
    ReportDownloadResponse reportDownloadResponse = getReportDownloadResponse();

    InputStream inputStream = reportDownloadResponse.getInputStream();
    return handleReportStreamResult(inputStream);
  }

  /**
   * Gets the report download response from the API and retries on failure.
   */
  protected ReportDownloadResponse getReportDownloadResponse() throws ReportProcessingException {
    String errorMessage = null;
    Exception lastException = null;
    ReportDownloadResponse result = null;

    for (int i = 1; i <= numAttempts; i++) {
      try {
        lastException = null;
        result = downloadReport();
        break;
      } catch (ReportException e) {
        errorMessage = "ReportException occurs when downloading report for "
            + reportDefinition.getReportType() + " with account "
            + session.getClientCustomerId() + ".";
        lastException = e;
        logger.error(errorMessage, e);
      } catch (ReportDownloadResponseException e) {
        errorMessage = "ReportDownloadResponseException occurs when downloading report for "
            + reportDefinition.getReportType() + " with account "
            + session.getClientCustomerId() + ".";
        lastException = e;
        logger.error(errorMessage, e);
      }

      // If we haven't succeeded, slow down the rate of requests (with exponential back off).
      try {
        // Sleep unless this was the last attempt.
        if (i < numAttempts) {
          long backoff = (long) Math.scalb(backoffInterval, i);
          logger.info("Back off for {}ms before next retry.", backoff);
          Thread.sleep(backoff);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new ReportProcessingException(
            "InterruptedException occurs while waiting to download report.", e);
      }
    }

    if (result == null) {
      throw new ReportProcessingException(
          "Failed to download report after all attempts.", lastException);
    }

    return result;
  }

  /**
   *  Downloads the report.
   */
  @VisibleForTesting ReportDownloadResponse downloadReport()
      throws ReportException, ReportDownloadResponseException {
    ReportDownloader reportDownloader = new ReportDownloader(session);
    return reportDownloader.downloadReport(reportDefinition);
  }

  /**
   * Transforms the downloaded result into a {@link File} object.
   *
   * @param reportStream the downloaded report stream
   * @return the downloaded report file
   */
  @VisibleForTesting File handleReportStreamResult(InputStream reportStream)
      throws ReportProcessingException {
    Preconditions.checkState(reportStream != null, "Cannot get report data: input stream is NULL.");

    // Get clientCustomerId from session and covert to Long type. The string field was set from
    // Long type in AwqlReportDownloader, so it's able to parse back to Long.
    Long clientCustomerId = Long.parseLong(session.getClientCustomerId());

    try (GZIPInputStream gzipReportStream = new GZIPInputStream(reportStream)) {
      String filenamePrefix = reportDefinition.getReportType() + "-" + clientCustomerId + "-";
      File reportFile = File.createTempFile(filenamePrefix, ".csv");

      // Copy stream to CSV report.
      logger.debug("Starting processing rules of " + reportFile.getPath() + " report...");
      Streams.copy(gzipReportStream, new FileOutputStream(reportFile));
      logger.debug("... " + reportFile.getPath()  + " copied successfully.");

      return reportFile;
    } catch (IOException e) {
      String errorMessage = "Failed to load report data from stream for "
          + reportDefinition.getReportType() + " with account "
          + session.getClientCustomerId() + ".";
      logger.error(errorMessage, e);
      throw new ReportProcessingException(errorMessage, e);
    }
  }
}
