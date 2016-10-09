// Copyright 2014 Google Inc. All Rights Reserved.
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.api.ads.adwords.awreporting.model.csv.annotation.CsvField;
import com.google.api.ads.adwords.awreporting.model.csv.annotation.CsvReport;
import com.google.api.ads.adwords.awreporting.model.csv.annotation.MoneyField;
import com.google.api.ads.adwords.awreporting.model.util.BigDecimalUtil;
import com.google.api.ads.adwords.lib.jaxb.v201609.ReportDefinitionReportType;
import com.google.common.collect.Lists;

import java.math.BigDecimal;

/**
 * Specific Report class for GeoPerformanceReports
 */
@Entity
@com.googlecode.objectify.annotation.Entity
@Table(name = "AW_ReportPlacement")
@CsvReport(value = ReportDefinitionReportType.PLACEMENT_PERFORMANCE_REPORT)
public class ReportPlacement extends ReportBase {

  @Column(name = "ADGROUP_ID")
  @CsvField(value = "Ad group ID", reportField = "AdGroupId")
  private Long adGroupId;

  @Column(name = "ADGROUP_NAME", length = 255)
  @CsvField(value = "Ad group", reportField = "AdGroupName")
  private String adGroupName;

  @Column(name = "ADGROUP_STATUS", length = 32)
  @CsvField(value = "Ad group state", reportField = "AdGroupStatus")
  private String adGroupStatus;
  
  @Column(name = "BID_MODIFIER")
  @CsvField(value = "Bid adj.", reportField = "BidModifier")
  private String bidModifier;
  
  @Column(name = "CAMPAIGN_ID")
  @CsvField(value = "Campaign ID", reportField = "CampaignId")
  private Long campaignId;

  @Column(name = "CAMPAIGN_NAME", length = 255)
  @CsvField(value = "Campaign", reportField = "CampaignName")
  private String campaignName;

  @Column(name = "CAMPAIGN_STATUS", length = 32)
  @CsvField(value = "Campaign state", reportField = "CampaignStatus")
  private String campaignStatus;

  @Column(name = "CPC_BID_SOURCE")
  @CsvField(value = "Max CPC source", reportField = "CpcBidSource")
  private String cpcBidSource;

  @Column(name = "CPM_BID_SOURCE")
  @CsvField(value = "Max CPM source", reportField = "CpmBidSource")
  private String cpmBidSource;

  @Column(name = "CRITERIA_DESTINATION_URL", length = 2048)
  @CsvField(value = "Destination URL", reportField = "CriteriaDestinationUrl")
  private String criteriaDestinationUrl;

  @Column(name = "DISPLAY_NAME", length = 2048)
  @CsvField(value = "Criteria Display Name", reportField = "DisplayName")
  private String displayName;

  @Column(name = "Id")
  @CsvField(value = "Criterion ID", reportField = "Id")
  private String criterionId;

  @Column(name = "IS_NEGATIVE")
  @CsvField(value = "Is negative", reportField = "IsNegative")
  private String isNegative;
  
  @Column(name = "IS_RESTRICT")
  @CsvField(value = "Is restricting", reportField = "IsRestrict")
  private String isRestrict;

  @Column(name = "MAX_CPC")
  @CsvField(value = "Max. CPC", reportField = "CpcBid")
  @MoneyField
  private BigDecimal maxCpc;

  @Column(name = "MAX_CPM")
  @CsvField(value = "Max. CPM", reportField = "CpmBid")
  @MoneyField
  private BigDecimal maxCpm;  

  @Column(name = "CRITERIA", length = 2048)
  @CsvField(value = "Placement", reportField = "Criteria")
  private String criteria; 

  @Column(name = "TARGETING_SETTING")
  @CsvField(value = "Targeting Setting", reportField = "TargetingSetting")
  private String targetingSetting;
  
  @Column(name = "ACTIVE_VIEW_CPM")
  @CsvField(value = "Active View avg. CPM", reportField = "ActiveViewCpm")
  @MoneyField
  private BigDecimal activeViewCpm;
  
  @Column(name = "ACTIVE_VIEW_CTR")
  @CsvField(value = "Active View viewable CTR", reportField = "ActiveViewCtr")
  private BigDecimal activeViewCtr;
  
  @Column(name = "ACTIVE_VIEW_IMPRESSIONS")
  @CsvField(value = "Active View viewable impressions", reportField = "ActiveViewImpressions")
  private Long activeViewImpressions;
  
  @Column(name = "ACTIVE_VIEW_MEASURABILITY")
  @CsvField(value = "Active View measurable impr. / impr.", reportField = "ActiveViewMeasurability")
  private BigDecimal activeViewMeasurability;
  
  @Column(name = "ACTIVE_VIEW_MEASURABLE_COST")
  @CsvField(value = "Active View measurable cost", reportField = "ActiveViewMeasurableCost")
  private Long activeViewMeasurableCost;
  
  @Column(name = "ACTIVE_VIEW_MEASURABLE_IMPRESSIONS")
  @CsvField(value = "Active View measurable impr.", reportField = "ActiveViewMeasurableImpressions")
  private Long activeViewMeasurableImpressions;
  
  @Column(name = "ACTIVE_VIEW_VIEWABILITY")
  @CsvField(value = "Active View viewable impr. / measurable impr.", reportField = "ActiveViewViewability")
  private BigDecimal activeViewViewability;
  
  @Column(name = "CONVERSION_TRACKER_ID")
  @CsvField(value = "Conversion Tracker Id", reportField = "ConversionTrackerId")
  private Long conversionTrackerId;
  
  @Column(name = "FINAL_APP_URLS", length=2048)
  @CsvField(value="App final URL", reportField = "FinalAppUrls")
  private String finalAppUrls;
  
  @Column(name = "FINAL_MOBILE_URLS", length=2048)
  @CsvField(value="Mobile final URL", reportField = "FinalMobileUrls")
  private String finalMobileUrls;
  
  @Column(name = "FINAL_URLS", length=2048)
  @CsvField(value="Final URL", reportField = "FinalUrls")
  private String finalUrls;
  
  @Column(name = "TRACKING_URL_TEMPLATE", length=2048)
  @CsvField(value = "Tracking template", reportField = "TrackingUrlTemplate")
  private String trackingUrlTemplate;
  
  @Column(name = "URL_CUSTOM_PARAMETERS", length=2048)
  @CsvField(value = "Custom parameter", reportField = "UrlCustomParameters")
  private String urlCustomParameters;
  
  @Column(name = "GMAIL_FORWARDS")
  @CsvField(value = "Gmail forwards", reportField = "GmailForwards")
  private Long gmailForwards;

  @Column(name = "GMAIL_SAVES")
  @CsvField(value = "Gmail saves", reportField = "GmailSaves")
  private Long gmailSaves;

  @Column(name = "GMAIL_SECONDARY_CLICKS")
  @CsvField(value = "Gmail clicks to website", reportField = "GmailSecondaryClicks")
  private Long gmailSecondaryClicks;
  
  @Column(name = "VIDEO_QUARTILE_25_RATE")
  @CsvField(value = "Video played to 25%", reportField = "VideoQuartile25Rate")
  private BigDecimal videoQuartile25Rate;
  
  @Column(name = "VIDEO_QUARTILE_50_RATE")
  @CsvField(value = "Video played to 50%", reportField = "VideoQuartile50Rate")
  private BigDecimal videoQuartile50Rate;
  
  @Column(name = "VIDEO_QUARTILE_75_RATE")
  @CsvField(value = "Video played to 75%", reportField = "VideoQuartile75Rate")
  private BigDecimal videoQuartile75Rate;
  
  @Column(name = "VIDEO_QUARTILE_100_RATE")
  @CsvField(value = "Video played to 100%", reportField = "VideoQuartile100Rate")
  private BigDecimal videoQuartile100Rate;

  /**
   * Hibernate needs an empty constructor
   */
  public ReportPlacement() {}

  @Override
  public void setId() {

    // Generating unique id after having date and accountId
    if (this.getAccountId() != null) {
      this.id = this.getAccountId().toString();
    } else {
      this.id = "-";
    }
    if (this.getCampaignId() != null) {
      this.id += "-" + this.getCampaignId().toString();
    } else {
      this.id += "-";
    }
    if (this.getAdGroupId() != null) {
      this.id += "-" + this.getAdGroupId().toString();
    } else {
      this.id += "-";
    }
    if (this.getCriterionId() != null) {
      this.id += "-" + this.getCriterionId().toString();
    } else {
      this.id += "-";
    }

    this.id += setIdDates();

    // Adding extra fields for unique ID
    if (this.getAdNetwork() != null && this.getAdNetwork().length() > 0) {
      this.id += "-" + this.getAdNetwork();
    }
    if (this.getAdNetworkPartners() != null && this.getAdNetworkPartners().length() > 0) {
      this.id += "-" + this.getAdNetworkPartners();
    }
    if (this.getDevice() != null && this.getDevice().length() > 0) {
      this.id += "-" + this.getDevice();
    }
    if (this.getClickType() != null && this.getClickType().length() > 0) {
      this.id += "-" + this.getClickType();
    }
    if (this.getCriteria() != null && this.getCriteria().length() > 0) {
      this.id += "-" + this.getCriteria();
    }
    if (this.getConversionTrackerId() != null) {
      this.id += "-" + this.getConversionTrackerId();
    }
  }

  public Long getAdGroupId() {
    return adGroupId;
  }

  public void setAdGroupId(Long adGroupId) {
    this.adGroupId = adGroupId;
  }

  public String getAdGroupName() {
    return adGroupName;
  }

  public void setAdGroupName(String adGroupName) {
    this.adGroupName = adGroupName;
  }

  public String getAdGroupStatus() {
    return adGroupStatus;
  }

  public void setAdGroupStatus(String adGroupStatus) {
    this.adGroupStatus = adGroupStatus;
  }

  public String getCampaignStatus() {
    return campaignStatus;
  }

  public void setCampaignStatus(String campaignStatus) {
    this.campaignStatus = campaignStatus;
  }

  public String getCampaignName() {
    return campaignName;
  }

  public void setCampaignName(String campaignName) {
    this.campaignName = campaignName;
  }

  public Long getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(Long campaignId) {
    this.campaignId = campaignId;
  }

  public String getCpcBidSource() {
    return cpcBidSource;
  }

  public void setCpcBidSource(String cpcBidSource) {
    this.cpcBidSource = cpcBidSource;
  }

  public String getCpmBidSource() {
    return cpmBidSource;
  }

  public void setCpmBidSource(String cpmBidSource) {
    this.cpmBidSource = cpmBidSource;
  }

  public String getCriteriaDestinationUrl() {
    return criteriaDestinationUrl;
  }

  public void setCriteriaDestinationUrl(String criteriaDestinationUrl) {
    this.criteriaDestinationUrl = criteriaDestinationUrl;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getCriterionId() {
    return criterionId;
  }

  public void setCriterionId(String criterionId) {
    this.criterionId = criterionId;
  }

  public String getIsNegative() {
    return isNegative;
  }

  public void setIsNegative(String isNegative) {
    this.isNegative = isNegative;
  }
  
  public String getIsRestrict() {
    return isRestrict;
  }
  
  public void setIsRestrict(String isRestrict) {
    this.isRestrict = isRestrict;
  }

  public BigDecimal getMaxCpc() {
    return maxCpc;
  }

  public void setMaxCpc(BigDecimal maxCpc) {
    this.maxCpc = maxCpc;
  }
  
  public BigDecimal getMaxCpm() {
    return maxCpm;
  }

  public void setMaxCpm(BigDecimal maxCpm) {
    this.maxCpm = maxCpm;
  }

  public String getCriteria() {
    return criteria;
  }

  public void setCriteria(String criteria) {
    this.criteria = criteria;
  }

  public String getTargetingSetting() {
    return targetingSetting;
  }

  public void setTargetingSetting(String targetingSetting) {
    this.targetingSetting = targetingSetting;
  }
  
  public String getActiveViewCpm() {
    return BigDecimalUtil.formatAsReadable(activeViewCpm);
  }

  public BigDecimal getActiveViewCpmBigDecimal() {
    return activeViewCpm;
  }

  public void setActiveViewCpm(String activeViewCpm) {
    this.activeViewCpm = BigDecimalUtil.parseFromNumberStringPercentage(activeViewCpm);
  }
  
  public String getActiveViewCtr() {
    return BigDecimalUtil.formatAsReadable(activeViewCtr);
  }

  public BigDecimal getActiveViewCtrBigDecimal() {
    return activeViewCtr;
  }

  public void setActiveViewCtr(String activeViewCtr) {
    this.activeViewCtr = BigDecimalUtil.parseFromNumberString(activeViewCtr);
  }
  
  public Long getActiveViewImpressions() {
    return activeViewImpressions;
  }
  
  public void setActiveViewImpressions(Long activeViewImpressions) {
    this.activeViewImpressions = activeViewImpressions;
  }
  
  public String getActiveViewMeasurability() {
    return BigDecimalUtil.formatAsReadable(activeViewMeasurability);
  }
  
  public BigDecimal getActiveViewMeasurabilityBigDecimal() {
    return activeViewMeasurability;
  }
  
  public void setActiveViewMeasurability(String activeViewMeasurability) {
    this.activeViewMeasurability = BigDecimalUtil.parseFromNumberString(activeViewMeasurability);
  }
  
  public Long getActiveViewMeasurableCost() {
    return activeViewMeasurableCost;
  }
  
  public void setActiveViewMeasurableCost(Long activeViewMeasurableCost) {
    this.activeViewMeasurableCost = activeViewMeasurableCost;
  }
  
  public Long getActiveViewMeasurableImpressions() {
    return activeViewMeasurableImpressions;
  }
  
  public void setActiveViewMeasurableImpressions(Long activeViewMeasurableImpressions) {
    this.activeViewMeasurableImpressions = activeViewMeasurableImpressions;
  }
  
  public String getActiveViewViewability() {
    return BigDecimalUtil.formatAsReadable(activeViewViewability);
  }
  
  public BigDecimal getActiveViewViewabilityBigDecimal() {
    return activeViewViewability;
  }
  
  public void setActiveViewViewability(String activeViewViewability) {
    this.activeViewViewability = BigDecimalUtil.parseFromNumberString(activeViewViewability);
  }
  
  public Long getConversionTrackerId() {
    return conversionTrackerId;
  }
  
  public void setConversionTrackerId(Long conversionTrackerId) {
    this.conversionTrackerId = conversionTrackerId;
  }
  
  public String getFinalAppUrls() {
    return finalAppUrls;
  }
  
  public boolean hasFinalAppUrl(String finalAppUrl) {
    if (finalAppUrls != null && finalAppUrls.length() > 0) {
      return Lists.newArrayList(finalAppUrls.split(";")).contains(finalAppUrl);
    } else {
      return false;
    }
  }
  
  public void setFinalAppUrls(String finalAppUrls) {
    this.finalAppUrls = finalAppUrls;
  }
  
  public String getFinalMobileUrls() {
    return finalMobileUrls;
  }
  
  public boolean hasFinalMobileUrl(String finalMobileUrl) {
    if (finalMobileUrls != null && finalMobileUrls.length() > 0) {
      return Lists.newArrayList(finalMobileUrls.split(";")).contains(finalMobileUrl);
    } else {
      return false;
    }
  }
  
  public void setFinalMobileUrls(String finalMobileUrls) {
    this.finalMobileUrls = finalMobileUrls;
  }
  
  public String getFinalUrls() {
    return finalUrls;
  }
  
  public boolean hasFinalUrl(String finalUrl) {
    if (finalUrls != null && finalUrls.length() > 0) {
      return Lists.newArrayList(finalUrls.split(";")).contains(finalUrl);
    } else {
      return false;
    }
  }
  
  public void setFinalUrls(String finalUrls) {
    this.finalUrls = finalUrls;
  }
  
  public String getTrackingUrlTemplate() {
    return trackingUrlTemplate;
  }
  
  public void setTrackingUrlTemplate(String trackingUrlTemplate) {
    this.trackingUrlTemplate = trackingUrlTemplate;
  }
  
  public String getUrlCustomParameters() {
    return urlCustomParameters;
  }
  
  public void setUrlCustomParameters(String urlCustomParameters) {
    this.urlCustomParameters = urlCustomParameters;
  }
  
  public Long getGmailForwards() {
    return gmailForwards;
  }
  
  public void setGmailForwards(Long gmailForwards) {
    this.gmailForwards = gmailForwards;
  }
  
  public Long getGmailSaves() {
    return gmailSaves;
  }
  
  public void setGmailSaves(Long gmailSaves) {
    this.gmailSaves = gmailSaves;
  }
  
  public Long getGmailSecondaryClicks() {
    return gmailSecondaryClicks;
  }
  
  public void setGmailSecondaryClicks(Long gmailSecondaryClicks) {
    this.gmailSecondaryClicks = gmailSecondaryClicks;
  }

  public String getVideoQuartile25Rate() {
    return BigDecimalUtil.formatAsReadable(videoQuartile25Rate);
  }
  
  public BigDecimal getVideoQuartile25RateBigDecimal() {
    return videoQuartile25Rate;
  }
  
  public void setVideoQuartile25Rate(String videoQuartile25Rate) {
    this.videoQuartile25Rate = BigDecimalUtil.parseFromNumberString(videoQuartile25Rate);
  }

  public String getVideoQuartile50Rate() {
    return BigDecimalUtil.formatAsReadable(videoQuartile50Rate);
  }
  
  public BigDecimal getVideoQuartile50RateBigDecimal() {
    return videoQuartile50Rate;
  }
  
  public void setVideoQuartile50Rate(String videoQuartile50Rate) {
    this.videoQuartile50Rate = BigDecimalUtil.parseFromNumberString(videoQuartile50Rate);
  }

  public String getVideoQuartile75Rate() {
    return BigDecimalUtil.formatAsReadable(videoQuartile75Rate);
  }
  
  public BigDecimal getVideoQuartile75RateBigDecimal() {
    return videoQuartile75Rate;
  }
  
  public void setVideoQuartile75Rate(String videoQuartile75Rate) {
    this.videoQuartile75Rate = BigDecimalUtil.parseFromNumberString(videoQuartile75Rate);
  }

  public String getVideoQuartile100Rate() {
    return BigDecimalUtil.formatAsReadable(videoQuartile100Rate);
  }
  
  public BigDecimal getVideoQuartile100RateBigDecimal() {
    return videoQuartile100Rate;
  }
  
  public void setVideoQuartile100Rate(String videoQuartile100Rate) {
    this.videoQuartile100Rate = BigDecimalUtil.parseFromNumberString(videoQuartile100Rate);
  }
}
