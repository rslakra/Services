package com.rslakra.ratelimiter.controller;

import com.rslakra.ratelimiter.payload.request.ShapeRequest;
import com.rslakra.ratelimiter.payload.response.ShapeResponse;
import com.rslakra.ratelimiter.service.ShapeService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping(value = "/api/v1/perimeters", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShapeController {

    private final Bucket bucket;
    private final ShapeService shapeService;

    @Autowired
    public ShapeController(ShapeService shapeService) {
        this.shapeService = shapeService;
        Bandwidth limit = Bandwidth.classic(50, Refill.greedy(50, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
            .addLimit(limit)
            .build();
    }

    /**
     * @param shapeRequest
     * @return
     */
    @PostMapping(value = "/rectangle")
    public ResponseEntity<ShapeResponse> rectangle(@RequestBody ShapeRequest shapeRequest) {
        ShapeResponse shapeResponse;
        if (bucket.tryConsume(1)) {
            shapeResponse = shapeService.perimeter(shapeRequest);
            return ResponseEntity.ok(shapeResponse);
        }

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
