package com.ratemyrealestate.dao;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AgentSearch {

	private int id;

	@NotNull
	@NotBlank
	private String agentname;

	public AgentSearch() {
	}

	public AgentSearch(String agentName) {
		this.agentname = agentName;
	}

	public AgentSearch(int id, String agentName) {
		this.id = id;
		this.agentname = agentName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAgentname() {
		return agentname;
	}

	public void setAgentname(String agentName) {
		this.agentname = agentName;
	}

	@Override
	public String toString() {
		return "Agent [id=" + id + ", agentname=" + agentname + "]";
	}

}
