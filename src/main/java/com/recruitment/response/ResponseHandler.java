package com.recruitment.response;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	private static final Logger logger = LoggerFactory.getLogger(ResponseHandler.class);

	/**
	 * A private constructor to hide the implicit public one
	 */
	private ResponseHandler() {
		logger.debug("Private Constructor Called");
	}

	private static final String IS_SUCCESS = "isSuccess";
	private static final String MESSAGE_KEY = "message";
	private static final String DATA_KEY = "data";
	private static final String ERROR_KEY = "error";

	public static ResponseEntity<Object> success (Object response, String message) {
		Map<Object, Object> result = new HashMap<>();
		result.put(IS_SUCCESS, true);
		result.put(MESSAGE_KEY, message);
		result.put(DATA_KEY, response);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public static ResponseEntity<Object> success (String message) {
		Map<Object, Object> result = new HashMap<>();
		result.put(IS_SUCCESS, true);
		result.put(MESSAGE_KEY, message);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public static ResponseEntity<Object> badRequest (String errorMessage) {
		Map<Object, Object> result = new HashMap<>();
		result.put(IS_SUCCESS, false);
		result.put(ERROR_KEY, errorMessage);
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<Object> notFound(String message) {
		Map<Object, Object> result = new HashMap<>();
		result.put(IS_SUCCESS, false);
		result.put(ERROR_KEY, message);
		return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<Object> alreadyExist( String message)
	{
		Map<Object, Object> result = new HashMap<>();
		result.put(IS_SUCCESS, false);
		result.put(ERROR_KEY, message);
		return new ResponseEntity<>(result, HttpStatus.CONFLICT );
	}

}
