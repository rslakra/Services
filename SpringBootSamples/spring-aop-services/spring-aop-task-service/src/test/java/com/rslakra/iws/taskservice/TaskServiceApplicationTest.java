package com.rslakra.iws.taskservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.devamatre.framework.spring.unittest.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/25/22 4:43 PM
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
public class TaskServiceApplicationTest {

    @LocalServerPort
    private int localServerPort;

    @Value("${local.management.port}")
    private int localManagementPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map>
            entity =
            this.testRestTemplate.getForEntity(TestUtils.pathString(this.localManagementPort, "actuator"), Map.class);
        assertNotNull(entity);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void shouldReturn200WhenSendingRequestToController() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<List>
            entity =
            this.testRestTemplate.getForEntity(TestUtils.pathString(this.localServerPort, "/"), List.class);
        assertNotNull(entity);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }


}
