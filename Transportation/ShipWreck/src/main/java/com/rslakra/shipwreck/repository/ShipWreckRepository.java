/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rslakra.shipwreck.repository;

import com.devamatre.framework.spring.persistence.repository.BaseRepository;
import com.rslakra.shipwreck.model.ShipWreck;
import org.springframework.stereotype.Repository;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
@Repository
public interface ShipWreckRepository extends BaseRepository<ShipWreck, Long> {

}
