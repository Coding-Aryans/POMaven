package com.TestProject.BasePage;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.TestProject.TestUtil.ExcelReader;
import com.TestProject.TestUtil.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BasePage {
	
	
	
	public static WebDriver driver;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	public static Properties Config=new Properties();
	public static Properties OR=new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"/src/test/resources/TestData/TestData.xlsx");
	public static WebDriverWait wait;
	public static ExtentReports extentReport=ExtentManager.GetExtent(System.getProperty("user.dir")+"/src/test/resources/TestReports/html/extent.html");
	public static ThreadLocal<ExtentTest> classLevelLog = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelLog = new ThreadLocal<ExtentTest>();
	public BasePage() {
		
	if(driver==null) {
		
		//Create a map to store  preferences 
		Map<String, Object> prefs = new HashMap<String, Object>();

		//add key and value to map as follow to switch off browser notification
		//Pass the argument 1 to allow and 2 to block
		prefs.put("profile.default_content_setting_values.notifications", 2);

		//Create an instance of ChromeOptions 
		ChromeOptions options = new ChromeOptions();

		// set ExperimentalOption - prefs 
		options.setExperimentalOption("prefs", prefs);
		
		PropertyConfigurator.configure("./src/test/resources/PropertiesFiles/log4j.properties");
		log.info("LOG file configure");
		
	try {
		fis=new FileInputStream("./src/test/resources/PropertiesFiles/Config.properties");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	try {
		Config.load(fis);
		log.info("Config file is loaded");
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	try {
		fis=new FileInputStream("./src/test/resources/PropertiesFiles/OR.properties");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	try {
		OR.load(fis);
		log.info("OR file is loaded");
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
	if(Config.getProperty("browser").equals("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver(options);
		log.info(" chrome browser launched  successfully..");
			} 
	else if(Config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				log.info(" firefox browser launched  successfully..");
					}
		driver.get(Config.getProperty("testUrl"));
		log.info("testurl read successfully..");
		driver.manage().window().maximize();
		log.info("window size maximum successfully..");
	//	driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("Implicit.Wait")),TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,Integer.parseInt(Config.getProperty("explicit.wait")));
	
	}
	
	}
	
	
	

	
	
	
	public static boolean isElementPresent(String locatorKey) {

		try {
			if (locatorKey.endsWith("_Xpath")) {

				driver.findElement(By.xpath(OR.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locatorKey)));
			}

			log.info("Finding an Element : " + locatorKey);
			return true;
		} catch (Throwable t) {

			log.info("Error while finding an Element : " + locatorKey + " error message is : " + t.getMessage());
			return false;
		}

	}
	
	public static void type(String locatorKey,String value) {
	if(locatorKey.endsWith("_Xpath")) {
		driver.findElement(By.xpath(OR.getProperty(locatorKey))).sendKeys(value);
	}else if(locatorKey.endsWith("_CSS")) {
		driver.findElement(By.cssSelector(OR.getProperty(locatorKey))).sendKeys(value);
		
	}
//	test.log(LogStatus.INFO, "Typing in : " + locatorKey + " entered value as " + value);
	testLevelLog.get().log(Status.INFO, "Typing in : " + locatorKey + " entered value as " + value);
	}
	
	public static void click(String locatorKey) {
		if(locatorKey.endsWith("_Xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locatorKey))).click();
		}else if(locatorKey.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locatorKey))).click();
		}
	//	test.log(LogStatus.INFO, "Clicked in : " + locatorKey );
		testLevelLog.get().log(Status.INFO, "Clicked in : " + locatorKey );
		}
	
	 static WebElement dropdown;
	    public void Select(String locator,String value) {

			if (locator.endsWith("_Xpath")) {
				dropdown=driver.findElement(By.xpath(OR.getProperty(locator)));
			} else if (locator.endsWith("_CSS")) {
				dropdown=driver.findElement(By.cssSelector(OR.getProperty(locator)));
			} else if (locator.endsWith("_ID")) {
				dropdown=driver.findElement(By.id(OR.getProperty(locator)));
			}

	    	Select select=new Select(dropdown);
	    	//select.selectByVisibleText(value);
	    	select.selectByValue(value);
	    
	    	//test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + value);
	    	testLevelLog.get().log(Status.INFO, "Selecting from dropdown: " + locator+ " value as " + value );
	    }
	
	
	
	public static void clear(String locatorKey) {
		driver.findElement(By.xpath(OR.getProperty(locatorKey))).clear();
		testLevelLog.get().log(Status.INFO, "clear in : " + locatorKey + "  value  ");
	}
      
	public static void getLinks(String locatorKey) {
		List<WebElement> elem =driver.findElements(By.xpath(OR.getProperty(locatorKey)));
		 for (WebElement webElement : elem) {
			  String link=webElement.getText();
			   testLevelLog.get().log(Status.INFO, "Page links are : " + link );
	}
		// testLevelLog.get().log(Status.INFO, "clear in : " + locatorKey + "  value  ");
	}
}
