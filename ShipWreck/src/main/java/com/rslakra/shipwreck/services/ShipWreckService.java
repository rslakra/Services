/**
 * 
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
