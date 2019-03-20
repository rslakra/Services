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
package com.rslakra.shipwreck.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.rslakra.shipwreck.model.ShipWreck;
import com.rslakra.shipwreck.repository.ShipWreckRepository;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
@Service
public class ShipWreckService {

	@Autowired
	private ShipWreckRepository shipWreckRepository;

	/**
	 * 
	 * @return
	 */
	public List<ShipWreck> list() {
		return shipWreckRepository.findAll();
	}

	/**
	 * 
	 * @param shipWreck
	 * @return
	 */
	public ShipWreck create(@RequestBody ShipWreck shipWreck) {
		return shipWreckRepository.saveAndFlush(shipWreck);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ShipWreck get(@PathVariable Long id) {
		return shipWreckRepository.findById(id).get();
	}

	/**
	 * 
	 * @param id
	 * @param shipWreck
	 * @return
	 */
	public ShipWreck update(@PathVariable Long id, @RequestBody ShipWreck shipWreck) {
		ShipWreck oldShipWreck = shipWreckRepository.findById(id).get();
		BeanUtils.copyProperties(shipWreck, oldShipWreck);
		return shipWreckRepository.saveAndFlush(oldShipWreck);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ShipWreck delete(@PathVariable Long id) {
		ShipWreck oldShipWreck = shipWreckRepository.findById(id).get();
		shipWreckRepository.deleteById(id);
		return oldShipWreck;
	}
}
