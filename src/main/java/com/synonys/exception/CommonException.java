package com.synonys.exception;


import org.springframework.http.HttpStatus;

public class CommonException extends RuntimeException {

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String getMessage() {
		return message;
	}

	private final HttpStatus httpStatus;
	private final String message;

	public CommonException(HttpStatus httpStatus, String message){
		super();
		this.httpStatus =httpStatus;
		this.message = message;
	}
}
