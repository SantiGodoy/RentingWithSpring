package com.curso.renting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.curso.renting.exception.NotFoundException;
import com.curso.renting.exception.ValidationException;

@ControllerAdvice
public class ControllerExceptionAdvicer {
	
	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
	public Exception handleNotFound(NotFoundException e) {
		return e;
	}
	
	@ResponseBody
	@ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public Exception handleValidation(ValidationException e) {
		return e;
	}
}
