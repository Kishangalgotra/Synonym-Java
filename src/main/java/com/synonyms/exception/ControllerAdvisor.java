package com.synonyms.exception;

import com.synonyms.controller.SynonymsController;
import com.synonyms.response.ApplicationResponse;
import com.synonyms.response.ResultCode;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(SynonymsController.class);

	@ExceptionHandler(CommonException.class)
	public ResponseEntity<Object> handleCommonException(HttpServletRequest request, CommonException exception){
		log.info("Request {} raised Error :{}",request.getRequestURL(), exception.getMessage());
		ApplicationResponse<String> error = new ApplicationResponse<>(ResultCode.FAILURE.getErrorCode(),
				exception.getMessage(),exception.getMessage());
		return ResponseEntity.status(exception.getHttpStatus()).body(error);
	}
}
