package com.rslakra.springbootsamples.springbootsample.domain.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 1/6/22 5:06 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderedProductInfo {

    private String productName;
    private String color;

}
