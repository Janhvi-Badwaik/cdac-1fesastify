package com.project.feastify.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade 
{
	Authentication getAuthentication();
}
