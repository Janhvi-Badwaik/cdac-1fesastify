package com.project.feastify.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade 
{
	@Override
	public Authentication getAuthentication() 
	{
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
