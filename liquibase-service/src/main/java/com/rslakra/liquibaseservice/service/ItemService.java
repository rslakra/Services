package com.rslakra.liquibaseservice.service;

import com.rslakra.liquibaseservice.persistence.entity.entity.Item;
import com.rslakra.liquibaseservice.exception.NoRecordFoundException;
import com.rslakra.liquibaseservice.persistence.repository.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 10/20/22 5:23 PM
 */
@Service
public class ItemService extends BaseService<Item, Long> {

    @Autowired
    private ItemRepository itemRepository;

    /**
     * @param item
     * @return
     */
    public Item create(Item item) {
        return itemRepository.save(item);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("No Record Found!"));
    }

    /**
     * @return
     */
    public List<Item> filter() {
        return itemRepository.findAll();
    }

    /**
     * @param item
     * @return
     */
    public Item update(Item item) {
        Item oldItem = item;
        if (item != null && item.getId() != null) {
            oldItem = findById(item.getId());
            BeanUtils.copyProperties(item, oldItem);
        }

        item = itemRepository.save(oldItem);
        return item;
    }

    /**
     * @param id
     * @return
     */
    public Item delete(Long id) {
        Item item = findById(id);
        if (item != null) {
            itemRepository.delete(item);
        }

        return item;
    }
}
