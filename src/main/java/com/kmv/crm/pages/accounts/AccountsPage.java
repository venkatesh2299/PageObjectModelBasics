package com.kmv.crm.pages.accounts;

import org.openqa.selenium.By;

import com.kmv.base.Page;

public class AccountsPage extends Page{
	
	public CreateAccountPage gotoCreateAccounts() {
		click("gotocreateaccountbtn_XPATH");
		return new CreateAccountPage();
	}
	
	public void gotoImportsAccount() {
		
	}

}
