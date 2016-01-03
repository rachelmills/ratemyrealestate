package com.ratemyrealestate.validators;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.ratemyrealestate.dto.SignupForm;
import com.ratemyrealestate.entities.User;
import com.ratemyrealestate.repositories.UserRepository;

@Component
public class SignupFormValidator extends LocalValidatorFactoryBean {

	@Resource
	UserRepository userRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(SignupForm.class);
	}

	@Override
	public void validate(Object obj, Errors errors, final Object... validationHints) {
		super.validate(obj, errors, validationHints);
		
		if (!errors.hasErrors()) {
			SignupForm signupForm = (SignupForm) obj;
			User user = userRepository.findOneByUsername(signupForm.getEmail());
			if (user != null) {
				errors.rejectValue("email", "emailNotUnique");
			}
		}
		
	}
}
