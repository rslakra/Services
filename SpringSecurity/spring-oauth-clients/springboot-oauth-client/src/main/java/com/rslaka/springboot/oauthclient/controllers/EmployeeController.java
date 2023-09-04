package com.rslaka.springboot.oauthclient.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rslaka.springboot.oauthclient.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Value("${authServiceBaseUrl}")
    private String authServiceBaseUrl;

    /**
     * @return
     */
    @RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
    public ModelAndView getEmployeeInfo() {
        LOGGER.debug("getEmployeeInfo()");
        return new ModelAndView("getEmployees");
    }

    /**
     * @param code
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     */
    @RequestMapping(value = "/showEmployees", method = RequestMethod.GET)
    public ModelAndView showEmployees(@RequestParam(value = "code", required = false) String code)
        throws JsonProcessingException, IOException {
        LOGGER.debug("+showEmployees({})", code);
        ResponseEntity<String> response = null;
        List<Employee> employees = null;

        if (Objects.isNull(code)) {
            employees = new ArrayList<>();
            employees.add(new Employee(UUID.randomUUID().toString(), "Rohtash Lakra"));
            employees.add(new Employee(UUID.randomUUID().toString(), "Rohtash Singh"));
            employees.add(new Employee(UUID.randomUUID().toString(), "Rohtash Singh Lakra"));
        } else {
            LOGGER.debug("Authorization Code: {}", code);
            RestTemplate restTemplate = new RestTemplate();
            String credentials = "oauth:secret";
            String encodedCredentials = new String(Base64.getEncoder().encode(credentials.getBytes()));
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("Authorization", "Basic " + encodedCredentials);
            HttpEntity<String> request = new HttpEntity<String>(headers);
            // access-token-url
            StringBuilder urlBuilder = new StringBuilder(authServiceBaseUrl);
            urlBuilder.append("/oauth/token?code=").append(code);
            urlBuilder.append("&grant_type=authorization_code");
            urlBuilder.append("&redirect_uri=http://localhost:8090/showEmployees");
            LOGGER.debug("urlBuilder:{}", urlBuilder);
            response = restTemplate.exchange(urlBuilder.toString(), HttpMethod.POST, request, String.class);
            LOGGER.debug("response.body:{}", response.getBody());

            // Get the Access Token From the received JSON response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.getBody());
            String token = node.path("access_token").asText();

            // Use the access token for authentication
            urlBuilder = new StringBuilder(authServiceBaseUrl);
            urlBuilder.append("/user/getEmployeesList");
            LOGGER.debug("urlBuilder:{}", urlBuilder);
            HttpHeaders reqHeaders = new HttpHeaders();
            reqHeaders.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(reqHeaders);
            ResponseEntity<Employee[]>
                responseEntity =
                restTemplate.exchange(urlBuilder.toString(), HttpMethod.GET, entity, Employee[].class);
            LOGGER.debug("responseEntity:{}", responseEntity);
            employees = Arrays.asList(responseEntity.getBody());
        }

        LOGGER.debug("employees:{}", employees);
        ModelAndView model = new ModelAndView("showEmployees");
        model.addObject("employees", employees);
        LOGGER.debug("-showEmployees(), model: {}", model);
        return model;
    }
}
