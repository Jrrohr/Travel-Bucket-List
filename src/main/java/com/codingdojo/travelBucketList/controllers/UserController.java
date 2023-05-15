package com.codingdojo.travelBucketList.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.travelBucketList.models.LoginUser;
import com.codingdojo.travelBucketList.models.User;
import com.codingdojo.travelBucketList.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userServ;
	
	 @GetMapping("/login")
	 public String index(Model model) {
	     model.addAttribute("newUser", new User());
	     model.addAttribute("newLogin", new LoginUser());
	     return "login.jsp";
	 }
	 
	 @PostMapping("/register")
	 public String register(@Valid @ModelAttribute("newUser") User newUser, 
	         BindingResult result, Model model, HttpSession session) {
		 userServ.register(newUser, result);
	     
	     if(result.hasErrors()) {
	         model.addAttribute("newLogin", new LoginUser());
	         return "login.jsp";
	     }
	     session.setAttribute("userId", newUser.getId());
	     return "redirect:/dashboard";
	 }
	 
	 @PostMapping("/login")
	 public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
	         BindingResult result, Model model, HttpSession session) {
	     User user = userServ.login(newLogin, result);
	 
	     if(result.hasErrors()) {
	         model.addAttribute("newUser", new User());
	         return "login.jsp";
	     }
	     session.setAttribute("userId", user.getId());
	     return "redirect:/dashboard";
	 }
	 
	 @GetMapping("/logout")
	 public String logout(HttpSession session) {
		 session.setAttribute("userId", null);
		 return "redirect:/";
	 }
}
