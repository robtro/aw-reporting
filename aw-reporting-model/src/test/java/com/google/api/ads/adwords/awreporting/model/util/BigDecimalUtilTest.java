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
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

/**
 * Test for the {@link BigDecimalUtil} class.
 */
@RunWith(JUnit4.class)
public class BigDecimalUtilTest {

  /**
   * Tests the American number format parsing. (##,###,###.00)
   */
  @Test
  public void testAmericanNumberFormat() {

    String numString = "1 200 300.10";
    BigDecimal parsed = BigDecimalUtil.parseFromNumberString(numString);

    assertEquals(
        "The parsed value is not the expected.", 1200300.10, parsed.doubleValue(), 1e-6);

    numString = "1,200,300.10";
    parsed = BigDecimalUtil.parseFromNumberString(numString);

    assertEquals(
        "The parsed value is not the expected.", 1200300.10, parsed.doubleValue(), 1e-6);
  }

  /**
   * Tests the Brazilian number format parsing (##.###.###,00).
   */
  @Test
  public void testBrazilianNumberFormat() {

    String numString = "1 200 300,10";
    BigDecimal parsed = BigDecimalUtil.parseFromNumberString(numString);

    assertEquals(
        "The parsed value is not the expected.", 1200300.10, parsed.doubleValue(), 1e-6);

    numString = "1.200.300,10";
    parsed = BigDecimalUtil.parseFromNumberString(numString);

    assertEquals(
        "The parsed value is not the expected.", 1200300.10, parsed.doubleValue(), 1e-6);
  }

  /**
   * Tests Comma rounding.
   */
  @Test
  public void testRoundingComma() {

    String numString = "0,39";
    BigDecimal parsed = BigDecimalUtil.parseFromNumberString(numString);
    assertEquals("The parsed value is not the expected.", 0.39, parsed.doubleValue(), 0.0);

    numString = "33,33";
    parsed = BigDecimalUtil.parseFromNumberString(numString);
    assertEquals("The parsed value is not the expected.", 33.33, parsed.doubleValue(), 0.0);
  }

  /**
   * Tests Dot rounding.
   */
  @Test
  public void testRoundingDot() {

    String numString = "0.39";
    BigDecimal parsed = BigDecimalUtil.parseFromNumberString(numString);
    assertEquals("The parsed value is not the expected.", 0.39, parsed.doubleValue(), 0.0);

    numString = "33.33";
    parsed = BigDecimalUtil.parseFromNumberString(numString);
    assertEquals("The parsed value is not the expected.", 33.33, parsed.doubleValue(), 0.0);
  }

  /**
   * Tests Percent sign.
   */
  @Test
  public void testPercent() {

    String numString = "0,39%";
    BigDecimal parsed = BigDecimalUtil.parseFromNumberString(numString);
    assertEquals("The parsed value is not the expected.", 0.39, parsed.doubleValue(), 0.0);

    numString = "33,33%";
    parsed = BigDecimalUtil.parseFromNumberString(numString);
    assertEquals("The parsed value is not the expected.", 33.33, parsed.doubleValue(), 0.0);
  }

  /**
   * Tests empty string.
   */
  @Test
  public void testEmtpy() {
    String numString = "";
    BigDecimal parsed = BigDecimalUtil.parseFromNumberString(numString);
    assertNull("The parsed value is not null.", parsed);
  }

  /**
   * Tests null string.
   */
  @Test
  public void testNull() {
    String numString = null;
    BigDecimal parsed = BigDecimalUtil.parseFromNumberString(numString);
    assertNull("The parsed value is not null.", parsed);
  }

  /**
   * Tests not a number.
   */
  @Test(expected = RuntimeException.class)
  public void testNotANumber() {
    String numString = "abcdef";
    BigDecimalUtil.parseFromNumberString(numString);
  }

  /**
   * Tests dash.
   */
  @Test
  public void testDash() {
    String numString = "--";
    BigDecimal parsed = BigDecimalUtil.parseFromNumberStringPercentage(numString);
    assertNull("The parsed value is not null.", parsed);
  }

  /**
   * Tests dash.
   */
  @Test
  public void testPercentStringNull() {
    String numString = null;
    BigDecimal parsed = BigDecimalUtil.parseFromNumberStringPercentage(numString);
    assertNull("The parsed value is not null.", parsed);
  }

  /**
   * Tests dash.
   */
  @Test
  public void testPercentStringEmpty() {
    String numString = "";
    BigDecimal parsed = BigDecimalUtil.parseFromNumberStringPercentage(numString);
    assertNull("The parsed value is not null.", parsed);
  }
}
