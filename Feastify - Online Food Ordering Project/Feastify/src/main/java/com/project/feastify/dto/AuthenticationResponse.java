package com.project.feastify.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse 
{
	private String email;
	private String toekn;
}
