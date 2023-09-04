package com.rslakra.liquibaseservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rslakra.liquibaseservice.persistence.entity.House;
import com.rslakra.liquibaseservice.persistence.repository.HouseRepository;
import com.rslakra.liquibaseservice.repository.HouseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 10/20/22 5:47 PM
 */
@ExtendWith(SpringExtension.class)
public class HouseServiceTest {

    @TestConfiguration
    static class HouseServiceConfiguration {

        @Bean
        public HouseService houseService() {
            return new HouseService();
        }
    }

    @Autowired
    private HouseService houseService;

    @Autowired
    private HouseRepository houseRepository;

    @BeforeEach
    public void setUp() {
        House rohtash = HouseRepositoryTest.createHouse(1L, "Rohtash", true);
        House singh = HouseRepositoryTest.createHouse(2L, "Singh", false);
        House lakra = HouseRepositoryTest.createHouse(3L, "Lakra", false);
        List<House> allHouses = Arrays.asList(rohtash, singh, lakra);

        Mockito.when(houseRepository.findById(rohtash.getId())).thenReturn(Optional.of(rohtash));
        Mockito.when(houseRepository.findById(singh.getId())).thenReturn(Optional.of(singh));
        Mockito.when(houseRepository.findById(lakra.getId())).thenReturn(Optional.of(lakra));
        Mockito.when(houseRepository.findAll()).thenReturn(allHouses);
        Mockito.when(houseRepository.findById(-99L)).thenReturn(null);
    }

    /**
     *
     */
    @Test
    public void testCreate() {
        House house = HouseRepositoryTest.createHouse("Create", true);
        House found = houseService.create(house);
        assertNotNull(found);
        assertNotNull(found.getId());
        assertEquals(found.getOwner(), house.getOwner());
    }

    /**
     *
     */
    @Test
    public void testFilter() {
        House house = houseService.create(HouseRepositoryTest.createHouse("Create", true));
        assertNotNull(house);
        assertNotNull(house.getId());

        House found = houseService.findById(house.getId());
        assertNotNull(found);
        assertNotNull(found.getId());
        assertEquals(found.getOwner(), house.getOwner());
    }

    /**
     *
     */
    @Test
    public void testUpdate() {
    }


    /**
     *
     */
    @Test
    public void testDelete() {
    }

}
