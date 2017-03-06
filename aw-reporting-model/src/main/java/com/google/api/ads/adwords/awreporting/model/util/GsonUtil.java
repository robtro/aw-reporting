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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Util class for {@code Gson}.
 */
public class GsonUtil {

  public static final GsonBuilder gsonBuilder =
      new GsonBuilder().setDateFormat(DateUtil.DATE_FORMAT_REPORT);

  /**
   * Private constructor to prevent instantiation of this utility class.
   */
  private GsonUtil() {}

  /**
   * @return the {@link GsonBuilder} used by this class.
   */
  public static Gson createGson() {
    return gsonBuilder.create();
  }
}
