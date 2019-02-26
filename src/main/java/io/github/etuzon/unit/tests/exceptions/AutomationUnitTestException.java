package io.github.etuzon.unit.tests.exceptions;

import io.github.etuzon.unit.tests.asserts.SoftAssertUnitTest;

public class AutomationUnitTestException extends Exception {

	private static final long serialVersionUID = 1L;

	public AutomationUnitTestException() {
		this("");
	}

	public AutomationUnitTestException(Exception e) {
		this(e.getMessage() + "\n" + getExceptionStacktrace(e));
	}

	public AutomationUnitTestException(String message) {
		super(message);
		super.addSuppressed(new Throwable(SoftAssertUnitTest.emptyErrors()));
	}

	private static String getExceptionStacktrace(Exception e) {
		StringBuilder sb = new StringBuilder();

		for (StackTraceElement stackElement : e.getStackTrace()) {
			sb.append(stackElement.getClassName()).append(".").append(stackElement.getMethodName()).append("(")
					.append(stackElement.getFileName()).append(":").append(stackElement.getLineNumber()).append(")\n");
		}

		return sb.toString();
	}
}