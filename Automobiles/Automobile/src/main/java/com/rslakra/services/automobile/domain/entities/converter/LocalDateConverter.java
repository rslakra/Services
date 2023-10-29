package com.rslakra.services.automobile.domain.entities.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @since 09-16-2019 1:33:16 PM
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateConverter.class);

    /**
     * @param localDate
     * @return
     */
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        LOGGER.debug("convertToDatabaseColumn({})", localDate);
        return (null == localDate ? null : Date.valueOf(localDate));
    }

    /**
     * @param date
     * @return
     */
    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        LOGGER.debug("convertToEntityAttribute({})", date);
        return (null == date ? null : date.toLocalDate());
    }

}
