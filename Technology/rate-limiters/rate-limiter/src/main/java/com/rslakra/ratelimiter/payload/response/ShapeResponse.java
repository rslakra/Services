package com.rslakra.ratelimiter.payload.response;

import com.rslakra.ratelimiter.enums.ShapeType;
import lombok.Data;

/**
 * @author Rohtash Lakra
 * @created 4/14/23 3:46 PM
 */
@Data
public class ShapeResponse {

    private ShapeType shape;
    private Double perimeter;
}
