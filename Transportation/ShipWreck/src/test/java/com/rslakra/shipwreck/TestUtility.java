package com.rslakra.shipwreck;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rslakra.shipwreck.model.ShipWreck;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
public final class TestUtility {

    public static final String UTF_8 = "utf8";
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
                                                                        MediaType.APPLICATION_JSON.getSubtype(),
                                                                        Charset.forName(UTF_8));

    /**
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static byte[] toBytes(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    /**
     * Returns the object as JSON string.
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String toJSONString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    /**
     * @param jsonString
     * @param klass
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T fromJSONString(String jsonString, Class<T> klass) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, klass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Returns the <code>ShipWreck</code> new object.
     *
     * @param shipWreckId
     * @param name
     * @param description
     * @return
     */
    public static ShipWreck newShipWrecks(long shipWreckId, String name, String description) {
        ShipWreck shipWreck = new ShipWreck();
        shipWreck.setId(shipWreckId);
        shipWreck.setName(name);
        shipWreck.setDescription(description);

        return shipWreck;
    }

    /**
     * Returns the <code>ShipWreck</code> new object.
     *
     * @param shipWreckId
     * @return
     */
    public static ShipWreck newShipWrecks(int shipWreckId) {
        return newShipWrecks(shipWreckId, "Name-" + shipWreckId, "Description-" + shipWreckId);
    }

    /**
     * Returns the list of <code>ShipWreck</code> objects.
     *
     * @param size
     * @return
     */
    public static List<ShipWreck> listOfShipWrecks(int size) {
        List<ShipWreck> shipWrecks = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            shipWrecks.add(newShipWrecks((i + 11)));
        }

        return shipWrecks;
    }

}
