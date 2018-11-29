package com.zopenlab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zopenlab.entities.User;

@Repository
public interface IUserDAO extends JpaRepository<User, Long>{

	public User findByUsername(String username);
}
