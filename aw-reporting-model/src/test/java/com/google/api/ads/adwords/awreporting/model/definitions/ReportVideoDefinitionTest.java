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

import com.google.api.ads.adwords.awreporting.model.entities.ReportVideo;
import com.google.api.ads.adwords.lib.jaxb.v201603.ReportDefinitionReportType;

import junit.framework.Assert;

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
public class ReportVideoDefinitionTest extends
AbstractReportDefinitionTest<ReportVideo> {

  /**
   * C'tor
   */
  public ReportVideoDefinitionTest() {

    super(ReportVideo.class,
        ReportDefinitionReportType.VIDEO_PERFORMANCE_REPORT,
        "src/test/resources/csv/video.csv");
  }

  @Override
  protected void testFirstEntry(ReportVideo row) {
    Assert.assertEquals(123456789L,row.getCampaignId().longValue());
    Assert.assertEquals(0.79, row.getCost().doubleValue());
    Assert.assertEquals(0.00, row.getCostPerConversion().doubleValue());
    Assert.assertEquals(88888888888L, row.getCreativeId().longValue());
    Assert.assertEquals(0.00, row.getCtrBigDecimal().doubleValue());
    Assert.assertEquals("2015-12-06", row.getDay());
    Assert.assertEquals("Sunday", row.getDayOfWeek());
    Assert.assertEquals("Computers", row.getDevice());
    Assert.assertEquals(0.00, row.getEngagementRateBigDecimal().doubleValue());
    Assert.assertEquals(0L, row.getEngagements().longValue());
    Assert.assertEquals(66L, row.getImpressions().longValue());
    Assert.assertEquals("2015-12-01", row.getMonth());
    Assert.assertEquals("December", row.getMonthOfYear());
    Assert.assertEquals("2015-10-01", row.getQuarter());
    Assert.assertEquals("UCW5BAmfbS3rMtl4GuRatM6g", row.getVideoChannelId());
    Assert.assertEquals(5000L, row.getVideoDuration().longValue());
    Assert.assertEquals("WUe7xuTj_Sg", row.getVideoId());
    Assert.assertEquals(92.42, row.getVideoQuartile100RateBigDecimal().doubleValue());
    Assert.assertEquals(92.42, row.getVideoQuartile75RateBigDecimal().doubleValue());
    Assert.assertEquals(95.45, row.getVideoQuartile50RateBigDecimal().doubleValue());
    Assert.assertEquals(100.00, row.getVideoQuartile25RateBigDecimal().doubleValue());
    Assert.assertEquals("Video Title 1", row.getVideoTitle());
    Assert.assertEquals(92.42, row.getVideoViewRateBigDecimal().doubleValue());
    Assert.assertEquals(61L, row.getVideoViews().longValue());
    Assert.assertEquals("2015-11-30", row.getWeek());
    Assert.assertEquals(2015L, row.getYear().longValue());
  }

  @Override
  protected void testLastEntry(ReportVideo row) {
    Assert.assertEquals(123456789L,row.getCampaignId().longValue());
    Assert.assertEquals(0.45, row.getCost().doubleValue());
    Assert.assertEquals(0.00, row.getCostPerConversion().doubleValue());
    Assert.assertEquals(88888888888L, row.getCreativeId().longValue());
    Assert.assertEquals(0.00, row.getCtrBigDecimal().doubleValue());
    Assert.assertEquals("2015-12-06", row.getDay());
    Assert.assertEquals("Sunday", row.getDayOfWeek());
    Assert.assertEquals("Tablets with full browsers", row.getDevice());
    Assert.assertEquals(0.00, row.getEngagementRateBigDecimal().doubleValue());
    Assert.assertEquals(0L, row.getEngagements().longValue());
    Assert.assertEquals(39L, row.getImpressions().longValue());
    Assert.assertEquals("2015-12-01", row.getMonth());
    Assert.assertEquals("December", row.getMonthOfYear());
    Assert.assertEquals("2015-10-01", row.getQuarter());
    Assert.assertEquals("UCW5BAmfbS3rMtl4GuRatM6g", row.getVideoChannelId());
    Assert.assertEquals(5000L, row.getVideoDuration().longValue());
    Assert.assertEquals("WUe7xuTj_Sg", row.getVideoId());
    Assert.assertEquals(89.74, row.getVideoQuartile100RateBigDecimal().doubleValue());
    Assert.assertEquals(87.18, row.getVideoQuartile75RateBigDecimal().doubleValue());
    Assert.assertEquals(89.74, row.getVideoQuartile50RateBigDecimal().doubleValue());
    Assert.assertEquals(94.87, row.getVideoQuartile25RateBigDecimal().doubleValue());
    Assert.assertEquals("Video Title 1", row.getVideoTitle());
    Assert.assertEquals(89.74, row.getVideoViewRateBigDecimal().doubleValue());
    Assert.assertEquals(35L, row.getVideoViews().longValue());
    Assert.assertEquals("2015-11-30", row.getWeek());
    Assert.assertEquals(2015L, row.getYear().longValue());
  }

  @Override
  protected int retrieveCsvEntries() {
    return 3;
  }

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
        "CostPerConvertedClick",
        "ValuePerConvertedClick",
        "ConversionCategoryName",
        "ConversionTypeName",
        "ViewThroughConversions",
        // Specific to Video Performance Report
        "AdGroupId",
        "AdGroupName",
        "AdGroupStatus",
        "CampaignId",
        "CampaignName",
        "CampaignStatus",
        "CreativeId",
        "VideoChannelId",
        "VideoDuration",
        "VideoId",
        "VideoTitle",
        "CreativeStatus"
    };
  }
}
