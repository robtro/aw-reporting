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

package com.google.api.ads.adwords.awreporting.downloader;

import com.google.api.client.util.Preconditions;
import com.google.common.annotations.VisibleForTesting;

/**
 * This class holds download settings for both FILE downloading and STREAM downloading.
 */
public class DownloadSetting {
  private int numThreads = 20; // Number of download threads.
  private int numAttempts = 5; // Number of download attempts.
  private int backoffInterval = 5000; // Exponential backoff interval between attempts (in millis).

  public DownloadSetting() {}
  
  @VisibleForTesting
  DownloadSetting(int numThreads, int numAttempts, int backoffInterval) {
    this.numThreads = numThreads;
    this.numAttempts = numAttempts;
    this.backoffInterval = backoffInterval;
  }

  public Integer getNumThreads() {
    return Integer.valueOf(this.numThreads);
  }

  public Integer getNumAttempts() {
    return Integer.valueOf(this.numAttempts);
  }

  public Integer getBackoffInterval() {
    return Integer.valueOf(this.backoffInterval);
  }
  
  public void setNumThreads(Integer numThreads) {
    Preconditions.checkNotNull(numThreads, "numThreads cannot be null.");
    Preconditions.checkArgument(numThreads > 0, "numThreads must be positive.");
    this.numThreads = numThreads.intValue();
  }

  public void setNumAttempts(Integer numAttempts) {
    Preconditions.checkNotNull(numAttempts, "numAttempts cannot be null.");
    Preconditions.checkArgument(numAttempts > 0, "numAttempts must be positive.");
    this.numAttempts = numAttempts.intValue();
  }

  public void setBackoffInterval(Integer backoffInterval) {
    Preconditions.checkNotNull(backoffInterval, "backoffInterval cannot be null.");
    Preconditions.checkArgument(backoffInterval >= 0, "backoffInterval must be non-negative.");
    this.backoffInterval = backoffInterval.intValue();
  }
  
  /**
   * Apply relevant settings ({@link #numAttempts}, {@link #backoffInterval}) to the RateLimiter
   * regarding report downloading.
   */
  public void applyToRateLimiter() {
    System.setProperty(
        "com.google.api.ads.adwords.extension.ratelimiter.ApiReportingRetryStrategy.maxAttemptsOnRateExceededError",
        String.valueOf(numAttempts));
    System.setProperty(
        "com.google.api.ads.adwords.extension.ratelimiter.ApiReportingRetryStrategy.backoffIntervalOnRateExceededError",
        String.valueOf(backoffInterval));
  }
}
