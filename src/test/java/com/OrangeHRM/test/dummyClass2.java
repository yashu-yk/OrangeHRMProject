package com.OrangeHRM.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.OrangeHRM.base.baseClass;


public class dummyClass2 extends baseClass {

	@Test
	public void dummytest2() {
		String title = getDriver().getTitle();
		assert title.equals("OrangeHRM"):"Test Failed- Title Not Matching";
		System.out.println("Test 2 Passed- Title is Matching");
	}
}

