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

import com.google.common.base.Strings;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.annotation.Nullable;

/**
 * {@code BigDecimalUtil} is a utility class for parsing and handling the different types
 * of number formats in the reports.
 */
public class BigDecimalUtil {
  private static final BigDecimal MICRO = new BigDecimal(1000000);

  private static final ThreadLocal<DecimalFormat> humanReadableFormat =
      new ThreadLocal<DecimalFormat>() {
        @Override
        protected DecimalFormat initialValue() {
          return new DecimalFormat("#0.00");
        }
      };

  /**
   * Private constructor to prevent instantiation of this utility class.
   */
  private BigDecimalUtil() {}

  /**
   * Finds out the number format in {@code String} format, and parse the number to
   * {@code BigDecimal} format.
   *
   * The passed number can contain white spaces of any sort, and can have the default separators
   * such as ',' and '.'.
   *
   * @param numberString the number in {@code String} format
   * @return the {@code BigDecimal} that was parsed from the {@code String}. If the numberString is
   *         {@code null} or empty, then {@code null} is returned.
   */
  public static BigDecimal parseFromNumberString(@Nullable String numberString) {
    BigDecimal result = null;

    if (numberString != null) {
      String nonSpacedString = numberString.replaceAll("[ \\t\\n\\x0B\\f\\r%]", "");

      if (!Strings.isNullOrEmpty(nonSpacedString)){
        int indexOfComma = nonSpacedString.indexOf(',');
        int indexOfDot = nonSpacedString.indexOf('.');
  
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
  
        if (indexOfComma < indexOfDot) {
          otherSymbols.setDecimalSeparator('.');
          otherSymbols.setGroupingSeparator(',');
        } else {
          otherSymbols.setDecimalSeparator(',');
          otherSymbols.setGroupingSeparator('.');
        }
  
        decimalFormat.setParseBigDecimal(true);
        decimalFormat.setDecimalFormatSymbols(otherSymbols);
  
        try {
          result = (BigDecimal) decimalFormat.parse(nonSpacedString);
        } catch (ParseException e) {
          throw new RuntimeException("Error parsing '" + nonSpacedString + "' as BigDecimal", e);
        }
      }
    }
    return result;
  }

  /**
   * Returns a new BigDecimal by parsing the supplied {@code numberString}.
   *
   * Mainly used for the "< 10%", "> 90%" or "--" numbers from the API.
   *
   * @param numberString the number in {@code String} format
   * @return the {@code BigDecimal} that was parsed from the {@code String}. If the numberString is
   *         {@code null} or empty, then {@code null} is returned.
   */
  public static BigDecimal parseFromNumberStringPercentage(@Nullable String numberString) {
    return StringsUtil.isEmptyValue(numberString) ? null
        : new BigDecimal(numberString.replaceAll("\\s|%|>|<", ""));
  }

  /**
   * Formats the given {@code BigDecimal} to a readable String.
   *
   * @param number the {@code BigDecimal} to be formatted
   * @return the formatted number with precision two. Null in case of null object
   */
  public static String formatAsReadable(@Nullable BigDecimal number) {
    return (number == null) ? null : humanReadableFormat.get().format(number);
  }
  
  /**
   * Returns a new BigDecimal money amount by parsing the supplied {@code numberString} 
   * and converting from micros.
   *
   * @param numberString the number in {@code String} format
   * @return the {@code BigDecimal} that was parsed from the {@code String} and converted from 
   *         micros. If the numberString is {@code null} or empty, then {@code null} is returned.
   */
  public static BigDecimal parseFromMoneyString(@Nullable String numberString){
    return StringsUtil.isEmptyValue(numberString) ? null
        : (new BigDecimal(numberString.replaceAll("[^\\d.]", ""))).divide(MICRO);
  }
}
