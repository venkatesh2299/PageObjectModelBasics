package com.kmv.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.kmv.base.Page;

import com.kmv.utilities.MonitoringMail;
import com.kmv.utilities.TestConfig;

import com.kmv.utilities.Utilities;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends Page implements ITestListener, ISuiteListener {

	public String messageBody;
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
		
	}

	public void onStart(ITestContext arg0) {
		
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+" Failed with exception: "+arg0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenShotName));
		
		Reporter.log("Click to see screenshot...");
		Reporter.log("<a target=\"_blank\" href="+Utilities.screenShotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+Utilities.screenShotName+"><img src="+Utilities.screenShotName+" height=200 width=200></img></a>");
		repo.endTest(test);
		repo.flush();
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP,arg0.getName().toUpperCase()+ " Skipped the test as the runmode is NO..");
		repo.endTest(test);
		repo.flush();
	}

	public void onTestStart(ITestResult arg0) {
		test = repo.startTest(arg0.getName().toUpperCase());
		// Runmode-Y
		System.out.println(Utilities.isTestRunnable(arg0.getName(), excel));
		
		
	
		
	}

	public void onTestSuccess(ITestResult arg0) {
		
		test.log(LogStatus.PASS, arg0.getName().toUpperCase()+" PASS..");
		repo.endTest(test);
		repo.flush();
	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		MonitoringMail mail = new MonitoringMail();
		
		
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/job/PageObjectModelBasics/Extent_20Report/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

}
