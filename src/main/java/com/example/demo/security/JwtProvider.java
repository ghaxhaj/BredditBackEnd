package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {

	
	
//	public String generateToken(Authentication authentication) {
//		User principal = (User) authentication.getPrincipal();
//		return Jwts.builder()
//				.setSubject(principal.getUsername())
//				.signWith(getPrivateKey())
//				.compact();
//	}
	
}
