package com.ratemyrealestate.controllers;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		System.out.println(ex.getMessage());
		return "error";
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessException(AccessDeniedException ex) {
		return "denied";
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public String handleConstraintViolationException(ConstraintViolationException ex) {
		return "You have already rated this agent.";
	}

}
