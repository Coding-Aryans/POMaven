package com.TestProject.Pages;

import org.testng.Assert;

import com.TestProject.BasePage.BasePage;

public class LoginPage extends BasePage {

	public LandingPage doLoginForLandingPage(String username, String password) {

		clear("email_Xpath");
		type("email_Xpath", username);
		type("pass_Xpath", password);
		click("RecoverLogin_Xpath");

		if (isElementPresent("Help_Xpath")) {
			click("TryAgain_CSS");
		}

		Assert.assertTrue(isElementPresent("logoutAnchr_Xpath"), "login tst failed");

		return new LandingPage();
	}

	

	public ReCoverPage ForgotPass() {
		click("ForgottenPass_CSS");
		return new ReCoverPage();

	}

	public void CreateAccount(String firstName,String Surname,String EMob,String NewPass,String Day,String year,String Month)  {
		type("firstName_Xpath",firstName);
		type("LastName_CSS",Surname);
		type("EMob_CSS",EMob);
		type("NewPass_CSS",NewPass);
		try {
			Thread.sleep(20);
			Select("Day_Xpath",Day);
			Select("Year_Xpath",year);
			Select("Month_Xpath",Month);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
       click("Gender_Xpath");
      click("SignUp_Xpath");
	}

	public void Links() {
		
		getLinks("FooterLinks_Xpath");

	}
}
