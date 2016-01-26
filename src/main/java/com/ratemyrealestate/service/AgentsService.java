package com.ratemyrealestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ratemyrealestate.dao.AgentsDAO;
import com.ratemyrealestate.entities.Agent;

@Service
public class AgentsService {

	private AgentsDAO agentsDAO;

	public Page<Agent> getCurrent(int pageNumber) {
		return agentsDAO.getAgents(pageNumber);
	}

	public void createAgent(Agent agent) {
		agentsDAO.create(agent);
	}

	public Agent getAgent(int id) {
		return agentsDAO.getAgent(id);
	}

	public Page<Agent> findAll(PageRequest page) {
		return agentsDAO.findAll(page);
	}

	public Page<Agent> findAllByAgentnameContainingIgnoringCase(String agentName, PageRequest page) {
		return agentsDAO.findAllByAgentName(agentName, page);
	}
	
	@Autowired
	public void setAgentsDAO(AgentsDAO agentsDAO) {
		this.agentsDAO = agentsDAO;
	}
}
