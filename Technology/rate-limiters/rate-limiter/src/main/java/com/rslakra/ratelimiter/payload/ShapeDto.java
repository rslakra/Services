package com.rslakra.ratelimiter.payload;

import com.rslakra.ratelimiter.enums.ShapeType;
import lombok.Data;

/**
 * @author Rohtash Lakra
 * @created 4/14/23 3:41 PM
 */
@Data
public class ShapeDto {

    private ShapeType shapeType;
    private Double length;
    private Double width;
    private Double perimeter;

}
