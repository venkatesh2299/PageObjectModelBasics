package com.kmv.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kmv.base.Page;
import com.kmv.crm.pages.accounts.AccountsPage;
import com.kmv.crm.pages.accounts.CreateAccountPage;
import com.kmv.pages.ZohoAppPage;
import com.kmv.utilities.Utilities;

public class CreateAccountTest {
	
	@Test(dataProviderClass=Utilities.class,dataProvider="dp")
	public void createAccountTest(Hashtable<String,String> data) throws InterruptedException {
		
		ZohoAppPage zp = new ZohoAppPage();
		zp.gotoCrm();
		
		Thread.sleep(10000);
		AccountsPage account = Page.menu.gotoAccounts();
		CreateAccountPage cap = account.gotoCreateAccounts();
		cap.createAccount(data.get("accountname"));
		// forcefully fail the test to capture screenshot
		//Assert.fail("Create account test failed...");
		
	}

}
