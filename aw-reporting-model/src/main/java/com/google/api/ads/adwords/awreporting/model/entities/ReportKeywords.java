// Copyright 2011 Google Inc. All Rights Reserved.
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
import com.google.api.ads.adwords.lib.jaxb.v201609.ReportDefinitionReportType;
import com.google.common.collect.Lists;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Specific report class for ReportKeyword
 */
@Entity
@com.googlecode.objectify.annotation.Entity
@Table(name = "AW_ReportKeywords")
@CsvReport(value = ReportDefinitionReportType.KEYWORDS_PERFORMANCE_REPORT)
public class ReportKeywords extends ReportBase {

  @Column(name = "KEYWORD_ID")
  @CsvField(value = "Keyword ID", reportField = "Id")
  private Long keywordId;

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

  @Column(name = "KEYWORD_MATCH_TYPE", length = 32)
  @CsvField(value = "Match type", reportField = "KeywordMatchType")
  private String keywordMatchType;

  @Column(name = "CRITERIA", length = 255)
  @CsvField(value = "Keyword", reportField = "Criteria")
  private String criteria;

  @Lob
  @Column(name = "CRITERIA_DESTINATION_URL", length = 2048)
  @CsvField(value = "Destination URL", reportField = "CriteriaDestinationUrl")
  private String criteriaDestinationUrl;

  @Column(name = "ADGROUP_ID")
  @CsvField(value = "Ad group ID", reportField = "AdGroupId")
  private Long adGroupId;

  @Column(name = "ADGROUP_NAME")
  @CsvField(value = "Ad group", reportField = "AdGroupName")
  private String adGroupName;

  @Column(name = "CAMPAIGN_ID")
  @CsvField(value = "Campaign ID", reportField = "CampaignId")
  private Long campaignId;

  @Column(name = "CAMPAIGN_NAME", length = 255)
  @CsvField(value = "Campaign", reportField = "CampaignName")
  private String campaignName;

  @Column(name = "STATUS", length = 32)
  @CsvField(value = "Keyword state", reportField = "Status")
  private String status;

  @Column(name = "IS_NEGATIVE")
  @CsvField(value = "Is negative", reportField = "IsNegative")
  private boolean negative;
  
  @Column(name = "AVERAGE_PAGEVIEWS")
  @CsvField(value = "Pages / visit", reportField = "AveragePageviews")
  private BigDecimal averagePageviews;

  @Column(name = "AVERAGE_TIME_ON_SITE")
  @CsvField(value = "Avg. visit duration (seconds)", reportField = "AverageTimeOnSite")
  private BigDecimal averageTimeOnSite;

  @Column(name = "BOUNCE_RATE")
  @CsvField(value = "Bounce rate", reportField = "BounceRate")
  private BigDecimal bounceRate;

  @Column(name = "PERCENT_NEW_VISITORS")
  @CsvField(value = "% new visits", reportField = "PercentNewVisitors")
  private BigDecimal percentNewVisitors;
  
  @Column(name = "MAX_CPC")
  @CsvField(value = "Max. CPC", reportField = "CpcBid")
  @MoneyField
  private BigDecimal maxCpc;

  @Column(name = "MAX_CPM")
  @CsvField(value = "Max. CPM", reportField = "CpmBid")
  @MoneyField
  private BigDecimal maxCpm;

  @Column(name = "SEARCH_EXACT_MATCH_IMPRESSION_SHARE")
  @CsvField(value = "Search Exact match IS", reportField = "SearchExactMatchImpressionShare")
  private BigDecimal searchExactMatchImpressionShare;

  @Column(name = "SEARCH_IMPRESSION_SHARE")
  @CsvField(value = "Search Impr. share", reportField = "SearchImpressionShare")
  private BigDecimal searchImpressionShare;

  @Column(name = "SEARCH_LOST_IS_RANK")
  @CsvField(value = "Search Lost IS (rank)", reportField = "SearchRankLostImpressionShare")
  private BigDecimal searchLostISRank;

  @Lob
  @Column(name = "LABELS", length = 2048)
  @CsvField(value = "Labels", reportField = "Labels")
  private String labels;

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
  
  @Column(name = "SLOT")
  @CsvField(value = "Top vs. Other", reportField = "Slot")
  private String slot;
  
  @Column(name = "ENHANCED_CPC_ENABLED")
  @CsvField(value = "Enhanced CPC enabled", reportField = "EnhancedCpcEnabled")
  private boolean enhancedCpcEnabled;
  
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
  public ReportKeywords() {
  }

  public ReportKeywords(Long topAccountId, Long accountId) {
    this.topAccountId = topAccountId;
    this.accountId = accountId;
  }

  @Override
  public void setId() {
    // Generating unique id after having accountId, campaignId, adGroupId and date
    if (this.getAccountId() != null && this.getCampaignId() != null && this.getAdGroupId() != null
        && this.getKeywordId() != null) {
      this.id = this.getAccountId() + "-" + this.getCampaignId() + "-" + this.getAdGroupId() + "-"
          + this.getKeywordId();
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
    if (this.getConversionTrackerId() != null) {
      this.id += "-" + this.getConversionTrackerId();
    }
  }

  // adGroupId
  public Long getKeywordId() {
    return keywordId;
  }

  public void setKeywordId(Long keywordId) {
    this.keywordId = keywordId;
  }

  // qualityScore
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

  // keywordMatchType
  public String getKeywordMatchType() {
    return keywordMatchType;
  }

  public void setKeywordMatchType(String keywordMatchType) {
    this.keywordMatchType = keywordMatchType;
  }

  // keywordText
  public String getCriteria() {
    return criteria;
  }

  public void setCriteria(String criteria) {
    this.criteria = criteria;
  }

  // criteriaDestinationUrl
  public String getCriteriaDestinationUrl() {
    return criteriaDestinationUrl;
  }

  public void setCriteriaDestinationUrl(String criteriaDestinationUrl) {
    this.criteriaDestinationUrl = criteriaDestinationUrl;
  }

  // adGroupId
  public Long getAdGroupId() {
    return adGroupId;
  }

  public void setAdGroupId(Long adGroupId) {
    this.adGroupId = adGroupId;
  }
  
  // adGroupName
  public String getAdGroupName() {
    return adGroupName;
  }
  
  public void setAdGroupName(String adGroupName) {
    this.adGroupName = adGroupName;
  }

  // campaignId
  public Long getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(Long campaignId) {
    this.campaignId = campaignId;
  }

  // campaignName
  public String getCampaignName() {
    return campaignName;
  }

  public void setCampaignName(String campaignName) {
    this.campaignName = campaignName;
  }
  
  // status
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  // negative
  public boolean isNegative() {
    return negative;
  }

  public void setNegative(String negative) {
    this.negative = Boolean.parseBoolean(negative);
  }

  public void setNegative(boolean negative) {
    this.negative = negative;
  }
  
  public String getAveragePageviews() {
    return BigDecimalUtil.formatAsReadable(averagePageviews);
  }

  public BigDecimal getAveragePageviewsBigDecimal() {
    return averagePageviews;
  }
  
  public void setAveragePageviews(String averagePageviews) {
    this.averagePageviews =  BigDecimalUtil.parseFromNumberString(averagePageviews);
  }

  public String getAverageTimeOnSite() {
    return BigDecimalUtil.formatAsReadable(averageTimeOnSite);
  }

  public BigDecimal getAverageTimeOnSiteBigDecimal() {
    return averageTimeOnSite;
  }
  
  public void setAverageTimeOnSite(String averageTimeOnSite) {
    this.averageTimeOnSite =  BigDecimalUtil.parseFromNumberString(averageTimeOnSite);
  }

  public String getBounceRate() {
    return BigDecimalUtil.formatAsReadable(bounceRate);
  }

  public BigDecimal getBounceRateBigDecimal() {
    return bounceRate;
  }
  
  public void setBounceRate(String bounceRate) {
    this.bounceRate =  BigDecimalUtil.parseFromNumberString(bounceRate);
  }

  public String getPercentNewVisitors() {
    return BigDecimalUtil.formatAsReadable(percentNewVisitors);
  }

  public BigDecimal getPercentNewVisitorsBigDecimal() {
    return percentNewVisitors;
  }
  
  public void setPercentNewVisitors(String percentNewVisitors) {
    this.percentNewVisitors =  BigDecimalUtil.parseFromNumberString(percentNewVisitors);
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

  public String getSearchImpressionShare() {
    return BigDecimalUtil.formatAsReadable(this.searchImpressionShare);
  }

  public BigDecimal getSearchImpressionShareBigDecimal() {
    return searchImpressionShare;
  }

  public void setSearchImpressionShare(String searchImpressionShare) {
    this.searchImpressionShare = BigDecimalUtil.parseFromNumberStringPercentage(searchImpressionShare);
  }

  public String getSearchLostISRank() {
    return BigDecimalUtil.formatAsReadable(this.searchLostISRank);
  }

  public BigDecimal getSearchLostISRankBigDecimal() {
    return searchLostISRank;
  }

  public void setSearchLostISRank(String lostISRank) {
    this.searchLostISRank = BigDecimalUtil.parseFromNumberStringPercentage(lostISRank);
  }
  
  public String getSearchExactMatchImpressionShare() {
    return BigDecimalUtil.formatAsReadable(this.searchExactMatchImpressionShare);
  }

  public BigDecimal getSearchExactMatchImpressionShareBigDecimal() {
    return searchExactMatchImpressionShare;
  }

  public void setSearchExactMatchImpressionShare(String searchExactMatchImpressionShare) {
    this.searchExactMatchImpressionShare = BigDecimalUtil.parseFromNumberStringPercentage(searchExactMatchImpressionShare);
  }

  public String getLabels() {
    return this.labels;
  }

  public boolean hasLabel(String label) {
    if (labels != null && labels.length() > 0) {
      return Lists.newArrayList(labels.split(";")).contains(label);
    } else {
      return false;
    }
  }

  public void setLabels(String labels) {
    this.labels = labels;
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
  
  public String getSlot() {
    return slot;
  }

  public void setSlot(String slot) {
    this.slot = slot;
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
