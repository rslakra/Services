package com.rslakra.liquibaseservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.rslakra.liquibaseservice.persistence.entity.entity.House;
import com.rslakra.liquibaseservice.persistence.repository.HouseRepository;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class HouseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private HouseRepository houseRepository;

    public static House createHouse(Long id, String owner, boolean fullyPaid) {
        House house = new House();
        house.setId(id);
        house.setOwner(owner);
        house.setFullyPaid(fullyPaid);
        return house;
    }

    public static House createHouse(String owner, boolean fullyPaid) {
        return createHouse(null, owner, fullyPaid);
    }

    public static House createHouse(String owner) {
        return createHouse(null, owner, false);
    }


    @BeforeEach
    void before() {
        var house = createHouse("Rohtash Lakra", true);
        house = entityManager.persistAndFlush(house);
    }

    @Test
    @DisplayName("find house by id")
    void testFindById() {
        var house = houseRepository.findById(1L);
        var condition = new Condition<House>(h -> h.isFullyPaid(), "Is fully paid");
        assertThat(house).isPresent();
        assertThat(house).hasValueSatisfying(condition);
    }
}
