/**
 * 
 */
package com.rslakra.shipwreck;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rslakra.shipwreck.controller.ShipWreckController;
import com.rslakra.shipwreck.model.ShipWreck;
import com.rslakra.shipwreck.repository.ShipWreckRepository;
import com.rslakra.shipwreck.services.ShipWreckService;

/**
 * The TestCase for the ShipWreck controller class. It uses the
 * <code>Mockito</code> for testing.
 * 
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
public class ShipWreckControllerHamcrestTest {

	@InjectMocks
	private ShipWreckController shipWreckController;

	@Mock
	private ShipWreckService shipWreckService;

	@Mock
	private ShipWreckRepository shipWreckRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 
	 * @param count
	 * @return
	 */
	private List<ShipWreck> listOfShipWrecks(int size) {
		List<ShipWreck> shipWrecks = new ArrayList<>();
		ShipWreck shipWreck = null;
		for (int i = 0; i < size; i++) {
			shipWreck = new ShipWreck();
			shipWreck.setId(Long.valueOf((i + 11)));
			shipWreck.setName("Name-" + shipWreck.getId());
			shipWreck.setName("Description-" + shipWreck.getId());
			shipWrecks.add(shipWreck);
		}

		return shipWrecks;
	}

	/**
	 * <pre>
	 * 1 U871 A very deep German UBoat! FAIR 200 44.12 138.44 1994 
	 * 2 U872 British merchant boat in the Red Sea. Wrecked 200 42.22 262.22 1994 
	 * 3 U873 A very deep Indian Ship with great power! GOOD 320 47.12 438.44 1987
	 * </pre>
	 */
	@Test
	public void testShipWreckFindAll() {
		List<ShipWreck> shipWrecks = listOfShipWrecks(5);
		when(shipWreckService.list()).thenReturn(shipWrecks);

		List<ShipWreck> resultShipWrecks = shipWreckController.list();
		verify(shipWreckService).list();

//		assertEquals(shipWrecks.size(), resultShipWrecks.size());
		assertThat(resultShipWrecks.size(), is(shipWrecks.size()));
	}

	@Test
	public void testShipWreckGet() {
		List<ShipWreck> shipWrecks = listOfShipWrecks(1);
		ShipWreck findShipWreck = shipWrecks.get(0);
		when(shipWreckService.get(findShipWreck.getId())).thenReturn(findShipWreck);

		ShipWreck shipWreck = shipWreckController.get(findShipWreck.getId());
		verify(shipWreckService).get(findShipWreck.getId());

//		assertEquals(findShipWreck.getId(), shipWreck.getId());
		assertThat(shipWreck.getId(), is(findShipWreck.getId()));
	}

	@Test
	public void testShipWreckCreate() {
		List<ShipWreck> shipWrecks = listOfShipWrecks(1);
		ShipWreck newShipWreck = shipWrecks.get(0);
		when(shipWreckService.create(newShipWreck)).thenReturn(newShipWreck);

		ShipWreck shipWreck = shipWreckController.create(newShipWreck);
		verify(shipWreckService).create(newShipWreck);

//		assertEquals(newShipWreck.getId(), shipWreck.getId());
		assertThat(shipWreck.getId(), is(newShipWreck.getId()));
	}

	@Test
	public void testShipWreckUpdate() {
		List<ShipWreck> shipWrecks = listOfShipWrecks(1);
		ShipWreck newShipWreck = shipWrecks.get(0);
		when(shipWreckService.update(newShipWreck.getId(), newShipWreck)).thenReturn(newShipWreck);

		newShipWreck.setName("Name-Updated-" + newShipWreck.getId());
		ShipWreck shipWreck = shipWreckController.update(newShipWreck.getId(), newShipWreck);

//		assertEquals(newShipWreck.getName(), shipWreck.getName());
		assertThat(shipWreck.getName(), is(newShipWreck.getName()));
	}

	@Test
	public void testShipWreckDelete() {
		List<ShipWreck> shipWrecks = listOfShipWrecks(5);
		ShipWreck shipWreckDeleted = shipWrecks.get(2);
		when(shipWreckService.delete(shipWreckDeleted.getId())).thenReturn(shipWreckDeleted);

		ShipWreck shipWreck = shipWreckController.delete(shipWreckDeleted.getId());
		assertEquals(shipWreckDeleted.getId(), shipWreck.getId());
		assertThat(shipWreck.getId(), is(shipWreckDeleted.getId()));
	}

}
