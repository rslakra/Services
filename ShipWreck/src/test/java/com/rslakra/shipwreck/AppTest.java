package com.rslakra.shipwreck;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rslakra.shipwreck.controller.HomeController;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void testApp() {
		String result = new HomeController().home();
		assertEquals("Shipwreck welcomes you!", result);
	}
}
