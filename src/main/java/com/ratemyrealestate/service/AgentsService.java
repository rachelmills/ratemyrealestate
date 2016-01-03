package com.ratemyrealestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ratemyrealestate.dao.AgentsDAO;
import com.ratemyrealestate.entities.Agent;

@Service
public class AgentsService {
	
	private AgentsDAO agentsDAO;

//	public List<Agent> getCurrent() {
//		return agentsDAO.getAgents();
//	}
//	
	public Page<Agent> getCurrent(int pageNumber) {
		return agentsDAO.getAgents(pageNumber);
	}

	public void createAgent(Agent agent) {
		agentsDAO.create(agent);
	}

//	public Agent getAgent(int id) {
//		return agentsDAO.getAgent(id);
//	}
//	
	public List<Agent> search(String agentName) {
		return agentsDAO.search(agentName);
	}
	
//	public String getAgentName(int agentId) {
//		return agentsDAO.getAgentName(agentId);
//	}
	
	@Autowired
	public void setAgentsDAO(AgentsDAO agentsDAO) {
		this.agentsDAO = agentsDAO;
	}

}
