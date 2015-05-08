package com.tzyangtang.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalErrorHandler {
	private static final Logger logger = Logger
			.getLogger(GlobalErrorHandler.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	void handleBadRequest(HttpServletRequest req, Exception ex)
			throws Exception {

		logger.error(req.getRequestURL().toString(), ex);

		// If the exception is annotated with @ResponseStatus rethrow it and let
		// the framework handle it
		if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null)
			throw ex;
	}
}
