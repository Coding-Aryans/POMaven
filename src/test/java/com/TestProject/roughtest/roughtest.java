package com.TestProject.roughtest;

import org.testng.annotations.Test;

import com.TestProject.Basetest.Base;
import com.TestProject.rough.Roughs;

public class roughtest extends Base{
	
	
	@Test
	public void testdemo() {
		Roughs r=new Roughs();
		r.testDemoPage();
	}

}
