package com.rslakra.springbootsamples.aopbeforeadvice.controller;

import com.rslakra.springbootsamples.aopbeforeadvice.aspect.Loggable;
import com.rslakra.springbootsamples.aopbeforeadvice.model.Advice;
import com.rslakra.springbootsamples.aopbeforeadvice.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("${restPrefix}/advices")
public class AdviceController {

    private final AdviceService adviceService;

    /**
     * @param adviceService
     */
    @Autowired
    public AdviceController(AdviceService adviceService) {
        this.adviceService = adviceService;
    }


    @Loggable(localOnly = true)
    @GetMapping
    @ResponseBody
    public List<Advice> getAll() {
        return adviceService.getAll();
    }

    @Loggable(localOnly = true)
    @GetMapping("/filter")
    public List<Advice> getByFilter(@RequestParam Map<String, Object> allParams) {
        if (Objects.nonNull(allParams)) {
            if (allParams.containsKey("id")) {
                Long id = Long.parseLong(allParams.get("id").toString());
                return Arrays.asList(adviceService.getById(id));
            }
        }

        return Collections.emptyList();
    }

    /**
     * @param advice
     */
    @Loggable
    @PostMapping
    @ResponseBody
    public Advice addAdvice(@RequestBody Advice advice) {
        return adviceService.create(advice);
    }

    /**
     * @param advices
     * @return
     */
    @PostMapping("/batch")
    @ResponseBody
    public List<Advice> addAdvices(@RequestBody List<Advice> advices) {
        final List<Advice> adviceList = new LinkedList<>();
        if (Objects.nonNull(advices)) {
            advices.forEach(advice -> adviceList.add(addAdvice(advice)));
        }

        return adviceList;
    }


}
