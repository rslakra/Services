package com.rslakra.ratelimiter.persistence.model.geometric.twodimensional;

import com.rslakra.ratelimiter.persistence.model.Dimension;
import com.rslakra.ratelimiter.persistence.model.geometric.GeometricShape;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Rohtash Lakra
 * @created 4/14/23 3:18 PM
 */
@Data
public class Rectangle extends GeometricShape {

    private Dimension dimension;

    /**
     * @return
     */
    public Double perimeter() {
        return BigDecimal.valueOf(2 * (dimension.getLength() + dimension.getWidth()))
            .setScale(2, RoundingMode.HALF_UP)
            .doubleValue();
    }
}
