package com.ratemyrealestate.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ratemyrealestate.dao.AgentSearch;

@ControllerAdvice
public class PopulateControllerAttributes {
	
	@ModelAttribute("agentSearch")
	public AgentSearch getAgentSearch() {
		return new AgentSearch();
	}
}