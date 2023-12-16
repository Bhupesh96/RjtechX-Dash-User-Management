package com.rjtechx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rjtechx.entity.User;
import com.rjtechx.repo.UserRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User saveUser(User user) {

		if (repo.count() == 0) {
			String password = passwordEncoder.encode(user.getPassword());
			user.setPassword(password);
			user.setRole("ROLE_ADMIN");
			user.setStatus(true);
			User newuser = repo.save(user);
			return newuser;

		} else {
			String password = passwordEncoder.encode(user.getPassword());
			user.setPassword(password);
			user.setRole("ROLE_USER");
			user.setStatus(true);
			User newuser = repo.save(user);
			return newuser;
		}

	}

	@Override
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");

	}

	@Override
	public List<User> getUsers() {

		return repo.findAll();
	}

	@Override
	public void updateUser(int id, User user) {

		User user2 = getExistingUser(id);
		user2.setAddress(user.getAddress());
		user2.setCity(user.getCity());
		user2.setElctricityCustomerID(user.getElctricityCustomerID());
		user2.setEmail(user.getEmail());
		user2.setMobile(user.getMobile());
		user2.setName(user.getName());
		user2.setPin(user2.getPin());
		user2.setRole(user.getRole());
		user2.setStatus(user.isStatus());
		repo.save(user2);

	}

	@Override
	public User getExistingUser(int id) {
		return repo.findById(id).orElse(null);
	}

}
