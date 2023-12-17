package com.rjtechx.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rjtechx.entity.User;
import com.rjtechx.repo.UserRepo;
import com.rjtechx.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepo repo;

	@GetMapping("/view-profile")
	public String userProfile(Principal principal, Model model) {
		model.addAttribute("title", "RjtechX Dash - Profile");
		User user = repo.findByEmail(principal.getName());
		model.addAttribute("user", user);
		return "user_profile";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/logout";
	}

}
