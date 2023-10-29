/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rslakra.shipwreck.model;

import com.devamatre.framework.core.ToString;
import com.devamatre.framework.spring.persistence.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shipwrecks")
public class ShipWreck extends AbstractEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "condition")
    private String condition;

    @Column(name = "depth")
    private Integer depth;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "year_discovered")
    private Integer yearDiscovered;

    /**
     * @param id
     * @param name
     * @param description
     * @param condition
     * @param depth
     * @param latitude
     * @param longitude
     * @param yearDiscovered
     */
    public ShipWreck(Long id, String name, String description, String condition, Integer depth, Double latitude,
                     Double longitude, Integer yearDiscovered) {
        setId(id);
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.depth = depth;
        this.latitude = latitude;
        this.longitude = longitude;
        this.yearDiscovered = yearDiscovered;
    }

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    public String toString() {
        return ToString.of(ShipWreck.class)
            .add("id", getId())
            .add("name", getName())
            .add("description", getDescription())
            .add("condition", getCondition())
            .add("depth", getDepth())
            .add("latitude", getLatitude())
            .add("longitude", getLongitude())
            .add("yearDiscovered", getYearDiscovered())
            .toString();
    }

}
