package com.kmv.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.kmv.base.Page;

public class LoginPage extends Page{
	
	
	public ZohoAppPage doLogin(String username, String password) throws InterruptedException {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		

		type("email_CSS",username);
		//span[text()='Next']
		click("nextbtn_XPATH");
		Thread.sleep(3000);
		
		type("password_CSS",password);
		click("signin_XPATH");
		
		Thread.sleep(20000);
		
		return new ZohoAppPage();
	}
	
	public void contactSales() {
		 
	}

}
