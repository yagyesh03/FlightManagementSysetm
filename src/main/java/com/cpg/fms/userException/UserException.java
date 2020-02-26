package com.cpg.fms.userException;
public class UserException extends Exception {

	String message;
	public UserException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}