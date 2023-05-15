package com.codingdojo.travelBucketList.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.travelBucketList.models.BucketList;
import com.codingdojo.travelBucketList.models.Destination;
import com.codingdojo.travelBucketList.models.User;
import com.codingdojo.travelBucketList.services.BucketListService;
import com.codingdojo.travelBucketList.services.DestinationService;
import com.codingdojo.travelBucketList.services.UserService;

@Controller
public class DestinationController {
	
	@Autowired
	private BucketListService blServ;
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private DestinationService desServ;
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		Long id = (Long) session.getAttribute("userId");
		if(id == null) {
			return "redirect:/";
		}
		User user = userServ.findById(id);
		List<BucketList> bucketList = blServ.findByUser(user);
		model.addAttribute("bucketList", bucketList);
		return "dashboard.jsp";
	}
	
	@GetMapping("/destinations/new")
	public String newDestination(@ModelAttribute("destination") Destination destination, HttpSession session, Model model) {
		Long id = (Long) session.getAttribute("userId");
		if(id == null) {
			return "redirect:/";
		}
		User user = userServ.findById(id);
		if(!user.getIsAdmin()) {
			return "redirect:/";
		}
		model.addAttribute("user", user);
		return "newdestination.jsp";
	}
	
	@PostMapping("/destinations/new")
	public String newDestination(@Valid @ModelAttribute("destination") Destination destination, BindingResult result) {
		desServ.newDestination(destination, result);
		if (result.hasErrors()) {
			return "newdestination.jsp";
		}
		return "redirect:/destinations";
	}
	
	@GetMapping("/destinations")
	public String allDestinations(HttpSession session, Model model) {
		List<Destination> destinations = desServ.findAll();
		model.addAttribute("destinations", destinations);
		Long id = (Long) session.getAttribute("userId");
		if(id != null) {
			User user = userServ.findById(id);
			model.addAttribute("user", user);
		}
		return "alldestinations.jsp";
	}
	
//	This ensures that you can only use an actual delete button instead of typing into URL
	@GetMapping("/destinations/{id}/delete")
	public String niceTryBuddy() {
		return "redirect:/destinations";
	}
	
//	Actual delete button
	@DeleteMapping("/destinations/{id}/delete")
	public String deleteDestination(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userServ.findById(userId);
		if(user.getIsAdmin()) {
			for(BucketList bucketlist:blServ.findAllByDesId(id)) {
				bucketlist.setDestination(null);
				bucketlist.setUser(null);
				blServ.saveBucketList(bucketlist);
			}
			desServ.delete(id);
		}
		return "redirect:/destinations";
	}
	
	@GetMapping("/destinations/{id}/edit")
	public String editDestination(@PathVariable("id") Long id, HttpSession session, Model model) {
		Destination destination = desServ.findById(id);
		Long userId = (Long) session.getAttribute("userId");
		if(userServ.findById(userId).getIsAdmin()) {
			User user = userServ.findById(userId);
			model.addAttribute("user", user);
			model.addAttribute("destination", destination);
			return "editdestination.jsp";
		} else {
			return "redirect:/destinations";
		}
	}
	
	@PutMapping("/destinations/{id}/edit")
	public String editDestination(@PathVariable("id") Long id, @Valid @ModelAttribute("destination") Destination destination, BindingResult result) {
		if(result.hasErrors()) {
			return "editlisting.jsp";
		} else {
			desServ.saveDestination(destination);
			return "redirect:/destinations/{id}";
		}
	}

}
