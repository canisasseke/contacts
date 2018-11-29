package com.zopenlab.services;

import java.util.List;

import com.zopenlab.entities.Role;
import com.zopenlab.entities.User;

public interface IAccountService {
	public User saveUser(User user);
	public List<User> findAllUsers();
	public User findUserByUsername(String username);
	public User updateUser(User user, Long userid);
	public void deleteUser(User user, Long userid);
	public Role saveRole(Role role);
	public void addRoleToUser(String username, String rolename);
	
}
