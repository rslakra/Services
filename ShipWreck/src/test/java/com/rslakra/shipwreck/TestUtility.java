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

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rslakra.shipwreck.model.ShipWreck;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 *
 */
public final class TestUtility {
	public static final String UTF_8 = "utf8";
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName(UTF_8));

	/**
	 * 
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
	 * 
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
	 * @param id
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
