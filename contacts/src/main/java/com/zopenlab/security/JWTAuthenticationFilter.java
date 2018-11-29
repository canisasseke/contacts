package com.zopenlab.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zopenlab.config.JWTConfig;
import com.zopenlab.entities.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	private JWTConfig jWTConfig;
	
	private ObjectMapper  objectMapper;
	
	private AuthenticationManager authenticationManager;
	
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager,ApplicationContext ctx) {
		super();
		this.authenticationManager = authenticationManager;
		this.jWTConfig=ctx.getBean(JWTConfig.class);
		this.objectMapper=ctx.getBean(ObjectMapper.class);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub		
		
		User user=null;
	
			try {
				user=objectMapper.readValue(request.getInputStream(), User.class);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		
		 
		System.out.println("---------------------------------------------");
		System.out.println("username: "+user.getUsername() );
		System.out.println("password: "+user.getPassword() );
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		org.springframework.security.core.userdetails.User springUser=(org.springframework.security.core.userdetails.User) authResult.getPrincipal();
		String jwttoken= Jwts.builder()
				.setSubject(springUser.getUsername())
				.setExpiration(new Date(System.currentTimeMillis()+this.jWTConfig.getExpiration_time()))
				.signWith(SignatureAlgorithm.HS512,jWTConfig.getSecret())
				.claim("roles",springUser.getAuthorities())
				.compact();
		response.addHeader(jWTConfig.getHeader_string(),jWTConfig.getToken_prefix()+" "+jwttoken);
		
	}

	
}
