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
package com.rslakra.shipwreck.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rslakra.shipwreck.model.ShipWreck;
import com.rslakra.shipwreck.services.ShipWreckService;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
@RestController
@RequestMapping("api/v1/")
public class ShipWreckController {

	@Autowired
//	private ShipWreckRepository shipWreckRepository;
	private ShipWreckService shipWreckService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
	public List<ShipWreck> list() {
		return shipWreckService.list();
	}

	/**
	 * 
	 * @param shipWreck
	 * @return
	 */
	@RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
	public ShipWreck create(@RequestBody ShipWreck shipWreck) {
		return shipWreckService.create(shipWreck);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
	public ShipWreck get(@PathVariable Long id) {
		return shipWreckService.get(id);
	}

	/**
	 * 
	 * @param id
	 * @param shipWreck
	 * @return
	 */
	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
	public ShipWreck update(@PathVariable Long id, @RequestBody ShipWreck shipWreck) {
		return shipWreckService.update(id, shipWreck);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
	public ShipWreck delete(@PathVariable Long id) {
		return shipWreckService.delete(id);
	}

}
