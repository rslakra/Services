/**
 * 
 */
package com.rslakra.shipwreck.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 *
 */
@RestController
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "Shipwreck welcomes you!";
	}

}
