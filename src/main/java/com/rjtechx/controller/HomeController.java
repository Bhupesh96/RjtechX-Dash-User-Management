package com.rjtechx.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rjtechx.entity.User;
import com.rjtechx.repo.UserRepo;
import com.rjtechx.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private UserRepo repo;
	
	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			User user = repo.findByEmail(email);
			m.addAttribute("user", user);
		}
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/logout";
	}
	
	@GetMapping("/show-users")
	public String showUsers(Model m) {
		return "show_users";
		
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession httpSession) {
		User u = userService.saveUser(user);
		if (u != null) {
			httpSession.setAttribute("msg", "Registered Successfully!!");
		} else {
			
			httpSession.setAttribute("msg", "Something went wrong!!");
		}
		return "redirect:/register";
	}
}
