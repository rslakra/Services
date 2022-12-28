package com.rslakra.springbootsamples.springbootaspects;

import com.rslakra.springbootsamples.springbootaspects.service.LogMethodExecutionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Rohtash Lakra (rlakra)
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
