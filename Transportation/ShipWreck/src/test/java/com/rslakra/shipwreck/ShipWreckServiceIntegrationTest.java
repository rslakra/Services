package com.rslakra.shipwreck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.rslakra.shipwreck.model.ShipWreck;
import com.rslakra.shipwreck.services.ShipWreckService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * The TestCase for the ShipWreck controller class. It uses the
 * <code>Mockito</code> for testing.
 *
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
@ExtendWith(SpringExtension.class)
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
        List<ShipWreck> resultShipWrecks = shipWreckService.getAll();
        assertNotEquals(resultShipWrecks.size(), 0);
    }

    @Test
    public void testShipWreckGet() {
        ShipWreck shipWreck = shipWreckService.getById(1L);
        assertEquals(1L, shipWreck.getId().longValue());
    }

    @Test
    public void testShipWreckCreate() {
        ShipWreck shipWreck = shipWreckService.create(new ShipWreck());
        assertEquals(4L, shipWreck.getId().longValue());
    }

    @Test
    public void testShipWreckUpdate() {
        ShipWreck oldShipWreck = shipWreckService.getById(2L);
        oldShipWreck.setName("Name-Updated-" + oldShipWreck.getId());
        ShipWreck shipWreck = shipWreckService.update(oldShipWreck);
        assertEquals(oldShipWreck.getName(), shipWreck.getName());
    }

    @Test
    public void testShipWreckDelete() {
        ShipWreck oldShipWreck = shipWreckService.getById(1L);
        ShipWreck shipWreck = shipWreckService.delete(oldShipWreck.getId());
        assertEquals(oldShipWreck.getId(), shipWreck.getId());
    }

}
