package com.project.feastify.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.feastify.custom_exceptions.ResourceNotFoundException;
import com.project.feastify.dto.ApiResponse;

@RestControllerAdvice

public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException
	(ResourceNotFoundException e) {
		System.out.println("in catch - res not found...");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
	}

}
