package com.rslakra.liquibaseservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.rslakra.liquibaseservice.persistence.entity.House;
import com.rslakra.liquibaseservice.persistence.entity.Item;
import com.rslakra.liquibaseservice.persistence.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

@DataJpaTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = AutowireMode.ALL)
public class ItemRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void before() {
        var house = new House();
        house.setOwner("Rohtash Lakra");
        house.setFullyPaid(true);
        house = entityManager.persist(house);

        var item = new Item();
        item.setName("Washing Machine");
        item.setHouse(entityManager.getEntityManager().getReference(House.class, 1));
        item = entityManager.persist(item);
    }

    @Test
    @DisplayName("find item by id")
    void testFindById() {
        var item = itemRepository.findById(2L);
        var
            condition =
            new Condition<Item>(i -> "Washing Machine".equals(i.getName()), "Name matches 'Washing Machine'");
        assertThat(item).isPresent();
        assertThat(item).hasValueSatisfying(condition);
    }
}
