package com.rslakra.services.automobile.domain.util;

import com.rslakra.services.automobile.config.DateTimeConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Rohtash Lakra
 * @created 4/26/23 5:55 PM
 */
public enum TimeUtils {
    INSTANCE;

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DateTimeConfig.DATE_PATTERN);

    /**
     * @return
     */
    public static LocalDate now() {
        return LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * @param dateString
     * @param pattern
     * @param timeZone
     * @return
     */
    private Date parseDate(String dateString, String pattern, String timeZone) {
        if (null != dateString) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
                return simpleDateFormat.parse(dateString);
            } catch (ParseException ex) {
                ex.printStackTrace();
//                LOGGER.info(CLAZZ, "parseDate", e, "Unable to parse date %s", dateStr);
            }
        }

        return null;
    }
}
