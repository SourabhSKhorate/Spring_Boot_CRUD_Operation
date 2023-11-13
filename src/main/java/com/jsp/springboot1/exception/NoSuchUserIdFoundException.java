package com.jsp.springboot1.exception;

public class NoSuchUserIdFoundException extends Exception{
	
	public NoSuchUserIdFoundException() {
		super("No user found with mentioned id");
	}
}
