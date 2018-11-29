package com.zopenlab.services;

import java.util.List;


import com.zopenlab.entities.Contact;

public interface IContactService {

	
	public List<Contact> findAllContacts();
	public Contact findContactById(Long id);
	public List<Contact> getContactByKeywordName(String kw);
	public Contact saveContact(Contact contact);
	public Contact updateContact(Contact contact, Long id);
	public void deleteContact(Long id);
}
