package com.OrangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.OrangeHRM.actiondriver.actionDriver;

public class LoginPage {

	// Initialize the action driver object with webdriver stance
	private actionDriver actiondriver;
	
	public LoginPage(WebDriver driver) {
		this.actiondriver = new actionDriver(driver);
	}
	
	//defining locators using By class
	private By userNameField = By.name("username");
	private By passwordField = By.name("password");
	private By loginButton = By.xpath("//button[@type='submit']");
	private By errorMessage = By.cssSelector(".oxd-text.oxd-text--p.oxd-alert-content-text");
	private By loginText = By.xpath("//h5[text()=\"Login\"]");
	
	//method to perform login
	public void login(String userName, String password) {
		actiondriver.enterText(userNameField,userName);
		actiondriver.enterText(passwordField,password);
		actiondriver.click(loginButton);
	}
	
	//Method to check if the error message is displayed
	public void isErrorMessageDislpayed() {
		actiondriver.isDisplayed(errorMessage);
	}
	
	//Method to get the text from Error message
		public String getErrorMessageText() {
			return actiondriver.getText(errorMessage);
		}
		
		//Verify if error is correct or not
		public boolean verifyErrorMessage(String expectedError) {
			 return actiondriver.compareText(errorMessage, expectedError);
		}
		
		//verify login text is displayed
		public boolean isLoginTextDisplyed() {
			 return actiondriver.isDisplayed(loginText);
		}
}
