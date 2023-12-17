package com.rjtechx.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rjtechx.entity.User;
import java.util.List;



public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
	public User findByVerification(String verification);
	
}
