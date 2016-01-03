package com.ratemyrealestate.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ratemyrealestate.entities.Rating;
import com.ratemyrealestate.service.AgentsService;
import com.ratemyrealestate.service.RatingsService;

@Controller
public class RatingsController {

	private RatingsService ratingsService;
	private AgentsService agentsService;
//	private UsersService usersService;

	@RequestMapping("/ratingsOld")
	public String showRatings(Model model) {
		model.addAttribute("name", "Ed");
//		List<Rating> ratings = ratingsService.getCurrent();
//		model.addAttribute("ratings", ratings);
		return "ratings";
	}

	@RequestMapping("/ratings")
	public String showRatingsForAgent(@RequestParam("agentid") int id,
			Model model, Principal principal) {
//		List<Rating> ratings = ratingsService.getRatingsForAgent(id);
//		int sum = 0;
//		int count = 0;
//		for (Rating rating : ratings) {
//			rating.setAgent(agentsService.getAgent(id));
//			count++;
//			sum += rating.getRating();
//		}
//		double averageRating = getAverageRating(sum, count);
//		System.out.println("average rating = " + averageRating);
//		model.addAttribute("agentname", agentsService.getAgent(id).getAgentName());
//		model.addAttribute("agentsuburb", agentsService.getAgent(id).getSuburb());
//		model.addAttribute("ratings", ratings);
//		model.addAttribute("averageRating", averageRating);
//		model.addAttribute("id", id);
//		model.addAttribute("display", "agent");
		return "ratings";
	}
	
	@RequestMapping("/allratings")
	public String showAllRatings(Model model, Principal principal) {
//		List<Rating> ratings = ratingsService.getRatings();
//		for (Rating rating : ratings) {
//			if (null != principal) {
//				rating.setRatedByUser(ratingsService.ratingExistsForUserAndAgent(rating.getAgent().getId(), usersService.getUserId(principal.getName())));
//			}
//			
//		}
//		model.addAttribute("ratings", ratings);
//		model.addAttribute("display", "all");	
		return "ratings";
	}

	@RequestMapping("/userratings")
	public String showRatingsForUser(@RequestParam("userid") int id, Model model) {
//		List<Rating> ratings = ratingsService.getRatingsForUser(id);
//
//		for (Rating rating : ratings) {
//			Agent agent = agentsService.getAgent(rating.getAgent().getId());
//			rating.setAgent(agent);
//		}
//
//		model.addAttribute("ratings", ratings);
//		model.addAttribute("id", id);
//		model.addAttribute("display", "user");
		return "ratings";
	}

	private double getAverageRating(int sum, int count) {
		if (count > 0) {
			return sum / (double) count;
		}
		return 0;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") int id) {
		System.out.println("id is " + id);
		return "home";
	}

	@RequestMapping(value = "/createrating")
	public String createRating(@RequestParam("agentid") int id, Model model) {
//		model.addAttribute("agent", agentsService.getAgent(id));
		return "createrating";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Rating rating, Principal principal, @RequestParam(value="edit", required=false) String edit, @RequestParam(value="ratingID", required=false) Integer ratingId, @RequestParam(value="agentID") Integer agentId, Model model) {
		model.addAttribute("edit", edit); 
//		rating.setAgent(agentsService.getAgent(agentId));
//		String username = principal.getName();
//		int userId = usersService.getUserId(username);
//		rating.setUser(usersService.getUser(userId));
//		if (edit != null) {
//			rating.setId(ratingId);
//			ratingsService.saveOrUpdate(rating);
//		} else {
//			if (ratingsService.hasRatings(username)) {
//				model.addAttribute("edit", "Confirm edit");
//			}
//			ratingsService.saveOrUpdate(rating);	
//		}
		return "ratingcreated";
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

//	@Autowired
//	public void setUsersService(UsersService usersService) {
//		this.usersService = usersService;
//	}
}
