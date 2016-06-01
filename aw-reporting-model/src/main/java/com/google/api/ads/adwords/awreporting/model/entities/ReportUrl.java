package com.google.api.ads.adwords.awreporting.model.entities;

import com.google.api.ads.adwords.awreporting.model.csv.annotation.CsvField;
import com.google.api.ads.adwords.awreporting.model.csv.annotation.CsvReport;
import com.google.api.ads.adwords.awreporting.model.csv.annotation.MoneyField;
import com.google.api.ads.adwords.awreporting.model.util.BigDecimalUtil;
import com.google.api.ads.adwords.awreporting.model.util.UrlHashUtil;
import com.google.api.ads.adwords.lib.jaxb.v201603.ReportDefinitionReportType;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Specific report class for ReportKeyword
 */
@Entity
@com.googlecode.objectify.annotation.Entity
@Table(name = "AW_ReportURL")
@CsvReport(value = ReportDefinitionReportType.URL_PERFORMANCE_REPORT,
    reportExclusions = {"AveragePosition", "Device", "ClickType"})
public class ReportUrl extends ReportBase {

  @Column(name = "AD_FORMAT")
  @CsvField(value = "Ad type", reportField = "AdFormat")
  private String adFormat;

  @Column(name = "AD_GROUP_CRITERION_STATUS")
  @CsvField(value = "Keyword/Placement state", reportField = "AdGroupCriterionStatus")
  private String adGroupCriterionStatus;

  @Column(name = "AD_GROUP_ID")
  @CsvField(value = "Ad group ID", reportField = "AdGroupId")
  private Long adGroupId;

  @Column(name = "AD_GROUP_NAME")
  @CsvField(value = "Ad group", reportField = "AdGroupName")
  private String adGroupName;

  @Column(name = "AD_GROUP_STATUS")
  @CsvField(value = "Ad group state", reportField = "AdGroupStatus")
  private String adGroupStatus;

  @Column(name = "CAMPAIGN_ID")
  @CsvField(value = "Campaign ID", reportField = "CampaignId")
  private Long campaignId;

  @Column(name = "CAMPAIGN_NAME")
  @CsvField(value = "Campaign", reportField = "CampaignName")
  private String campaignName;

  @Column(name = "CAMPAIGN_STATUS")
  @CsvField(value = "Campaign state", reportField = "CampaignStatus")
  private String campaignStatus;

  @Column(name = "CRITERIA_PARAMETERS")
  @CsvField(value = "Keyword / Placement", reportField = "CriteriaParameters")
  public String criteriaParameters;

  @Column(name = "DISPLAY_NAME", length = 2048)
  @CsvField(value = "Criteria Display Name", reportField = "DisplayName")
  private String displayName;

  @Column(name = "DOMAIN")
  @CsvField(value = "Domain", reportField = "Domain")
  private String domain;

  @Column(name = "IS_AUTO_OPTIMIZED")
  @CsvField(value = "Targeting Mode", reportField = "IsAutoOptimized")
  private String isAutoOptimized;

  @Column(name = "IS_BID_ON_PATH")
  @CsvField(value = "Added", reportField = "IsBidOnPath")
  private String isBidOnPath;

  @Column(name = "IS_PATH_EXCLUDED")
  @CsvField(value = "Excluded", reportField = "IsPathExcluded")
  private String isPathExcluded;

  @Column(name = "URL", length = 2048)
  @CsvField(value = "URL", reportField = "Url")
  private String url;
  
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
  public ReportUrl() {}

  public ReportUrl(Long topAccountId, Long accountId) {
    this.topAccountId = topAccountId;
    this.accountId = accountId;
  }

  @Override
  public void setId() {
    // Generating unique id after having accountId, campaignId, adGroupId and date
    this.id = "";
    if (this.getAccountId() != null) {
      this.id += this.getAccountId() + "-";
    }
    if (this.getCampaignId() != null) {
      this.id += this.getCampaignId() + "-";
    }
    if (this.getAdGroupId() != null) {
      this.id += this.getAdGroupId() + "-";
    }
 
    // Generating a SHA-1 Hash of the URLs for ID generation
    if (this.getUrl() != null) { 
      this.id += UrlHashUtil.createUrlHash(this.getUrl());
    }

    if (this.getAdFormat() != null) {
      this.id += this.getAdFormat() + "-";
    }

    this.id += setIdDates();

    // Adding extra fields for unique ID
    if (this.getAdNetwork() != null && this.getAdNetwork().length() > 0) {
      this.id += "-" + this.getAdNetwork();
    }
    if (this.getAdNetworkPartners() != null && this.getAdNetworkPartners().length() > 0) {
      this.id += "-" + this.getAdNetworkPartners();
    }
    if (this.getConversionTrackerId() != null) {
      this.id += "-" + this.getConversionTrackerId();
    }
  }

  public String getAdFormat() {
    return adFormat;
  }

  public void setAdFormat(String adFormat) {
    this.adFormat = adFormat;
  }

  public String getAdGroupCriterionStatus() {
    return adGroupCriterionStatus;
  }

  public void setAdGroupCriterionStatus(String adGroupCriterionStatus) {
    this.adGroupCriterionStatus = adGroupCriterionStatus;
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

  public String getCriteriaParameters() {
    return criteriaParameters;
  }

  public void setCriteriaParameters(String criteriaParameters) {
    this.criteriaParameters = criteriaParameters;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getIsAutoOptimized() {
    return isAutoOptimized;
  }

  public void setIsAutoOptimized(String isAutoOptimized) {
    this.isAutoOptimized = isAutoOptimized;
  }

  public String getIsBidOnPath() {
    return isBidOnPath;
  }

  public void setIsBidOnPath(String isBidOnPath) {
    this.isBidOnPath = isBidOnPath;
  }

  public String getIsPathExcluded() {
    return isPathExcluded;
  }

  public void setIsPathExcluded(String isPathExcluded) {
    this.isPathExcluded = isPathExcluded;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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
