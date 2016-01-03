package com.ratemyrealestate.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

// this annotation is to tell hibernate this is to be considered as a bean, 
//and which table to map to with primary key
@Entity
@Table(name="rating")
public class Rating {

	@Id
	@GeneratedValue
	private int id;
	
	private int rating;
	
	// tell hibernate this is a many to one relationship and say what join column is
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;

	@ManyToOne
	@JoinColumn(name="agentID")
	private Agent agent;
	
	@Transient
	private boolean ratedByUser = false;
	
	public Rating() {
	}
	
	public Rating(Agent agent, int rating) {
		this.agent = agent;
		this.rating = rating;
	}

	public Rating(Agent agent, User user, int rating) {
		this.agent = agent;
		this.user = user;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	public boolean isRatedByUser() {
		return ratedByUser;
	}

	public void setRatedByUser(boolean ratedByUser) {
		this.ratedByUser = ratedByUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agent == null) ? 0 : agent.hashCode());
		result = prime * result + rating;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Rating other = (Rating) obj;
		if (agent == null) {
			if (other.agent != null)
				return false;
		} else if (!agent.equals(other.agent))
			return false;
		if (rating != other.rating)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", user = "
				+ user + ", rating=" + rating + "]";
	}
}
