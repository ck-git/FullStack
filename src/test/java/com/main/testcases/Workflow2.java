package com.main.testcases;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.main.pof.BlogHomePage;
import com.main.pof.GenericFunctions;

public class Workflow2 {
	
	public WebDriver driver;
	BlogHomePage bloghomepage;
	
	
	
	@BeforeTest
	public void launchBrowser(){
		driver= new FirefoxDriver();
		bloghomepage = new BlogHomePage(driver);
		bloghomepage = bloghomepage.loadBlogHomePage();
		driver.manage().window().maximize();
	}
	
	@Test(priority=1)
	public void verifyHomePage(){
		
		//Assert.assertTrue(GenericFunctions.VerifyPageTitle(driver, "My Blog – Just another WordPress site:"));
		Assert.assertTrue(GenericFunctions.VerifyPageHeading(bloghomepage.my_blog_link, "My Blog"));
	}
	
	@Test(priority=2)
	public void searchPost(){
		Assert.assertTrue(bloghomepage.searchPost("Firstpost"));
		Assert.assertFalse(bloghomepage.searchPost("non-existing"));
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
	}
	
	@Test(priority=4)
	public void postComment(){
		Assert.assertTrue(bloghomepage.postComment());
	}
	
	
	
    @AfterTest
	    public void tearDown(){
	    	driver.close();
	    	driver.quit();
	}
    
   
	
	
	
	
	

}
