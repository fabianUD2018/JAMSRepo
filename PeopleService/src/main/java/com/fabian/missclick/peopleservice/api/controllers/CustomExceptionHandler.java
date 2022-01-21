package com.fabian.missclick.peopleservice.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class CustomExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity handleRuntimeException(RuntimeException ex) {
		return ResponseEntity.noContent().build();
	}
	
	
}
