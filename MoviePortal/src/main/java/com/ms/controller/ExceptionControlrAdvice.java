package com.ms.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ms.model.ErrorStatus;

@ControllerAdvice
public class ExceptionControlrAdvice {

	static final Logger logger = Logger.getLogger(MoviesOnlineController.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorStatus> exceptionHandler(Exception ex) {
		ErrorStatus errorstatus = new ErrorStatus();
		errorstatus.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorstatus.setMessage("Please contact your administrator");
		logger.error(ex.getMessage(), ex);
		return new ResponseEntity<ErrorStatus>(errorstatus, HttpStatus.OK);
	}
}
