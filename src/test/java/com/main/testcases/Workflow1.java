package com.main.testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.main.pof.AllPostsPage;
import com.main.pof.CreatePostPage;
import com.main.pof.DashboardPage;
import com.main.pof.Database;
import com.main.pof.GenericFunctions;

public class Workflow1 extends GenericFunctions{
	
	DashboardPage dashboardpage;
	CreatePostPage createpostpage;
	AllPostsPage allpostspage;
	Database db;
	
	@org.testng.annotations.DataProvider(name = "DP1")
	  public Object[][] createData1() throws Exception {
		  
	      Object[][] retObjArr= GenericFunctions.getCSV("TestData/login.csv");
	      return(retObjArr);
	  }
	 
	 @org.testng.annotations.DataProvider(name = "DP2")
	  public Object[][] createData2() throws Exception {
		  
	      Object[][] retObjArr= GenericFunctions.getCSV("TestData/CreatePosttitles.csv");
	      return(retObjArr);
	  }
	 
	 
	@Test (dataProvider = "DP1", priority = 1)
	public void testLoginSuccess(String username, String password){
    	
		bloghomepage = bloghomepage.loadBlogHomePage();
		loginpage = bloghomepage.clickLoginInLink();
		dashboardpage= loginpage.loginAction(username, password);
		Assert.assertTrue(GenericFunctions.validateText(driver,"Dashboard"));
		Assert.assertTrue(GenericFunctions.VerifyPageHeading(dashboardpage.admin_menu, "Howdy, "+username));
		dashboardpage.signOut();
		
		bloghomepage = bloghomepage.loadBlogHomePage();
		loginpage = bloghomepage.clickLoginInLink();
		dashboardpage= loginpage.loginAction("user1", "password"); //Login failure scenario to capture snapshot
		Assert.assertTrue(GenericFunctions.validateText(driver,"Dashboard"));
		Reporter.log("Login and logout verified");
	}
    
	@Test(dataProvider = "DP1", priority = 2)
	public void login(String username, String password){
		bloghomepage = bloghomepage.loadBlogHomePage();
		loginpage = bloghomepage.clickLoginInLink();
		dashboardpage= loginpage.loginAction(username, password);
	}
	
    @Test(dataProvider = "DP2", priority = 3)
    public void createNewPost(String post_title, String dbusername,String dbpassword){
 
		createpostpage = new CreatePostPage(driver);
		createpostpage = createpostpage.loadCreatePostPage();
		createpostpage.createPost(post_title);	
		String dbUrl = "jdbc:mysql://10.51.231.3:3306/wordpress";
		db = new Database();
		db.connectDatabase(dbUrl, dbusername, dbpassword);
		db.verifyPostTitleAndContent(post_title);
		db.verifyPostTitleAndContent("foo.comment");//Failure scenario for database
		Reporter.log("Post created successfully");
   }
	
    @Test(priority = 4)
    public void searchPostOnUI(){
		
    	allpostspage = new AllPostsPage(driver);
    	allpostspage = allpostspage.loadAllPostsPage();
    	Assert.assertFalse(allpostspage.searchPost("Non-existing post"));
    	Assert.assertTrue(allpostspage.searchPost("post"));
    	Reporter.log("Searched for existing and non-existing post");
    }
    
    @Test(priority = 5)
    public void addSearchDeletePost(){
  
	
		createpostpage = new CreatePostPage(driver);
      	createpostpage = createpostpage.loadCreatePostPage();
		createpostpage.createPost("Post To be Deleted");
		allpostspage = new AllPostsPage(driver);
    	allpostspage = allpostspage.loadAllPostsPage();
    	//allpostspage.searchPost("Post to be Deleted");
    	allpostspage.deletePost("Post To be Deleted");
    	Assert.assertFalse(allpostspage.searchPost("Post to Deleted"));
    	dashboardpage.signOut();
    	Reporter.log("Search deleted post");
    }
    
}
