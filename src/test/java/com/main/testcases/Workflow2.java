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
	
	//public WebDriver driver;
	//BlogHomePage bloghomepage;
	static String screenshot_name = "";
	static String browserName="";
	


	@Test(priority=6)
	public void verifyHomePage() throws Exception{
		
		//Assert.assertTrue(GenericFunctions.VerifyPageTitle(driver, "My Blog – Just another WordPress site:"));
		//GenericFunctions.takeScreenShot(driver, "F:\\screenshots\\", "screenshot_name",browserName);
		bloghomepage = bloghomepage.loadBlogHomePage();
		Assert.assertTrue(GenericFunctions.VerifyPageTitle(driver, "QE Full Stack"));
		Assert.assertTrue(GenericFunctions.VerifyPageHeading(bloghomepage.my_blog_link, "QE Full Stack"));
		Reporter.log("Test verifyHomePage passed");		
		}
	
	
	@Test(priority=7)
	public void searchPost(){
		Assert.assertTrue(bloghomepage.searchPost("Firstpost"));
		Assert.assertFalse(bloghomepage.searchPost("non-existing")); // Negative scenario testing - expected result is Test Case pass
		Assert.assertTrue(bloghomepage.searchPost("Failure-Scenario")); //Failure scenario to showcase snapshot on test case failure
		//Reporter.log("Test searchPost passed");
	}
	
	@org.testng.annotations.DataProvider(name = "DP3")
	public Object[][] createData2() throws Exception {
		  
	      Object[][] retObjArr= GenericFunctions.getCSV("TestData/VerifyLinks.csv");
	      return(retObjArr);
	}
	
	@Test(dataProvider = "DP3", priority=8)
	public void verifyLinksOnPage(String link_to_be_verified, String link_to_be_clicked){
		Assert.assertTrue(bloghomepage.verifyLinkUnderRecentPost(link_to_be_verified));
		bloghomepage.visitAnotherPost(link_to_be_clicked);
		Assert.assertTrue(bloghomepage.verifyLinkUnderRecentPost(link_to_be_clicked));
		Reporter.log("Test verifyLinksOnPage passed");
	}
	
	@Test(priority=9)
	public void postComment(){
		bloghomepage.recent_first_post.click();
		Assert.assertTrue(bloghomepage.postComment());
		Reporter.log("Test postComment passed");
	}
	
	
	
}
