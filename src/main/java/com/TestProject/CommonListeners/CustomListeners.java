package com.TestProject.CommonListeners;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.TestProject.BasePage.BasePage;
import com.TestProject.TestUtil.CaptureScreenShots;
import com.TestProject.rough.Roughs;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class CustomListeners extends BasePage implements ITestListener {

	public void onTestStart(ITestResult result) {
		// test = rep.startTest(result.getName().toUpperCase());
		ExtentTest test = classLevelLog.get().createNode(result.getName());
		testLevelLog.set(test);
		testLevelLog.get().info("Testcase:" + result.getName() + "test execution started");
	}

	public void onTestSuccess(ITestResult result) {
//		test.log(LogStatus.PASS, result.getName().toUpperCase()+" PASS");
//		rep.endTest(test);
//		rep.flush();
	
		testLevelLog.get().pass("This test case got passed");

		try {
			testLevelLog.get().addScreenCaptureFromPath(CaptureScreenShots.capture());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentReport.flush();

	}

	public void onTestFailure(ITestResult result) {

	//	String excepionMessage = result.getThrowable().toString();
		String excepionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		testLevelLog.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");

		String failureLogg = "This Test case got Failed";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testLevelLog.get().log(Status.FAIL, m);
		
		
		try {
			testLevelLog.get().addScreenCaptureFromPath(CaptureScreenShots.capture());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentReport.flush();

//		test.log(LogStatus.FAIL, result.getName().toUpperCase()+"<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
//				+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
//				+ " \n");
//		rep.endTest(test);
//		rep.flush();
	}

	public void onTestSkipped(ITestResult result) {
//		test.log(LogStatus.SKIP, result.getName().toUpperCase()+" Skipped the test as the Run mode is NO");
//		rep.endTest(test);
//		rep.flush();
		testLevelLog.get().skip("This test case got skipped");
		extentReport.flush();

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
