package com.example.cs4500_sp19_josies.services;

import java.util.List;

import com.example.cs4500_sp19_josies.models.User;

public interface UserService {
	User createUser(User user);
	List<User> findAllUsers();
	User findUserById(Integer userId);
}