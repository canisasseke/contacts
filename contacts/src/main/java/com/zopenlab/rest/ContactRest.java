package com.zopenlab.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zopenlab.entities.Contact;
import com.zopenlab.services.IContactService;

@RestController
@CrossOrigin("*")
public class ContactRest {
	
	@Autowired
	IContactService contactService;
	
	
	@GetMapping("/contacts")
	public List<Contact> findAllContacts() {
		
		return contactService.findAllContacts();
	}

	@GetMapping("/contacts/{id}")
	public Contact findContactById(@PathVariable Long id) {
		return contactService.findContactById(id);
	}
	
	@GetMapping("/contacts/ByKeyword")
	public List<Contact> getContactByKeywordName(@RequestParam(name="kw", defaultValue="") String kw) {
		return contactService.getContactByKeywordName(kw);
	}
	
	@PostMapping("/contacts")
	public  ResponseEntity<Contact> saveContact(@Valid @RequestBody Contact contact) {
		
		contactService.saveContact(contact);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contact.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/contacts/{id}")
	public Contact updateContact(@Valid @RequestBody Contact contact, @PathVariable Long id) {
		
		return contactService.updateContact(contact, id);
	}
	
	@DeleteMapping("/contacts/{id}")
	public void deleteContact(@PathVariable Long id) {
		
		contactService.deleteContact(id);
	}
}
