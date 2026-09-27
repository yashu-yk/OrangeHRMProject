package com.OrangeHRM.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.OrangeHRM.base.baseClass;
import com.OrangeHRM.pages.HomePage;
import com.OrangeHRM.pages.LoginPage;
import com.OrangeHRM.utilities.ExtentManager;

@Listeners(com.OrangeHRM.listeners.TestListener.class)
public class HomePageTest extends baseClass {

	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver());
		homePage  = new HomePage(getDriver());
	}
	
	@Test
	public void verifyOrangeHRMLogo() {
		//ExtentManager.startTest("Home Page Verify Logo Test"); ...//implemented in ITestlistener
		ExtentManager.logStep("Navigating to Login Page entering username and password");
		loginPage.login("admin", "admin123");
		ExtentManager.logStep("Verifying Logo is visible or not");
		Assert.assertTrue(homePage.verifyOrangeHRMlogo(),"Logo is not visible");
		ExtentManager.logStep("Validation Successful");
		homePage.logout();
		ExtentManager.logStep("Logged out Successfully!");
		//Assert.assertTrue(loginPage.isLoginTextDisplyed(),"This is not login page");
	}
}
