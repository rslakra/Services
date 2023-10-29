package com.rslakra.thymeleaffragments;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rslakra.thymeleaffragments.config.StaticDataProperties;
import com.rslakra.thymeleaffragments.persistence.entity.City;
import com.rslakra.thymeleaffragments.persistence.repository.CityRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties(StaticDataProperties.class)
public class CitiesInitializer implements InitializingBean {

    @Resource
    private CityRepository cityRepository;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private StaticDataProperties staticDataProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        org.springframework.core.io.Resource resource = new ClassPathResource(staticDataProperties.getJsonFileName());

        List<City> cities;
        try (InputStream inputStream = resource.getInputStream()) {
            cities = objectMapper.readValue(inputStream, new TypeReference<List<City>>() {
            });
        }
        cities.forEach(cityRepository::add);
    }
}