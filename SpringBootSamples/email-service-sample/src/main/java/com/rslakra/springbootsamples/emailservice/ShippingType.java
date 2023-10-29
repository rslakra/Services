package com.rslakra.springbootsamples.emailservice;

/**
 * @author Rohtash Lakra
 * @created 1/6/22 5:11 PM
 */
public enum ShippingType {
    EXPRESS,
    NORMAL,
    SUPER_EXPRESS;

    /**
     * @param shippingType
     * @return
     */
    public static ShippingType of(String shippingType) {
        return ShippingType.valueOf(shippingType);
    }


    /**
     * Validate the shipping type
     *
     * @param shippingType
     * @return
     */
    public static boolean isValidShippingType(String shippingType) {
        for (ShippingType shipType : ShippingType.values()) {
            if (shippingType.equalsIgnoreCase(shippingType)) {
                return true;
            }
        }

        return false;
    }
}
