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
import com.google.api.ads.adwords.awreporting.model.csv.annotation.MoneyField;
import com.google.api.ads.adwords.awreporting.model.entities.dateranges.DateRangeHandler;
import com.google.api.ads.adwords.awreporting.model.entities.dateranges.Last14DaysDateRangeHandler;
import com.google.api.ads.adwords.awreporting.model.entities.dateranges.Last30DaysDateRangeHandler;
import com.google.api.ads.adwords.awreporting.model.entities.dateranges.Last7DaysDateRangeHandler;
import com.google.api.ads.adwords.awreporting.model.entities.dateranges.LastMonthDateRangeHandler;
import com.google.api.ads.adwords.awreporting.model.entities.dateranges.LastWeekDateRangeHandler;
import com.google.api.ads.adwords.awreporting.model.entities.dateranges.ThisMonthDateRangeHandler;
import com.google.api.ads.adwords.awreporting.model.entities.dateranges.TodayDateRangeHandler;
import com.google.api.ads.adwords.awreporting.model.entities.dateranges.YesterdayDateRangeHandler;
import com.google.api.ads.adwords.awreporting.model.util.BigDecimalUtil;
import com.google.api.ads.adwords.awreporting.model.util.DateUtil;
import com.google.api.ads.adwords.lib.jaxb.v201609.ReportDefinitionDateRangeType;
import com.google.api.client.util.Maps;

import com.googlecode.objectify.annotation.Index;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * The base abstract class with base report fields
 *
 * Fields from http://code.google.com/apis/adwords/docs/appendix/reports.html Fields from
 * http://code.google.com/apis/adwords/docs/reportguide.html
 */
@MappedSuperclass
public abstract class ReportBase extends Report {

  public static final String DAYOFWEEK = "dayOfWeek";
  public static final String WEEK = "week";
  public static final String MONTH = "month";
  public static final String QUARTER = "quarter";
  public static final String YEAR = "year";

  // General
  @Column(name = "ACCOUNT_DESCRIPTIVE_NAME", length = 255)
  @CsvField(value = "Account", reportField = "AccountDescriptiveName")
  protected String accountDescriptiveName;

  @Column(name = "ACCOUNTTIMEZONEID")
  @CsvField(value = "Time zone", reportField = "AccountTimeZoneId")
  protected String accountTimeZoneId;

  @Column(name = "CUSTOMER_DESCRIPTIVE_NAME")
  @CsvField(value = "Client name", reportField = "CustomerDescriptiveName")
  protected String customerDescriptiveName;

  @Column(name = "PRIMARYCOMPANYNAME")
  @CsvField(value = "Company name", reportField = "PrimaryCompanyName")
  protected String primaryCompanyName;

  @Column(name = "CURRENCY_CODE", length = 6)
  @CsvField(value = "Currency", reportField = "AccountCurrencyCode")
  protected String currencyCode;

  // Date Segments
  @Index
  @Column(name = "DAY")
  @CsvField(value = "Day", reportField = "Date")
  protected Date day;

  @Column(name = "DAYOFWEEK")
  @CsvField(value = "Day of week", reportField = "DayOfWeek")
  protected String dayOfWeek;

  @Column(name = "WEEK")
  @CsvField(value = "Week", reportField = "Week")
  protected String week;

  @Index
  @Column(name = "MONTH")
  @CsvField(value = "Month", reportField = "Month")
  protected Date month;

  @Column(name = "MONTH_OF_YEAR")
  @CsvField(value = "Month of Year", reportField = "MonthOfYear")
  protected String monthOfYear;

  @Column(name = "QUARTER")
  @CsvField(value = "Quarter", reportField = "Quarter")
  private String quarter;

  @Column(name = "YEAR")
  @CsvField(value = "Year", reportField = "Year")
  protected Long year;

  // Main Metrics
  @Column(name = "COST")
  @CsvField(value = "Cost", reportField = "Cost")
  @MoneyField
  protected BigDecimal cost;

  @Column(name = "CLICKS")
  @CsvField(value = "Clicks", reportField = "Clicks")
  protected Long clicks;

  @Column(name = "IMPRESSIONS")
  @CsvField(value = "Impressions", reportField = "Impressions")
  protected Long impressions;

  @Column(name = "CTR")
  @CsvField(value = "CTR", reportField = "Ctr")
  protected BigDecimal ctr;

  @Column(name = "AVERAGE_CPM")
  @CsvField(value = "Avg. CPM", reportField = "AverageCpm")
  @MoneyField
  protected BigDecimal avgCpm;

  @Column(name = "AVERAGE_CPC")
  @CsvField(value = "Avg. CPC", reportField = "AverageCpc")
  @MoneyField
  protected BigDecimal avgCpc;
  
  @Column(name = "AVERAGE_COST")
  @CsvField(value = "Avg. Cost", reportField = "AverageCost")
  @MoneyField
  protected BigDecimal avgCost;
  
  @Column(name = "AVERAGE_CPE")
  @CsvField(value = "Avg. CPE", reportField = "AverageCpe")
  protected BigDecimal avgCpe;
  
  @Column(name = "AVERAGE_CPV")
  @CsvField(value = "Avg. CPV", reportField = "AverageCpv")
  protected BigDecimal avgCpv;

  @Column(name = "AVERAGE_POSITION")
  @CsvField(value = "Avg. position", reportField = "AveragePosition")
  protected BigDecimal avgPosition;

  // Main Segments
  @Column(name = "DEVICE", length = 64)
  @CsvField(value = "Device", reportField = "Device")
  protected String device;

  @Column(name = "CLICK_TYPE", length = 64)
  @CsvField(value = "Click type", reportField = "ClickType")
  protected String clickType;

  @Column(name = "NETWORK", length = 32)
  @CsvField(value = "Network", reportField = "AdNetworkType1")
  protected String adNetwork;

  @Column(name = "NETWORK_PARTNERS", length = 32)
  @CsvField(value = "Network (with search partners)", reportField = "AdNetworkType2")
  protected String adNetworkPartners;

  // Conversion Columns
  @Column(name = "CONVERSIONS")
  @CsvField(value = "Conversions", reportField = "Conversions")
  protected BigDecimal conversions;
  
  @Column(name = "CONVERSION_RATE")
  @CsvField(value = "Conv. rate", reportField = "ConversionRate")
  protected BigDecimal conversionRate;
  
  @Column(name = "CONVERSION_VALUE")
  @CsvField(value = "Total conv. value", reportField = "ConversionValue")
  protected BigDecimal conversionValue;
  
  @Column(name = "COST_PER_CONVERSION")
  @CsvField(value = "Cost / conv.", reportField = "CostPerConversion")
  @MoneyField
  protected BigDecimal costPerConversion;
  
  @Column(name = "VALUE_PER_CONVERSION")
  @CsvField(value = "Value / conv.", reportField = "ValuePerConversion")
  protected BigDecimal valuePerConversion;
  
  @Column(name = "ALL_CONVERSIONS")
  @CsvField(value = "All conv.", reportField = "AllConversions")
  protected BigDecimal allConversions;
  
  @Column(name = "ALL_CONVERSION_RATE")
  @CsvField(value = "All conv. rate", reportField = "AllConversionRate")
  protected BigDecimal allConversionRate;
  
  @Column(name = "ALL_CONVERSION_VALUE")
  @CsvField(value = "All conv. value", reportField = "AllConversionValue")
  protected BigDecimal allConversionValue;
  
  @Column(name = "COST_PER_ALL_CONVERSION")
  @CsvField(value = "Cost / all conv.", reportField = "CostPerAllConversion")
  @MoneyField
  protected BigDecimal costPerAllConversion;
  
  @Column(name = "VALUE_PER_ALL_CONVERSION")
  @CsvField(value = "Value / all conv.", reportField = "ValuePerAllConversion")
  protected BigDecimal valuePerAllConversion;
  
  @Column(name = "CONVERSIONCATEGORYNAME")
  @CsvField(value = "Conversion category", reportField = "ConversionCategoryName")
  protected String conversionCategoryName;

  @Column(name = "CONVERSIONTYPENAME")
  @CsvField(value = "Conversion name", reportField = "ConversionTypeName")
  protected String conversionTypeName;

  @Column(name = "VIEWTHROUGHCONVERSIONS")
  @CsvField(value = "View-through conv.", reportField = "ViewThroughConversions")
  protected Long viewThroughConversions = 0L;
  
  @Column(name = "ENGAGEMENTS")
  @CsvField(value = "Engagements", reportField = "Engagements")
  protected Long engagements;
  
  @Column(name = "ENGAGEMENT_RATE")
  @CsvField(value = "Engagement rate", reportField = "EngagementRate")
  protected BigDecimal engagementRate;
  
  @Column(name = "INTERACTIONS")
  @CsvField(value = "Interactions", reportField = "Interactions")
  protected Long interactions;
  
  @Column(name = "INTERACTION_RATE")
  @CsvField(value = "Interaction Rate", reportField = "InteractionRate")
  protected BigDecimal interactionRate;
  
  @Column(name = "VIDEO_VIEWS")
  @CsvField(value = "Views", reportField = "VideoViews")
  protected Long videoViews;
  
  @Column(name = "VIDEO_VIEW_RATE")
  @CsvField(value = "View rate", reportField = "VideoViewRate")
  protected BigDecimal videoViewRate;

  private static final Map<String, DateRangeHandler> dateRangeHandlers = Maps.newHashMap();

  static {
    dateRangeHandlers.put(ReportDefinitionDateRangeType.LAST_14_DAYS.name(),
        new Last14DaysDateRangeHandler());
    dateRangeHandlers.put(ReportDefinitionDateRangeType.LAST_30_DAYS.name(),
        new Last30DaysDateRangeHandler());
    dateRangeHandlers.put(ReportDefinitionDateRangeType.LAST_7_DAYS.name(),
        new Last7DaysDateRangeHandler());
    dateRangeHandlers.put(ReportDefinitionDateRangeType.LAST_MONTH.name(),
        new LastMonthDateRangeHandler());
    dateRangeHandlers.put(ReportDefinitionDateRangeType.LAST_WEEK.name(),
        new LastWeekDateRangeHandler());
    dateRangeHandlers.put(ReportDefinitionDateRangeType.THIS_MONTH.name(),
        new ThisMonthDateRangeHandler());
    dateRangeHandlers.put(ReportDefinitionDateRangeType.TODAY.name(), new TodayDateRangeHandler());
    dateRangeHandlers.put(ReportDefinitionDateRangeType.YESTERDAY.name(),
        new YesterdayDateRangeHandler());
  }

  /**
   * Hibernate needs an empty constructor
   */
  public ReportBase() {
    timestamp = new DateTime().toDate();
  }

  public ReportBase(Long topAccountId, Long accountId) {
    this.topAccountId = topAccountId;
    this.accountId = accountId;
    timestamp = new DateTime().toDate();
  }

  @Override
  public abstract void setId();

  @Override
  public String setIdDates() {
    // Time Segments
    if (this.getDay() != null) {
      return "-" + this.getDay();
    }
    
    String id = "";
    // DayOfWeek may overlap with Week / Month / Quarter / Year
    if (this.getDayOfWeek() != null) {
      id += "-" + this.getDayOfWeek();
    }
    // Week may partially overlap with Month / Quarter / Year.
    if (this.getWeek() != null) {
      id += "-" + this.getWeek();
    }
    if (this.getMonthOfYear() != null) {
      id += "-" + this.getMonthOfYear();
    } else if (this.getMonth() != null) {
      id += "-" + DateUtil.formatYearMonth(this.getMonthDateTime());
    } else if (this.getQuarter() != null) {
      id += "-" + this.getQuarter();
    } else if (this.getYear() != null) {
      id += "-" + this.getYear();
    }
    if (!id.isEmpty()) {
      return id;
    }

    if (this.getDateRangeType() != null) {
      DateRangeHandler handler = dateRangeHandlers.get(this.getDateRangeType());
      if (handler != null) {
        this.setMonth(handler.retrieveMonth(DateTime.now()));
        this.setDateStart(DateUtil.formatYearMonthDay(handler.retrieveDateStart(DateTime.now())));
        this.setDateEnd(DateUtil.formatYearMonthDay(handler.retrieveDateEnd(DateTime.now())));
      }
    }
    if (this.getDateStart() != null && this.getDateEnd() != null) {
      return "-" + this.getDateStart() + "-" + this.getDateEnd();
    }
    return "";
  }

  public String getDay() {
    if (day != null) {
      return DateUtil.formatYearMonthDay(day);
    } else {
      return null;
    }
  }

  public void setDay(DateTime day) {
    this.day = new DateTime(day).toDate();
  }

  public void setDay(String day) {
    try {
      DateTime parseDateTime = DateUtil.parseDateTime(day);
      if (parseDateTime != null) {
        this.day = parseDateTime.toDate();
      }
    } catch (IllegalArgumentException e) {
      this.day = null;
    }
  }

  public String getMonth() {
    if (month != null) {
      return DateUtil.formatYearMonthDay(month);
    } else {
      return null;
    }
  }

  public DateTime getMonthDateTime() {
    return new DateTime(month);
  }

  public void setMonth(DateTime month) {
    this.month = new DateTime(month).toDate();
  }

  public void setMonth(String month) {
    try {
      DateTime parseDateTime = DateUtil.parseDateTime(month);
      if (parseDateTime != null) {
        this.month = parseDateTime.toDate();
      }
    } catch (IllegalArgumentException e) {
      this.month = null;
    }
  }

  public String getMonthOfYear() {
    return monthOfYear;
  }

  public void setMonthOfYear(String monthOfYear) {
    this.monthOfYear = monthOfYear;
  }

  public String getDayOfWeek() {
    return dayOfWeek;
  }

  public void setDayOfWeek(String dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  public String getWeek() {
    return week;
  }

  public void setWeek(String week) {
    this.week = week;
  }

  public String getQuarter() {
    return quarter;
  }

  public void setQuarter(String quarter) {
    this.quarter = quarter;
  }

  public Long getYear() {
    return year;
  }

  public void setYear(Long year) {
    this.year = year;
  }

  public void setCtr(BigDecimal ctr) {
    this.ctr = ctr;
  }

  public void setAvgPosition(BigDecimal avgPosition) {
    this.avgPosition = avgPosition;
  }

  public String getAccountDescriptiveName() {
    return accountDescriptiveName;
  }

  public void setAccountDescriptiveName(String accountDescriptiveName) {
    this.accountDescriptiveName = accountDescriptiveName;
  }

  public String getAccountTimeZoneId() {
    return accountTimeZoneId;
  }

  public void setAccountTimeZoneId(String accountTimeZoneId) {
    this.accountTimeZoneId = accountTimeZoneId;
  }

  public String getPrimaryCompanyName() {
    return primaryCompanyName;
  }

  public void setPrimaryCompanyName(String primaryCompanyName) {
    this.primaryCompanyName = primaryCompanyName;
  }

  public String getCustomerDescriptiveName() {
    return customerDescriptiveName;
  }

  public void setCustomerDescriptiveName(String customerDescriptiveName) {
    this.customerDescriptiveName = customerDescriptiveName;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  public Long getClicks() {
    return clicks;
  }

  public void setClicks(Long clicks) {
    this.clicks = clicks;
  }

  public Long getImpressions() {
    return impressions;
  }

  public void setImpressions(Long impressions) {
    this.impressions = impressions;
  }

  public String getCtr() {
    return BigDecimalUtil.formatAsReadable(ctr);
  }

  public BigDecimal getCtrBigDecimal() {
    return ctr;
  }

  public void setCtr(String ctr) {
    this.ctr = BigDecimalUtil.parseFromNumberString(ctr);
  }

  public BigDecimal getAvgCpm() {
    return avgCpm;
  }
  
  public void setAvgCpm(BigDecimal avgCpm) {
    this.avgCpm = avgCpm;
  }

  public BigDecimal getAvgCpc() {
    return avgCpc;
  }
  
  public void setAvgCpc(BigDecimal avgCpc) {
    this.avgCpc = avgCpc;
  }
  
  public BigDecimal getAvgCost() {
    return avgCost;
  }
  
  public void setAvgCost(BigDecimal avgCost) {
    this.avgCost = avgCost;
  }
  
  public String getAvgCpe() {
    return BigDecimalUtil.formatAsReadable(avgCpe);
  }
  
  public BigDecimal getAvgCpeBigDecimal() {
    return avgCpe;
  }
  
  public void setAvgCpe(String avgCpe) {
    this.avgCpe = BigDecimalUtil.parseFromNumberString(avgCpe);
  }
  
  public String getAvgCpv() {
    return BigDecimalUtil.formatAsReadable(avgCpv);
  }
  
  public BigDecimal getAvgCpvBigDecimal() {
    return avgCpv;
  }
  
  public void setAvgCpv(String avgCpv) {
    this.avgCpv = BigDecimalUtil.parseFromNumberString(avgCpv);
  }

  public String getAvgPosition() {
    return BigDecimalUtil.formatAsReadable(avgPosition);
  }

  public BigDecimal getAvgPositionBigDecimal() {
    return avgPosition;
  }

  public void setAvgPosition(String avgPosition) {
    this.avgPosition = BigDecimalUtil.parseFromNumberString(avgPosition);
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String getDevice() {
    return device;
  }

  public void setDevice(String device) {
    this.device = device;
  }

  public String getClickType() {
    return clickType;
  }

  public void setClickType(String clickType) {
    this.clickType = clickType;
  }

  public String getAdNetwork() {
    return adNetwork;
  }

  public void setAdNetwork(String adNetwork) {
    this.adNetwork = adNetwork;
  }

  public String getAdNetworkPartners() {
    return adNetworkPartners;
  }

  public void setAdNetworkPartners(String adNetworkPartners) {
    this.adNetworkPartners = adNetworkPartners;
  }

  // Conversion Columns
  public String getConversions() {
    return BigDecimalUtil.formatAsReadable(conversions);
  }
  
  public BigDecimal getConversionsBigDecimal() {
    return conversions;
  }
  
  public void setConversions(String conversions) {
    this.conversions = BigDecimalUtil.parseFromNumberString(conversions);
  }
  
  public String getConversionRate() {
    return BigDecimalUtil.formatAsReadable(conversionRate);
  }
  
  public BigDecimal getConversionRateBigDecimal() {
    return conversionRate;
  }
  
  public void setConversionRate(String conversionRate) {
    this.conversionRate = BigDecimalUtil.parseFromNumberString(conversionRate);
  }
  
  public String getConversionValue() {
    return BigDecimalUtil.formatAsReadable(conversionValue);
  }
  
  public BigDecimal getConversionValueBigDecimal() {
    return conversionValue;
  }
  
  public void setConversionValue(String conversionValue) {
    this.conversionValue = BigDecimalUtil.parseFromNumberString(conversionValue);
  }
  
  public BigDecimal getCostPerConversion() {
    return costPerConversion;
  }
  
  public void setCostPerConversion(BigDecimal costPerConversion) {
    this.costPerConversion = costPerConversion;
  }
  
  public String getValuePerConversion() {
    return BigDecimalUtil.formatAsReadable(valuePerConversion);
  }
  
  public BigDecimal getValuePerConversionBigDecimal() {
    return valuePerConversion;
  }
  
  public void setValuePerConversion(String valuePerConversion) {
    this.valuePerConversion = BigDecimalUtil.parseFromNumberString(valuePerConversion);
  }
  
  public String getAllConversions() {
    return BigDecimalUtil.formatAsReadable(allConversions);
  }
  
  public BigDecimal getAllConversionsBigDecimal() {
    return allConversions;
  }
  
  public void setAllConversions(String allConversions) {
    this.allConversions = BigDecimalUtil.parseFromNumberString(allConversions);
  }
  
  public String getAllConversionRate() {
    return BigDecimalUtil.formatAsReadable(allConversionRate);
  }
  
  public BigDecimal getAllConversionRateBigDecimal() {
    return allConversionRate;
  }
  
  public void setAllConversionRate(String allConversionRate) {
    this.allConversionRate = BigDecimalUtil.parseFromNumberString(allConversionRate);
  }
  
  public String getAllConversionValue() {
    return BigDecimalUtil.formatAsReadable(allConversionValue);
  }
  
  public BigDecimal getAllConversionValueBigDecimal() {
    return allConversionValue;
  }
  
  public void setAllConversionValue(String allConversionValue) {
    this.allConversionValue = BigDecimalUtil.parseFromNumberString(allConversionValue);
  }
  
  public BigDecimal getCostPerAllConversion() {
    return costPerAllConversion;
  }
  
  public void setCostPerAllConversion(BigDecimal costPerAllConversion) {
    this.costPerAllConversion = costPerAllConversion;
  }
  
  public String getValuePerAllConversion() {
    return BigDecimalUtil.formatAsReadable(valuePerAllConversion);
  }
  
  public BigDecimal getValuePerAllConversionBigDecimal() {
    return valuePerAllConversion;
  }
  
  public void setValuePerAllConversion(String valuePerAllConversion) {
    this.valuePerAllConversion = BigDecimalUtil.parseFromNumberString(valuePerAllConversion);
  }

  public String getConversionCategoryName() {
    return conversionCategoryName;
  }

  public void setConversionCategoryName(String conversionCategoryName) {
    this.conversionCategoryName = conversionCategoryName;
  }

  public String getConversionTypeName() {
    return conversionTypeName;
  }

  public void setConversionTypeName(String conversionTypeName) {
    this.conversionTypeName = conversionTypeName;
  }

  public Long getViewThroughConversions() {
    return viewThroughConversions;
  }

  public void setViewThroughConversions(Long viewThroughConversions) {
    this.viewThroughConversions = viewThroughConversions;
  }
  
  public Long getEngagements() {
    return engagements;
  }
  
  public void setEngagements(Long engagements) {
    this.engagements = engagements;
  }
  
  public String getEngagementRate()
  {
    return BigDecimalUtil.formatAsReadable(engagementRate);
  }
  
  public BigDecimal getEngagementRateBigDecimal()
  {
    return engagementRate;
  }
  
  public void setEngagementRate(String engagementRate) {
    this.engagementRate = BigDecimalUtil.parseFromNumberString(engagementRate);
  }
  
  public Long getInteractions() {
    return interactions;
  }
  
  public void setInteractions(Long interactions) {
    this.interactions = interactions;
  }
  
  public String getInteractionRate() {
    return BigDecimalUtil.formatAsReadable(interactionRate);
  }
  
  public BigDecimal getInteractionRateBigDecimal() {
    return interactionRate;
  }
  
  public void setInteractionRate(String interactionRate) {
    this.interactionRate = BigDecimalUtil.parseFromNumberString(interactionRate);
  }
  
  public Long getVideoViews() {
    return videoViews;
  }
  
  public void setVideoViews(Long videoViews) {
    this.videoViews = videoViews;
  }
  
  public String getVideoViewRate() {
    return BigDecimalUtil.formatAsReadable(videoViewRate);
  }
  
  public BigDecimal getVideoViewRateBigDecimal() {
    return videoViewRate;
  }
  
  public void setVideoViewRate(String videoViewRate) {
    this.videoViewRate = BigDecimalUtil.parseFromNumberString(videoViewRate);
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result
        + ((accountDescriptiveName == null) ? 0 : accountDescriptiveName.hashCode());
    result = prime * result + ((accountTimeZoneId == null) ? 0 : accountTimeZoneId.hashCode());
    result = prime * result + ((adNetwork == null) ? 0 : adNetwork.hashCode());
    result = prime * result + ((adNetworkPartners == null) ? 0 : adNetworkPartners.hashCode());
    result = prime * result + ((avgCpc == null) ? 0 : avgCpc.hashCode());
    result = prime * result + ((avgCpm == null) ? 0 : avgCpm.hashCode());
    result = prime * result + ((avgCost == null) ? 0 : avgCost.hashCode());
    result = prime * result + ((avgCpe == null) ? 0 : avgCpe.hashCode());
    result = prime * result + ((avgCpv == null) ? 0 : avgCpv.hashCode());
    result = prime * result + ((avgPosition == null) ? 0 : avgPosition.hashCode());
    result = prime * result + ((clickType == null) ? 0 : clickType.hashCode());
    result = prime * result + ((clicks == null) ? 0 : clicks.hashCode());
    result = prime * result
        + ((conversionCategoryName == null) ? 0 : conversionCategoryName.hashCode());
    result = prime * result + ((conversionTypeName == null) ? 0 : conversionTypeName.hashCode());
    result = prime * result + ((cost == null) ? 0 : cost.hashCode());
    result = prime * result + ((ctr == null) ? 0 : ctr.hashCode());
    result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
    result = prime * result
        + ((customerDescriptiveName == null) ? 0 : customerDescriptiveName.hashCode());
    result = prime * result + ((day == null) ? 0 : day.hashCode());
    result = prime * result + ((dayOfWeek == null) ? 0 : dayOfWeek.hashCode());
    result = prime * result + ((device == null) ? 0 : device.hashCode());
    result = prime * result + ((impressions == null) ? 0 : impressions.hashCode());
    result = prime * result + ((month == null) ? 0 : month.hashCode());
    result = prime * result + ((monthOfYear == null) ? 0 : monthOfYear.hashCode());
    result = prime * result + ((primaryCompanyName == null) ? 0 : primaryCompanyName.hashCode());
    result = prime * result + ((quarter == null) ? 0 : quarter.hashCode());
    result = prime * result
        + ((viewThroughConversions == null) ? 0 : viewThroughConversions.hashCode());
    result = prime * result + ((week == null) ? 0 : week.hashCode());
    result = prime * result + ((year == null) ? 0 : year.hashCode());
    result = prime * result + ((engagements == null) ? 0 : engagements.hashCode());
    result = prime * result + ((engagementRate == null) ? 0 : engagementRate.hashCode());
    result = prime * result + ((interactions == null) ? 0 : interactions.hashCode());
    result = prime * result + ((interactionRate == null) ? 0 : interactionRate.hashCode());
    result = prime * result + ((videoViews == null) ? 0 : videoViews.hashCode());
    result = prime * result + ((videoViewRate == null) ? 0 : videoViewRate.hashCode());
    result = prime * result + ((conversions == null) ? 0 : conversions.hashCode());
    result = prime * result + ((conversionRate == null) ? 0 : conversionRate.hashCode());
    result = prime * result + ((conversionValue == null) ? 0 : conversionValue.hashCode());
    result = prime * result + ((costPerConversion == null) ? 0 : costPerConversion.hashCode());
    result = prime * result + ((valuePerConversion == null) ? 0 : valuePerConversion.hashCode());
    result = prime * result + ((allConversions == null) ? 0 : allConversions.hashCode());
    result = prime * result + ((allConversionRate == null) ? 0 : allConversionRate.hashCode());
    result = prime * result + ((allConversionValue == null) ? 0 : allConversionValue.hashCode());
    result = prime * result + ((costPerAllConversion == null) ? 0 : costPerAllConversion.hashCode());
    result = prime * result + ((valuePerAllConversion == null) ? 0 : valuePerAllConversion.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    ReportBase other = (ReportBase) obj;
    if (accountDescriptiveName == null) {
      if (other.accountDescriptiveName != null)
        return false;
    } else if (!accountDescriptiveName.equals(other.accountDescriptiveName))
      return false;
    if (accountTimeZoneId == null) {
      if (other.accountTimeZoneId != null)
        return false;
    } else if (!accountTimeZoneId.equals(other.accountTimeZoneId))
      return false;
    if (adNetwork == null) {
      if (other.adNetwork != null)
        return false;
    } else if (!adNetwork.equals(other.adNetwork))
      return false;
    if (adNetworkPartners == null) {
      if (other.adNetworkPartners != null)
        return false;
    } else if (!adNetworkPartners.equals(other.adNetworkPartners))
      return false;
    if (avgCpc == null) {
      if (other.avgCpc != null)
        return false;
    } else if (!avgCpc.equals(other.avgCpc))
      return false;
    if (avgCpm == null) {
      if (other.avgCpm != null)
        return false;
    } else if (!avgCpm.equals(other.avgCpm))
      return false;
    if (avgCost == null) {
      if (other.avgCost != null)
        return false;
    } else if (!avgCost.equals(other.avgCost))
      return false;
    if (avgCpe == null) {
      if (other.avgCpe != null)
        return false;
    } else if (!avgCpe.equals(other.avgCpe))
      return false;
    if (avgCpv == null) {
      if (other.avgCpv != null)
        return false;
    } else if (!avgCpv.equals(other.avgCpv))
      return false;
    if (avgPosition == null) {
      if (other.avgPosition != null)
        return false;
    } else if (!avgPosition.equals(other.avgPosition))
      return false;
    if (clickType == null) {
      if (other.clickType != null)
        return false;
    } else if (!clickType.equals(other.clickType))
      return false;
    if (clicks == null) {
      if (other.clicks != null)
        return false;
    } else if (!clicks.equals(other.clicks))
      return false;
    if (conversionCategoryName == null) {
      if (other.conversionCategoryName != null)
        return false;
    } else if (!conversionCategoryName.equals(other.conversionCategoryName))
      return false;
    if (conversionTypeName == null) {
      if (other.conversionTypeName != null)
        return false;
    } else if (!conversionTypeName.equals(other.conversionTypeName))
      return false;
    if (cost == null) {
      if (other.cost != null)
        return false;
    } else if (!cost.equals(other.cost))
      return false;
    if (ctr == null) {
      if (other.ctr != null)
        return false;
    } else if (!ctr.equals(other.ctr))
      return false;
    if (currencyCode == null) {
      if (other.currencyCode != null)
        return false;
    } else if (!currencyCode.equals(other.currencyCode))
      return false;
    if (customerDescriptiveName == null) {
      if (other.customerDescriptiveName != null)
        return false;
    } else if (!customerDescriptiveName.equals(other.customerDescriptiveName))
      return false;
    if (day == null) {
      if (other.day != null)
        return false;
    } else if (!day.equals(other.day))
      return false;
    if (dayOfWeek == null) {
      if (other.dayOfWeek != null)
        return false;
    } else if (!dayOfWeek.equals(other.dayOfWeek))
      return false;
    if (device == null) {
      if (other.device != null)
        return false;
    } else if (!device.equals(other.device))
      return false;
    if (impressions == null) {
      if (other.impressions != null)
        return false;
    } else if (!impressions.equals(other.impressions))
      return false;
    if (month == null) {
      if (other.month != null)
        return false;
    } else if (!month.equals(other.month))
      return false;
    if (monthOfYear == null) {
      if (other.monthOfYear != null)
        return false;
    } else if (!monthOfYear.equals(other.monthOfYear))
      return false;
    if (primaryCompanyName == null) {
      if (other.primaryCompanyName != null)
        return false;
    } else if (!primaryCompanyName.equals(other.primaryCompanyName))
      return false;
    if (quarter == null) {
      if (other.quarter != null)
        return false;
    } else if (!quarter.equals(other.quarter))
      return false;
    if (viewThroughConversions == null) {
      if (other.viewThroughConversions != null)
        return false;
    } else if (!viewThroughConversions.equals(other.viewThroughConversions))
      return false;
    if (week == null) {
      if (other.week != null)
        return false;
    } else if (!week.equals(other.week))
      return false;
    if (year == null) {
      if (other.year != null)
        return false;
    } else if (!year.equals(other.year))
      return false;
    if (engagements == null) {
      if (other.engagements != null)
        return false;
    } else if (!engagements.equals(other.engagements))
      return false;
    if (engagementRate == null) {
      if (other.engagementRate != null)
        return false;
    } else if (!engagementRate.equals(other.engagementRate))
      return false;
    if (interactions == null) {
      if (other.interactions != null)
        return false;
    } else if (!interactions.equals(other.interactions))
      return false;
    if (interactionRate == null) {
      if (other.interactionRate != null)
        return false;
    } else if (!interactionRate.equals(other.interactionRate))
      return false;
    if (videoViews == null) {
      if (other.videoViews != null)
        return false;
    } else if (!videoViews.equals(other.videoViews))
      return false;
    if (videoViewRate == null) {
      if (other.videoViewRate != null)
        return false;
    } else if (!videoViewRate.equals(other.videoViewRate))
      return false;
    if (conversions == null) {
      if (other.conversions != null)
        return false;
    } else if (!conversions.equals(other.conversions))
      return false;
    if (conversionRate == null) {
      if (other.conversionRate != null)
        return false;
    } else if (!conversionRate.equals(other.conversionRate))
      return false;
    if (conversionValue == null) {
      if (other.conversionValue != null)
        return false;
    } else if (!conversionValue.equals(other.conversionValue))
      return false;
    if (costPerConversion == null) {
      if (other.costPerConversion != null)
        return false;
    } else if (!costPerConversion.equals(other.costPerConversion))
      return false;
    if (valuePerConversion == null) {
      if (other.valuePerConversion != null)
        return false;
    } else if (!valuePerConversion.equals(other.valuePerConversion))
      return false;
    if (allConversions == null) {
      if (other.allConversions != null)
        return false;
    } else if (!allConversions.equals(other.allConversions))
      return false;
    if (allConversionRate == null) {
      if (other.allConversionRate != null)
        return false;
    } else if (!allConversionRate.equals(other.allConversionRate))
      return false;
    if (allConversionValue == null) {
      if (other.allConversionValue != null)
        return false;
    } else if (!allConversionValue.equals(other.allConversionValue))
      return false;
    if (costPerAllConversion == null) {
      if (other.costPerAllConversion != null)
        return false;
    } else if (!costPerAllConversion.equals(other.costPerAllConversion))
      return false;
    if (valuePerAllConversion == null) {
      if (other.valuePerAllConversion != null)
        return false;
    } else if (!valuePerAllConversion.equals(other.valuePerAllConversion))
      return false;
    return true;
  }
}
