package com.hierophant.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hierophant.errorhandling.ApiError;
import com.hierophant.errorhandling.ApiValidationError;
import com.hierophant.exceptions.UserNotFoundException;
//Handler for rest exceptions
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	Logger log = LoggerFactory.getLogger(this.getClass());
	//build the response entity
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}

	/*
	 * Intercepts exceptions that caused by Invalid JSON
	 * 
	 * Might want to send back a 4XX Series
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String error = "Malformed JSON Request";
		log.error( "Malformed JSON Request in Api");
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
	}

	/*
	 * Intercepts exceptions caused by Hibernate Validation
	 * 
	 * Recall the javax.validation package
	 * With annotations such as @Length or @Pattern or @Email
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String error = "Request failed validation";
		log.error( "Request failed validation in Api Error");
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex);
		
		for(FieldError e : ex.getFieldErrors()) {
			apiError.addSubError(new ApiValidationError(e.getObjectName(), e.getDefaultMessage(), e.getField(),
					e.getRejectedValue()));
		}
		return buildResponseEntity(apiError);
		
	}

	/*
	 * The below handlers will intercept custom business logic exceptions/scenarios
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
		String error = "No User Found";
		log.error(error);
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, error, ex));
	}
}
