package com.ratemyrealestate.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ratemyrealestate.dao.FormValidationGroup;
import com.ratemyrealestate.dto.SignupForm;
import com.ratemyrealestate.entities.Rating;
import com.ratemyrealestate.service.RatingsService;
import com.ratemyrealestate.service.UsersService;
import com.ratemyrealestate.util.MyUtils;

@Controller
public class LoginController {

	private UsersService usersService;
	
	@Autowired
	private RatingsService ratingsService;

	@RequestMapping("/login")
	public String showLogin(Model model) {
		return "login";
	}

	@RequestMapping("/newuser")
	public String showNewUser(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		return "newuser";
	}

	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	// @Validated is an annotation for using a validation group at the web layer
	public String createUser(@ModelAttribute("signupForm") @Validated(FormValidationGroup.class) @Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "newuser";
		}

		if (usersService.exists(signupForm.getEmail())) {
			result.rejectValue("email", "DuplicateKey.user.username");
			return "newuser";
		}

		try {
			usersService.createUser(signupForm);
		} catch (DuplicateKeyException ex) {
			result.rejectValue("email", "DuplicateKey.user.username");
			return "newuser";
		}
		MyUtils.flash(redirectAttributes, "success", "signupSuccess");
		return "redirect:usercreated";
	}
	
	@RequestMapping(value = "/usercreated")
	public String userCreated() {
		return "usercreated";
	}
	

	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {

//		List<User> users = usersService.getAllUsers();
//		model.addAttribute("users", users);

		return "admin";
	}

//	when data get sent to browser the content header affects what the browser does.  eg text/html
//	will display html, application/excel will start excel.  We are sending json out and content type
//	in the content header will be application/json
	@RequestMapping(value="/getRatings", method=RequestMethod.GET, produces="application/json")
//	spring will look at the data being return from the requestmapping and try to return it in an appropriate format. 
//	Will look at the 'produces' to determine the appropriate format.  Will find jackson dependencies and use that to
//	process the data - invokes jackson to return JSON
	@ResponseBody
	public Map<String, Object> getRatings(Principal principal) {
		List<Rating> ratings = null;
		if (principal == null) {
			ratings = new ArrayList<>();
		} else {
			String name = principal.getName();
			ratings = ratingsService.getRatingsForUser(name);
		}
		
//		JSON takes maps, lists etc and returns them into human and machine readable notation
		Map<String, Object> data = new HashMap<>();
			data.put("ratings", ratings);
			data.put("number", ratings.size());
		return data;
	}

	@RequestMapping(value="/sendComment", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
//	this method will receive json from our jquery post, so needs @requestbody
	public Map<String, Object> sendComment(Principal principal, @RequestBody Map<String, Object> data) {

		String text = (String) data.get("text");
		String agentName = (String)data.get("agentname");
		String suburb = (String)data.get("suburb");
		int target = (int)data.get("target");
		System.out.println(text + ", " + agentName + ", " + suburb);
		
		
//		this map will be the return value json map to be return to our javascript
		Map<String, Object> rval = new HashMap<>();
		
		rval.put("success", true);
		rval.put("target", target);
//		the return value gets passed to the success and error functions
		return rval;
//		no tiles handler needed as pure json, but still need to add to security-context
	}

	
	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}

	@RequestMapping("/getRatings")
	public String showRatings() {
		return "ratings1";
	}

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

}
