package com.rslakra.springbootsamples.aopbeforeadvice.service;

import com.rslakra.springbootsamples.aopbeforeadvice.model.Advice;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdviceService {

    private static final Map<Long, Advice> CACHE = new HashMap<>();

    /**
     * @param id
     * @return
     */
    public Advice getById(Long id) {
        if (CACHE.containsKey(id)) {
            return CACHE.get(id);
        }

        throw new RuntimeException("No record found for id:" + id);
    }

    /**
     * @return
     */
    public List<Advice> getAll() {
        return CACHE.values().stream().collect(Collectors.toList());
    }

    /**
     * @param advice
     */
    public Advice create(Advice advice) {
        if (Objects.nonNull(advice)) {
            CACHE.putIfAbsent(advice.getId(), advice);
        }

        return advice;
    }
}
