package com.kmv.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kmv.base.Page;
import com.kmv.crm.pages.accounts.AccountsPage;
import com.kmv.crm.pages.accounts.CreateAccountPage;
import com.kmv.pages.HomePage;
import com.kmv.pages.LoginPage;
import com.kmv.pages.ZohoAppPage;
import com.kmv.utilities.Utilities;

public class LoginTest extends BaseTest{
	
	@Test(dataProviderClass=Utilities.class, dataProvider="dp")
	public void loginTest(Hashtable<String,String> data) throws InterruptedException {
		HomePage home = new HomePage();
		LoginPage lp = home.goToLogin();
		ZohoAppPage zp = lp.doLogin(data.get("username"), data.get("password"));
		// forcefully fail the test to capture screenshot
		//Assert.fail("Login test failed..");
		
		
	}

}
