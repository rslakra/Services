package com.rslakra.liquibaseservice;

import com.rslakra.liquibaseservice.persistence.entity.House;
import com.rslakra.liquibaseservice.persistence.entity.Item;
import com.rslakra.liquibaseservice.persistence.repository.HouseRepository;
import com.rslakra.liquibaseservice.persistence.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LiquibaseApplication {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(LiquibaseApplication.class);
    private static final boolean ADD_DUMMY_RECORDS = false;

    /**
     * @param args
     */
    public static void main(String[] args) {
        LOGGER.debug("Launching LiquibaseApplication ...");
        SpringApplication.run(LiquibaseApplication.class, args);
    }


    /**
     * @param houseRepository
     * @param itemRepository
     * @return
     */
    @Bean
    CommandLineRunner init(HouseRepository houseRepository, ItemRepository itemRepository) {
        return args -> {
            if (ADD_DUMMY_RECORDS) {
                LOGGER.info("Saving house...");
                var house = new House();
                house.setOwner("Rohtash Lakra");
                house.setFullyPaid(true);
                house = houseRepository.save(house);
                LOGGER.info("Saved house instance: {}", house);

                LOGGER.info("Saving item in house");
                var item = new Item();
                item.setName("Washing machine");
                item.setHouse(house);
                item = itemRepository.save(item);
                LOGGER.info("Saved item {} in house", item);
            }
        };
    }

}
