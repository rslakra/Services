package com.rslakra.healthcare.healthsuite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author rlakra
 *
 */
@Controller
public class HelloController {

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/greeting")
	public String sayHello(Model model) {

		model.addAttribute("greeting", "Hello World");

		return "hello";
	}

}
