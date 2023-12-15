package com.rjtechx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjtechx.entity.User;
import com.rjtechx.repo.UserRepo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepo repo;

	@GetMapping("/show-users")
	public String showUsers(Model model) {
		List<User> user = repo.findAll();
		model.addAttribute(user);
		return "show_users";
		
	}
}
