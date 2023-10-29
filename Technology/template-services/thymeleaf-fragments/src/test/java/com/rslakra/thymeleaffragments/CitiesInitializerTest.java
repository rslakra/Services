package com.rslakra.thymeleaffragments;

import static org.assertj.core.api.Assertions.assertThat;

import com.rslakra.thymeleaffragments.persistence.entity.City;
import com.rslakra.thymeleaffragments.persistence.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import javax.annotation.Resource;

@SpringBootTest(classes = ThymeleafFragmentsApplication.class)
@WebAppConfiguration
class CitiesInitializerTest {

    @Resource
    private CityRepository cityRepository;

    @Test
    void should_have_inserted_cities() {
        List<City> cities = cityRepository.getAll();

        assertThat(cities).isNotEmpty();
    }
}