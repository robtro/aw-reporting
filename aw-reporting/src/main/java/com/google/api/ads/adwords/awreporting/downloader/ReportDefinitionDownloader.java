// Copyright 2015 Google Inc. All Rights Reserved.
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.ads.adwords.awreporting.util.ReportDefinitionFieldsMap;
import com.google.api.ads.adwords.jaxws.factory.AdWordsServices;
import com.google.api.ads.adwords.jaxws.v201601.cm.ApiException_Exception;
import com.google.api.ads.adwords.jaxws.v201601.cm.ReportDefinitionField;
import com.google.api.ads.adwords.jaxws.v201601.cm.ReportDefinitionServiceInterface;
import com.google.api.ads.adwords.jaxws.v201601.mcm.ApiException;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.jaxb.v201601.ReportDefinitionReportType;

import org.apache.log4j.Logger;

/**
 * {@code ReportDefinitionDelegate} encapsulates the {@code ReportDefinitionServiceInterface}.
 *
 * This service is used to get all the report fields what are segmented.
 *
 */
public class ReportDefinitionDownloader {
  
  private static final Logger LOGGER = Logger.getLogger(ReportDefinitionDownloader.class);

  private static final int RETRIES_COUNT = 5;
  private static final int BACKOFF_INTERVAL = 1000 * 5;

  private int retriesCount = RETRIES_COUNT;
  private int backoffInterval = BACKOFF_INTERVAL;
  
  private ReportDefinitionServiceInterface reportDefinitionService;
  
  private final Map<ReportDefinitionReportType, ReportDefinitionFieldsMap> typedRdfMap
      = new HashMap<ReportDefinitionReportType, ReportDefinitionFieldsMap>();
  
  public void init(AdWordsSession session) {
    this.reportDefinitionService
        = new AdWordsServices().get(session, ReportDefinitionServiceInterface.class);
  }
  
  /**
   * Get report definition fields map for the specified report type.
   * 
   * @param reportType the specified report type
   */
  public ReportDefinitionFieldsMap getReportDefinitionFieldsMap(
      ReportDefinitionReportType reportType) throws ApiException {
    ReportDefinitionFieldsMap rdfMap = typedRdfMap.get(reportType);
    if (rdfMap == null) {
      rdfMap = generateReportDefinitionData(reportType);
      typedRdfMap.put(reportType, rdfMap);
    }
    
    return rdfMap;
  }

  /**
   * For the specified report type, Download report definition data from ReportDefinitionService.
   * 
   * @param reportType the specified report type
   */
  private ReportDefinitionFieldsMap generateReportDefinitionData(
      ReportDefinitionReportType reportType) throws ApiException {
    
    List<ReportDefinitionField> reportDefinitionFields = null;
    for (int i = 1; i <= this.retriesCount; ++i) {
      try {
        reportDefinitionFields = downloadReportDefinitionFields(reportType);
        break;
      } catch (ApiException_Exception e) {
        String errorMsg = "(Error getting report definition: " + e.getMessage() + " "
            + e.getCause() + " Retry# " + i + "/" + retriesCount + ")";
        LOGGER.error(errorMsg);
        
        if (this.retriesCount == i) {
          // failed the last retry, just rethrow
          throw new ApiException(
              errorMsg, new com.google.api.ads.adwords.jaxws.v201601.cm.ApiException());
        }
      }
      
      // Slow down the rate of requests increasingly to avoid running into rate limits.
      try {
        long backoff = (long) Math.scalb(this.backoffInterval, i);
        LOGGER.error("Back off for " + backoff + "ms before next retry.");
        Thread.sleep(backoff);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
    
    // Generate the report definition data
    LOGGER.info("Successfully downloaded report definition for " + reportType.value() + ".");
    return new ReportDefinitionFieldsMap(reportDefinitionFields);
  }
  
  protected List<ReportDefinitionField> downloadReportDefinitionFields(
      ReportDefinitionReportType reportType) throws ApiException_Exception {
    return reportDefinitionService.getReportFields(convertReportType(reportType));
  }
  
  // TODO: cz
  // check if we should use com.google.api.ads.adwords.jaxws.v201601.cm.ReportDefinitionReportType
  // everywhere in this project?
  private com.google.api.ads.adwords.jaxws.v201601.cm.ReportDefinitionReportType
  convertReportType(ReportDefinitionReportType reportType) {
    return com.google.api.ads.adwords.jaxws.v201601.cm.ReportDefinitionReportType.valueOf(
        reportType.name());
  }

  
  /**
   * @param retriesCount the retriesCount to set. Default value = 5
   */
  public void setRetriesCount(int retriesCount) {
    this.retriesCount = retriesCount;
  }

  /**
   * @param backoffInterval the backoffInterval to set. Default value = 1000 * 5
   */
  public void setBackoffInterval(int backoffInterval) {
    this.backoffInterval = backoffInterval;
  }
}
