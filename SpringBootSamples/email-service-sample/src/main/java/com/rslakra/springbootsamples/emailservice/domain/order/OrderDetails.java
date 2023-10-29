package com.rslakra.springbootsamples.emailservice.domain.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author Rohtash Lakra
 * @created 1/6/22 5:01 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderDetails {

    private Set<OrderedProducts> orderedProducts;

}
