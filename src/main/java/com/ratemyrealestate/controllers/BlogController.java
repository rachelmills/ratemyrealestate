package com.ratemyrealestate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {

	@RequestMapping("/createblogentry")
	public String createBlogEntry() {
		return "createblogentry";
	}	
}
