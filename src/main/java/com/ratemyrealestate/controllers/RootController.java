package com.ratemyrealestate.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class RootController {

	// no longer need this as we can add a view resolver in a config class to
	// map urls to basic views.
	// @RequestMapping("/")
	//// @ResponseBody this is only needed when we are using @Controller, not
	// @RestController, and not using spring.mvc.view.prefix
	// in application.properties
	// public String home() {
	// return "home";
	// }
}
