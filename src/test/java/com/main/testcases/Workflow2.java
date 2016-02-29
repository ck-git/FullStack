package com.main.testcases;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.main.pof.BlogHomePage;
import com.main.pof.GenericFunctions;

public class Workflow2 extends GenericFunctions{
	
	public WebDriver driver;
	BlogHomePage bloghomepage;
	static String screenshot_name = "";
	static String browserName="";
	


	@Test(priority=1)
	public void verifyHomePage() throws Exception{
		
		//Assert.assertTrue(GenericFunctions.VerifyPageTitle(driver, "My Blog – Just another WordPress site:"));
		//GenericFunctions.takeScreenShot(driver, "F:\\screenshots\\", "screenshot_name",browserName);
		Assert.assertTrue(GenericFunctions.VerifyPageHeading(bloghomepage.my_blog_link, "My Blog"));
		Reporter.log("Test verifyHomePage passed");
			
		}
	
	
	@Test(priority=2)
	public void searchPost(){
		Assert.assertTrue(bloghomepage.searchPost("Firstpost"));
		Assert.assertTrue(bloghomepage.searchPost("Failure-Scenario"));
		Assert.assertFalse(bloghomepage.searchPost("non-existing"));
		Reporter.log("Test searchPost passed");
	}
	
	@org.testng.annotations.DataProvider(name = "DP3")
	public Object[][] createData2() throws Exception {
		  
	      Object[][] retObjArr= GenericFunctions.getCSV("TestData/VerifyLinks.csv");
	      return(retObjArr);
	}
	
	@Test(dataProvider = "DP3", priority=3)
	public void verifyLinksOnPage(String link_to_be_verified, String link_to_be_clicked){
		
		Assert.assertTrue(bloghomepage.verifyLinkUnderRecentPost(link_to_be_verified));
		bloghomepage.visitAnotherPost(link_to_be_clicked);
		Reporter.log("Test verifyLinksOnPage passed");
	}
	
	@Test(priority=4)
	public void postComment(){
		bloghomepage.visitAnotherPost("Secondpost");
		Assert.assertTrue(bloghomepage.postComment());
		Reporter.log("Test postComment passed");
	}
	
	
	
}
