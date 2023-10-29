package com.rslakra.thymeleaffragments.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.rslakra.thymeleaffragments.persistence.entity.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class CityRepositoryTest {

    private static final String CITY_ID = "sim";
    private CityRepository cityRepository;
    private City simCity;

    @BeforeEach
    void setup() {
        cityRepository = new CityRepository();
        simCity = new City(CITY_ID, "Sim City", 2016, 123_456);
        cityRepository.add(simCity);
    }

    @Test
    void should_return_empty_optional_for_unknown_id() {
        Optional<City> city = cityRepository.find("unknown");

        assertThat(city).isNotPresent();
    }

    @Test
    void should_add_city() {
        Optional<City> city = cityRepository.find(CITY_ID);

        assertThat(city)
            .isPresent()
            .hasValue(simCity);
    }

    @Test
    void should_update_city() {
        simCity.setFoundedIn(2015);
        cityRepository.update(simCity);

        Optional<City> city = cityRepository.find(CITY_ID);

        assertThat(city)
            .isPresent()
            .hasValueSatisfying(s -> assertThat(s.getFoundedIn()).isEqualTo(2015));
    }

    @Test
    void should_remove_city() {
        cityRepository.remove(CITY_ID);

        Optional<City> city = cityRepository.find(CITY_ID);

        assertThat(city).isNotPresent();
    }

    @Test
    void should_find_all_cities() {
        List<City> cities = cityRepository.getAll();

        assertThat(cities)
            .hasSize(1)
            .containsExactly(simCity);
    }
}