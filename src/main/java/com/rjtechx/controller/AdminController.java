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

import com.rjtechx.entity.User;
import com.rjtechx.repo.UserRepo;
import com.rjtechx.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserRepo repo;

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/show-users")
	public String showUsers(Model model) {
		List<User> user = repo.findAll();
		model.addAttribute("user", user);
		return "show_users";

	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model, HttpSession httpSession) {
		Optional<User> userOptional = repo.findById(id);
		User user = userOptional.get();
		repo.delete(user);
		httpSession.setAttribute("msg", "User Deleted!!");
		return "redirect:/admin/show-users";
	}

	@PostMapping("/update-user/{id}")
	public String updateUser(@PathVariable("id") Integer id, Model model) {
		Optional<User> userOptional = repo.findById(id);
		System.out.println(userOptional);
		User user = userOptional.get();
		model.addAttribute("user", user);
		return "update_user";

	}

	@PostMapping("/update-user")
	public String saveUpdatedUser(@ModelAttribute User user, HttpSession session, Principal p) {
		
		userService.updateUser(user.getId(), user);
		
		session.setAttribute("msg", "User Updated!!");

		System.out.println(user);

		return "redirect:/admin/show-users";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/logout";
	}
	
}
