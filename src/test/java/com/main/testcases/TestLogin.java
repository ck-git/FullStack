package com.main.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.main.pof.AllPostsPage;
import com.main.pof.CreatePostPage;
import com.main.pof.DashboardPage;
import com.main.pof.GenericFunctions;

public class TestLogin extends GenericFunctions{
	
	DashboardPage dashboardpage;
	CreatePostPage createpostpage;
	AllPostsPage allpostspage;
	
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
	}
    
    @Test(dataProvider = "DP2", priority = 2)
    public void createNewPost(String post_title){
    	bloghomepage = bloghomepage.loadBlogHomePage();
	   	loginpage = bloghomepage.clickLoginInLink();
		dashboardpage= loginpage.loginAction("admin","Siteadmin@123");
		createpostpage = new CreatePostPage(driver);
		createpostpage = createpostpage.loadCreatePostPage();
		createpostpage.createPost(post_title);		
		dashboardpage.signOut();
   }
	
    @Test(dataProvider = "DP1", priority = 3)
    public void searchPostOnUI(String username, String password){
    	
    	bloghomepage = bloghomepage.loadBlogHomePage();
		loginpage = bloghomepage.clickLoginInLink();
		dashboardpage= loginpage.loginAction(username,password);
		
    	allpostspage = new AllPostsPage(driver);
    	allpostspage = allpostspage.loadAllPostsPage();
    	Assert.assertFalse(allpostspage.searchPost("Non-existing post"));
    	Assert.assertTrue(allpostspage.searchPost("post"));
    }
    
    @Test(priority = 4)
    public void addSearchDeletePost(){
  
    	/*bloghomepage = bloghomepage.loadBlogHomePage();
		loginpage = bloghomepage.clickLoginInLink();
		dashboardpage= loginpage.loginAction("admin","Siteadmin@123");*/
		
		createpostpage = new CreatePostPage(driver);
      	createpostpage = createpostpage.loadCreatePostPage();
		createpostpage.createPost("Post To be Deleted");
		allpostspage = new AllPostsPage(driver);
    	allpostspage = allpostspage.loadAllPostsPage();
    	allpostspage.searchPost("Post to be Deleted");
    	allpostspage.deletePost("Post To be Deleted");
    	Assert.assertTrue(allpostspage.searchPost("Post to Deleted"));
    	dashboardpage.signOut();
    }
    
/*    @Test(priority = 5)
    public void searchPostAfterDeletion(){
    	createpostpage = new CreatePostPage(driver);
      	createpostpage = createpostpage.loadCreatePostPage();
		createpostpage.createPost("Search postafter deletion");
		allpostspage = new AllPostsPage(driver);
    	allpostspage = allpostspage.loadAllPostsPage();
    	allpostspage.searchPost("Search postafter deletion");
    	allpostspage.deletePost("Search postafter deletion");
    	allpostspage.searchPost("Search postafter deletion");
    	dashboardpage.signOut();
    }*/
    
/*    @AfterTest
    public void tearDown(){
    	driver.close();
    	driver.quit();    	
    }   
*/
}
