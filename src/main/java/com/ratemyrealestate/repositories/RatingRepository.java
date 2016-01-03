package com.ratemyrealestate.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ratemyrealestate.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

	List<Rating> findOneByUserId(int userId);
}
