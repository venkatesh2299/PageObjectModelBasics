package com.kmv.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.kmv.crm.pages.accounts.AccountsPage;

public class TopMenu {

	/*
	 * HomePage HASA TopMenu
	 * AccountsPage HASA TopMenu
	 * E#very page HASA TopMenu
	 * 
	 *  
	 * 
	 * */
	
	WebDriver driver;
	
	public TopMenu(WebDriver driver) {
		this.driver = driver;
	}
	public void gotoHome() {

	}

	public void gotoLeads() {

	}

	public void gotoContacts() {

	}

	public AccountsPage gotoAccounts() {

		Page.click("accountstab_XPATH");
		return new AccountsPage();
	}

	public void gotoDeals() {

	}
	
	public void signOut() {
		
	}

}
