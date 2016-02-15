package com.psl.fullstack.pom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DashboardPage {
	
	public WebDriver driver;
	@FindBy(how = How.XPATH, using = "//div[@class='wrap']/h1")
	public WebElement dashboard_heading;
	@FindBy(how = How.XPATH, using = "//li[@id='wp-admin-bar-my-account']/a")
	public WebElement admin_menu;
	@FindBy(how = How.LINK_TEXT, using = "Posts")
	public WebElement posts_menu;
	@FindBy(how = How.LINK_TEXT, using = "Add New")
	public WebElement add_new_post;
	@FindBy(how = How.LINK_TEXT, using = "All Posts")
	public WebElement all_posts;
	@FindBy(how = How.LINK_TEXT, using = "Log Out")
	public WebElement log_out_link;
}
