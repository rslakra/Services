package com.rslakra.services.automobile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.format.DateTimeFormatter;

/**
 * @author Rohtash Lakra
 * @created 4/26/23 4:58 PM
 */
@Configuration
public class DateTimeConfig extends WebMvcConfigurationSupport {

    public static final String DATE_PATTERN = "MM-dd-yyyy";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * @return
     */
    @Bean
    @Override
    public FormattingConversionService mvcConversionService() {
        /** DefaultFormattingConversionService with a false parameter, which means Spring won't register any formatters by default. */
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        DateTimeFormatterRegistrar dateTimeRegistrar = new DateTimeFormatterRegistrar();
        dateTimeRegistrar.setDateFormatter(DateTimeFormatter.ofPattern(DATE_PATTERN));
        dateTimeRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
        dateTimeRegistrar.registerFormatters(conversionService);

        DateFormatterRegistrar dateRegistrar = new DateFormatterRegistrar();
        dateRegistrar.setFormatter(new DateFormatter(DATE_PATTERN));
        dateRegistrar.registerFormatters(conversionService);

        return conversionService;
    }
}