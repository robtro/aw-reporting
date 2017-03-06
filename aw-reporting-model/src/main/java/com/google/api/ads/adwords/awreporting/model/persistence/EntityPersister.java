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

package com.google.api.ads.adwords.awreporting.model.persistence;

import com.google.api.ads.adwords.awreporting.model.entities.Report;
import java.util.List;

/**
 * The {@code ReportEntitiesPersister} is the base interface to persist the retrieved data from the
 * AdWords API.
 *
 * <p>The implementations should provide a way to persist the {@code Report} entities.
 *
 * <p>The implementation is responsible for deciding the best way to perform all the
 * operations, i.e. the most performing manner or the least resource demanding.
 */
public interface EntityPersister {

  /**
   * Persists the report entities so they can be retrieved later.
   *
   * @param reportEntities the entities that were retrieved from the AW report API.
   */
  void persistReportEntities(List<? extends Report> reportEntities);
  
  /**
   * Saves and returns the specified non-report entity.
   */
  <T> T save(T entity);

  /**
   * Gets the entities that contains the given value on the given property.
   *
   * @param classT the entity T class
   * @param key the property name
   * @param value the property value
   * @return the list of entities that were found
   */
  <T, V> List<T> get(Class<T> classT, String key, V value);
}
