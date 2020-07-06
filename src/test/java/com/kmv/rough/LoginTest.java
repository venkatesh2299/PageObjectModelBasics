package com.kmv.rough;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.kmv.base.Page;
import com.kmv.crm.pages.accounts.AccountsPage;
import com.kmv.crm.pages.accounts.CreateAccountPage;
import com.kmv.pages.HomePage;
import com.kmv.pages.LoginPage;
import com.kmv.pages.ZohoAppPage;

public class LoginTest {

	public static void main(String[] args) throws InterruptedException {

		// this is third change .  right click on login test to commit
		HomePage home = new HomePage();
		LoginPage lp = home.goToLogin();
		ZohoAppPage zp = lp.doLogin("venkateshkm2001@gmail.com", "zohokmv2020");
		zp.gotoCrm();
		AccountsPage account = Page.menu.gotoAccounts();
		CreateAccountPage cap = account.gotoCreateAccounts();
		cap.createAccount("Savings");
		
	}

}
