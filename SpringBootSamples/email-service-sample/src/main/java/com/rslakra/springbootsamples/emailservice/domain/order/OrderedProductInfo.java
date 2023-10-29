package com.rslakra.springbootsamples.emailservice.domain.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 1/6/22 5:06 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderedProductInfo {

    private String productName;
    private String color;

}
