package com.rjtechx.service;

import java.util.List;

import com.rjtechx.entity.User;

public interface UserService {


	public User saveUser(User user);
	public void removeSessionMessage();
	List<User> getUsers();
}
