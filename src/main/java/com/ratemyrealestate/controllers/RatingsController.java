package com.ratemyrealestate.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ratemyrealestate.dao.AgentSearch;
import com.ratemyrealestate.entities.Agent;
import com.ratemyrealestate.entities.Rating;
import com.ratemyrealestate.entities.User;
import com.ratemyrealestate.service.AgentsService;
import com.ratemyrealestate.service.RatingsService;
import com.ratemyrealestate.service.UsersService;
import com.ratemyrealestate.util.MyUtils;

@Controller
public class RatingsController {

	private RatingsService ratingsService;
	private AgentsService agentsService;
	private UsersService usersService;

	@RequestMapping("/ratings")
	public String showRatingsForAgent(@RequestParam("agentid") int id,
			Model model, Principal principal) {
		List<Rating> ratings = ratingsService.getRatingsForAgent(id);
		int sum = 0;
		int count = 0;
		for (Rating rating : ratings) {
			rating.setAgent(agentsService.getAgent(id));
			count++;
			sum += rating.getRating();
		}
		double averageRating = getAverageRating(sum, count);
		System.out.println("average rating = " + averageRating);
		model.addAttribute("agentname", agentsService.getAgent(id).getAgentname());
		model.addAttribute("agentsuburb", agentsService.getAgent(id).getSuburb());
		model.addAttribute("ratings", ratings);
		model.addAttribute("averageRating", averageRating);
		model.addAttribute("id", id);
		model.addAttribute("display", "agent");
		return "ratings";
	}
	
	@RequestMapping("/allratings")
	public String showAllRatings(Model model, Principal principal) {
		List<Rating> ratings = ratingsService.getRatings();

		if (null != principal) {
			ratings.forEach(rating -> rating.setRatedByUser(ratingsService.ratingExistsForUserAndAgent(rating.getAgent().getId(), usersService.getUser(principal.getName()))));
		}
		model.addAttribute("ratings", ratings);
		model.addAttribute("display", "all");	
		return "ratings";
	}

	@RequestMapping("/userratings/{pageNumber}")
	public String showRatingsForUser(@RequestParam("userid") int id , Model model, @PageableDefault(size=5) Pageable pageable, 
			@PathVariable("pageNumber") Integer pageNumber) {
		PageRequest page = new PageRequest(pageNumber,  pageable.getPageSize());
		
		Page<Rating> ratings = ratingsService.getRatingsForUser(usersService.getUser(id).getUsername(), page);

		for (Rating rating : ratings) {
			Agent agent = agentsService.getAgent(rating.getAgent().getId());
			rating.setAgent(agent);
		}

		model.addAttribute("ratings", ratings);
		model.addAttribute("id", id);
		model.addAttribute("display", "user");
		return "ratings";
	}
	
	private double getAverageRating(int sum, int count) {
		if (count > 0) {
			return sum / (double) count;
		}
		return 0;
	}

	@RequestMapping(value = "/createrating")
	public String createRating(@RequestParam("agentid") int id, Model model, Principal principal, RedirectAttributes redirectAttributes) {
		if (ratingsService.ratingExistsForUserAndAgent(id, usersService.getUser(principal.getName()))) {
			MyUtils.flash(redirectAttributes, "danger", "ratingAlreadyExists");
			return "redirect:/search/0";
		}
		model.addAttribute("agent", agentsService.getAgent(id));
		return "createrating";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Rating rating, Principal principal, @RequestParam(value="edit", required=false) String edit, @RequestParam(value="ratingID", required=false) Integer ratingId, @RequestParam(value="agentid") int agentid, Model model,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("edit", edit); 
		
		String username = principal.getName();
		User user = usersService.getUser(username);
		
		if (ratingsService.ratingExistsForUserAndAgent(agentid, user)) {
			MyUtils.flash(redirectAttributes, "danger", "ratingAlreadyExists");
		} else {
			rating.setAgent(agentsService.getAgent(agentid));
			rating.setUser(user);
			ratingsService.saveOrUpdate(rating);
			MyUtils.flash(redirectAttributes, "success", "ratingCreatedSuccess");
		}
		return "redirect:/";
	}
	
	@RequestMapping("/editrating")
	public String editRating(@RequestParam("agentid") Integer agentId, @RequestParam("userid") Integer userId, Model model) {
//		Rating rating = ratingsService.getRating(agentId, userId);
//		model.addAttribute("rating", rating);
//		Agent agent = agentsService.getAgent(agentId);
//		model.addAttribute("agent", agent);
		return "createrating";
	}
	
	@RequestMapping("/deleterating")
	public String deleteRating(@RequestParam("agentid") Integer agentId, @RequestParam("userid") Integer userId, Model model) {
//		ratingsService.deleteRating(agentId, userId);
		return "ratingdeleted";
	}

	@Autowired
	public void setRatingsService(RatingsService ratingsService) {
		this.ratingsService = ratingsService;
	}

	@Autowired
	public void setAgentsService(AgentsService agentsService) {
		this.agentsService = agentsService;
	}

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
}
