package com.rslakra.shipwreck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.shipwreck.controller.ShipWreckController;
import com.rslakra.shipwreck.model.ShipWreck;
import com.rslakra.shipwreck.repository.ShipWreckRepository;
import com.rslakra.shipwreck.services.ShipWreckService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * The TestCase for the ShipWreck controller class. It uses the
 * <code>Mockito</code> for testing.
 *
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
public class ShipWreckControllerTest {

    @InjectMocks
    private ShipWreckController shipWreckController;

    @Mock
    private ShipWreckService shipWreckService;

    @Mock
    private ShipWreckRepository shipWreckRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
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
        List<ShipWreck> shipWrecks = TestUtility.listOfShipWrecks(5);
        when(shipWreckService.getAll()).thenReturn(shipWrecks);

        List<ShipWreck> resultShipWrecks = shipWreckController.getAll();
        verify(shipWreckService).getAll();

        assertEquals(shipWrecks.size(), resultShipWrecks.size());
    }

    @Test
    public void testShipWreckGet() {
        ShipWreck findShipWreck = TestUtility.newShipWrecks(1);
        when(shipWreckService.getById(findShipWreck.getId())).thenReturn(findShipWreck);

        ShipWreck shipWreck = null;//shipWreckController.getByFilter(Payload.newBuilder().ofPair("id", 1)).get(0);
        verify(shipWreckService).getById(findShipWreck.getId());

        assertEquals(findShipWreck.getId(), shipWreck.getId());
    }

    @Test
    public void testShipWreckCreate() {
        ShipWreck newShipWreck = TestUtility.newShipWrecks(1);
        when(shipWreckService.create(newShipWreck)).thenReturn(newShipWreck);

        ResponseEntity<ShipWreck> shipWreck = shipWreckController.create(newShipWreck);
        verify(shipWreckService).create(newShipWreck);

        assertEquals(newShipWreck.getId(), shipWreck.getBody().getId());
    }

    @Test
    public void testShipWreckUpdate() {
        ShipWreck newShipWreck = TestUtility.newShipWrecks(1);
//        when(shipWreckService.update(newShipWreck.getId(), newShipWreck)).thenReturn(newShipWreck);

        newShipWreck.setName("Name-Updated-" + newShipWreck.getId());
//        ShipWreck shipWreck = shipWreckController.update(newShipWreck.getId(), newShipWreck);

//        assertEquals(newShipWreck.getName(), shipWreck.getName());
    }

    @Test
    public void testShipWreckDelete() {
        List<ShipWreck> shipWrecks = TestUtility.listOfShipWrecks(5);
        ShipWreck shipWreckDeleted = shipWrecks.get(2);
        when(shipWreckService.delete(shipWreckDeleted.getId())).thenReturn(shipWreckDeleted);

//        ShipWreck shipWreck = shipWreckController.delete(shipWreckDeleted.getId());
//        assertEquals(shipWreckDeleted.getId(), shipWreck.getId());
    }

}
