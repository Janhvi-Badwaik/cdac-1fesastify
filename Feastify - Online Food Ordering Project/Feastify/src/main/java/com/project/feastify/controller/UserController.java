package com.project.feastify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.feastify.dto.UserRequestDTO;
import com.project.feastify.dto.UserResponseDTO;
import com.project.feastify.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserService userService;

	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponseDTO register(@RequestBody UserRequestDTO request)
	{
		UserResponseDTO response = userService.registerUser(request);
		return response;
	}
}
