/**
 * 
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
