package com.ratemyrealestate.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ratemyrealestate.entities.Agent;
import com.ratemyrealestate.entities.Rating;
import com.ratemyrealestate.entities.User;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

	Rating findOneByUserId(int userId);

	Rating findOneByAgentAndUser(Agent agent, User user);

	Page<Rating> findAllByUserId(int id, Pageable page);
	
	List<Rating> findAllByUserId(int id);
	
	List<Rating> findAllByAgentId(int id);
}
