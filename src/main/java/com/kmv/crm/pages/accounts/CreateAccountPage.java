package com.kmv.crm.pages.accounts;

import org.openqa.selenium.By;

import com.kmv.base.Page;

public class CreateAccountPage extends Page {
	
	public void createAccount(String accountName) {
		type("accountname_CSS",accountName);
	}

}
