package io.github.etuzon.unit.tests.exceptions;

public class InvalidUnitTestException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidUnitTestException() {
		super();
	}
	
	public InvalidUnitTestException(String message) {
		super(message);
	}
}