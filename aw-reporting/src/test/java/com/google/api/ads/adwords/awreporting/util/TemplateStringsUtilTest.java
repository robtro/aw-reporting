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

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test case for the {@link TemplateStringsUtil} class.
 */
@RunWith(JUnit4.class)
public class TemplateStringsUtilTest {

  /**
   * Tests formatting date ranges e.g. 'March 2014 - April 2014'
   */
  @Test
  public void testFormatFullMonthDateRange() {
    LocalDate startDate = new LocalDate(2014, 3, 1);
    LocalDate endDate = new LocalDate(2014, 4, 30);
    String dateRange = 
        TemplateStringsUtil.formatFullMonthDateRange(startDate, endDate);
    assertEquals("March 2014 - April 2014", dateRange);
    
    endDate = new LocalDate(2014, 3, 10);
    dateRange = TemplateStringsUtil
        .formatFullMonthDateRange(startDate, endDate);
    assertEquals("March 2014", dateRange);
  }

  /**
   * Tests formatting date ranges to 'MMMM yyyy' e.g. 'March 2014'
   */
  @Test
  public void testFormatDateFullMonthYear() {
    LocalDate date = new LocalDate(2014, 3, 8);
    String formatted = TemplateStringsUtil.formatDateFullMonthYear(date);
    assertEquals("March 2014", formatted);
  }

  /**
   * Tests formatting date ranges to 'MMM yyyy' e.g. 'Sep 2015'
   */
  @Test
  public void testFormatDateAbrieviatedMonthYear() {
    LocalDate date = new LocalDate(2015, 9, 21);
    String formatted = 
        TemplateStringsUtil.formatDateAbrieviatedMonthYear(date);
    assertEquals("Sep 2015", formatted);
  }
}
