package com.ratemyrealestate.dao;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ratemyrealestate.entities.Agent;
import com.ratemyrealestate.entities.Rating;
import com.ratemyrealestate.entities.User;
import com.ratemyrealestate.repositories.RatingRepository;

@Repository
@Transactional
@Component("ratingsDao")
public class RatingsDAO {
	
	@Autowired
	private RatingRepository ratingRepository;

	public List<Rating> getRatings() {
		return ratingRepository.findAll();  // add criteria for enabled users only?
	}
	
	public Rating getRating(Agent agent, User user) {
		return ratingRepository.findOneByAgentAndUser(agent, user);
	}

	public List<Rating> getRatingsForAgent(int agentId) {
		return ratingRepository.findAllByAgentId(agentId);
	}

	public Page<Rating> getRatingsForUser(User user, Pageable page) {
		return ratingRepository.findAllByUserId(user.getId(), page);
	}
	
	public List<Rating> getRatingsForUser(User user) {
		return ratingRepository.findAllByUserId(user.getId());
	}

	public void saveOrUpdate(Rating rating) throws ConstraintViolationException {
		ratingRepository.save(rating);
	}

//	public boolean delete(int agentId, User user) {
//		Criteria crit = session().createCriteria(Rating.class);
//		crit.add(Restrictions.eq("agentID", agentId));
//		crit.add(Restrictions.eq("userID", user.getId()));
//		Query query = session()
//				.createQuery(
//						"delete from Rating where agentID = :agentId and userID = :userId");
//		query.setLong("agentId", agentId);
//		query.setLong("userId", user.getId());
//		return query.executeUpdate() == 1;
//	}
//	

	public boolean ratingExistsForUserAndAgent(Agent agent, User user) {
		return getRating(agent, user) == null ? false : true;
	}
}
