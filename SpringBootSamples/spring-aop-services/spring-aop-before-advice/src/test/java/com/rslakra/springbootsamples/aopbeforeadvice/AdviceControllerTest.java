package com.rslakra.springbootsamples.aopbeforeadvice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rslakra.springbootsamples.aopbeforeadvice.controller.AdviceController;
import com.rslakra.springbootsamples.aopbeforeadvice.model.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 1/20/22 6:07 PM
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AdviceControllerTest {

    @Autowired
    private AdviceController adviceController;

    /**
     *
     * @return
     */
    private Advice createAdvice() {
        Advice advice = new Advice();
        advice.setId(Long.valueOf(16L));
        advice.setName("Lakra");
        advice.setMessage("How are you doing?");
        return advice;
    }

    @Test
    public void testAddAdvice() {
        Advice advice = createAdvice();
        Advice adviceSaved = adviceController.addAdvice(advice);
        assertEquals(advice, adviceSaved);
    }

    @Test
    public void testGetAll() {
        Advice advice = createAdvice();
        Advice adviceSaved = adviceController.addAdvice(advice);
        assertEquals(advice, adviceSaved);
        List<Advice> advices = adviceController.getAll();
        assertNotNull(advices);
        assertEquals(1, advices.size());
    }
}
