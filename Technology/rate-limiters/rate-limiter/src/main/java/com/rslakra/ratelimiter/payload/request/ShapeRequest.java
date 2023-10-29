package com.rslakra.ratelimiter.payload.request;

import com.rslakra.ratelimiter.enums.ShapeType;
import com.rslakra.ratelimiter.persistence.model.Dimension;
import lombok.Data;

/**
 * @author Rohtash Lakra
 * @created 4/14/23 3:45 PM
 */
@Data
public class ShapeRequest {

    private ShapeType shape;
    private Dimension dimension;

}
