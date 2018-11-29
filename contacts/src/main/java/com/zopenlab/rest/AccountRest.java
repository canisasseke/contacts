package com.zopenlab.rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zopenlab.entities.User;
import com.zopenlab.services.IAccountService;

@RestController
public class AccountRest {
	
	@Autowired
	IAccountService accountService;
	
	
	@PostMapping("/account/users/")
	public ResponseEntity<User> registerUser(@RequestBody RegisterForm userForm) {
		if(!userForm.getPassword().equals(userForm.getConfirmpassword())) throw new RuntimeException("password don't match");
		User user = accountService.findUserByUsername(userForm.getUsername());
		if(user!=null) throw new RuntimeException("user already exist");
		user=new User(userForm.getUsername(), userForm.getPassword(), userForm.getEmail(), true);
		accountService.saveUser(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}").buildAndExpand(user.getUsername()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/account/users/{username}")
	public User findUserByUsername(@PathVariable String username) {
		
		return accountService.findUserByUsername(username);
	}

}
