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

package com.google.api.ads.adwords.awreporting.model.persistence.mongodb;

import com.google.api.ads.adwords.awreporting.model.entities.AuthToken;
import com.google.api.ads.adwords.awreporting.model.persistence.AuthTokenPersister;
import com.google.api.ads.adwords.awreporting.model.persistence.EntityPersister;
import com.google.common.collect.Iterables;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * The MongoDb implementation of the token persister.
 */
@Component
public class MongoAuthTokenPersister implements AuthTokenPersister {
  
  private EntityPersister mongoEntityPersister;

  @Autowired
  public MongoAuthTokenPersister(
      @Qualifier("mongoEntityPersister") EntityPersister mongoEntityPersister) {
    this.mongoEntityPersister = mongoEntityPersister;
  }

  @Override
  public void persistAuthToken(AuthToken authToken) {
    mongoEntityPersister.save(authToken);
  }

  @Override
  public AuthToken getAuthToken(String topManagerAccountId) {
    List<AuthToken> list =
        mongoEntityPersister.get(AuthToken.class, "topAccountId", topManagerAccountId);
    return Iterables.getOnlyElement(list, null);
  }
}
