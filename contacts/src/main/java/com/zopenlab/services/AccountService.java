package com.zopenlab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zopenlab.dao.IRoleDAO;
import com.zopenlab.dao.IUserDAO;
import com.zopenlab.entities.Role;
import com.zopenlab.entities.User;

@Service
@Transactional
public class AccountService implements IAccountService {

	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private IRoleDAO roleDAO;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userDAO.save(user);
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return userDAO.findAll();
	}

	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userDAO.findByUsername(username);
	}

	@Override
	public User updateUser(User user, Long userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(User user, Long userid) {
		// TODO Auto-generated method stub

	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return roleDAO.save(role);
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		// TODO Auto-generated method stub
		User user = userDAO.findByUsername(username);
		Role role = roleDAO.findByRoleName(rolename);
		user.getRoles().add(role);
	}

}
