package com.rslakra.springbootsamples.springbootcommon.utils;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 11/19/21 5:56 PM
 */
public enum TimeUtils {

    INSTANCE;

    /**
     * @param date
     * @return
     */
    public static String toGMTDate(final Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
    }

    /**
     * @param date
     * @return
     */
    public static String toISODate(final Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
    }

}
