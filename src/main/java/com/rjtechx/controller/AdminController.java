package com.rjtechx.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjtechx.entity.User;
import com.rjtechx.repo.UserRepo;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserRepo repo;
	
	//show user
	@GetMapping("/show-users")
	public List<User> showUsers(Model m) {
		 List<User> user = repo.findAll();
		return user;
		
	}
}
