package com.rslakra.libraryservice.service.context;

import com.rslakra.libraryservice.service.ContextService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:49 PM
 */
@Service
public class ContextServiceImpl implements ContextService {

    private static ApplicationContext appContext;

    /**
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    /**
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> beanClass) {
        return appContext.getBean(beanClass);
    }
}
