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
	}
	
	@Test
	public void verifyHomePage(){
		
		//Assert.assertTrue(GenericFunctions.VerifyPageTitle(driver, "My Blog – Just another WordPress site:"));
		Assert.assertTrue(GenericFunctions.VerifyPageHeading(bloghomepage.my_blog_link, "My Blog"));
	}
	
	@Test
	public void searchPost(){
		Assert.assertTrue(bloghomepage.searchPost("faltu"));
		
	}
	
    @AfterTest
	    public void tearDown(){
	    	driver.close();
	    	driver.quit();
	}
	
	
	
	
	

}
