package com.OrangeHRM.test;

import com.OrangeHRM.utilities.RetryAnalyzer;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.OrangeHRM.base.baseClass;

public class dummyClass extends baseClass {

	@Test()
	public void dummytest() {
		String title = getDriver().getTitle();
		assert title.equals("OrangeHRM"):"Test Failed- Title Not Matching";
		System.out.println("Test Passed- Title is Matching");
		//throw new SkipException("Skipping this test as part of test");
	}
}

