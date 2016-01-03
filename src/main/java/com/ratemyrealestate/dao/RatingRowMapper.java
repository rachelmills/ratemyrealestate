package com.ratemyrealestate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ratemyrealestate.entities.Rating;
import com.ratemyrealestate.entities.User;

public class RatingRowMapper implements RowMapper<Rating>{

	@Override
	public Rating mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		user.setId(rs.getInt("userID"));
		
		Rating rating = new Rating();

		rating.setId(rs.getInt("id"));
//		rating.setAgentID(rs.getInt("agentID"));
//		rating.setUserID(rs.getInt("userID"));
		rating.setRating(rs.getInt("rating"));

		return rating;
	}

}
