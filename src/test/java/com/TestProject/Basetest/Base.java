package com.TestProject.Basetest;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import com.TestProject.BasePage.BasePage;
import com.aventstack.extentreports.ExtentTest;

public class Base extends  BasePage {

	
	

	
	@BeforeClass
	public void beforeClass() {
		
		ExtentTest classLevelTest = extentReport.createTest(getClass().getSimpleName());
		classLevelLog.set(classLevelTest);

	}
	@AfterSuite
	public void tearDown() {
		driver.quit();
		
	}
}
