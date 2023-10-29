package com.rslakra.springbootsamples.aoparoundadvice;

import com.rslakra.springbootsamples.aoparoundadvice.service.LogMethodExecutionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Rohtash Lakra
 * @created 1/20/22 6:07 PM
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LogMethodExecutionServiceTest {

    @Autowired
    private LogMethodExecutionService logService;

    @Test
    public void testLogExecutionTime() {
        try {
            logService.logExecutionTime();
        } catch (InterruptedException e) {
            //ignore me
        }
    }
}
