package com.main.pof;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LeftMenuPanel {
	
	public WebDriver driver;
	
	@FindBy(how = How.LINK_TEXT, using = "Posts")
	public WebElement posts_menu;
	@FindBy(how = How.LINK_TEXT, using = "Add New")
	public WebElement add_new_post;
	@FindBy(how = How.LINK_TEXT, using = "All Posts")
	public WebElement all_posts;
	
	
	public LeftMenuPanel(WebDriver driver) {
		this.driver = driver;
	}
	

}
