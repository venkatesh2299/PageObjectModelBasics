package com.kmv.testcases;

import org.testng.annotations.AfterSuite;

import com.kmv.base.Page;

public class BaseTest {
	
	@AfterSuite
	public void tearDown() {
		Page.quit();
	}

}
