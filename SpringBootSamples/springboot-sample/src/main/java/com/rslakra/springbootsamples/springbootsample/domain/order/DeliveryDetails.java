package com.rslakra.springbootsamples.springbootsample.domain.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 1/6/22 5:08 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class DeliveryDetails {

    private String houseNumber;
    private String street;
    private String city;
    private String state;
    private String country;

}
