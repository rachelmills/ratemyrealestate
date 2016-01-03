package com.ratemyrealestate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {
	
	@RequestMapping("/about")
	public String showAbout(Model model) {
		return "about";
	}
}
