package com.rslakra.thymeleaffragments.persistence.repository;

import com.rslakra.thymeleaffragments.persistence.entity.City;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class CityRepository {

    private final Set<City> cities = new HashSet<>();

    public Optional<City> find(String id) {
        return cities
            .stream()
            .filter(c -> c.getId().equals(id))
            .findFirst();
    }

    public void add(City city) {
        cities.add(city);
    }

    public void update(City city) {
        remove(city.getId());
        add(city);
    }


    public void remove(String id) {
        if (StringUtils.isNotBlank(id)) {
            cities.removeIf(c -> c.getId().equals(id));
        }
    }

    public List<City> getAll() {
        List<City> cityList = new ArrayList<>(cities);
        cityList.sort(Comparator.comparing(City::getName));
        return cityList;
    }
}