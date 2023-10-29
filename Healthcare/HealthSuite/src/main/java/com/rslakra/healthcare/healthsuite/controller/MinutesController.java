package com.rslakra.healthcare.healthsuite.controller;

import java.util.List;

import javax.validation.Valid;

import com.rslakra.healthcare.healthsuite.model.Activity;
import com.rslakra.healthcare.healthsuite.model.Exercise;
import com.rslakra.healthcare.healthsuite.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author rlakra
 *
 */
@Controller
public class MinutesController {

	@Autowired
	private ExerciseService exerciseService;

	/**
	 * 
	 * @param exercise
	 * @return
	 */
	@RequestMapping(value = "/addMinutes", method = RequestMethod.GET)
	public String getMinutes(@ModelAttribute("exercise") Exercise exercise) {

		return "addMinutes";
	}

	/**
	 * 
	 * @param exercise
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/addMinutes", method = RequestMethod.POST)
	public String addMinutes(@Valid @ModelAttribute("exercise") Exercise exercise, BindingResult result) {

		System.out.println("exercise: " + exercise.getMinutes());
		System.out.println("exercise activity: " + exercise.getActivity());

		if (result.hasErrors()) {
			return "addMinutes";
		}

		return "addMinutes";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/activities", method = RequestMethod.GET)
	public @ResponseBody List<Activity> findAllActivities() {
		return exerciseService.findAllActivities();
	}

}
