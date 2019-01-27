package org.eltn.projects.core.tests.exceptions;

import org.eltn.projects.core.tests.asserts.SoftAssert;

public class AutomationTestException extends Exception {

	private static final long serialVersionUID = 1L;

	public AutomationTestException() {
		this("");
	}

	public AutomationTestException(Exception e) {
		this(e.getMessage() + "\n" + getExceptionStacktrace(e));
	}

	public AutomationTestException(String message) {
		super(message);
		super.addSuppressed(new Throwable(SoftAssert.emptyErrors()));
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