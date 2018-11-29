package com.zopenlab.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.zopenlab.dao.IContactDAO;
import com.zopenlab.entities.Contact;


@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {
	Contact c = new Contact("ASSEKE", "Canis",null, "canis@gmail.com");
	Contact c1=new Contact("ASSEKE", "Tognin",null, "tognin@gmail.com");
	 List<Contact> contacts= new ArrayList<>();
	
	 

	@InjectMocks
	ContactService  contactService;
	
	@Mock
	IContactDAO contactDAOMock;
	
	@Test
	public void testFindAllContacts() {
		contacts.add(c);
		contacts.add(c1);
		when(contactDAOMock.findAll()).thenReturn(contacts);
		assertTrue(contactService.findAllContacts().contains(c1));
		assertTrue(contactService.findAllContacts().get(1).getPrenom().equals("Tognin"));
		//verify(contactDAOMock).findAll();
	}
	
	@Test
	public void testFindContactById() {
		when(contactDAOMock.findById(Mockito.anyLong())).thenReturn(Optional.of(c));
		assertEquals("ASSEKE", contactService.findContactById(1L).getNom());
		assertEquals("Canis", contactService.findContactById(1L).getPrenom());
		assertEquals("canis@gmail.com", contactService.findContactById(1L).getEmail());
	}
	
	@Test
	public void testSaveContact() {
		when(contactDAOMock.save(Mockito.any(Contact.class))).thenReturn(c);
		assertEquals(c, contactService.saveContact(c1));
		assertTrue(contactService.saveContact(c1).getPrenom().equals("Canis"));
	}
	
	
}
