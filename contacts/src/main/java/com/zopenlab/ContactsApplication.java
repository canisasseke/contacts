package com.zopenlab;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.zopenlab.entities.Contact;
import com.zopenlab.entities.Role;
import com.zopenlab.entities.User;
import com.zopenlab.services.IAccountService;
import com.zopenlab.services.IContactService;

@SpringBootApplication
@EnableAsync
public class ContactsApplication  implements CommandLineRunner{

	@Autowired
	IContactService contactService;
	@Autowired
	IAccountService accountService;
	
	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		contactService.saveContact(new Contact("ASSEKE", "Canis", df.parse("21/12/1979"), "canis@gmail.com"));
		contactService.saveContact(new Contact("BOUSSOU", "Dominique", df.parse("09/01/2014"), "bkd@gmail.com"));
		contactService.saveContact(new Contact("ASSEKE", "Isaac", df.parse("09/01/2014"), "tognin@gmail.com"));
		contactService.saveContact(new Contact("ODY", "Nagege Marina", df.parse("02/07/1982"), "ody@gmail.com"));
		contactService.saveContact(new Contact("AGUIA", "Aristide", df.parse("09/01/2014"), "agui@gmail.com"));
		contactService.findAllContacts().stream().filter(p->p.getNom().contains("ASS")).forEach(System.out::println);
		contactService.findAllContacts().forEach(System.out::println);
		contactService.getContactByKeywordName("ASS").forEach(System.out::println);
		System.out.println("-----------------------------save users---------------------");
		accountService.saveUser(new User("kaskinas", "kaskinas", "kaskinas@yahoo.fr", true));
		accountService.saveUser(new User("canis", "canis", "canisasseke@gmail.com", true));
		accountService.saveRole(new Role("admin"));
		accountService.saveRole(new Role("user"));
		accountService.addRoleToUser("kaskinas", "user");
		accountService.addRoleToUser("canis", "user");
		accountService.addRoleToUser("canis", "admin");
		accountService.findAllUsers().forEach(user->{
			System.out.println(user);
		});
	}
}
