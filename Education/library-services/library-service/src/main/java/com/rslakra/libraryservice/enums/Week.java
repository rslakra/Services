package com.rslakra.swaggerservice.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @version 1.0.0
 * @since Aug 08, 2021 15:49:10
 */
public enum Week {
    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);

    private static final Map<Integer, Week> weekLookup = new HashMap<>();

    private int code;

    private Week(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * @param code
     * @return
     */
    public static Week get(int code) {
        if (weekLookup.isEmpty()) {
            EnumSet.allOf(Week.class).forEach(week -> weekLookup.put(week.getCode(), week));
        }

        return weekLookup.get(code);
    }
}
