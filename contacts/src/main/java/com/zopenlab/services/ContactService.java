package com.zopenlab.services;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zopenlab.dao.IContactDAO;
import com.zopenlab.entities.Contact;
import com.zopenlab.exceptions.ContactNotFounException;

@Service
@Transactional
public class ContactService implements IContactService {

	@Autowired
	IContactDAO  contactDAO;
	
	@Override
	public List<Contact> findAllContacts() {
		// TODO Auto-generated method stub
		return contactDAO.findAll();
	}

	@Override
	public Contact findContactById(Long id) {
		// TODO Auto-generated method stub
		Optional<Contact> opt =contactDAO.findById(id);
		if(!opt.isPresent()) throw new ContactNotFounException("this contact does not exist");
		
		return opt.get();
	}
	
	@Override
	public List<Contact> getContactByKeywordName(String kw) {
		// TODO Auto-generated method stub
		return contactDAO.getContactByKeywordName("%"+kw+"%");
	}

	@Override
	public Contact saveContact(Contact contact) {
		// TODO Auto-generated method stub
		return contactDAO.save(contact);
	}

	@Override
	public Contact updateContact(Contact contact, Long id) {
		// TODO Auto-generated method stub
		Optional<Contact> opt =contactDAO.findById(id);
		if(!opt.isPresent()) throw new ContactNotFounException("this contact does not exist");
		//Contact c =opt.get();
		contact.setId(id);
		
		return contactDAO.save(contact);
	}

	@Override
	public void deleteContact(Long id) {
		// TODO Auto-generated method stub
		Optional<Contact> opt =contactDAO.findById(id);
		if(!opt.isPresent()) throw new ContactNotFounException("this contact does not exist");
		contactDAO.deleteById(id);
	}

	
}
