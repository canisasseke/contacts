package com.zopenlab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zopenlab.entities.Role;

@Repository
public interface IRoleDAO extends JpaRepository<Role, Long>{

	public Role findByRoleName(String rolename);
}
