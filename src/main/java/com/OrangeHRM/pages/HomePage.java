package com.OrangeHRM.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.OrangeHRM.actiondriver.actionDriver;
import com.OrangeHRM.base.baseClass;

public class HomePage {

		private actionDriver actionDriver;
		
		//defining locators using By class
		private By adminTab = By.xpath("//span[text()='Admin']");
		private By homePageOrangeHRMLogoImage = By.xpath("//img[@alt='client brand banner']");
		private By homePageIdButton = By.xpath("//img[@alt=\"profile picture\" and @class=\"oxd-userdropdown-img\"]");
		private By homePageLogoutButton = By.xpath("//a[text()='Logout']");
		
		public HomePage(WebDriver driver) {
			this.actionDriver = new actionDriver(driver);
		}
		
		// Method to verify if Admin tab is visible
		public boolean isAdminTabVisible() {
			return actionDriver.isDisplayed(adminTab);
		}
		
		public boolean verifyOrangeHRMlogo() {
			return actionDriver.isDisplayed(homePageOrangeHRMLogoImage);
		}
		
		// Method to perform logout operation
		public void logout() {
			actionDriver.click(homePageIdButton);
			actionDriver.click(homePageLogoutButton);
		}
}
