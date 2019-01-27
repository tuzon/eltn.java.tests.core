package org.eltn.projects.core.tests.asserts;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class SoftAssert {
	public enum InfoMessageLogLevelEnum {
		TRACE, DEBUG, INFO;
	}

	private static final Logger logger = Logger.getLogger(SoftAssert.class.getName());

	private static String errors = "";
	private static InfoMessageLogLevelEnum infoMessageLevel = InfoMessageLogLevelEnum.INFO;

	public static void assertTrueNow(boolean condition, String errorMessage) {
		assertTrue(condition, errorMessage);
		assertAll();
	}

	public static void assertTrueNow(boolean condition, String errorMessage, String infoMessage) {
		assertTrue(condition, errorMessage, infoMessage);
		assertAll();
	}

	public static void assertNotNullNow(Object object, String errorMessage) {
		assertNotNullNow(object, errorMessage, null);
	}
	
	public static void assertNotNullNow(Object object, String errorMessage, String infoMessage) {
		assertNotNull(object, errorMessage, infoMessage);
		assertAll();
	}
	
	public static void failNow(String errorMessage) {
		assertTrueNow(false, errorMessage);
	}
	
	public static boolean assertNotNull(Object object, String errorMessage) {
		return assertNotNull(object, errorMessage, null);
	}
	
	public static boolean assertNotNull(Object object, String errorMessage, String infoMessage) {
		return assertTrue(object != null, errorMessage, infoMessage);
	}

	public static boolean assertTrue(boolean condition, String errorMessage) {
		return assertTrue(condition, errorMessage, null);
	}

	public static boolean assertTrue(boolean condition, String errorMessage, String infoMessage) {
		if (infoMessage != null) {
			printInfoMessage(infoMessage);
		}

		if (condition == false) {
			errorMessage = "[Assertion Error] " + errorMessage;

			logger.error(errorMessage);

			if (errors.isEmpty() == false) {
				errors += "\n";
			}

			errors += errorMessage;
		}

		return condition;
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

	public static void setInfoMessageLogLevel(InfoMessageLogLevelEnum level) {
		infoMessageLevel = level;
	}

	private static void printInfoMessage(String message) {
		if (infoMessageLevel == InfoMessageLogLevelEnum.TRACE) {
			logger.trace(message);
		} else if (infoMessageLevel == InfoMessageLogLevelEnum.DEBUG) {
			logger.debug(message);
		} else if (infoMessageLevel == InfoMessageLogLevelEnum.INFO) {
			logger.info(message);
		}
	}
}