package com.project.feastify.controller;


import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.feastify.dto.AuthenticationRequest;
import com.project.feastify.dto.AuthenticationResponse;
import com.project.feastify.service.AppUserDetailsService;
import com.project.feastify.utils.JwtUtil;


@RestController
@RequestMapping("/api")
@AllArgsConstructor


public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private  AppUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		final String jwtToken = jwtUtil.generateToKen(userDetails);
		return new AuthenticationResponse(request.getEmail(), request.getPassword());
	}

}
