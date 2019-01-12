package org.eltn.projects.core.tests.base;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.eltn.projects.core.tests.asserts.SoftAssert;
import org.eltn.projects.core.tests.exceptions.InvalidTestException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
	public static final String CLASS_SEPARETOR = "================================================================";

	private final Logger logger = Logger.getLogger(BaseTest.class.getName());

	protected BaseTest() {
	}

	@BeforeClass
	public void beforeClassBaseTest() {
		logger.info("\n" + CLASS_SEPARETOR + "\nStarting Class [" + super.getClass().getSimpleName() + "]\n"
				+ CLASS_SEPARETOR);
	}

	@BeforeMethod
	public void beforeMethodBaseTest(Method method) {
		logger.info("Starting Test [" + super.getClass().getSimpleName() + "." + method.getName() + "]");
	}

	@AfterMethod
	public void afterMethodBaseTest(ITestResult result) throws InvalidTestException {
		String errors = SoftAssert.emptyErrors();

		if (result.isSuccess()) {
			if (errors.isEmpty()) {
				logger.info("Test [" + super.getClass().getSimpleName() + "." + result.getName() + "] PASSED");
			} else {
				throw new InvalidTestException(getInvalidTestExceptionMessage(result, errors));
			}
		} else {
			logger.info("Test [" + super.getClass().getSimpleName() + "." + result.getTestName() + "] FAILED");
			if (SoftAssert.emptyErrors().isEmpty() == false) {
				throw new InvalidTestException(getInvalidTestExceptionMessage(result, errors));
			}
		}
	}

	private String getInvalidTestExceptionMessage(ITestResult result, String errors) {
		return "Missing SoftAssert.assertAll() in test [" + super.getClass().getSimpleName() + "." + result.getName()
				+ "]\nAssert error list:\n" + errors;
	}
}