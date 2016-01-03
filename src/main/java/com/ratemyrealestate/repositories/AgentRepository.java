package com.ratemyrealestate.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ratemyrealestate.entities.Agent;

public interface AgentRepository extends JpaRepository<Agent, Integer>{

	Page<Agent> findAllByAgentnameContainingIgnoringCase(String agentName, Pageable pageable);
	Page<Agent> findAll(Pageable pageable);
}
