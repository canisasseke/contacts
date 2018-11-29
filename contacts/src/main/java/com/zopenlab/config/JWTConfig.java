package com.zopenlab.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="jwt")
public class JWTConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  String secret;
	private long expiration_time;
	private String token_prefix;
	private String header_string;
	
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public long getExpiration_time() {
		return expiration_time;
	}
	public void setExpiration_time(long expiration_time) {
		this.expiration_time = expiration_time;
	}
	public String getToken_prefix() {
		return token_prefix;
	}
	public void setToken_prefix(String token_prefix) {
		this.token_prefix = token_prefix;
	}
	public String getHeader_string() {
		return header_string;
	}
	public void setHeader_string(String header_string) {
		this.header_string = header_string;
	}
	
	
	
}
