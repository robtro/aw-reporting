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

package com.google.api.ads.adwords.awreporting.util;

import java.util.Locale;
import javax.annotation.Nullable;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Utility class for generating or formatting values for presentation within exported reports
 * such as currencies, dates and other numeric values.
 */
public final class TemplateStringsUtil {

  private static final String DATE_FORMAT_FULL_MONTH_YEAR_TEXT = "MMMMM yyyy";
  private static final DateTimeFormatter dateFormatterFullMonthYear =
      DateTimeFormat.forPattern(DATE_FORMAT_FULL_MONTH_YEAR_TEXT).withLocale(Locale.US);

  protected static final String DATE_FORMAT_ABRIEVIATED_MONTH_YEAR_TEXT = "MMM yyyy";
  private static final DateTimeFormatter dateFormatterAbrieviatedMonthYear =
      DateTimeFormat.forPattern(DATE_FORMAT_ABRIEVIATED_MONTH_YEAR_TEXT).withLocale(Locale.US);

  private TemplateStringsUtil() {}

  /**
   * Convenience method to format a date range in monthly format (e.g. March 2015 - April 2016).
   * If start and end months are the same, just one month is returned (March 2015).
   *
   * @param startDate the date range start date as a {@code org.jodatime.LocalDate}. Now in the
   * case of null object.
   * @param endDate the date range end date as a {@code org.jodatime.LocalDate}. Now in the case of
   * null object.
   */
  public static String formatFullMonthDateRange(@Nullable LocalDate startDate,
      @Nullable LocalDate endDate) {
    String formattedStartDate = formatDateFullMonthYear(startDate);
    String formattedEndDate = formatDateFullMonthYear(endDate);
    String formattedDateRange = "";

    if (formattedStartDate.equals(formattedEndDate)) {
      formattedDateRange = formattedStartDate;
    } else {
      formattedDateRange = formattedStartDate + " - " + formattedEndDate;
    }

    return formattedDateRange;
  }

  /**
   * Formats the date to the format: MMMM yyyy (e.g. 'March 2015')
   *
   * @param date the date as a {@code org.jodatime.LocalDate}. Now in the case of null object.
   * @return the {@code String} representing the formatted date
   */
  public static String formatDateFullMonthYear(@Nullable LocalDate date) {
    return TemplateStringsUtil.dateFormatterFullMonthYear.print(date);
  }

  /**
   * Formats the date to the format: MMM yyyy (e.g. 'Mar 2015')
   *
   * @param date the date as a {@code org.jodatime.LocalDate}. Now in the case of null object.
   * @return the {@code String} representing the formatted date
   */
  public static String formatDateAbrieviatedMonthYear(@Nullable LocalDate date) {
    return TemplateStringsUtil.dateFormatterAbrieviatedMonthYear.print(date);
  }
}
