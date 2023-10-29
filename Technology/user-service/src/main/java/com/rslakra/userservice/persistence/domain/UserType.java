package com.rslakra.userservice.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 12/5/22 19:57 AM
 */
@Getter
@Setter
@NoArgsConstructor
public class UserType {
    private Long id;
    private String name;
    private String category;
    private String severity;
    private String destination;
    private String landingUrl;
    private String options;
    private Integer muteDays;
}
