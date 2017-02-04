package com.test.username.exception;

public class BlackListedUserException extends Exception {
	public BlackListedUserException(String message){
		super(message);
	}
}
