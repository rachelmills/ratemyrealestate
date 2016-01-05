package com.ratemyrealestate.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ratemyrealestate.dao.AgentSearch;
import com.ratemyrealestate.entities.Agent;
import com.ratemyrealestate.repositories.AgentRepository;
import com.ratemyrealestate.service.AgentsService;

@Controller
public class AgentsController {

	private AgentsService agentsService;
	
	@Autowired
	private AgentRepository agentRepository;

	@RequestMapping("/createagent")
	public String createNewAgent(Model model) {
		model.addAttribute("agent", new Agent());
		return "createagent";
	}

	@RequestMapping(value = "/docreateagent", method = RequestMethod.POST)
	public String doCreateAgent(Model model, @Valid Agent agent, BindingResult result) {
		if (result.hasErrors()) {
			return "createagent";
		}
		agentsService.createAgent(agent);
		return "agentcreated";
	}
	
	@RequestMapping(value="/search/{pageNumber}", method=RequestMethod.GET)
	public String search(Model model, @PageableDefault(size=5) Pageable pageable, @PathVariable("pageNumber") Integer pageNumber, 
			@RequestParam(value="agentname", required=false) String agentName, @Valid AgentSearch agent, BindingResult result) {
		
		if (result.hasErrors()) {
			return "home";
		}
		
		PageRequest page = new PageRequest(pageNumber, pageable.getPageSize(), Direction.ASC, "agentname");
		
		Page<Agent> agents;
		if (agentName.equals("")) {
			agents = agentRepository.findAll(page);	
		} else {
			agents = agentRepository.findAllByAgentnameContainingIgnoringCase(agentName, page);
		}
		
		model.addAttribute("agentList", agents);
		model.addAttribute("pageable", page);
		model.addAttribute("agentname", agentName);
		
		return "agents";
	}

	@Autowired
	public void setAgentsService(AgentsService agentsService) {
		this.agentsService = agentsService;
	}
}
