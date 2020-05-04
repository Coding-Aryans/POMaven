package com.TestProject.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.TestProject.Basetest.Base;
import com.TestProject.Pages.LandingPage;
import com.TestProject.Pages.LoginPage;
import com.TestProject.Pages.ReCoverPage;
import com.TestProject.TestUtil.DataProviderClass;

public class HomePageTest extends Base {
	
	
    @Test(dataProviderClass = DataProviderClass.class,dataProvider = "dp",priority = 1,enabled=false)	
	public void LoginTest(Hashtable<String,String> data) {
    	testLevelLog.get().assignAuthor("Akshay");
		testLevelLog.get().assignCategory("Regression");
		LoginPage lp=new LoginPage();
    	LandingPage Land=lp.doLoginForLandingPage(data.get("username"),data.get("password"));
		

}

    
  @Test(priority = 0,enabled=false)
    public void RecoverAccount() {
    	testLevelLog.get().assignAuthor("Akshay");
    	testLevelLog.get().assignCategory("Sanity");
    	LoginPage lp=new LoginPage();
    	lp.ForgotPass();
    }
  
  @Test(dataProviderClass = DataProviderClass.class,dataProvider = "dp",enabled=false)
  public void CreateAccount(Hashtable<String,String> data) {
  	testLevelLog.get().assignAuthor("Akshay");
  	testLevelLog.get().assignCategory("Smoke");
  	LoginPage lp=new LoginPage();
  	lp.CreateAccount(data.get("firstName"),data.get("Surname"),data.get("EMob"),data.get("NewPass"),data.get("Day"),data.get("Year"),data.get("Month"));
  }
  
  @Test
  public void ValidatePageLinks() {
	  LoginPage lp=new LoginPage();
	  lp.Links();
  }
}
