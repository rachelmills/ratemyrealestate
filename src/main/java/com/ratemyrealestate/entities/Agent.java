package com.ratemyrealestate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="agent")
public class Agent {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@NotNull
	@Size(min=5, max=50)
	@Column(name="agentname")
	private String agentname;
	
	@NotNull
	@Size(min=5, max=20)
	private String suburb;

	public Agent() {
	}

	public Agent(String agentname, String suburb) {
		this.agentname = agentname;
		this.suburb = suburb;
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

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	
	public String getSuburb() {
		return suburb;
	}
	
	@Override
	public String toString() {
		return "Agent [id=" + id + ", agentName=" + agentname + " + suburb=" + suburb + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((agentname == null) ? 0 : agentname.hashCode());
		result = prime * result + ((suburb == null) ? 0 : suburb.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agent other = (Agent) obj;
		if (agentname == null) {
			if (other.agentname != null)
				return false;
		} else if (!agentname.equals(other.agentname))
			return false;
		if (suburb == null) {
			if (other.suburb != null)
				return false;
		} else if (!suburb.equals(other.suburb))
			return false;
		return true;
	}
	
	

}
