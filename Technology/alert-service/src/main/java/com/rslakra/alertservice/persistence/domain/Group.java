package com.rslakra.alertservice.persistence.domain;

import java.util.Set;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 9/15/23 2:28 PM
 */
public class Group {

    private UUID uuid;
    private String name;
    private Set<UUID> members;

}
