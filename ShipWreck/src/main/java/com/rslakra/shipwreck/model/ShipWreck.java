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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
@Entity
//@Table(name = "ShipWreck")
public class ShipWreck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Column(name = "Name")
	private String name;

//	@Column(name = "Description")
	private String description;

//	@Column(name = "Condition")
	private String condition;

//	@Column(name = "Depth")
	private Integer depth;

//	@Column(name = "Latitude")
	private Double latitude;

//	@Column(name = "Longitude")
	private Double longitude;

//	@Column(name = "YearDiscovered")
	private Integer yearDiscovered;

	public ShipWreck() {
	}

	/**
	 * 
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
		this.id = id;
		this.name = name;
		this.description = description;
		this.condition = condition;
		this.depth = depth;
		this.latitude = latitude;
		this.longitude = longitude;
		this.yearDiscovered = yearDiscovered;
	}

	/**
	 * Returns the value of the id.
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * The id to be set.
	 *
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns the value of the name.
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * The name to be set.
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the value of the description.
	 *
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * The description to be set.
	 *
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the value of the condition.
	 *
	 * @return condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * The condition to be set.
	 *
	 * @param condition
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * Returns the value of the depth.
	 *
	 * @return depth
	 */
	public Integer getDepth() {
		return depth;
	}

	/**
	 * The depth to be set.
	 *
	 * @param depth
	 */
	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	/**
	 * Returns the value of the latitude.
	 *
	 * @return latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * The latitude to be set.
	 *
	 * @param latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Returns the value of the longitude.
	 *
	 * @return longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * The longitude to be set.
	 *
	 * @param longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Returns the value of the yearDiscovered.
	 *
	 * @return yearDiscovered
	 */
	public Integer getYearDiscovered() {
		return yearDiscovered;
	}

	/**
	 * The yearDiscovered to be set.
	 *
	 * @param yearDiscovered
	 */
	public void setYearDiscovered(Integer yearDiscovered) {
		this.yearDiscovered = yearDiscovered;
	}

	/**
	 * Returns the string representation of this object.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

}
