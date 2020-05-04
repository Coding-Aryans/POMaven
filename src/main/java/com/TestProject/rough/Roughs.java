package com.TestProject.rough;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.TestProject.BasePage.BasePage;

public class Roughs extends BasePage  {
	 
	
	
	public void testDemoPage() {
		
		//Select("Day_Xpath",Date);
		
//	WebElement	ele=driver.findElement(By.xpath("//span[@class='_5k_3']/span[1]/input"));
	  
	   List<WebElement> elem =driver.findElements(By.xpath("//div[@id='pageFooterChildren']/ul"));
	   int len=elem.size();
	   System.out.println(len);
	   for (WebElement webElement : elem) {
		   System.out.println(webElement.getText()+" ");
		
	}
	   
	
	}
	

}
