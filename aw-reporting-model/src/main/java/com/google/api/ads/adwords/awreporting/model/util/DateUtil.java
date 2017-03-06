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

package com.google.api.ads.adwords.awreporting.model.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * {@code DateUtil} is a utility class for parsing and handling the different types
 * of date formats in the AdWords API.
 */
public final class DateUtil {

  protected static final String DATE_FORMAT_REPORT = "yyyyMMdd";
  private static final DateTimeFormatter DATE_FORMATTER_YEAR_MONTH_DAY_NO_DASH =
      DateTimeFormat.forPattern(DATE_FORMAT_REPORT);

  private static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";
  private static final DateTimeFormatter DATE_FORMATTER_YEAR_MONTH_DAY =
      DateTimeFormat.forPattern(DATE_FORMAT_SHORT);

  private static final String DATE_FORMAT_SHORT_WITHOUTDAY = "yyyy-MM";
  private static final DateTimeFormatter DATE_FORMATTER_YEAR_MONTH =
      DateTimeFormat.forPattern(DATE_FORMAT_SHORT_WITHOUTDAY);

  private static final String DATE_FORMAT_SHORT_WITHOUTDAY_NODASH = "yyyyMM";
  private static final DateTimeFormatter DATE_FORMATTER_YEAR_MONTH_NO_DASH =
      DateTimeFormat.forPattern(DATE_FORMAT_SHORT_WITHOUTDAY_NODASH);

  private static final List<DateTimeFormatter> formatters = new ArrayList<DateTimeFormatter>();

  static {
    formatters.add(DATE_FORMATTER_YEAR_MONTH_DAY);
    formatters.add(DATE_FORMATTER_YEAR_MONTH);
    formatters.add(DATE_FORMATTER_YEAR_MONTH_DAY_NO_DASH);
    formatters.add(DATE_FORMATTER_YEAR_MONTH_NO_DASH);
  }

  /**
   * Private constructor.
   */
  private DateUtil() {}

  /**
   * Formats the date to the format: yyyyMMdd
   *
   * @param date the date as a {@code java.util.Date}
   * @return the {@code String} that represents the date as yyyyMMdd
   */
  public static String formatYearMonthDayNoDash(Date date) {
    return formatYearMonthDayNoDash(new LocalDate(date));
  }

  /**
   * Formats the date to the format: yyyyMMdd
   *
   * @param date the date as a {@code org.jodatime.LocalDate}
   * @return the {@code String} that represents the date as yyyyMMdd
   */
  public static String formatYearMonthDayNoDash(LocalDate date) {
    return DATE_FORMATTER_YEAR_MONTH_DAY_NO_DASH.print(date);
  }

  /**
   * Formats the date to the ISO format without the time zone information: yyyy-MM-dd
   *
   * @param date the date as a {@code java.util.Date}
   * @return the {@code String} that represents the date in ISO format
   */
  public static String formatYearMonthDay(Date date) {
    return formatYearMonthDay(new LocalDate(date));
  }

  /**
   * Formats the date to the ISO format without the time zone information: yyyy-MM-dd
   *
   * @param date the date as a {@code org.jodatime.LocalDate}
   * @return the {@code String} that represents the date in ISO format
   */
  public static String formatYearMonthDay(LocalDate date) {
    return DATE_FORMATTER_YEAR_MONTH_DAY.print(date);
  }

  /**
   * Formats the date to the ISO format without the time zone and day information: yyyy-MM
   *
   * @param date the date as a {@code java.util.Date}
   * @return the {@code String} that represents the date in ISO format
   */
  public static String formatYearMonth(Date date) {
    return DateUtil.formatYearMonth(new LocalDate(date));
  }

  /**
   * Formats the date to the ISO format without the time zone and day information: yyyy-MM
   *
   * @param date the date as a {@code org.jodatime.LocalDate}
   * @return the {@code String} that represents the date in ISO format
   */
  public static String formatYearMonth(LocalDate date) {
    return DATE_FORMATTER_YEAR_MONTH.print(date);
  }

  /**
   * Formats the date to the ISO format without the time zone and day information: yyyyMM
   *
   * @param date the date as a {@code java.util.Date}
   * @return the {@code String} that represents the date in ISO format
   */
  public static String formatYearMonthNoDash(Date date) {
    return formatYearMonthNoDash(new LocalDate(date));
  }

  /**
   * Formats the date to the ISO format without the time zone and day information: yyyyMM
   *
   * @param date the date as a {@code org.jodatime.LocalDate}
   * @return the {@code String} that represents the date in ISO format
   */
  public static String formatYearMonthNoDash(LocalDate date) {
    return DATE_FORMATTER_YEAR_MONTH_NO_DASH.print(date);
  }

  /**
   * Attempts to parse the given {@code String} to a {@code LocalDate} using one of the known
   * formatters.
   *
   * The attempt falls back to all the formatters, and if the format is unknown, {@code null} is
   * returned.
   *
   * @param timestamp the time stamp in {@code String} format.
   * @return the parsed {@code LocalDate}, or {@code null} in case that the format is unknown.
   */
  public static LocalDate parseLocalDate(String timestamp) {
    if (timestamp != null) {
      for (DateTimeFormatter formatter : DateUtil.formatters) {
        try {
          return formatter.parseLocalDate(timestamp);
        } catch (IllegalArgumentException e) {
          // Silently skips to the next formatter.
        }
      }
    }
    return null;
  }

  /**
   * Create a {@code LocalDate} that represents the last month, and formats it to the yyyy-MM 
   * format.
   *
   * @return the date formatted
   */
  public static String lastMonthInYearMonthFormat() {
    LocalDate lastMonth = new LocalDate().minusMonths(1);
    return DATE_FORMATTER_YEAR_MONTH.print(lastMonth);
  }

  /**
   * Get a LocalDate for the first day of the previous month.
   * @return LocalDate
   */
  public static LocalDate firstDayPreviousMonth() {
    return new LocalDate().minusMonths(1).dayOfMonth().withMinimumValue();
  }

  /**
   * Get a LocalDate for the last day of the previous month.
   * @return LocalDate
   */
  public static LocalDate lastDayPreviousMonth() {
    return new LocalDate().minusMonths(1).dayOfMonth().withMaximumValue();
  }

  /**
   * Get a LocalDate for the first day of a month.
   * @return LocalDate
   */
  public static LocalDate firstDayMonth(LocalDate localDate) {
    return localDate.dayOfMonth().withMinimumValue();
  }

  /**
   * Get a LocalDate for the last day of a month.
   * @return LocalDate
   */
  public static LocalDate lastDayMonth(LocalDate localDate) {
    return localDate.dayOfMonth().withMaximumValue();
  }
}
