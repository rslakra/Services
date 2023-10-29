package com.rslakra.healthcare.healthsuite.service;

import java.util.List;

import com.rslakra.healthcare.healthsuite.model.Activity;

/**
 * 
 * @author rlakra
 *
 */
public interface ExerciseService {

	/**
	 * 
	 * @return
	 */
	List<Activity> findAllActivities();

}
