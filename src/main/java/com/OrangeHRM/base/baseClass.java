package com.OrangeHRM.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.OrangeHRM.utilities.ExtentManager;
import com.OrangeHRM.utilities.LoggerManager;
import com.OrangeHRM.actiondriver.actionDriver;

public class baseClass {

	private static Properties prop;
//	private WebDriver driver;
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static ThreadLocal<actionDriver> actionDriver = new ThreadLocal<>();
	public static final Logger logger = LoggerManager.getLogger(baseClass.class);
	
	@BeforeSuite
	public void loadConfig() throws IOException {
		// Load the configuration file
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/config.properties");
		prop.load(fis);
		logger.info("config.properties file loaded");	
	}
	
	@BeforeMethod
	public void setUp() {
		launchBrowser();
		configBrowser();
		
		// Initialize ActionDriver for the current Thread
				actionDriver.set(new actionDriver(getDriver()));
				logger.info("ActionDriver initlialized for thread: " + Thread.currentThread().getId());
				
		logger.info("WebDriver Initialized and Browser Maximized");
//		logger.trace("This is a Trace message");
//		logger.error("This is a error message");
//		logger.debug("This is a debug message");
//		logger.fatal("This is a fatal message");
//		logger.warn("This is a warm message");

	}
	
	public void launchBrowser() {

		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) {
			// Create ChromeOptions
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=new"); // Run Chrome in headless mode
			options.addArguments("--disable-gpu"); // Disable GPU for headless mode
			options.addArguments("--window-size=1920,1080"); // Set window size
			options.addArguments("--disable-notifications"); // Disable browser notifications
			options.addArguments("--no-sandbox"); // Required for some CI environments like Jenkins
			options.addArguments("--disable-dev-shm-usage"); // Resolve issues in resource-limited environments

			driver.set(new ChromeDriver(options));
			ExtentManager.registerDriver(getDriver());
			logger.info("ChromeDriver Instance is created.");
		}
		
		else if(browser.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
			ExtentManager.registerDriver(getDriver());
			logger.info("EdgeDriver Instance is created.");
		}
		else {
			throw new IllegalArgumentException("Browser not supported");
		}
		
	}
	
	public void configBrowser() {
		// Implicit Wait
				int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
				//boolean seleniumGrid = Boolean.parseBoolean(System.getProperty("seleniumGrid", prop.getProperty("seleniumGrid")));
				getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

				// maximize the browser
				//getDriver().manage().window().maximize();

				//navigate to url
				try {
					getDriver().get(prop.getProperty("url"));
				} catch (Exception e) {
					System.out.println("Failed to navigte to the URL");
				}
				
	}
	
	// Getter Method for WebDriver
		public static WebDriver getDriver() {

			if (driver.get() == null) {
				System.out.println("WebDriver is not initialized");
				throw new IllegalStateException("WebDriver is not initialized");
			}
			return driver.get();

		}
		
		// Getter Method for ActionDriver
		public static actionDriver getActionDriver() {

			if (actionDriver.get() == null) {
				System.out.println("ActionDriver is not initialized");
				throw new IllegalStateException("ActionDriver is not initialized");
			}
			return actionDriver.get();

		}
	
		// Driver setter method
		public void setDriver(ThreadLocal<WebDriver> driver) {
			this.driver = driver;
		}
	
	public static Properties getProperties() {
		return prop;
	}
	
	public void setProperties(Properties prop) {
		this.prop = prop;
	}
	
	// Static wait for pause
	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
		@AfterMethod
		public synchronized void tearDown() {
			if (getDriver() != null) {
				try {
					getDriver().quit();
				} catch (Exception e) {
					System.out.println("unable to quit the driver:" + e.getMessage());
				}
			}
			logger.info("WebDriver instance is closed.");
			driver.remove();
			actionDriver.remove();
			// driver = null;
			// actionDriver = null;
			 //ExtentManager.endTest(); 
			 //--This has been implemented in TestListener
		}
}
