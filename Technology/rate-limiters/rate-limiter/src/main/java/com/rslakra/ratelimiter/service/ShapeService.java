package com.rslakra.ratelimiter.service;

import com.rslakra.ratelimiter.payload.request.ShapeRequest;
import com.rslakra.ratelimiter.payload.response.ShapeResponse;
import com.rslakra.ratelimiter.persistence.model.geometric.twodimensional.Rectangle;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 4/14/23 3:10 PM
 */
@Service
public class ShapeService {

    /**
     * @param shapeRequest
     * @return
     */
    public ShapeResponse perimeter(ShapeRequest shapeRequest) {
        if (Objects.isNull(shapeRequest.getShape())) {
            throw new IllegalArgumentException("The shape type should provide!");
        }

        ShapeResponse shapeResponse = new ShapeResponse();
        shapeResponse.setShape(shapeRequest.getShape());
        switch (shapeRequest.getShape()) {
            case ARC:
                break;

            case RECTANGLE:
                Rectangle rectangle = new Rectangle();
                rectangle.setDimension(shapeRequest.getDimension());
                shapeResponse.setPerimeter(rectangle.perimeter());
                break;
        }

        return shapeResponse;
    }


}
