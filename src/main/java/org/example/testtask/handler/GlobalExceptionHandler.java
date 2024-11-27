package org.example.testtask.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
		log.warn("Missing request parameter: {}", ex.getParameterName());
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body("Required parameter '" + ex.getParameterName() + "' is missing.");
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		log.warn("Type mismatch for parameter: {}", ex.getName());
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body("Parameter '" + ex.getName() + "' has an invalid value.");
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
		log.warn("Invalid argument: {}", ex.getMessage());
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body("Invalid request: " + ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		log.error("Unexpected error occurred: {}", ex.getMessage(), ex);
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("An unexpected error occurred. Please try again later.");
	}
}


