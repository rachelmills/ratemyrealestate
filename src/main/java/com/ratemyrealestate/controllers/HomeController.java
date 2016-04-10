package com.ratemyrealestate.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ratemyrealestate.dao.AgentSearch;
import com.ratemyrealestate.dto.SignupForm;
import com.ratemyrealestate.service.RatingsService;
import com.ratemyrealestate.service.UsersService;
import com.ratemyrealestate.validators.SignupFormValidator;

@Controller
public class HomeController {
	
	@Autowired
	private RatingsService ratingsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private SignupFormValidator signupFormValidator;

	@RequestMapping("/home")
	public String showHome(Model model, Principal principal) {
		boolean hasRatings = false;

		if (null != principal) {
			hasRatings = ratingsService.hasRatings(principal.getName());
			model.addAttribute("userid", usersService.getUser(principal.getName()).getId());
		}

		model.addAttribute("agentSearch", new AgentSearch());
		model.addAttribute("hasRatings", hasRatings);
		return "home";
	}
	
	@Autowired
	public HomeController(UsersService usersService, SignupFormValidator signupFormValidator) {
		this.signupFormValidator = signupFormValidator;
		this.usersService = usersService;
	}

	@InitBinder("signupForm") // name of form is same as model attribute in signup method
	protected void initSignupBinder(WebDataBinder binder) {
		binder.setValidator(signupFormValidator);
	}

//	@RequestMapping(value = "/signup", method = RequestMethod.GET)
//	public String signup(Model model) {
//		model.addAttribute("name", "Puss");
//		model.addAttribute(new SignupForm());
//		return "signup";
//	}

//	@RequestMapping(value = "/signup", method = RequestMethod.POST)
//	public String signup(@ModelAttribute("signupForm") @Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
//
//		if (result.hasErrors()) {
//			return "signup";
//		}
////		logger.info(signupForm.toString());
//		usersService.createUser(signupForm);
//
////		MyUtils.flash(redirectAttributes, "success", "signupSuccess");
//		return "redirect:/";
//	}

}
