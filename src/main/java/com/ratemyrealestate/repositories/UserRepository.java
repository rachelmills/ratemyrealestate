package com.ratemyrealestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ratemyrealestate.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findOneByUsername(String username);

}
