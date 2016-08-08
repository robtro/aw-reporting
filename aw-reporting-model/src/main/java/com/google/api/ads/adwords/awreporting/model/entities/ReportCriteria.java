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

package com.google.api.ads.adwords.awreporting.model.entities;

import com.google.api.ads.adwords.awreporting.model.csv.annotation.CsvField;
import com.google.api.ads.adwords.awreporting.model.csv.annotation.CsvReport;
import com.google.api.ads.adwords.awreporting.model.csv.annotation.MoneyField;
import com.google.api.ads.adwords.awreporting.model.util.BigDecimalUtil;
import com.google.api.ads.adwords.lib.jaxb.v201607.ReportDefinitionReportType;
import com.google.common.collect.Lists;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Specific report class for ReportKeyword
 */
@Entity
@com.googlecode.objectify.annotation.Entity
@Table(name = "AW_ReportCriteria")
@CsvReport(value = ReportDefinitionReportType.CRITERIA_PERFORMANCE_REPORT)
public class ReportCriteria extends ReportBase {

  @Column(name = "AD_GROUP_ID")
  @CsvField(value = "Ad group ID", reportField = "AdGroupId")
  private Long adGroupId;

  @Column(name = "AD_GROUP_NAME")
  @CsvField(value = "Ad group", reportField = "AdGroupName")
  private String adGroupName;

  @Column(name = "AD_GROUP_STATUS")
  @CsvField(value = "Ad group state", reportField = "AdGroupStatus")
  private String adGroupStatus;

  @Column(name = "ADVERTISER_EXPERIMENT_SEGMENTATION_BIN")
  @CsvField(value = "ACE split", reportField = "AdvertiserExperimentSegmentationBin")
  private String advertiserExperimentSegmentationBin;

  @Column(name = "APPROVAL_STATUS")
  @CsvField(value = "Approval Status", reportField = "ApprovalStatus")
  private String approvalStatus;

  @Column(name = "BID_MODIFIER")
  @CsvField(value = "Bid adj.", reportField = "BidModifier")
  private BigDecimal bidModifier;

  @Column(name = "CAMPAIGN_ID")
  @CsvField(value = "Campaign ID", reportField = "CampaignId")
  private Long campaignId;

  @Column(name = "CAMPAIGN_NAME")
  @CsvField(value = "Campaign", reportField = "CampaignName")
  private String campaignName;

  @Column(name = "CAMPAIGN_STATUS")
  @CsvField(value = "Campaign state", reportField = "CampaignStatus")
  private String campaignStatus;

  @Column(name = "CLICK_SIGNIFICANCE")
  @CsvField(value = "Clicks ACE indicator", reportField = "ClickSignificance")
  private String clickSignificance;

  @Column(name = "COST_SIGNIFICANCE")
  @CsvField(value = "Cost ACE indicator", reportField = "CostSignificance")
  private String costSignificance;

  @Column(name = "CPC_BID_SOURCE")
  @CsvField(value = "Max CPC source", reportField = "CpcBidSource")
  private String cpcBidSource;

  @Column(name = "CPC_SIGNIFICANCE")
  @CsvField(value = "CPC ACE indicator", reportField = "CpcSignificance")
  private String cpcSignificance;

  @Column(name = "CPM_SIGNIFICANCE")
  @CsvField(value = "CPM ACE indicator", reportField = "CpmSignificance")
  private String cpmSignificance;

  @Column(name = "CRITERIA", length = 2048)
  @CsvField(value = "Keyword / Placement", reportField = "Criteria")
  private String criteria;

  @Column(name = "CRITERIA_DESTINATION_URL", length = 2048)
  @CsvField(value = "Keyword/Placement destination URL", reportField = "CriteriaDestinationUrl")
  private String criteriaDestinationUrl;

  @Column(name = "CRITERIA_TYPE")
  @CsvField(value = "Criteria Type", reportField = "CriteriaType")
  private String criteriaType;

  @Column(name = "CTR_SIGNIFICANCE")
  @CsvField(value = "CTR ACE indicator", reportField = "CtrSignificance")
  private String ctrSignificance;

  @Column(name = "DISPLAY_NAME")
  @CsvField(value = "Criteria Display Name", reportField = "DisplayName")
  private String displayName;

  @Column(name = "FIRST_PAGE_CPC")
  @CsvField(value = "First page CPC", reportField = "FirstPageCpc")
  private String firstPageCpc;

  @Column(name = "TOP_OF_PAGE_CPC")
  @CsvField(value = "Top of page CPC", reportField = "TopOfPageCpc")
  private String topOfPageCpc;
  
  @Column(name = "FIRST_POSITION_CPC")
  @CsvField(value = "First position CPC", reportField = "FirstPositionCpc")
  private String firstPositionCpc;

  @Column(name = "ESTIMATED_ADD_CLICKS_AT_FIRST_POSITION_CPC")
  @CsvField(value = "Est. add. clicks/wk (first position bid)", reportField = "EstimatedAddClicksAtFirstPositionCpc")
  private Long estimatedAddClicksAtFirstPositionCpc;

  @Column(name = "ESTIMATED_ADD_COST_AT_FIRST_POSITION_CPC")
  @CsvField(value = "Est. add. clicks/wk (first position bid)", reportField = "EstimatedAddClicksAtFirstPositionCpc")
  @MoneyField
  private BigDecimal estimatedAddCostAtFirstPositionCpc;

  @Column(name = "KEYWORD_ID")
  @CsvField(value = "Keyword ID", reportField = "Id")
  private String keywordId;

  @Column(name = "IMPRESSION_SIGNIFICANCE")
  @CsvField(value = "Impressions ACE indicator", reportField = "ImpressionSignificance")
  private String impressionSignificance;

  @Column(name = "IS_NEGATIVE")
  @CsvField(value = "Is negative", reportField = "IsNegative")
  private String isNegative;

  @Column(name = "MAX_CPC")
  @CsvField(value = "Max. CPC", reportField = "CpcBid")
  @MoneyField
  private BigDecimal maxCpc;

  @Column(name = "MAX_CPM")
  @CsvField(value = "Max. CPM", reportField = "CpmBid")
  @MoneyField
  private BigDecimal maxCpm;

  @Column(name = "PARAMETER")
  @CsvField(value = "Dynamic ad target", reportField = "Parameter")
  private String parameter;

  @Column(name = "PERCENT_CPA")
  @CsvField(value = "Max. CPA%", reportField = "PercentCpa")
  private String percentCpa;

  @Column(name = "POSITION_SIGNIFICANCE")
  @CsvField(value = "Position ACE indicator", reportField = "PositionSignificance")
  private String positionSignificance;

  @Column(name = "QUALITY_SCORE")
  @CsvField(value = "Quality score", reportField = "QualityScore")
  private Long qualityScore;

  @Column(name = "CREATIVE_QUALITY_SCORE", length = 32)
  @CsvField(value = "Ad relevance", reportField = "CreativeQualityScore")
  private String creativeQualityScore;

  @Column(name = "POST_CLICK_QUALITY_SCORE", length = 32)
  @CsvField(value = "Landing page experience", reportField = "PostClickQualityScore")
  private String postClickQualityScore;

  @Column(name = "SEARCH_PREDICTED_CTR", length = 32)
  @CsvField(value = "Expected clickthrough rate", reportField = "SearchPredictedCtr")
  private String searchPredictedCtr;

  @Column(name = "SLOT")
  @CsvField(value = "Top vs. side", reportField = "Slot")
  private String slot;

  @Column(name = "STATUS")
  @CsvField(value = "Keyword/Placement state", reportField = "Status")
  private String status;

  @Column(name = "VIEW_THROUGH_CONVERSIONS_SIGNIFICANCE")
  @CsvField(value = "View-through conv. ACE indicator",
  reportField = "ViewThroughConversionsSignificance")
  private String viewThroughConversionsSignificance;
  
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
  
  @Column(name = "CPV_BID")
  @CsvField(value = "Max. CPV", reportField = "CpvBid")
  @MoneyField
  private BigDecimal cpvBid;
  
  @Column(name = "CPV_BID_SOURCE")
  @CsvField(value = "Max CPV source", reportField = "CpvBidSource")
  private String cpvBidSource;
  
  @Column(name = "ENHANCED_CPC_ENABLED")
  @CsvField(value = "Enhanced CPC enabled", reportField = "EnhancedCpcEnabled")
  private boolean enhancedCpcEnabled;

  @Column(name = "ENHANCED_CPV_ENABLED")
  @CsvField(value = "Enhanced CPV enabled", reportField = "EnhancedCpvEnabled")
  private boolean enhancedCpvEnabled;
  
  @Column(name = "SYSTEM_SERVING_STATUS")
  @CsvField(value = "Criterion serving status", reportField = "SystemServingStatus")
  private String systemServingStatus;
  
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
  public ReportCriteria() {
  }

  public ReportCriteria(Long topAccountId, Long accountId) {
    this.topAccountId = topAccountId;
    this.accountId = accountId;
  }

  @Override
  public void setId() {
    // Generating unique id after having accountId, campaignId, adGroupId
    // and date
    this.id = this.getAccountId() + "-" + this.getCampaignId() + "-"
        + this.getAdGroupId() + "-" + this.getKeywordId();

    this.id += this.setIdDates();

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

  public String getAdvertiserExperimentSegmentationBin() {
    return advertiserExperimentSegmentationBin;
  }

  public void setAdvertiserExperimentSegmentationBin(
      String advertiserExperimentSegmentationBin) {
    this.advertiserExperimentSegmentationBin = advertiserExperimentSegmentationBin;
  }

  public String getApprovalStatus() {
    return approvalStatus;
  }

  public void setApprovalStatus(String approvalStatus) {
    this.approvalStatus = approvalStatus;
  }

  public String getBidModifier() {
    return BigDecimalUtil.formatAsReadable(bidModifier);
  }

  public void setBidModifier(String bidModifier) {
    this.bidModifier = BigDecimalUtil.parseFromNumberString(bidModifier);
  }

  public Long getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(Long campaignId) {
    this.campaignId = campaignId;
  }

  public String getCampaignName() {
    return campaignName;
  }

  public void setCampaignName(String campaignName) {
    this.campaignName = campaignName;
  }

  public String getCampaignStatus() {
    return campaignStatus;
  }

  public void setCampaignStatus(String campaignStatus) {
    this.campaignStatus = campaignStatus;
  }

  public String getClickSignificance() {
    return clickSignificance;
  }

  public void setClickSignificance(String clickSignificance) {
    this.clickSignificance = clickSignificance;
  }

  public String getCpcBidSource() {
    return cpcBidSource;
  }

  public void setCpcBidSource(String cpcBidSource) {
    this.cpcBidSource = cpcBidSource;
  }

  public String getCpcSignificance() {
    return cpcSignificance;
  }

  public void setCpcSignificance(String cpcSignificance) {
    this.cpcSignificance = cpcSignificance;
  }

  public String getCpmSignificance() {
    return cpmSignificance;
  }

  public void setCpmSignificance(String cpmSignificance) {
    this.cpmSignificance = cpmSignificance;
  }

  public String getCriteria() {
    return criteria;
  }

  public void setCriteria(String criteria) {
    this.criteria = criteria;
  }

  public String getCriteriaDestinationUrl() {
    return criteriaDestinationUrl;
  }

  public void setCriteriaDestinationUrl(String criteriaDestinationUrl) {
    this.criteriaDestinationUrl = criteriaDestinationUrl;
  }

  public String getCriteriaType() {
    return criteriaType;
  }

  public void setCriteriaType(String criteriaType) {
    this.criteriaType = criteriaType;
  }

  public String getCtrSignificance() {
    return ctrSignificance;
  }

  public void setCtrSignificance(String ctrSignificance) {
    this.ctrSignificance = ctrSignificance;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getFirstPageCpc() {
    return firstPageCpc;
  }

  public void setFirstPageCpc(String firstPageCpc) {
    this.firstPageCpc = firstPageCpc;
  }

  public String getTopOfPageCpc() {
    return topOfPageCpc;
  }

  public void setTopOfPageCpc(String topOfPageCpc) {
    this.topOfPageCpc = topOfPageCpc;
  }
  
  public String getFirstPositionCpc() {
    return firstPositionCpc;
  }
  
  public void setFirstPositionCpc(String firstPositionCpc) {
    this.firstPositionCpc = firstPositionCpc;
  }
  
  public Long getEstimatedAddClicksAtFirstPositionCpc() {
    return estimatedAddClicksAtFirstPositionCpc;
  }
  
  public void setEstimatedAddClicksAtFirstPositionCpc(Long estimatedAddClicksAtFirstPositionCpc) {
    this.estimatedAddClicksAtFirstPositionCpc = estimatedAddClicksAtFirstPositionCpc;
  }
  
  public BigDecimal getEstimatedAddCostAtFirstPositionCpc() {
    return estimatedAddCostAtFirstPositionCpc;
  }

  public void setEstimatedAddCostAtFirstPositionCpc(BigDecimal estimatedAddCostAtFirstPositionCpc) {
    this.estimatedAddCostAtFirstPositionCpc = estimatedAddCostAtFirstPositionCpc;
  }

  public String getKeywordId() {
    return keywordId;
  }

  public void setKeywordId(String keywordId) {
    this.keywordId = keywordId;
  }

  public String getImpressionSignificance() {
    return impressionSignificance;
  }

  public void setImpressionSignificance(String impressionSignificance) {
    this.impressionSignificance = impressionSignificance;
  }

  public String getIsNegative() {
    return isNegative;
  }

  public void setIsNegative(String isNegative) {
    this.isNegative = isNegative;
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

  public String getParameter() {
    return parameter;
  }

  public void setParameter(String parameter) {
    this.parameter = parameter;
  }

  public String getPercentCpa() {
    return percentCpa;
  }

  public void setPercentCpa(String percentCpa) {
    this.percentCpa = percentCpa;
  }

  public String getPositionSignificance() {
    return positionSignificance;
  }

  public void setPositionSignificance(String positionSignificance) {
    this.positionSignificance = positionSignificance;
  }

  public Long getQualityScore() {
    return qualityScore;
  }

  public void setQualityScore(Long qualityScore) {
    this.qualityScore = qualityScore;
  }
  
  public String getCreativeQualityScore() {
    return creativeQualityScore;
  }
  
  public void setCreativeQualityScore(String creativeQualityScore) {
    this.creativeQualityScore = creativeQualityScore;
  }
  
  public String getPostClickQualityScore() {
    return postClickQualityScore;
  }
  
  public void setPostClickQualityScore(String postClickQualityScore) {
    this.postClickQualityScore = postClickQualityScore;
  }
  
  public String getSearchPredictedCtr() {
    return searchPredictedCtr;
  }
  
  public void setSearchPredictedCtr(String searchPredictedCtr) {
    this.searchPredictedCtr = searchPredictedCtr;
  }

  public String getSlot() {
    return slot;
  }

  public void setSlot(String slot) {
    this.slot = slot;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getViewThroughConversionsSignificance() {
    return viewThroughConversionsSignificance;
  }

  public void setViewThroughConversionsSignificance(
      String viewThroughConversionsSignificance) {
    this.viewThroughConversionsSignificance = viewThroughConversionsSignificance;
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
  
  public BigDecimal getCpvBid() {
    return cpvBid;
  }
  
  public void setCpvBid(BigDecimal cpvBid) {
    this.cpvBid = cpvBid;
  }
  
  public String getCpvBidSource() {
    return cpvBidSource;
  }
  
  public void setCpvBidSource(String cpvBidSource) {
    this.cpvBidSource = cpvBidSource;
  }
  
  public boolean isEnhancedCpcEnabled() {
    return enhancedCpcEnabled;
  }
  
  public void setEnhancedCpcEnabled(boolean enhancedCpcEnabled) {
    this.enhancedCpcEnabled = enhancedCpcEnabled;
  }
  
  public void setEnhancedCpcEnabled(String enhancedCpcEnabled) {
    this.enhancedCpcEnabled = Boolean.parseBoolean(enhancedCpcEnabled);
  }
  
  public boolean isEnhancedCpvEnabled() {
    return enhancedCpvEnabled;
  }
  
  public void setEnhancedCpvEnabled(boolean enhancedCpvEnabled) {
    this.enhancedCpvEnabled = enhancedCpvEnabled;
  }
  
  public void setEnhancedCpvEnabled(String enhancedCpvEnabled) {
    this.enhancedCpvEnabled = Boolean.parseBoolean(enhancedCpvEnabled);
  }
  
  public String getSystemServingStatus() {
    return systemServingStatus;
  }
  
  public void setSystemServingStatus(String systemServingStatus) {
    this.systemServingStatus = systemServingStatus;
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
