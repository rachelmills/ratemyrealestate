package com.ratemyrealestate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ratemyrealestate.entities.Rating;
import com.ratemyrealestate.entities.User;
import com.ratemyrealestate.repositories.RatingRepository;

@Repository
@Transactional
@Component("ratingsDao")
public class RatingsDAO {
	
	@Autowired
	private RatingRepository ratingRepository;

//	@Autowired
//	private SessionFactory sessionFactory;
//
//	public Session session() {
//		return sessionFactory.getCurrentSession();
//	}
//
	public List<Rating> getRatings() {
		return ratingRepository.findAll();  // add criteria for enabled users only?
	}
	
//	public Rating getRatingById(int ratingId) {
//		Criteria crit = session().createCriteria(Rating.class);
//		crit.add(Restrictions.idEq(ratingId));
//		Rating rating = (Rating) crit.uniqueResult();
//		return rating;
//	}
//
//	public Rating getRating(int agentId, User user) {
//		Criteria crit = session().createCriteria(Rating.class);
//		crit.createAlias("user", "u");
//		crit.createAlias("agent", "a");
//		crit.add(Restrictions.eq("a.id", agentId));
//		crit.add(Restrictions.eq("u.id", user.getId()));
//		Rating rating = (Rating) crit.uniqueResult();
//		return rating;
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Rating> getRatingsForAgent(int agentId) {
//		Criteria crit = session().createCriteria(Rating.class);
//		crit.createAlias("user", "u");
//		crit.createAlias("agent", "a");
//		crit.add(Restrictions.eq("u.enabled", true));
//		crit.add(Restrictions.eq("a.id", agentId));
//		return crit.list();
//	}
//
	public List<Rating> getRatingsForUser(User user) {
////		Criteria crit = session().createCriteria(Rating.class);
////		crit.createAlias("user", "user");
////		crit.add(Restrictions.eq("user.id", user.getId()));
//		List<Rating> ratings = crit.list();
//		return ratings;
		return ratingRepository.findOneByUserId(user.getId());
	}

//	public void saveOrUpdate(Rating rating) throws ConstraintViolationException {
////		if (getRating(rating.getId()).)
//		session().saveOrUpdate(rating);
//	}
//
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
//	public boolean ratingExistsForUserAndAgent(int agentId, User user) {
//		return getRating(agentId, user) == null ? false : true;
//	}


}
