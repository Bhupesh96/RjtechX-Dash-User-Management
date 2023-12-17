package com.rjtechx.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rjtechx.entity.User;
import com.rjtechx.repo.UserRepo;
import com.rjtechx.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
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

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("title", "RjtechX Dash - Register");
		return "register";
	}

	@GetMapping("/signin")
	public String login(Model model) {
		model.addAttribute("title", "RjtechX Dash - Login");
		return "login";
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "RjtechX Dash - Home");
		return "home";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession httpSession, HttpServletRequest request, Model model) {
		model.addAttribute("title", "RjtechX Dash - CreateUser");
		String url = request.getRequestURL().toString(); // http://localhost:8080/saveUser
		url = url.replace(request.getServletPath(), ""); // http://localhost:8080/
		User u = userService.saveUser(user, url);
		if (u != null) {
			httpSession.setAttribute("msg", "Registration Successfull");
			httpSession.setAttribute("msg2", "Please Check Your Mail To Enable Your Account");
		} else {

			httpSession.setAttribute("msg", "Something went wrong!!");
		}
		return "redirect:/register";
	}

	@GetMapping("/verify")
	public String verifyAccount(@Param("code") String code, Model model) {
		model.addAttribute("title", "RjtechX Dash - Verify");
		boolean userAccount = userService.verifyAccount(code);
		if (userAccount) {
			model.addAttribute("msg", "Your account is successfully verified!!");
		} else {
			model.addAttribute("msg", "Your verification link is incorrect or Your account is already verified");
		}
		return "verification_message";
	}
}
