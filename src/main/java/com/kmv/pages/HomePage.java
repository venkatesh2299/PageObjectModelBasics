package com.kmv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.kmv.base.Page;

public class HomePage extends Page {

	public void goToSupport() {

		driver.findElement(By.cssSelector("a.zh-support")).click();
	}

	public void goToSignUp() {
		driver.findElement(By.cssSelector("a.zh-signup")).click();
	}

	public LoginPage goToLogin() {

		click("loginlink_CSS");
		return new LoginPage();
	}

	public void goToCustomers() {
		driver.findElement(By.cssSelector("a.zh-customers")).click();
	}

	public void goToLearnMore() {
		driver.findElement(By.cssSelector("a.learn-more")).click();
	}

	public void validateFooterLinks() {

	}

}
