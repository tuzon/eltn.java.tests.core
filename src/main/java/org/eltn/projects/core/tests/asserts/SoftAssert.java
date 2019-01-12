package org.eltn.projects.core.tests.asserts;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class SoftAssert {
	private static final Logger logger = Logger.getLogger(SoftAssert.class.getName());

	private static String errors = "";

	public static void assertTrueNow(boolean condition, String errorMessage) {
		assertTrue(condition, errorMessage);
		assertAll();
	}

	public static void assertTrueNow(boolean condition, String errorMessage, String infoMessage) {
		assertTrue(condition, errorMessage, infoMessage);
		assertAll();
	}

	public static void failNow(String errorMessage) {
		assertTrueNow(false, errorMessage);
	}
	
	public static void assertTrue(boolean condition, String errorMessage) {
		assertTrue(condition, errorMessage, null);
	}

	public static void assertTrue(boolean condition, String errorMessage, String infoMessage) {
		if (infoMessage != null) {
			logger.info(infoMessage);
		}

		if (condition == false) {
			errorMessage = "[Assertion Error] " + errorMessage;
			
			logger.error(errorMessage);
			
			if (errors.isEmpty() == false) {
				errors += "\n";
			}

			errors += errorMessage;
		}
	}

	public static void fail(String errorMessage) {
		assertTrue(false, errorMessage);
	}
	
	public static void assertAll() {
		String temp = errors;
		errors = "";
		Assert.assertTrue(temp.isEmpty(), temp);
	}

	public static String getCurrentErrors() {
		return errors;
	}

	public static String emptyErrors() {
		String temp = errors;
		errors = "";
		return temp;
	}
}