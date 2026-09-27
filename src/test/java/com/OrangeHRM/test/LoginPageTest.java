package com.OrangeHRM.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.OrangeHRM.base.baseClass;
import com.OrangeHRM.pages.HomePage;
import com.OrangeHRM.pages.LoginPage;
import com.OrangeHRM.utilities.ExtentManager;

public class LoginPageTest extends baseClass{

	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver());
		homePage  = new HomePage(getDriver());
	}
	
	@Test
	public void verifyValidLoginTest() {
		//ExtentManager.startTest("Valid Login Test");
		System.out.println("Running testMethod1 on thread: " + Thread.currentThread().getId());
		ExtentManager.logStep("Navigating to Login Page entering username and password");
		loginPage.login("admin","admin123");
		ExtentManager.logStep("Verifying Admin tab is visible or not");
		Assert.assertTrue(homePage.isAdminTabVisible(),"Admin tab should be visible after successfull login ");
		ExtentManager.logStep("Validation Successful");
		homePage.logout();
		ExtentManager.logStep("Logged out Successfully!");
		//staticWait(2);
		//Assert.assertTrue(loginPage.isLoginTextDisplyed(),"This is not login page");
	}
	
	@Test
	public void inValidLoginTest() {
		//ExtentManager.startTest("In-valid Login Test!");
		ExtentManager.logStep("Navigating to Login Page entering username and password");
		loginPage.login("admin","admin");
		String expectedErrorMessage = "Invalid credentials";
		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage),"Test Failed: Invalid error message");
		ExtentManager.logStep("Validation Successful");
	}
}
