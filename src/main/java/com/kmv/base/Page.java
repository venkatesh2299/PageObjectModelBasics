package com.kmv.base;

import java.io.FileInputStream;
import org.testng.Assert;
import org.testng.Reporter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.kmv.utilities.ExcelReader;
import com.kmv.utilities.ExtentManager;

import com.kmv.utilities.Utilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Page {

	/*
	 * logs properties reports jdbc
	 * 
	 */

	public static WebDriver driver; // make it static,to avoid opening new browser
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\com\\kmv\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports repo = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;

	public static TopMenu menu;

	public Page() {

		// set driver to null,to avoid opening new browser

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\com\\kmv\\properties\\Config.properties");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			config.load(fis);
			log.debug("Config file loaded..!!!");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\com\\kmv\\properties\\OR.properties");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			or.load(fis);
			log.debug("OR file loaded..!!!");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\kmv\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\kmv\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				or.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// jenkins system environment
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");
			}

			config.setProperty("browser", browser);
			if (config.getProperty("browser").equals("firefox")) {

				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\kmv\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("chrome")) {

				/*
				 * System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
				 * + "\\src\\test\\resources\\com\\kmv\\executables\\chromedriver.exe");
				 */
				driver = new ChromeDriver();
				log.debug("Chrome launched..!!!");
			} else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\kmv\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

			// chrome options to remove any popups,alerts,infobars
			Map<String, Object> prefs = new HashMap<String, Object>();

			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");

			driver = new ChromeDriver(options);

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to..: " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			menu = new TopMenu(driver);
		}
		
		

	}
	
	public static void quit() {
		driver.quit();
	}
	
	// create keywords like click and type
		public static void click(String locator) {
			
			if(locator.endsWith("_CSS")){
			driver.findElement(By.cssSelector(or.getProperty(locator))).click();
			} else if(locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(or.getProperty(locator))).click();
			}else if(locator.endsWith("_ID")) {
				driver.findElement(By.id(or.getProperty(locator))).click();
			}
			
			log.debug("Clicking on an element "+locator);
			test.log(LogStatus.INFO, "Clicking on.."+locator);
		}
		
		public static void type(String locator, String value) {
			
			if(locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
			}else if(locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
			}else if(locator.endsWith("_ID")) {
				driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
			}
			
			log.debug("Typing in an element "+locator+"Entered value as "+value);
			test.log(LogStatus.INFO, "Typing in.."+locator+" and entered Value as.."+value);
		}
		
		static WebElement dropdown;
		
		public static void select(String locator, String value) {
			if(locator.endsWith("_CSS")) {
				dropdown=driver.findElement(By.cssSelector(or.getProperty(locator)));
				}else if(locator.endsWith("_XPATH")) {
					dropdown=driver.findElement(By.xpath(or.getProperty(locator)));
				}else if(locator.endsWith("_ID")) {
					dropdown=driver.findElement(By.id(or.getProperty(locator)));
				}
			
			Select select = new Select(dropdown);
			select.selectByVisibleText(value);
			
			log.debug("Selecting from dropdown "+locator+" Value as "+value);
			test.log(LogStatus.INFO, "Selecting from dropdown..: "+locator+"  Value as.."+value);
		}
		
		
		
		public boolean isElementTrue(By by) {
			
			try {
				
				driver.findElement(by);
				return true;
			}
			catch(NoSuchElementException e) {
				return false;
			}
			
			
			
		}
		
		// soft assertion
		public static void verifyEquals(String actual,String expected) throws IOException {
			
			try {
				
				Assert.assertEquals(actual, expected);
				
			}catch(Throwable t) {
				
				Utilities.captureScreenshot();
				
				//reportNG
				Reporter.log("<br>"+"Verification failure: "+t.getMessage()+"<br>");
				Reporter.log("<a target=\"_blank\" href="+Utilities.screenShotName+"><img src="+Utilities.screenShotName+" height=200 width=200></img></a>");
				Reporter.log("<br>");
				Reporter.log("<br>");
				
				//Extent Reports
				test.log(LogStatus.FAIL," Verification failure: " +t.getMessage());
				test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenShotName));
				
			
			
			}
			
		}

}
