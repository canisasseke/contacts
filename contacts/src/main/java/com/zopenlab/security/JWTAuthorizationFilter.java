package com.zopenlab.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zopenlab.config.JWTConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

	private JWTConfig jWTConfig;
	
	
	public JWTAuthorizationFilter(ApplicationContext ctx) {
		
		this.jWTConfig = ctx.getBean(JWTConfig.class);
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "Origin,"
				+ "Content-Type,Accept,X-Requested-With,Acces-Control-Request-Method,"
				+ "Acces-Control-Request-Headers,Authorization"
							);
		response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,"
				+ "Access-Control-Allow-Credentials,Authorization");
		if(request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			
			String jwt=request.getHeader(jWTConfig.getHeader_string());
			if(jwt==null || !jwt.startsWith(jWTConfig.getToken_prefix())) {
				filterChain.doFilter(request, response);
				return;
			}
			
			Claims claims=Jwts.parser()
						.setSigningKey(jWTConfig.getSecret())
						.parseClaimsJws(jwt.replace(jWTConfig.getToken_prefix(), ""))
						.getBody();
			String username=claims.getSubject();
			
			@SuppressWarnings("unchecked")
			List<Map<String, String>> roles= (List<Map<String, String>>) claims.get("roles");
			
			Collection<GrantedAuthority> authorities= new ArrayList<>();
			roles.forEach(r->{
				authorities.add(new SimpleGrantedAuthority(r.get("authority")));
			});
			
			UsernamePasswordAuthenticationToken authenticatedUser=new UsernamePasswordAuthenticationToken(username, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			filterChain.doFilter(request, response);
		}
		
		
	}

}
