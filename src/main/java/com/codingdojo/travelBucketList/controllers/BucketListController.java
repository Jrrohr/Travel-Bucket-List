package com.codingdojo.travelBucketList.controllers;

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
public class BucketListController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private BucketListService blServ;
	
	@Autowired
	private DestinationService desServ;
	
	@GetMapping("/destinations/{id}")
	public String showDestination(@PathVariable("id") Long id, HttpSession session, Model model, @ModelAttribute("bucketList") BucketList bucketList) {
		Destination destination = desServ.findById(id);
		model.addAttribute("destination", destination);
		Long userId = (Long) session.getAttribute("userId");
		 if(userId != null) {
			User user = userServ.findById(userId);
			model.addAttribute("user", user);
			BucketList oneItem = blServ.findByDesAndUserId(destination.getId(), user.getId());
			model.addAttribute("desOnList", oneItem);
		 } else {
			 model.addAttribute("user", null);
			 model.addAttribute("desOnList", null);
		 }
		return "detail.jsp";
	}
	
	@PostMapping("/bucketlist/add/{desId}")
	public String newBucketList(@PathVariable("desId") Long desId, @Valid @ModelAttribute("bucketList") BucketList bucketList, BindingResult result, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userServ.findById(userId);
		Destination destination = desServ.findById(desId);
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("destination", destination);
			return "detail.jsp";
		}
		destination.getBucketLists().add(bucketList);
		user.getBucketLists().add(bucketList);
		blServ.saveBucketList(bucketList);
		return "redirect:/destinations/{desId}";
	}
	
	@PutMapping("/bucketlist/{id}/edit")
	public String editBucketList(@PathVariable("id") Long id, @Valid @ModelAttribute("bucketList") BucketList bucketList, BindingResult result) {
		if(result.hasErrors()) {
			return "detail.jsp";
		} else {
			blServ.saveBucketList(bucketList);
			Long desId = bucketList.getDestination().getId();
			return "redirect:/destinations/" + desId;
		}
	}
	
	@DeleteMapping("/bucketlist/{id}/delete")
	public String deleteBucketList(@PathVariable("id") Long id, HttpSession session) {
		Long desId = blServ.findById(id).getDestination().getId();
		blServ.delete(id);
		return "redirect:/destinations/" + desId;
	}

}
