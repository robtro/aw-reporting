// Copyright 2013 Google Inc. All Rights Reserved.
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

package com.google.api.ads.adwords.awreporting.model.definitions;

import com.google.api.ads.adwords.awreporting.model.entities.ReportCriteria;
import com.google.api.ads.adwords.lib.jaxb.v201509.ReportDefinitionReportType;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests the Campaign Performance report definition.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:aw-report-sql-beans.xml")
@ActiveProfiles("TEST")
public class ReportCriteriaDefinitionTest extends
AbstractReportDefinitionTest<ReportCriteria> {

  /**
   * C'tor
   */
  public ReportCriteriaDefinitionTest() {

    super(ReportCriteria.class,
        ReportDefinitionReportType.CRITERIA_PERFORMANCE_REPORT,
        "src/test/resources/csv/criteria.csv");
  }

  /**
   * @see com.google.api.ads.adwords.awreporting.model.definitions.
   * AbstractReportDefinitionTest#testFirstEntry(
   * com.google.api.ads.adwords.awreporting.model.entities.Report)
   */
  @Override
  protected void testFirstEntry(ReportCriteria first) {
    //Assert.assertEquals(1252422563L, first.getAccountId().longValue());
    //TODO
  }

  /**
   * @see com.google.api.ads.adwords.awreporting.model.definitions.
   * AbstractReportDefinitionTest#testLastEntry(
   * com.google.api.ads.adwords.awreporting.model.entities.Report)
   */
  @Override
  protected void testLastEntry(ReportCriteria last) {
    //Assert.assertEquals(1252422563L, last.getAccountId().longValue());
    //TODO
  }

  /**
   * @see com.google.api.ads.adwords.awreporting.model.definitions.
   * AbstractReportDefinitionTest#retrieveCsvEntries()
   */
  @Override
  protected int retrieveCsvEntries() {
    return 10;
  }

  /**
   * @see com.google.api.ads.adwords.awreporting.model.definitions.
   * AbstractReportDefinitionTest#retrievePropertiesToBeSelected()
   */
  @Override
  protected String[] retrievePropertiesToBeSelected() {

    return new String[] {
        // Report
        "ExternalCustomerId",
        // ReportBase
        "AccountDescriptiveName",
        "AccountTimeZoneId",
        "CustomerDescriptiveName",
        "PrimaryCompanyName",
        "AccountCurrencyCode",
        "Date",
        "DayOfWeek",
        "Week",
        "Month",
        "MonthOfYear",
        "Quarter",
        "Year",
        "Cost",
        "Clicks",
        "Impressions",
        "Ctr",
        "AverageCost",
        "AverageCpm",
        "AverageCpc",
        "AverageCpe",
        "AverageCpv",
        "AveragePosition",
        "Device",
        "ClickType",
        "AdNetworkType1",
        "AdNetworkType2",
        "Engagements",
        "EngagementRate",
        "Interactions",
        "InteractionRate",
        "VideoViews",
        "VideoViewRate",
        "VideoQuartile25Rate",
        "VideoQuartile50Rate",
        "VideoQuartile75Rate",
        "VideoQuartile100Rate",
        "Conversions",
        "ConversionRate",
        "ConversionValue",
        "CostPerConversion",
        "ValuePerConversion",
        "AllConversions",
        "AllConversionRate",
        "AllConversionValue",
        "CostPerAllConversion",
        "ValuePerAllConversion",
        "ConvertedClicks",
        "ClickConversionRate",
        "ClickConversionRateSignificance",
        "ConvertedClicksSignificance",
        "CostPerConvertedClick",
        "CostPerConvertedClickSignificance",
        "ValuePerConvertedClick",
        "ConversionCategoryName",
        "ConversionTypeName",
        "ViewThroughConversions",
        // Specific to Campaign Performance Report
        "AdGroupId",
        "AdGroupName",
        "AdGroupStatus",
        "AdvertiserExperimentSegmentationBin",
        "ApprovalStatus",
        "BidModifier",
        "CampaignId",
        "CampaignName",
        "CampaignStatus",
        "ClickSignificance",
        "CostSignificance",
        "CpcBidSource",
        "CpcSignificance",
        "CpmSignificance",
        "Criteria",
        "CriteriaDestinationUrl",
        "CriteriaType",
        "CtrSignificance",
        "DisplayName",
        "FirstPageCpc",
        "Id",
        "ImpressionSignificance",
        "IsNegative",
        "CpcBid",
        "CpmBid",
        "Parameter",
        "PercentCpa",
        "PositionSignificance",
        "QualityScore",
        "Slot",
        "Status",
        "TopOfPageCpc",
        "ViewThroughConversionsSignificance",
        "ActiveViewCpm",
        "ActiveViewCtr",
        "ActiveViewImpressions",
        "ActiveViewMeasurability",
        "ActiveViewMeasurableCost",
        "ActiveViewMeasurableImpressions",
        "ActiveViewViewability",
        "ConversionTrackerId",
        "FinalAppUrls",
        "FinalMobileUrls",
        "FinalUrls",
        "TrackingUrlTemplate",
        "UrlCustomParameters",
        "GmailForwards",
        "GmailSaves",
        "GmailSecondaryClicks",
        "CpvBid",
        "CpvBidSource",
        "EnhancedCpcEnabled",
        "EnhancedCpvEnabled",
        "SystemServingStatus"
    };
  }
}
