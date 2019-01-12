package org.eltn.projects.core.tests.exceptions;

public class InvalidTestException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidTestException() {
		super();
	}
	
	public InvalidTestException(String message) {
		super(message);
	}
}