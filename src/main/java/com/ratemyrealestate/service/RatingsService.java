package com.ratemyrealestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratemyrealestate.dao.RatingsDAO;
import com.ratemyrealestate.dao.UsersDAO;
import com.ratemyrealestate.entities.Rating;
import com.ratemyrealestate.entities.User;

@Service
public class RatingsService {

	private RatingsDAO ratingsDAO;

	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private AgentsService agentsService;

//	public List<Rating> getCurrent() {
//		return ratingsDAO.getRatings();
//	}
//
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void saveOrUpdate(Rating rating) {
		ratingsDAO.saveOrUpdate(rating);
	}

	public List<Rating> getRatingsForAgent(int agentId) {
		return ratingsDAO.getRatingsForAgent(agentId);
	}

	public Page<Rating> getRatingsForUser(String username, Pageable page) {
		User user = usersDAO.getUserByUsername(username);
		return ratingsDAO.getRatingsForUser(user, page);
	}
	
	public List<Rating> getRatingsForUser(String username) {
		User user = usersDAO.getUserByUsername(username);
		return ratingsDAO.getRatingsForUser(user);
	}
//	
//	public Rating getRating(int agentId, int userId) {
//		return ratingsDAO.getRating(agentId, usersDAO.getUser(userId));
//	}
//	
	public List<Rating> getRatings() {
		return ratingsDAO.getRatings();
	}
	
//	public boolean deleteRating(int agentId, int userId) {
//		return ratingsDAO.delete(agentId, usersDAO.getUser(userId));
//	}
//
	public boolean hasRatings(String name) {
		if (null == name) {
			return false;
		}

		List<Rating> ratings = ratingsDAO.getRatingsForUser(usersDAO.getUserByUsername(name));
		if (ratings.size() == 0) {
			return false;
		}
		return true;
	}

	@Transactional
	public boolean ratingExistsForUserAndAgent (int agentId, User user) {
		return ratingsDAO.ratingExistsForUserAndAgent(agentsService.getAgent(agentId), user);
	}
	
	@Autowired
	public void setRatingsDAO(RatingsDAO ratingsDAO) {
		this.ratingsDAO = ratingsDAO;
	}

	public Rating getRatingForUserAndAgent(Integer agentId, User user) {
		return ratingsDAO.getRating(agentsService.getAgent(agentId), user);
	}	
}
