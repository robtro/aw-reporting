package com.google.api.ads.adwords.awreporting.churnprediction.entities;

import com.google.api.ads.adwords.awreporting.churnprediction.annotations.AccumulatedSignal;
import com.google.api.ads.adwords.awreporting.churnprediction.annotations.AverageSignal;
import com.google.api.ads.adwords.awreporting.churnprediction.annotations.ExcludeFromTraining;
import com.google.api.ads.adwords.awreporting.churnprediction.annotations.RatioSignal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is the class that will hold all the signals to be used in the prediction API.
 */
@Entity
@Table(name = "AW_AccountSignals")
public class AccountSignals {
  // Attributes
  @Id
  @Column(unique = true, name = "CUSTOMER_ID")
  @ExcludeFromTraining
  private long cid;

  @Column(name = "COMPANY_NAME")
  @ExcludeFromTraining
  private String companyName;

  @Column(name = "DESCRIPTIVE_NAME")
  @ExcludeFromTraining
  private String descriptiveName;

  public AccountSignals() {}

  public AccountSignals(long cid, String companyName, String descriptiveName) {
    this.cid = cid;
    this.companyName = companyName;
    this.descriptiveName = descriptiveName;
  }

  // Stats
  // Last >0 "impressions" Account report
  @Column(name = "LAST_IMPRESSION")
  private int lastImpression;

  //  Last >0 "clicks" Account report
  @Column(name = "LAST_CLICK")
  private int lastClick;

  // Last >0 "cost" Account report
  @Column(name = "LAST_SPEND")
  private int lastSpend;

  // count keyword db
  // Filled by Keyword signals calculator
  @Column(name = "ACTIVE_KEYWORD_COUNT")
  private int activeKeywordCount;

  // count adGroups db
  @Column(name = "ADGROUPS_COUNT")
  private int adgroupsCount;

  // count campaigns db
  @Column(name = "CAMPAIGN_COUNT")
  private int campaignCount;

  // count ads db
  @Column(name = "CREATIVE_COUNT")
  private int creativeCount;

  // divide adGroup by campaigns
  @Column(name = "ADGROUP_TO_CAMPAIGN_RATIO")
  private double adGroupToCampaignRatio;

  // divide ad by adgroup
  @Column(name = "CREATIVE_TO_ADGROUP_RATIO")
  private double creativeToAdGroupRatio;

  // divide ads by campaigns
  @Column(name = "CREATIVE_TO_CAMPAIGN_RATIO")
  private double creativeToCampaignRatio;

  // divide keyword by adGroups
  @Column(name = "KEYWORD_TO_ADGROUP_RATIO")
  private double keywordToAdGroupRatio;

  // low QS <=5
  // Filled by Keyword signals calculator
  @Column(name = "KEYWORD_LOW_QS_RATIO")
  private double keywordLowQsRatio;

  // max value of CPC for all keywords
  // Filled by Keyword signals calculator
  @Column(name = "KEYWORD_MAX_CPC_MAX")
  private double keywordMaxCpcMax;

  // TDB
  @Column(name = "UNUSED_BUDGET_RATIO_14_DAY")
  private long unusedBudgetRatio14day;

  // TBD
  @Column(name = "UNUSED_BUDGET_RATIO_28_DAY")
  private long unusedBudgetRatio28day;

  // count neg keywords db, divide by keywords
  @Column(name = "NEG_KEYWORD_RATIO")
  private double negKeywordRatio;

  // Average position
  @AverageSignal(property = "avgPosition", days = 14)
  @Column(name = "AVG_POS_14_DAY")
  private double avgPos14day;

  @AverageSignal(property = "avgPosition", days = 28)
  @Column(name = "AVG_POS_28_DAY")
  private double avgPos28day;

  @AverageSignal(property = "avgPosition", months = 1)
  @Column(name = "AVG_POS_1_MONTH")
  private double avgPos1month;

  @AverageSignal(property = "avgPosition", months = 2)
  @Column(name = "AVG_POS_2_MONTH")
  private double avgPos2month;

  @AverageSignal(property = "avgPosition", months = 3)
  @Column(name = "AVG_POS_3_MONTH")
  private double avgPos3month;

  @AverageSignal(property = "avgPosition", months = 4)
  @Column(name = "AVG_POS_4_MONTH")
  private double avgPos6month;

  @RatioSignal(property = "avgPosition", firstDay = 14, secondDay = 28)
  @Column(name = "AVG_POS_14_TO_28_RATIO")
  private double avgPos14to28Ratio;

  @RatioSignal(property = "avgPosition", firstMonth = 1, secondMonth = 2)
  @Column(name = "AVG_POS_1M_TO_2M_RATIO")
  private double avgPos1mto2mRatio;

  @RatioSignal(property = "avgPosition", firstMonth = 1, secondMonth = 3)
  @Column(name = "AVG_POS_1M_TO_3M_RATIO")
  private double avgPos1mto3mRatio;

  @RatioSignal(property = "avgPosition", firstMonth = 1, secondMonth = 6)
  @Column(name = "AVG_POS_1M_TO_6M_RATIO")
  private double avgPos1mto6mRatio;

  // Keyword quality score
  @Column(name = "AVG_QUALITY_SCORE")
  private double avgQualityScore;

  @Column(name = "KW_SCORE_0_PERCENT")
  private double kwScore0Percent;

  @Column(name = "KW_SCORE_1_PERCENT")
  private double kwScore1Percent;

  @Column(name = "KW_SCORE_2_PERCENT")
  private double kwScore2Percent;

  @Column(name = "KW_SCORE_3_PERCENT")
  private double kwScore3Percent;

  @Column(name = "KW_SCORE_4_PERCENT")
  private double kwScore4Percent;

  @Column(name = "KW_SCORE_5_PERCENT")
  private double kwScore5Percent;

  @Column(name = "KW_SCORE_6_PERCENT")
  private double kwScore6Percent;

  @Column(name = "KW_SCORE_7_PERCENT")
  private double kwScore7Percent;

  @Column(name = "KW_SCORE_8_PERCENT")
  private double kwScore8Percent;

  @Column(name = "KW_SCORE_9_PERCENT")
  private double kwScore9Percent;

  @Column(name = "KW_SCORE_10_PERCENT")
  private double kwScore10Percent;

  // Impressions
  @AccumulatedSignal(property = "impressions", days = 14)
  @Column(name = "IMPRS_14_DAY")
  private long imprs14day;

  @AccumulatedSignal(property = "impressions", days = 28)
  @Column(name = "IMPRS_28_DAY")
  private long imprs28day;

  @AccumulatedSignal(property = "impressions", months = 1)
  @Column(name = "IMPRS_1_MONTH")
  private long imprs1month;

  @AccumulatedSignal(property = "impressions", months = 2)
  @Column(name = "IMPRS_2_MONTH")
  private long imprs2month;

  @AccumulatedSignal(property = "impressions", months = 3)
  @Column(name = "IMPRS_3_MONTH")
  private long imprs3month;

  @AccumulatedSignal(property = "impressions", months = 6)
  @Column(name = "IMPRS_6_MONTH")
  private long imprs6month;

  @RatioSignal(property = "impressions", firstDay = 14, secondDay = 28)
  @Column(name = "IMPRS_14_TO_28_RATIO")
  private double imprs14to28Ratio;

  @RatioSignal(property = "impressions", firstMonth = 1, secondMonth = 2)
  @Column(name = "IMPRS_1M_TO_2M_RATIO")
  private double imprs1mto2mRatio;

  @RatioSignal(property = "impressions", firstMonth = 1, secondMonth = 3)
  @Column(name = "IMPRS_1M_TO_3M_RATIO")
  private double imprs1mto3mRatio;

  @RatioSignal(property = "impressions", firstMonth = 1, secondMonth = 6)
  @Column(name = "IMPRS_1M_TO_6M_RATIO")
  private double imprs1mto6mRatio;

  // Clicks
  @AccumulatedSignal(property = "clicks", days = 14)
  @Column(name = "CLICKS_14_DAYS")
  private long clicks14day;

  @AccumulatedSignal(property = "clicks", days = 28)
  @Column(name = "CLICKS_28_DAYS")
  private long clicks28day;

  @AccumulatedSignal(property = "clicks", months = 1)
  @Column(name = "CLICKS_1_MONTH")
  private long clicks1month;

  @AccumulatedSignal(property = "clicks", months = 2)
  @Column(name = "CLICKS_2_MONTH")
  private long clicks2month;

  @AccumulatedSignal(property = "clicks", months = 3)
  @Column(name = "CLICKS_3_MONTH")
  private long clicks3month;

  @AccumulatedSignal(property = "clicks", months = 6)
  @Column(name = "CLICKS_6_MONTH")
  private long clicks6month;

  @RatioSignal(property = "clicks", firstDay = 14, secondDay = 28)
  @Column(name = "CLICKS_14_TO_28_RATIO")
  private double clicks14to28Ratio;

  @RatioSignal(property = "clicks", firstMonth = 1, secondMonth = 2)
  @Column(name = "CLICKS_1M_TO_2M_RATIO")
  private double clicks1mto2mRatio;

  @RatioSignal(property = "clicks", firstMonth = 1, secondMonth = 3)
  @Column(name = "CLICKS_1M_TO_3M_RATIO")
  private double clicks1mto3mRatio;

  @RatioSignal(property = "clicks", firstMonth = 1, secondMonth = 6)
  @Column(name = "CLICKS_1M_TO_6M_RATIO")
  private double clicks1mto6mRatio;

  // Conversions
  @AccumulatedSignal(property = "conversionsManyPerClick", days = 14)
  @Column(name = "CONVS_14_DAY")
  private long convs14day;

  @AccumulatedSignal(property = "conversionsManyPerClick", days = 28)
  @Column(name = "CONVS_28_DAY")
  private long convs28day;

  @AccumulatedSignal(property = "conversionsManyPerClick", months = 1)
  @Column(name = "CONVS_1_MONTH")
  private long convs1month;

  @AccumulatedSignal(property = "conversionsManyPerClick", months = 2)
  @Column(name = "CONVS_2_MONTH")
  private long convs2month;

  @AccumulatedSignal(property = "conversionsManyPerClick", months = 3)
  @Column(name = "CONVS_3_MONTH")
  private long convs3month;

  @AccumulatedSignal(property = "conversionsManyPerClick", months = 6)
  @Column(name = "CONVS_6_MONTH")
  private long convs6month;

  @RatioSignal(property = "conversionsManyPerClick", firstDay = 14, secondDay = 28)
  @Column(name = "CONVS_14_TO_28_RATIO")
  private double convs14to28Ratio;

  @RatioSignal(property = "conversionsManyPerClick", firstMonth = 1, secondMonth = 2)
  @Column(name = "CONVS_1M_TO_2M_RATIO")
  private double convs1mto2mRatio;

  @RatioSignal(property = "conversionsManyPerClick", firstMonth = 1, secondMonth = 3)
  @Column(name = "CONVS_1M_TO_3M_RATIO")
  private double convs1mto3mRatio;

  @RatioSignal(property = "conversionsManyPerClick", firstMonth = 1, secondMonth = 6)
  @Column(name = "CONVS_1M_TO_6M_RATIO")
  private double convs1mto6mRatio;

  // Cost
  @AccumulatedSignal(property = "cost", days = 14)
  @Column(name = "COST_14_DAY")
  private long cost14day;

  @AccumulatedSignal(property = "cost", days = 28)
  @Column(name = "COST_28_DAY")
  private long cost28day;

  @AccumulatedSignal(property = "cost", months = 1)
  @Column(name = "COST_1_MOTNH")
  private long cost1month;

  @AccumulatedSignal(property = "cost", months = 2)
  @Column(name = "COST_2_MOTNH")
  private long cost2month;

  @AccumulatedSignal(property = "cost", months = 3)
  @Column(name = "COST_3_MOTNH")
  private long cost3month;

  @AccumulatedSignal(property = "cost", months = 6)
  @Column(name = "COST_6_MOTNH")
  private long cost6month;

  @AccumulatedSignal(property = "cost", months = 12)
  @Column(name = "COST_1_YEAR")
  private long cost1year;

  @RatioSignal(property = "cost", firstDay = 14, secondDay = 28)
  @Column(name = "COST_14_TO_28_RATIO")
  private double cost14to28Ratio;

  @RatioSignal(property = "cost", firstMonth = 1, secondMonth = 2)
  @Column(name = "COST_1M_TO_2M_RATIO")
  private double cost1mto2mRatio;

  @RatioSignal(property = "cost", firstMonth = 1, secondMonth = 3)
  @Column(name = "COST_1M_TO_3M_RATIO")
  private double cost1mto3mRatio;

  @RatioSignal(property = "cost", firstMonth = 1, secondMonth = 6)
  @Column(name = "COST_1M_TO_6M_RATIO")
  private double cost1mto6mRatio;

  @RatioSignal(property = "cost", firstMonth = 1, secondMonth = 12)
  @Column(name = "COST_1M_TO_12M_RATIO")
  private double cost1mto12mRatio;

  // Conversion rate
  @AverageSignal(property = "conversionRateManyPerClick", days = 14)
  @Column(name = "CONVSRATE_14_DAY")
  private long convsRate14day;

  @AverageSignal(property = "conversionRateManyPerClick", days = 28)
  @Column(name = "CONVSRATE_28_DAY")
  private long convsRate28day;

  @RatioSignal(property = "conversionRateManyPerClick", firstDay = 14, secondDay = 28)
  @Column(name = "CONVSRATE_14_TO_28_RATIO")
  private double convsRate14to28Ratio;

  // CTR
  @AverageSignal(property = "ctr", days = 14)
  @Column(name = "CTR_14_DAY")
  private long ctr14day;

  @AverageSignal(property = "ctr", days = 28)
  @Column(name = "CTR_28_DAY")
  private long ctr28day;

  @AverageSignal(property = "ctr", months = 1)
  @Column(name = "CTR_1_MONTH")
  private long ctr1month;

  @AverageSignal(property = "ctr", months = 2)
  @Column(name = "CTR_2_MONTH")
  private long ctr2month;

  @AverageSignal(property = "ctr", months = 3)
  @Column(name = "CTR_3_MONTH")
  private long ctr3month;

  @AverageSignal(property = "ctr", months = 6)
  @Column(name = "CTR_6_MONTH")
  private long ctr6month;

  @RatioSignal(property = "ctr", firstDay = 14, secondDay = 28)
  @Column(name = "CTR_14_TO_28_RATIO")
  private double ctr14to28Ratio;

  @RatioSignal(property = "ctr", firstMonth = 1, secondMonth = 2)
  @Column(name = "CTR_1M_TO_2M_RATIO")
  private double ctr1mto2mRatio;

  @RatioSignal(property = "ctr", firstMonth = 1, secondMonth = 3)
  @Column(name = "CTR_1M_TO_3M_RATIO")
  private double ctr1mto3mRatio;

  @RatioSignal(property = "ctr", firstMonth = 1, secondMonth = 6)
  @Column(name = "CTR_1M_TO_6M_RATIO")
  private double ctr1mto6mRatio;

  // CPC
  @AverageSignal(property = "avgCpc", days = 14)
  @Column(name = "CPC_14_DAY")
  private long cpc14day;

  @AverageSignal(property = "avgCpc", days = 28)
  @Column(name = "CPC_28_DAY")
  private long cpc28day;

  @AverageSignal(property = "avgCpc", months = 1)
  @Column(name = "CPC_1_MONTH")
  private long cpc1month;

  @AverageSignal(property = "avgCpc", months = 2)
  @Column(name = "CPC_2_MONTH")
  private long cpc2month;

  @AverageSignal(property = "avgCpc", months = 3)
  @Column(name = "CPC_3_MONTH")
  private long cpc3month;

  @AverageSignal(property = "avgCpc", months = 6)
  @Column(name = "CPC_6_MONTH")
  private long cpc6month;

  @RatioSignal(property = "avgCpc", firstDay = 14, secondDay = 28)
  @Column(name = "CPC_14_TO_28_RATIO")
  private double cpc14to28Ratio;

  @RatioSignal(property = "avgCpc", firstMonth = 1, secondMonth = 2)
  @Column(name = "CPC_1M_TO_2M_RATIO")
  private double cpc1mto2mRatio;

  @RatioSignal(property = "avgCpc", firstMonth = 1, secondMonth = 3)
  @Column(name = "CPC_1M_TO_3M_RATIO")
  private double cpc1mto3mRatio;

  @RatioSignal(property = "avgCpc", firstMonth = 1, secondMonth = 6)
  @Column(name = "CPC_1M_TO_6M_RATIO")
  private double cpc1mto6mRatio;

  public long getCid() {
    return cid;
  }

  public void setCid(long cid) {
    this.cid = cid;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getDescriptiveName() {
    return descriptiveName;
  }

  public void setDescriptiveName(String descriptiveName) {
    this.descriptiveName = descriptiveName;
  }

  public int getLastImpression() {
    return lastImpression;
  }

  public void setLastImpression(int lastImpression) {
    this.lastImpression = lastImpression;
  }

  public int getLastClick() {
    return lastClick;
  }

  public void setLastClick(int lastClick) {
    this.lastClick = lastClick;
  }

  public int getLastSpend() {
    return lastSpend;
  }

  public void setLastSpend(int lastSpend) {
    this.lastSpend = lastSpend;
  }

  public int getActiveKeywordCount() {
    return activeKeywordCount;
  }

  public void setActiveKeywordCount(int activeKeywordCount) {
    this.activeKeywordCount = activeKeywordCount;
  }

  public int getAdgroupsCount() {
    return adgroupsCount;
  }

  public void setAdgroupsCount(int adgroupsCount) {
    this.adgroupsCount = adgroupsCount;
  }

  public int getCampaignCount() {
    return campaignCount;
  }

  public void setCampaignCount(int campaignCount) {
    this.campaignCount = campaignCount;
  }

  public int getCreativeCount() {
    return creativeCount;
  }

  public void setCreativeCount(int creativeCount) {
    this.creativeCount = creativeCount;
  }

  public double getAdGroupToCampaignRatio() {
    return adGroupToCampaignRatio;
  }

  public void setAdGroupToCampaignRatio(double adGroupToCampaignRatio) {
    this.adGroupToCampaignRatio = adGroupToCampaignRatio;
  }

  public double getCreativeToAdGroupRatio() {
    return creativeToAdGroupRatio;
  }

  public void setCreativeToAdGroupRatio(double creativeToAdGroupRatio) {
    this.creativeToAdGroupRatio = creativeToAdGroupRatio;
  }

  public double getCreativeToCampaignRatio() {
    return creativeToCampaignRatio;
  }

  public void setcreativeToCampaignRatio(double creativeToCampaignRatio) {
    this.creativeToCampaignRatio = creativeToCampaignRatio;
  }

  public double getKeywordToAdGroupRatio() {
    return keywordToAdGroupRatio;
  }

  public void setKeywordToAdGroupRatio(double keywordToAdGroupRatio) {
    this.keywordToAdGroupRatio = keywordToAdGroupRatio;
  }

  public double getKeywordLowQsRatio() {
    return keywordLowQsRatio;
  }

  public void setKeywordLowQsRatio(double keywordLowQsRatio) {
    this.keywordLowQsRatio = keywordLowQsRatio;
  }

  public double getKeywordMaxCpcMax() {
    return keywordMaxCpcMax;
  }

  public void setKeywordMaxCpcMax(double keywordMaxCpcMax) {
    this.keywordMaxCpcMax = keywordMaxCpcMax;
  }

  public long getUnusedBudgetRatio14day() {
    return unusedBudgetRatio14day;
  }

  public void setUnusedBudgetRatio14day(long unusedBudgetRatio14day) {
    this.unusedBudgetRatio14day = unusedBudgetRatio14day;
  }

  public long getUnusedBudgetRatio28day() {
    return unusedBudgetRatio28day;
  }

  public void setUnusedBudgetRatio28day(long unusedBudgetRatio28day) {
    this.unusedBudgetRatio28day = unusedBudgetRatio28day;
  }

  public double getNegKeywordRatio() {
    return negKeywordRatio;
  }

  public void setNegKeywordRatio(double negKeywordRatio) {
    this.negKeywordRatio = negKeywordRatio;
  }

  public double getAvgPos14day() {
    return avgPos14day;
  }

  public void setAvgPos14day(double avgPos14day) {
    this.avgPos14day = avgPos14day;
  }

  public double getAvgPos28day() {
    return avgPos28day;
  }

  public void setAvgPos28day(double avgPos28day) {
    this.avgPos28day = avgPos28day;
  }

  public double getAvgPos1month() {
    return avgPos1month;
  }

  public void setAvgPos1month(double avgPos1month) {
    this.avgPos1month = avgPos1month;
  }

  public double getAvgPos2month() {
    return avgPos2month;
  }

  public void setAvgPos2month(double avgPos2month) {
    this.avgPos2month = avgPos2month;
  }

  public double getAvgPos3month() {
    return avgPos3month;
  }

  public void setAvgPos3month(double avgPos3month) {
    this.avgPos3month = avgPos3month;
  }

  public double getAvgPos6month() {
    return avgPos6month;
  }

  public void setAvgPos6month(double avgPos6month) {
    this.avgPos6month = avgPos6month;
  }

  public double getAvgPos14to28Ratio() {
    return avgPos14to28Ratio;
  }

  public void setAvgPos14to28Ratio(double avgPos14to28Ratio) {
    this.avgPos14to28Ratio = avgPos14to28Ratio;
  }

  public double getAvgPos1mto2mRatio() {
    return avgPos1mto2mRatio;
  }

  public void setAvgPos1mto2mRatio(double avgPos1mto2mRatio) {
    this.avgPos1mto2mRatio = avgPos1mto2mRatio;
  }

  public double getAvgPos1mto3mRatio() {
    return avgPos1mto3mRatio;
  }

  public void setAvgPos1mto3mRatio(double avgPos1mto3mRatio) {
    this.avgPos1mto3mRatio = avgPos1mto3mRatio;
  }

  public double getAvgPos1mto6mRatio() {
    return avgPos1mto6mRatio;
  }

  public void setAvgPos1mto6mRatio(double avgPos1mto6mRatio) {
    this.avgPos1mto6mRatio = avgPos1mto6mRatio;
  }

  public double getAvgQualityScore() {
    return avgQualityScore;
  }

  public void setAvgQualityScore(double avgQualityScore) {
    this.avgQualityScore = avgQualityScore;
  }

  public double getKwScore0Percent() {
    return kwScore0Percent;
  }

  public void setKwScore0Percent(double kwScore0Percent) {
    this.kwScore0Percent = kwScore0Percent;
  }

  public double getKwScore1Percent() {
    return kwScore1Percent;
  }

  public void setKwScore1Percent(double kwScore1Percent) {
    this.kwScore1Percent = kwScore1Percent;
  }

  public double getKwScore2Percent() {
    return kwScore2Percent;
  }

  public void setKwScore2Percent(double kwScore2Percent) {
    this.kwScore2Percent = kwScore2Percent;
  }

  public double getKwScore3Percent() {
    return kwScore3Percent;
  }

  public void setKwScore3Percent(double kwScore3Percent) {
    this.kwScore3Percent = kwScore3Percent;
  }

  public double getKwScore4Percent() {
    return kwScore4Percent;
  }

  public void setKwScore4Percent(double kwScore4Percent) {
    this.kwScore4Percent = kwScore4Percent;
  }

  public double getKwScore5Percent() {
    return kwScore5Percent;
  }

  public void setKwScore5Percent(double kwScore5Percent) {
    this.kwScore5Percent = kwScore5Percent;
  }

  public double getKwScore6Percent() {
    return kwScore6Percent;
  }

  public void setKwScore6Percent(double kwScore6Percent) {
    this.kwScore6Percent = kwScore6Percent;
  }

  public double getKwScore7Percent() {
    return kwScore7Percent;
  }

  public void setKwScore7Percent(double kwScore7Percent) {
    this.kwScore7Percent = kwScore7Percent;
  }

  public double getKwScore8Percent() {
    return kwScore8Percent;
  }

  public void setKwScore8Percent(double kwScore8Percent) {
    this.kwScore8Percent = kwScore8Percent;
  }

  public double getKwScore9Percent() {
    return kwScore9Percent;
  }

  public void setKwScore9Percent(double kwScore9Percent) {
    this.kwScore9Percent = kwScore9Percent;
  }

  public double getKwScore10Percent() {
    return kwScore10Percent;
  }

  public void setKwScore10Percent(double kwScore10Percent) {
    this.kwScore10Percent = kwScore10Percent;
  }

  public long getImprs14day() {
    return imprs14day;
  }

  public void setImprs14day(long imprs14day) {
    this.imprs14day = imprs14day;
  }

  public long getImprs28day() {
    return imprs28day;
  }

  public void setImprs28day(long imprs28day) {
    this.imprs28day = imprs28day;
  }

  public long getImprs1month() {
    return imprs1month;
  }

  public void setImprs1month(long imprs1month) {
    this.imprs1month = imprs1month;
  }

  public long getImprs2month() {
    return imprs2month;
  }

  public void setImprs2month(long imprs2month) {
    this.imprs2month = imprs2month;
  }

  public long getImprs3month() {
    return imprs3month;
  }

  public void setImprs3month(long imprs3month) {
    this.imprs3month = imprs3month;
  }

  public long getImprs6month() {
    return imprs6month;
  }

  public void setImprs6month(long imprs6month) {
    this.imprs6month = imprs6month;
  }

  public double getImprs14to28Ratio() {
    return imprs14to28Ratio;
  }

  public void setImprs14to28Ratio(double imprs14to28Ratio) {
    this.imprs14to28Ratio = imprs14to28Ratio;
  }

  public double getImprs1mto2mRatio() {
    return imprs1mto2mRatio;
  }

  public void setImprs1mto2mRatio(double imprs1mto2mRatio) {
    this.imprs1mto2mRatio = imprs1mto2mRatio;
  }

  public double getImprs1mto3mRatio() {
    return imprs1mto3mRatio;
  }

  public void setImprs1mto3mRatio(double imprs1mto3mRatio) {
    this.imprs1mto3mRatio = imprs1mto3mRatio;
  }

  public double getImprs1mto6mRatio() {
    return imprs1mto6mRatio;
  }

  public void setImprs1mto6mRatio(double imprs1mto6mRatio) {
    this.imprs1mto6mRatio = imprs1mto6mRatio;
  }

  public long getClicks14day() {
    return clicks14day;
  }

  public void setClicks14day(long clicks14day) {
    this.clicks14day = clicks14day;
  }

  public long getClicks28day() {
    return clicks28day;
  }

  public void setClicks28day(long clicks28day) {
    this.clicks28day = clicks28day;
  }

  public long getClicks1month() {
    return clicks1month;
  }

  public void setClicks1month(long clicks1month) {
    this.clicks1month = clicks1month;
  }

  public long getClicks2month() {
    return clicks2month;
  }

  public void setClicks2month(long clicks2month) {
    this.clicks2month = clicks2month;
  }

  public long getClicks3month() {
    return clicks3month;
  }

  public void setClicks3month(long clicks3month) {
    this.clicks3month = clicks3month;
  }

  public long getClicks6month() {
    return clicks6month;
  }

  public void setClicks6month(long clicks6month) {
    this.clicks6month = clicks6month;
  }

  public double getClicks14to28Ratio() {
    return clicks14to28Ratio;
  }

  public void setClicks14to28Ratio(double clicks14to28Ratio) {
    this.clicks14to28Ratio = clicks14to28Ratio;
  }

  public double getClicks1mto2mRatio() {
    return clicks1mto2mRatio;
  }

  public void setClicks1mto2mRatio(double clicks1mto2mRatio) {
    this.clicks1mto2mRatio = clicks1mto2mRatio;
  }

  public double getClicks1mto3mRatio() {
    return clicks1mto3mRatio;
  }

  public void setClicks1mto3mRatio(double clicks1mto3mRatio) {
    this.clicks1mto3mRatio = clicks1mto3mRatio;
  }

  public double getClicks1mto6mRatio() {
    return clicks1mto6mRatio;
  }

  public void setClicks1mto6mRatio(double clicks1mto6mRatio) {
    this.clicks1mto6mRatio = clicks1mto6mRatio;
  }

  public long getConvs14day() {
    return convs14day;
  }

  public void setConvs14day(long convs14day) {
    this.convs14day = convs14day;
  }

  public long getConvs28day() {
    return convs28day;
  }

  public void setConvs28day(long convs28day) {
    this.convs28day = convs28day;
  }

  public long getConvs1month() {
    return convs1month;
  }

  public void setConvs1month(long convs1month) {
    this.convs1month = convs1month;
  }

  public long getConvs2month() {
    return convs2month;
  }

  public void setConvs2month(long convs2month) {
    this.convs2month = convs2month;
  }

  public long getConvs3month() {
    return convs3month;
  }

  public void setConvs3month(long convs3month) {
    this.convs3month = convs3month;
  }

  public long getConvs6month() {
    return convs6month;
  }

  public void setConvs6month(long convs6month) {
    this.convs6month = convs6month;
  }

  public double getConvs14to28Ratio() {
    return convs14to28Ratio;
  }

  public void setConvs14to28Ratio(double convs14to28Ratio) {
    this.convs14to28Ratio = convs14to28Ratio;
  }

  public double getConvs1mto2mRatio() {
    return convs1mto2mRatio;
  }

  public void setConvs1mto2mRatio(double convs1mto2mRatio) {
    this.convs1mto2mRatio = convs1mto2mRatio;
  }

  public double getConvs1mto3mRatio() {
    return convs1mto3mRatio;
  }

  public void setConvs1mto3mRatio(double convs1mto3mRatio) {
    this.convs1mto3mRatio = convs1mto3mRatio;
  }

  public double getConvs1mto6mRatio() {
    return convs1mto6mRatio;
  }

  public void setConvs1mto6mRatio(double convs1mto6mRatio) {
    this.convs1mto6mRatio = convs1mto6mRatio;
  }

  public long getCost14day() {
    return cost14day;
  }

  public void setCost14day(long cost14day) {
    this.cost14day = cost14day;
  }

  public long getCost28day() {
    return cost28day;
  }

  public void setCost28day(long cost28day) {
    this.cost28day = cost28day;
  }

  public long getCost1month() {
    return cost1month;
  }

  public void setCost1month(long cost1month) {
    this.cost1month = cost1month;
  }

  public long getCost1year() {
    return cost1year;
  }

  public void setCost1year(long cost1year) {
    this.cost1year = cost1year;
  }

  public long getCost2month() {
    return cost2month;
  }

  public void setCost2month(long cost2month) {
    this.cost2month = cost2month;
  }

  public long getCost3month() {
    return cost3month;
  }

  public void setCost3month(long cost3month) {
    this.cost3month = cost3month;
  }

  public long getCost6month() {
    return cost6month;
  }

  public void setCost6month(long cost6month) {
    this.cost6month = cost6month;
  }

  public double getCost14to28Ratio() {
    return cost14to28Ratio;
  }

  public void setCost14to28Ratio(double cost14to28Ratio) {
    this.cost14to28Ratio = cost14to28Ratio;
  }

  public double getCost1mto2mRatio() {
    return cost1mto2mRatio;
  }

  public void setCost1mto2mRatio(double cost1mto2mRatio) {
    this.cost1mto2mRatio = cost1mto2mRatio;
  }

  public double getCost1mto3mRatio() {
    return cost1mto3mRatio;
  }

  public void setCost1mto3mRatio(double cost1mto3mRatio) {
    this.cost1mto3mRatio = cost1mto3mRatio;
  }

  public double getCost1mto6mRatio() {
    return cost1mto6mRatio;
  }

  public void setCost1mto6mRatio(double cost1mto6mRatio) {
    this.cost1mto6mRatio = cost1mto6mRatio;
  }

  public double getCost1mto12mRatio() {
    return cost1mto12mRatio;
  }

  public void setCost1mto12mRatio(double cost1mto12mRatio) {
    this.cost1mto12mRatio = cost1mto12mRatio;
  }

  public long getConvsRate14day() {
    return convsRate14day;
  }

  public void setConvsRate14day(long convsRate14day) {
    this.convsRate14day = convsRate14day;
  }

  public long getConvsRate28day() {
    return convsRate28day;
  }

  public void setConvsRate28day(long convsRate28day) {
    this.convsRate28day = convsRate28day;
  }

  public double getConvsRate14to28Ratio() {
    return convsRate14to28Ratio;
  }

  public void setConvsRate14to28Ratio(double convsRate14to28Ratio) {
    this.convsRate14to28Ratio = convsRate14to28Ratio;
  }

  public long getCtr14day() {
    return ctr14day;
  }

  public void setCtr14day(long ctr14day) {
    this.ctr14day = ctr14day;
  }

  public long getCtr28day() {
    return ctr28day;
  }

  public void setCtr28day(long ctr28day) {
    this.ctr28day = ctr28day;
  }

  public long getCtr1month() {
    return ctr1month;
  }

  public void setCtr1month(long ctr1month) {
    this.ctr1month = ctr1month;
  }

  public long getCtr2month() {
    return ctr2month;
  }

  public void setCtr2month(long ctr2month) {
    this.ctr2month = ctr2month;
  }

  public long getCtr3month() {
    return ctr3month;
  }

  public void setCtr3month(long ctr3month) {
    this.ctr3month = ctr3month;
  }

  public long getCtr6month() {
    return ctr6month;
  }

  public void setCtr6month(long ctr6month) {
    this.ctr6month = ctr6month;
  }

  public double getCtr14to28Ratio() {
    return ctr14to28Ratio;
  }

  public void setCtr14to28Ratio(double ctr14to28Ratio) {
    this.ctr14to28Ratio = ctr14to28Ratio;
  }

  public double getCtr1mto2mRatio() {
    return ctr1mto2mRatio;
  }

  public void setCtr1mto2mRatio(double ctr1mto2mRatio) {
    this.ctr1mto2mRatio = ctr1mto2mRatio;
  }

  public double getCtr1mto3mRatio() {
    return ctr1mto3mRatio;
  }

  public void setCtr1mto3mRatio(double ctr1mto3mRatio) {
    this.ctr1mto3mRatio = ctr1mto3mRatio;
  }

  public double getCtr1mto6mRatio() {
    return ctr1mto6mRatio;
  }

  public void setCtr1mto6mRatio(double ctr1mto6mRatio) {
    this.ctr1mto6mRatio = ctr1mto6mRatio;
  }

  public long getCpc14day() {
    return cpc14day;
  }

  public void setCpc14day(long cpc14day) {
    this.cpc14day = cpc14day;
  }

  public long getCpc28day() {
    return cpc28day;
  }

  public void setCpc28day(long cpc28day) {
    this.cpc28day = cpc28day;
  }

  public long getCpc1month() {
    return cpc1month;
  }

  public void setCpc1month(long cpc1month) {
    this.cpc1month = cpc1month;
  }

  public long getCpc2month() {
    return cpc2month;
  }

  public void setCpc2month(long cpc2month) {
    this.cpc2month = cpc2month;
  }

  public long getCpc3month() {
    return cpc3month;
  }

  public void setCpc3month(long cpc3month) {
    this.cpc3month = cpc3month;
  }

  public long getCpc6month() {
    return cpc6month;
  }

  public void setCpc6month(long cpc6month) {
    this.cpc6month = cpc6month;
  }

  public double getCpc14to28Ratio() {
    return cpc14to28Ratio;
  }

  public void setCpc14to28Ratio(double cpc14to28Ratio) {
    this.cpc14to28Ratio = cpc14to28Ratio;
  }

  public double getCpc1mto2mRatio() {
    return cpc1mto2mRatio;
  }

  public void setCpc1mto2mRatio(double cpc1mto2mRatio) {
    this.cpc1mto2mRatio = cpc1mto2mRatio;
  }

  public double getCpc1mto3mRatio() {
    return cpc1mto3mRatio;
  }

  public void setCpc1mto3mRatio(double cpc1mto3mRatio) {
    this.cpc1mto3mRatio = cpc1mto3mRatio;
  }

  public double getCpc1mto6mRatio() {
    return cpc1mto6mRatio;
  }

  public void setCpc1mto6mRatio(double cpc1mto6mRatio) {
    this.cpc1mto6mRatio = cpc1mto6mRatio;
  }
}
