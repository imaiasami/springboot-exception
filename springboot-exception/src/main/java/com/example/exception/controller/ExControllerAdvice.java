package com.example.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.errorModel.ErrorResult;
import com.example.exception.errorModel.UserException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(value = { "com.example.exception.controller" })
public class ExControllerAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public ErrorResult illegalExHanle(IllegalArgumentException e) {
		log.info("ex: {}", e);
		return new ErrorResult("BAD REQUEST", e.getMessage());
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResult> userExhandle(UserException e) {
		log.info("ex: {}", e.getMessage());
		ErrorResult result = new ErrorResult("USER-EX", e.getMessage());
		return new ResponseEntity<ErrorResult>(result, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler
	public ResponseEntity<ErrorResult> exHandle(Exception e) {
		log.info("ex: {}", e.getMessage());
		ErrorResult result = new ErrorResult("EX", e.getMessage());
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
