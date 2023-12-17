package com.rjtechx.service;

import java.util.List;

import com.rjtechx.entity.User;

public interface UserService {


	public User saveUser(User user, String url);
	public void removeSessionMessage();
	List<User> getUsers();
	public void updateUser(int id, User user);
	public User getExistingUser(int id);
	public void sendEmail(User user, String url);
	public boolean verifyAccount(String verification);
}
