package com.rslakra.liquibaseservice.service;

import com.rslakra.liquibaseservice.persistence.entity.House;
import com.rslakra.liquibaseservice.exception.NoRecordFoundException;
import com.rslakra.liquibaseservice.persistence.repository.HouseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 10/20/22 5:22 PM
 */
@Service
public class HouseService extends AbstractService<House, Long> {

    @Autowired
    public HouseRepository houseRepository;

    /**
     * @param house
     * @return
     */
    public House create(House house) {
        return houseRepository.save(house);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public House findById(Long id) {
        return houseRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id=%d", id));
    }

    /**
     * @return
     */
    public List<House> filter() {
        return houseRepository.findAll();
    }

    /**
     * @param house
     * @return
     */
    public House update(House house) {
        House oldHouse = house;
        if (house != null && house.getId() != null) {
            oldHouse = findById(house.getId());
            BeanUtils.copyProperties(house, oldHouse);
        }

        house = houseRepository.save(oldHouse);
        return house;
    }

    /**
     * @param id
     * @return
     */
    public House delete(Long id) {
        House house = findById(id);
        if (house != null) {
            houseRepository.delete(house);
        }

        return house;
    }

}
