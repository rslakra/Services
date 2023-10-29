# Liquibase Spring Boot Demo

This repository contains the code accompanying the tutorial at <>..

## Running the application

To run this demo, run the following command

```bash
> .\gradlew bootRun
```

## Testing

The following JUnit tests verify we can write and read from the database:

```java
@DataJpaTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = AutowireMode.ALL)
public class HouseRepositoryTest {
    private final HouseRepository houseRepository;
    private final TestEntityManager em;

    @BeforeEach
    void before() {
        var house = new House();
        house.setOwner("Rohtash Lakra");
        house.setFullyPaid(true);
        em.persist(house);
    }

    @Test
    @DisplayName("find house by id")
    void testFindById() {
        var house = houseRepository.findById(1);
        var condition = new Condition<House>(h -> h.isFullyPaid(), "Is fully paid");
        assertThat(house).isPresent();
        assertThat(house).hasValueSatisfying(condition);
    }
}

@DataJpaTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = AutowireMode.ALL)
public class ItemRepositoryTest {
    private final TestEntityManager em;
    private final ItemRepository itemRepository;

    @BeforeEach
    void before() {
        var house = new House();
        house.setOwner("James Tuffour");
        house.setFullyPaid(true);
        em.persist(house);

        var item = new Item();
        item.setName("Washing machine");
        item.setHouse(em.getEntityManager().getReference(House.class, 1));
        em.persist(item);
    }

    @Test
    @DisplayName("find item by id")
    void testFindById() {
        var item = itemRepository.findById(2);
        var condition = new Condition<Item>(i -> "Washing machine".equals(i.getName()), "Name matches 'Washing Machine'");
        assertThat(item).isPresent();
        assertThat(item).hasValueSatisfying(condition);
    }
}
```


### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.5/gradle-plugin/reference/html/#build-image)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#howto.data-access.exposing-spring-data-repositories-as-rest)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

# Author
- Rohtash Lakra