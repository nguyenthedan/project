package com.example.blog.services;

import com.example.blog.models.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
