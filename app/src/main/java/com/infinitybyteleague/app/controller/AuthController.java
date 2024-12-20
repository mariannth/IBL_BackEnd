package com.infinitybyteleague.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.infinitybyteleague.app.service.dto.AuthRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SuppressWarnings("deprecation")
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
	
	//Variable para guardar mi JWT
	
	private static final String SECRET_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public String login(@RequestBody AuthRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
				);
		//Implementando el JWT
		return Jwts.builder()
				.setSubject(((AuthRequest) authentication.getPrincipal()).getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact()
				;
	}

}
