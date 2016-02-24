package com.main.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.main.pof.AllPostsPage;
import com.main.pof.BlogHomePage;
import com.main.pof.CreatePostPage;
import com.main.pof.DashboardPage;
import com.main.pof.GenericFunctions;
import com.main.pof.LoginPage;

public class TestLogin {
	
	public WebDriver driver;
	BlogHomePage bloghomepage;
	LoginPage loginpage;
	DashboardPage dashboardpage;
	CreatePostPage createpostpage;
	AllPostsPage allpostspage;
	
	
	@BeforeTest
	public void launchBrowser(){
		driver= new FirefoxDriver();
		bloghomepage = new BlogHomePage(driver);
	}
	
	/*@org.testng.annotations.DataProvider(name = "DP1")
	  public Object[][] createData1() throws Exception {
		  
	      Object[][] retObjArr= GenericFunctions.getCSV("TestData/login.csv");
	      return(retObjArr);
	  }
	 
    @Test (dataProvider = "DP1")
	public void testLoginSuccess(String username, String password){
    	
		bloghomepage = bloghomepage.loadBlogHomePage();
		loginpage = bloghomepage.clickLoginInLink();
		dashboardpage= loginpage.loginAction(username, password);
		Assert.assertTrue(GenericFunctions.validateText(driver,"Dashboard"));
		Assert.assertTrue(GenericFunctions.VerifyPageHeading(dashboardpage.admin_menu, "Howdy, admin"));
		//dashboardpage.signOut();
	}*/
    
    @org.testng.annotations.DataProvider(name = "DP2")
	  public Object[][] createData2() throws Exception {
		  
	      Object[][] retObjArr= GenericFunctions.getCSV("TestData/CreatePosttitles.csv");
	      return(retObjArr);
	  }
    
    @Test(dataProvider = "DP2")
    public void createNewPost(String post_title){
   	bloghomepage = bloghomepage.loadBlogHomePage();
   	loginpage = bloghomepage.clickLoginInLink();
	dashboardpage= loginpage.loginAction("admin","admin");
	createpostpage = new CreatePostPage(driver);
	createpostpage = createpostpage.loadCreatePostPage();
	createpostpage.createPost(post_title);  	
  	
   }
	
    /*@Test
    public void searchPostOnUI(){
    	
    	bloghomepage = bloghomepage.loadBlogHomePage();
		loginpage = bloghomepage.clickLoginInLink();
		dashboardpage= loginpage.loginAction("admin","admin");
    	allpostspage = new AllPostsPage(driver);
    	allpostspage = allpostspage.loadAllPostsPage();
    	allpostspage.searchPost("faltu");
    	
    	
    }
    
    @Test
    public void addSearchDeletePost(){
    	bloghomepage = bloghomepage.loadBlogHomePage();
		loginpage = bloghomepage.clickLoginInLink();
		dashboardpage= loginpage.loginAction("admin","admin");
		createpostpage = new CreatePostPage(driver);
      	createpostpage = createpostpage.loadCreatePostPage();
		createpostpage.createPost("Post To be Deleted");
		allpostspage = new AllPostsPage(driver);
    	allpostspage = allpostspage.loadAllPostsPage();
    	allpostspage.searchPost("Post To be Deleted");
    	//Call Function to delete post
    }
    
    @Test
    public void searchPostAfterDeletion(){
    	bloghomepage = bloghomepage.loadBlogHomePage();
		loginpage = bloghomepage.clickLoginInLink();
		dashboardpage= loginpage.loginAction("admin","admin");
		createpostpage = new CreatePostPage(driver);
      	createpostpage = createpostpage.loadCreatePostPage();
		createpostpage.createPost("Search postafter deletion");
    	//Call Function to delete post
		allpostspage = new AllPostsPage(driver);
    	allpostspage = allpostspage.loadAllPostsPage();
    	allpostspage.searchPost("Search postafter deletion");
    }*/
    
    
    
    
    @AfterTest
    public void tearDown(){
    	dashboardpage.signOut();
    	driver.close();
    	driver.quit();
    	
    	
    	
    }
    
    
    
    
    
    
    
	
}
