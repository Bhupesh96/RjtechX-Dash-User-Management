package com.rjtechx.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rjtechx.entity.User;
import com.rjtechx.repo.UserRepo;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public User saveUser(User user, String url) {

		if (repo.count() == 0) {
			String password = passwordEncoder.encode(user.getPassword());
			user.setPassword(password);
			user.setRole("ROLE_ADMIN");
			user.setStatus(false);
			user.setVerification(UUID.randomUUID().toString());
			User newuser = repo.save(user);
			if (newuser != null) {
				sendEmail(newuser, url);
			}
			return newuser;

		} else {
			String password = passwordEncoder.encode(user.getPassword());
			user.setPassword(password);
			user.setRole("ROLE_USER");
			user.setStatus(false);
			user.setVerification(UUID.randomUUID().toString());
			User newuser = repo.save(user);
			if (newuser != null) {
				sendEmail(newuser, url);
			}
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

	@Override
	public void sendEmail(User user, String url) {
		String from = "bs.work9617@gmail.com";
		String to = user.getEmail();
		String subject = "Account Verfication";
		String content = "Dear [[name]],<br>" + "Please click the link below to verify your registration:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">Click here to verify your account</a></h3>"
				+ "Thank you,<br>" + "RjtecX Dash";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setFrom(from, "RjtechX Dash");
			helper.setTo(to);
			helper.setSubject(subject);
			content = content.replace("[[name]]", user.getName());
			String siteUrl = url + "/verify?code=" + user.getVerification();
			content = content.replace("[[URL]]", siteUrl);
			helper.setText(content, true);
			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean verifyAccount(String verification) {
		User user = repo.findByVerification(verification);
		if (user == null) {
			return false;
		} else {
			user.setStatus(true);
			user.setVerification(null);
			repo.save(user);
			return true;
		}

	}

}
