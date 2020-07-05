package com.kmv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.kmv.base.Page;
import com.kmv.crm.CRMHomePage;

public class ZohoAppPage extends Page {

	public CRMHomePage gotoCrm() {
		click("crmlink_XPATH");
		return new CRMHomePage();
	}

	public void gotoConnect() {
		driver.findElement(By.xpath("//a[@href='https://connect.zoho.in/']")).click();
		;
	}

}
