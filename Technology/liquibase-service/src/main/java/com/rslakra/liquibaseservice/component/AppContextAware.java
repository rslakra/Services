package com.rslakra.liquibaseservice.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:49 PM
 */
@Component
public class AppContextAware implements ApplicationContextAware {

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
