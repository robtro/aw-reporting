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
import javax.annotation.Nullable;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * {@code StringsUtil} is a utility class for parsing and handling the different String
 * formats in the reports.
 */
public class StringsUtil {

  private static final String EMPTY_VALUE = "--";
  
  /**
   * Returns true if String is null, empty, or equal to two dashes (--).
   */
  public static boolean isEmptyValue(@Nullable String value) {
    return Strings.isNullOrEmpty(value) || value.trim().equals(StringsUtil.EMPTY_VALUE);
  }

  /**
   * Returns String with dashes removed or empty String. 
   */
  public static String removeDashes(@Nullable String value){
    if (Strings.isNullOrEmpty(value)){
      return "";
    } else {
      return value.replaceAll("-", "");
    }
  }
  
  /**
   * Returns customer id as long with dashes removed or empty String. 
   */
  public static Long parseCustomerId(@Nullable String value){
      return Long.parseLong(StringsUtil.removeDashes(value));
  }
  
  /**
   * Calculates a SHA-1 Hash of the specified string
   *
   * @param s the string that needs to calculate hash
   * @return the SHA-1 hash of the input string
   */
  public static String calculateHash(String s) {
    return DigestUtils.sha1Hex(s);
  }
}

