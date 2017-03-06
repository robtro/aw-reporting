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

import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.client.AdWordsSession.ImmutableAdWordsSession;
import com.google.api.ads.common.lib.exception.ValidationException;

/**
 * Utilities of AdWordsSession
 */
public class AdWordsSessionUtil {
  /**
   * Build a new {@code ImmutableAdWordsSession} for the given cid.
   * @param sessionBuilder the adwords session builder
   * @param cid the client customer id (in long format)
   * @return an immutable adwords session for the specified cid
   */
  public static ImmutableAdWordsSession buildImmutableSessionForCid(
      AdWordsSession.Builder sessionBuilder, Long cid) throws ValidationException {
    String cidStr = cid == null ? null : String.valueOf(cid);
    return buildImmutableSessionForCid(sessionBuilder, cidStr);
  }

  /**
   * Build a new {@code ImmutableAdWordsSession} for the given cid.
   * @param sessionBuilder the adwords session builder
   * @param cid the client customer id (in string format)
   * @return an immutable adwords session for the specified cid
   */
  public static ImmutableAdWordsSession buildImmutableSessionForCid(
      AdWordsSession.Builder sessionBuilder, String cid) throws ValidationException {
    return sessionBuilder.withClientCustomerId(cid).buildImmutable();
  }
}
