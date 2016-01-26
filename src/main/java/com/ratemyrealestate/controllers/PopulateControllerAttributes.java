package com.ratemyrealestate.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ratemyrealestate.dao.AgentSearch;
import com.ratemyrealestate.service.UsersService;

@ControllerAdvice
public class PopulateControllerAttributes {
	
	@Autowired
	UsersService usersService;
	
	@ModelAttribute("agentSearch")
	public AgentSearch getAgentSearch() {
		return new AgentSearch();
	}
	
//	@ModelAttribute("userid")
//	public int getUserId(Principal principal) {
//		int id = usersService.getUser(principal.getName()).getId();
//		return id;		
//	}
}