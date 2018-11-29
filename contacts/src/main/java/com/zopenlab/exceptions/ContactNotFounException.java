package com.zopenlab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ContactNotFounException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContactNotFounException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}
