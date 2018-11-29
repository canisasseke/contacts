package com.zopenlab.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zopenlab.entities.Contact;

@Repository
public interface IContactDAO extends JpaRepository<Contact, Long>{

	@Query("select c from Contact c where c.nom like :x")
	public List<Contact> getContactByKeywordName(@Param("x") String kw);
}
