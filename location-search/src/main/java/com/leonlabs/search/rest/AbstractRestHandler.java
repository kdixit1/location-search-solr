package com.leonlabs.search.rest;

import com.leonlabs.search.view.ErrorView;
import com.leonlabs.search.view.RestErrorInfo;
import com.leonlabs.search.constant.SearchConstants;
import com.leonlabs.search.exception.DataFormatException;
import com.leonlabs.search.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * This class is meant to be extended by all REST resource "controllers".
 * It contains exception mapping and other common REST API functionality
 */
//@ControllerAdvice?
public abstract class AbstractRestHandler implements ApplicationEventPublisherAware {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected ApplicationEventPublisher eventPublisher;
    
    List<ErrorView> errorDetails ;

    protected static final String  DEFAULT_PAGE_SIZE = "100";
    protected static final String DEFAULT_PAGE_NUM = "0";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public
    @ResponseBody
    RestErrorInfo handleException(Exception ex, WebRequest request, HttpServletResponse response) {
    	errorDetails = new ArrayList<>();
    	ErrorView errorView = new ErrorView();
    	errorView.setErrorCode(SearchConstants.SYSTEM_ERROR);
    	errorView.setErrorDescription(ex.getMessage());
        log.info("Converting Data Store exception to RestResponse : " + ex.getMessage());
        return new RestErrorInfo(ex, "You messed up.");
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataFormatException.class)
    public
    @ResponseBody
    RestErrorInfo handleDataStoreException(DataFormatException ex, WebRequest request, HttpServletResponse response) {
        log.info("Converting Data Store exception to RestResponse : " + ex.getMessage());
        return new RestErrorInfo(ex, "You messed up.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public
    @ResponseBody
    RestErrorInfo handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, HttpServletResponse response) {
        log.info("ResourceNotFoundException handler:" + ex.getMessage());
        return new RestErrorInfo(ex, "Sorry I couldn't find it.");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

}