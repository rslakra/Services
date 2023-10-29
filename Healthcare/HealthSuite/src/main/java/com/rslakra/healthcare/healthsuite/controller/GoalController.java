package com.rslakra.healthcare.healthsuite.controller;

import javax.validation.Valid;

import com.rslakra.healthcare.healthsuite.model.Goal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author rlakra
 *
 */
@Controller
@SessionAttributes("goal")
public class GoalController {

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addGoal", method = RequestMethod.GET)
	public String addGoal(Model model) {
		Goal goal = new Goal();
		goal.setMinutes(10);
		model.addAttribute("goal", goal);

		return "addGoal";
	}

	/**
	 * 
	 * @param goal
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "addGoal", method = RequestMethod.POST)
	public String updateGoal(@Valid @ModelAttribute("goal") Goal goal, BindingResult result) {

		System.out.println("result has errors: " + result.hasErrors());

		System.out.println("Goal set: " + goal.getMinutes());

		if (result.hasErrors()) {
			return "addGoal";
		}

		return "redirect:index.jsp";
	}

}
