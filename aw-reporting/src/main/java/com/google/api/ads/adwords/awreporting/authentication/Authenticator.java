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

package com.google.api.ads.adwords.awreporting.authentication;

import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.common.lib.exception.OAuthException;

/**
 * Authenticator interface for OAuth.
 */
public interface Authenticator {
  /**
   * Authenticates the user against the API(s) depending on the OAuth scope and then returns an
   * {@link com.google.api.ads.adwords.lib.client.AdWordsSession.Builder}.
   *
   * @return an adwords session builder with the authentication info
   * @throws OAuthException error on the OAuth process
   */
  AdWordsSession.Builder authenticate() throws OAuthException;
}
