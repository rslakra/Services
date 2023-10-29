package com.rslakra.healthcare.healthsuite.model;

import org.hibernate.validator.constraints.Range;

/**
 * 
 * @author rlakra
 *
 */
public class Goal {

	@Range(min = 1, max = 120)
	private int minutes;

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

}
