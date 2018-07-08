package com.leonlabs.search.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.leonlabs.search.service.SearchService;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(SearchService.class);
	
	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex){
		log.info("SQLException Occured:: URL="+request.getRequestURL());
		return "database_error";
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public String handleIOException(){
		log.error("IOException handler executed");
		return "internal-server-error";
	}
}