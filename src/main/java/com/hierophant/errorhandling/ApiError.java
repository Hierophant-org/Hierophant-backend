package com.hierophant.errorhandling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ApiError {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;//timestamp
	
	private int status;//status
	
	private String error; // Represents our HTTP error (in words)

	private String message;
	private String debugMessage;
	List<ApiSubError> subErrors = new ArrayList<>();
	//all the ways to create a new APi Exception
	protected ApiError() {
		super();
		timestamp = LocalDateTime.now();
	}
	
	public ApiError(HttpStatus status) {
		this();
		this.status = status.value(); 
		this.error = status.getReasonPhrase();
	}
	
	public ApiError(HttpStatus status, Throwable ex) {
		this(status);
		this.message = "No message available";
		this.debugMessage = ex.getLocalizedMessage();
	}
	
	public ApiError(HttpStatus status, String message, Throwable ex) {
		this(status, ex);
		this.message = message;
	}
	
	public void addSubError(ApiSubError error) {
		
		this.subErrors.add(error);
	}
}