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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Test case for the {@link DateUtil} class.
 */
@RunWith(Parameterized.class)
public class DateUtilTest {
  private final Calendar localCalendar = Calendar.getInstance();
  private final Calendar calendar;
  
  @Parameters(name = "{index}: calendar={0}")
  public static Iterable<Object[]> data() {
    return Arrays.asList(new Object[][] { 
      {Calendar.getInstance()},
      {Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"))},
      {Calendar.getInstance(TimeZone.getTimeZone("America/New_York"))},
      {Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"))},
      {Calendar.getInstance(TimeZone.getTimeZone("Europe/London"))},
      {Calendar.getInstance(TimeZone.getTimeZone("Europe/Zurich"))},
      {Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"))},
      {Calendar.getInstance(TimeZone.getTimeZone("Asia/Hong_Kong"))},
      {Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"))},
      {Calendar.getInstance(TimeZone.getTimeZone("Australia/Sydney"))}
    }
    );
  }

  public DateUtilTest (Calendar calendar){
    this.calendar = calendar;
  }
  
  /**
   * Tests the date format for the yyyy-MM-dd pattern.
   */
  @Test
  public void testYearMonthDay() {
    calendar.set(2011, Calendar.SEPTEMBER, 15, 0, 0);
    int offset =
        calendar.getTimeZone().getOffset(calendar.getTimeInMillis())
            - localCalendar.getTimeZone().getOffset(calendar.getTimeInMillis());

    Date date = new Date(calendar.getTimeInMillis() + offset);
    String formatted = DateUtil.formatYearMonthDay(date);
    assertEquals(
        "Error formatting yyyy-MM-dd for " + calendar.getTimeZone().getID(),
        "2011-09-15",
        formatted);

    calendar.set(2013, Calendar.JULY, 1, 0, 0);
    offset =
        calendar.getTimeZone().getOffset(calendar.getTimeInMillis())
            - localCalendar.getTimeZone().getOffset(calendar.getTimeInMillis());
    date.setTime(calendar.getTimeInMillis() + offset);
    formatted = DateUtil.formatYearMonthDay(date);
    assertEquals(
        "Error formatting yyyy-MM-dd for " + calendar.getTimeZone().getID(),
        "2013-07-01",
        formatted);

    LocalDate localDate = new LocalDate(2011, 9, 15);
    formatted = DateUtil.formatYearMonthDay(localDate);
    assertEquals(
        "Error formatting yyyy-MM-dd for " + calendar.getTimeZone().getID(),
        "2011-09-15",
        formatted);

    localDate = new LocalDate(2013, 1, 31);
    formatted = DateUtil.formatYearMonthDay(localDate);
    assertEquals(
        "Error formatting yyyy-MM-dd for " + calendar.getTimeZone().getID(),
        "2013-01-31",
        formatted);
  }

  /**
   * Tests the date format for the yyyyMMdd pattern.
   */
  @Test
  public void testYearMonthDayNoDash() {
    calendar.set(2011, Calendar.SEPTEMBER, 15, 0, 0);
    int offset =
        calendar.getTimeZone().getOffset(calendar.getTimeInMillis())
            - localCalendar.getTimeZone().getOffset(calendar.getTimeInMillis());

    Date date = new Date(calendar.getTimeInMillis() + offset);
    String formatted = DateUtil.formatYearMonthDayNoDash(date);
    assertEquals(
        "Error formatting yyyyMMdd for " + calendar.getTimeZone().getID(), "20110915", formatted);

    calendar.set(2013, Calendar.JULY, 1, 0, 0);
    offset =
        calendar.getTimeZone().getOffset(calendar.getTimeInMillis())
            - localCalendar.getTimeZone().getOffset(calendar.getTimeInMillis());
    date.setTime(calendar.getTimeInMillis() + offset);
    formatted = DateUtil.formatYearMonthDayNoDash(date);
    assertEquals(
        "Error formatting yyyyMMdd for " + calendar.getTimeZone().getID(), "20130701", formatted);

    LocalDate localDate = new LocalDate(2011, 9, 15);
    formatted = DateUtil.formatYearMonthDayNoDash(localDate);
    assertEquals(
        "Error formatting yyyyMMdd for " + calendar.getTimeZone().getID(), "20110915", formatted);

    localDate = new LocalDate(2013, 1, 31);
    formatted = DateUtil.formatYearMonthDayNoDash(localDate);
    assertEquals(
        "Error formatting yyyyMMdd for " + calendar.getTimeZone().getID(), "20130131", formatted);
  }

  /**
   * Tests the date format for the yyyy-MM pattern.
   */
  @Test
  public void testYearMonth() {
    calendar.set(2011, Calendar.SEPTEMBER, 15, 0, 0);
    int offset =
        calendar.getTimeZone().getOffset(calendar.getTimeInMillis())
            - localCalendar.getTimeZone().getOffset(calendar.getTimeInMillis());

    Date date = new Date(calendar.getTimeInMillis() + offset);
    String formatted = DateUtil.formatYearMonth(date);
    assertEquals(
        "Error formatting yyyy-MM for " + calendar.getTimeZone().getID(), "2011-09", formatted);

    calendar.set(2013, Calendar.JULY, 1, 0, 0);
    offset =
        calendar.getTimeZone().getOffset(calendar.getTimeInMillis())
            - localCalendar.getTimeZone().getOffset(calendar.getTimeInMillis());
    date.setTime(calendar.getTimeInMillis() + offset);
    formatted = DateUtil.formatYearMonth(date);
    assertEquals(
        "Error formatting yyyy-MM for " + calendar.getTimeZone().getID(), "2013-07", formatted);

    LocalDate localDate = new LocalDate(2011, 9, 15);
    formatted = DateUtil.formatYearMonth(localDate);
    assertEquals(
        "Error formatting yyyy-MM for " + calendar.getTimeZone().getID(), "2011-09", formatted);

    localDate = new LocalDate(2013, 1, 31);
    formatted = DateUtil.formatYearMonth(localDate);
    assertEquals(
        "Error formatting yyyy-MM for " + calendar.getTimeZone().getID(), "2013-01", formatted);
  }

  /**
   * Tests the parsing of a {@code String} in the format yyyy-MM-dd to a valid {@code LocalDate}.
   */
  @Test
  public void testParseYearMonthDay() {
    String toParse = "2010-02-22";
    LocalDate localDate = DateUtil.parseLocalDate(toParse);
    assertNotNull("Error parsing yyyy-MM-dd", localDate);

    assertEquals(2010, localDate.getYear());
    assertEquals(2, localDate.getMonthOfYear());
    assertEquals(22, localDate.getDayOfMonth());

    assertNull("Error parsing yyyy-MM-dd", DateUtil.parseLocalDate("invalid date"));
  }

  /**
   * Tests the parsing of a {@code String} in the format yyyy-MM to a valid {@code LocalDate}.
   */
  @Test
  public void testParseYearMonth() {
    String toParse = "2000-12";
    LocalDate localDate = DateUtil.parseLocalDate(toParse);
    assertNotNull("Error parsing yyyy-MM", localDate);

    assertEquals(2000, localDate.getYear());
    assertEquals(12, localDate.getMonthOfYear());
    assertEquals(1, localDate.getDayOfMonth());

    toParse = "2010-20";
    assertNull("Error parsing yyyy-MM", DateUtil.parseLocalDate(toParse));
  }

  /**
   * Tests the parsing of a {@code String} in the format yyyyMMdd to a valid {@code LocalDate}.
   */
  @Test
  public void testParseYearMonthDayNoDash() {
    String toParse = "20010101";
    LocalDate localDate = DateUtil.parseLocalDate(toParse);
    assertNotNull("Error parsing yyyyMMdd", localDate);

    assertEquals(2001, localDate.getYear());
    assertEquals(1, localDate.getMonthOfYear());
    assertEquals(1, localDate.getDayOfMonth());

    toParse = "20001032";
    assertNull("Error parsing yyyyMMdd", DateUtil.parseLocalDate(toParse));
  }
}
