package com.rslakra.shipwreck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rslakra.shipwreck.model.ShipWreck;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShipWreckControllerWebIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    /**
     * @param path
     * @return
     */
    private String getUrl(String path) {
        String urlString = "http://localhost:" + port + "/api/v1/shipwrecks";
        if (path != null && !path.isEmpty()) {
            urlString += (path.startsWith("/") ? path : "/" + path);
        }

        return urlString;
    }

    /**
     * @return
     */
    private String getUrl() {
        return getUrl(null);
    }

    /**
     * Creates the new <code>ShipWreck</code> object.
     */
    @Test
    public void testShipWreckCreate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ShipWreck newShipWreck = TestUtility.newShipWrecks(1);

        ResponseEntity<String> responseEntity = null;

        HttpEntity<ShipWreck> request = new HttpEntity<ShipWreck>(newShipWreck, headers);
//		responseEntity = restTemplate.exchange(getUrl(null), HttpMethod.POST, request, String.class);
        responseEntity = restTemplate.postForEntity(getUrl(null), request, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ShipWreck responseObject = TestUtility.fromJSONString(responseEntity.getBody(), ShipWreck.class);
        assertThat(responseObject.getName()).isEqualTo(newShipWreck.getName());

        // fetch saved object
        responseEntity = restTemplate.getForEntity(getUrl(responseObject.getId().toString()), String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ShipWreck shipWreckResponse = TestUtility.fromJSONString(responseEntity.getBody(), ShipWreck.class);
        assertThat(shipWreckResponse.getId()).isEqualTo(responseObject.getId());
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
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getUrl(), String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<?> shipWreckResponse = TestUtility.fromJSONString(responseEntity.getBody(), List.class);
        assertThat(shipWreckResponse.size()).isGreaterThanOrEqualTo(0);

//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
//			jsonNode.findPath("name");
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}

    }

    @Test
    public void testShipWreckGet() {
        ResponseEntity<String> responseEntity = null;
        try {
            long id = 6L;
            responseEntity = restTemplate.getForEntity(getUrl(String.valueOf(id)), String.class);
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            ShipWreck shipWreckResponse = TestUtility.fromJSONString(responseEntity.getBody(), ShipWreck.class);
            assertEquals(id, shipWreckResponse.getId().longValue());
        } catch (Exception ex) {
            ex.printStackTrace();
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        }
    }

    @Test
    public void testShipWreckUpdate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ShipWreck newShipWreck = TestUtility.newShipWrecks(1);

        ResponseEntity<String> responseEntity = null;
        HttpEntity<ShipWreck> request = new HttpEntity<ShipWreck>(newShipWreck, headers);
        // created new record
        responseEntity = restTemplate.postForEntity(getUrl(null), request, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ShipWreck responseObject = TestUtility.fromJSONString(responseEntity.getBody(), ShipWreck.class);
        assertThat(responseObject.getName()).isEqualTo(newShipWreck.getName());

        // update this above record.
        responseObject.setName("Updated ShipWreck using Testcase!");
        request = new HttpEntity<ShipWreck>(responseObject, headers);
        responseEntity = restTemplate.exchange(getUrl(responseObject.getId().toString()), HttpMethod.PUT, request,
                                               String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        ShipWreck updatedResponse = TestUtility.fromJSONString(responseEntity.getBody(), ShipWreck.class);
        assertThat(updatedResponse.getName()).isEqualTo(responseObject.getName());
    }

    /**
     * Deletes the record.
     */
    @Test
    public void testShipWreckDelete() {
        ResponseEntity<String> responseEntity = null;
        try {
            // find 1st record
            responseEntity = restTemplate.getForEntity(getUrl("1"), String.class);
            assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
            ShipWreck findResponse = TestUtility.fromJSONString(responseEntity.getBody(), ShipWreck.class);
            assertEquals(1L, findResponse.getId().longValue());

            // delete it now this.
            restTemplate.delete(getUrl(findResponse.getId().toString()));
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

            responseEntity = restTemplate.getForEntity(getUrl(findResponse.getId().toString()), String.class);
            assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        } catch (Exception ex) {
            ex.printStackTrace();
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        }
    }

}
