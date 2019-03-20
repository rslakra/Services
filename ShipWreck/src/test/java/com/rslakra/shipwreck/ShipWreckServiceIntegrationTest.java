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
package com.rslakra.shipwreck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rslakra.shipwreck.model.ShipWreck;
import com.rslakra.shipwreck.services.ShipWreckService;

/**
 * The TestCase for the ShipWreck controller class. It uses the
 * <code>Mockito</code> for testing.
 * 
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ShipWreckServiceIntegrationTest {

	@Autowired
	private ShipWreckService shipWreckService;

	/**
	 * <pre>
	 * 1 U871 A very deep German UBoat! FAIR 200 44.12 138.44 1994 
	 * 2 U872 British merchant boat in the Red Sea. Wrecked 200 42.22 262.22 1994 
	 * 3 U873 A very deep Indian Ship with great power! GOOD 320 47.12 438.44 1987
	 * </pre>
	 */
	@Test
	public void testShipWreckFindAll() {
		List<ShipWreck> resultShipWrecks = shipWreckService.list();
		assertNotEquals(resultShipWrecks.size(), 0);
	}

	@Test
	public void testShipWreckGet() {
		ShipWreck shipWreck = shipWreckService.get(1L);
		assertEquals(1L, shipWreck.getId().longValue());
	}

	@Test
	public void testShipWreckCreate() {
		ShipWreck shipWreck = shipWreckService.create(new ShipWreck());
		assertEquals(4L, shipWreck.getId().longValue());
	}

	@Test
	public void testShipWreckUpdate() {
		ShipWreck oldShipWreck = shipWreckService.get(2L);
		oldShipWreck.setName("Name-Updated-" + oldShipWreck.getId());
		ShipWreck shipWreck = shipWreckService.update(oldShipWreck.getId(), oldShipWreck);
		assertEquals(oldShipWreck.getName(), shipWreck.getName());
	}

	@Test
	public void testShipWreckDelete() {
		ShipWreck oldShipWreck = shipWreckService.get(1L);
		ShipWreck shipWreck = shipWreckService.delete(oldShipWreck.getId());
		assertEquals(oldShipWreck.getId(), shipWreck.getId());
	}

}
