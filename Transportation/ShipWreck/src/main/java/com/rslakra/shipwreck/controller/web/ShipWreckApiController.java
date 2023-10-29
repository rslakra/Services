package com.rslakra.shipwreck.controller.web;

import com.rslakra.shipwreck.model.ShipWreck;
import com.rslakra.shipwreck.services.ShipWreckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
@Controller
@RequestMapping("${apiPrefix}")
public class ShipWreckApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipWreckApiController.class);

    private final ShipWreckService shipWreckService;

    /**
     * @param shipWreckService
     */
    @Autowired
    public ShipWreckApiController(ShipWreckService shipWreckService) {
        LOGGER.debug("ShipWreckApiController({})", shipWreckService);
        this.shipWreckService = shipWreckService;
    }

    /**
     * @return
     */
    @GetMapping("/")
    public String indexPage() {
        LOGGER.debug("indexPage()");
        return "index.html";
    }

    /**
     * @return
     */
    @GetMapping("/shipwrecks")
    @ResponseBody
    public List<ShipWreck> list() {
        LOGGER.debug("+list()");
        List<ShipWreck> shipWrecks = shipWreckService.getAll();
        LOGGER.debug("-list(), shipWrecks: {}", shipWrecks);
        return shipWrecks;
    }

    /**
     * @param shipWreck
     * @return
     */
    @PostMapping("/shipwrecks")
    @ResponseBody
    public ShipWreck create(@RequestBody ShipWreck shipWreck) {
        LOGGER.debug("+create({})", shipWreck);
        shipWreck = shipWreckService.create(shipWreck);
        LOGGER.debug("-create(), shipWreck: {}", shipWreck);
        return shipWreck;
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/shipwrecks/{id}")
    @ResponseBody
    public ShipWreck findById(@PathVariable Long id) {
        LOGGER.debug("+findById({})", id);
        ShipWreck shipWreck = shipWreckService.getById(id);
        LOGGER.debug("-findById(), shipWreck: {}", shipWreck);
        return shipWreck;
    }

    /**
     * @param id
     * @param shipWreck
     * @return
     */
    @PutMapping("/shipwrecks/{id}")
    @ResponseBody
    public ShipWreck update(@PathVariable Long id, @RequestBody ShipWreck shipWreck) {
        LOGGER.debug("+update({})", shipWreck);
        shipWreck = shipWreckService.update(shipWreck);
        LOGGER.debug("-update(), shipWreck: {}", shipWreck);
        return shipWreck;
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("shipwrecks/{id}")
    @ResponseBody
    public ShipWreck delete(@PathVariable Long id) {
        LOGGER.debug("+delete({})", id);
        ShipWreck shipWreck = shipWreckService.delete(id);
        LOGGER.debug("-delete(), shipWreck: {}", shipWreck);
        return shipWreck;
    }

}
