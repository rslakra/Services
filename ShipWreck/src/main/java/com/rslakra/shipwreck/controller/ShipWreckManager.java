///**
// * 
// */
//package com.rslakra.shipwreck.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.rslakra.shipwreck.model.ShipWreck;
//
///**
// * @author Rohtash Singh Lakra
// * @version 1.0.0
// */
//public class ShipWreckManager {
//	private static ShipWreckManager instance;
//	private final Map<Long, ShipWreck> shipWrecks = new HashMap<Long, ShipWreck>();
//	private Long idIndex = 3L;
//
//	/**
//	 * 
//	 */
//	private ShipWreckManager() {
//		// populate initial wrecks
//		shipWrecks.put(1L, new ShipWreck(1L, "U869", "A very deep German UBoat", "FAIR", 200, 44.12, 138.44, 1994));
//		shipWrecks.put(2L, new ShipWreck(2L, "Thistlegorm", "British merchant boat in the Red Sea", "GOOD", 80, 44.12,
//				138.44, 1994));
//		shipWrecks.put(3L, new ShipWreck(3L, "S.S. Yongala",
//				"A luxury passenger ship wrecked on the great barrier reef", "FAIR", 50, 44.12, 138.44, 1994));
//	}
//
//	/**
//	 * 
//	 * @return
//	 */
//	public static ShipWreckManager getInstance() {
//		if (instance == null) {
//			synchronized (ShipWreckManager.class) {
//				if (instance == null) {
//					instance = new ShipWreckManager();
//				}
//			}
//		}
//
//		return instance;
//	}
//
//	/**
//	 * 
//	 * @return
//	 */
//	public List<ShipWreck> list() {
//		return new ArrayList<ShipWreck>(shipWrecks.values());
//	}
//
//	/**
//	 * 
//	 * @param shipWreck
//	 * @return
//	 */
//	public ShipWreck create(ShipWreck shipWreck) {
//		idIndex += idIndex;
//		shipWreck.setId(idIndex);
//		shipWrecks.put(idIndex, shipWreck);
//		return shipWreck;
//	}
//
//	/**
//	 * 
//	 * @param id
//	 * @return
//	 */
//	public ShipWreck get(Long id) {
//		return shipWrecks.get(id);
//	}
//
//	/**
//	 * 
//	 * @param id
//	 * @param shipWreck
//	 * @return
//	 */
//	public ShipWreck update(Long id, ShipWreck shipWreck) {
//		shipWrecks.put(id, shipWreck);
//		return shipWreck;
//	}
//
//	/**
//	 * 
//	 * @param id
//	 * @return
//	 */
//	public ShipWreck delete(Long id) {
//		return shipWrecks.remove(id);
//	}
//
//}
