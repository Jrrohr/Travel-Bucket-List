package com.codingdojo.travelBucketList.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codingdojo.travelBucketList.models.Destination;
import com.codingdojo.travelBucketList.models.User;
import com.codingdojo.travelBucketList.services.DestinationService;
import com.codingdojo.travelBucketList.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private DestinationService desServ;
	
	@Autowired
	private UserService userServ;
	
	@GetMapping("/")
	public String home(HttpSession session, Model model) {
		Long id = (Long) session.getAttribute("userId");
		if(id != null) {
			User user = userServ.findById(id);
			model.addAttribute("user", user);
		}
		List<Destination> destinations = desServ.top3();
		model.addAttribute("destinations", destinations);
		return "index.jsp";
	}

}
