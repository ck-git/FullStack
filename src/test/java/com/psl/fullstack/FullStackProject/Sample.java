package com.psl.fullstack.FullStackProject;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

public class Sample {
  @Test
  public void f() {
	  Assert.assertTrue(true);
	  Reporter.log("passed");
  }
  @BeforeClass
  public void beforeClass() {
  }

}
