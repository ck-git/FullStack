package com.psl.fullstack.pom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AllPostsPage {
	
	public WebDriver driver;
	@FindBy(how = How.ID, using = "message")
	public WebElement message_area;
	@FindBy(how = How.ID, using = "post-search-input")
	public WebElement post_search_textbox;
	@FindBy(how = How.ID, using = "search-submit")
	public WebElement search_post_button;
	@FindBy(how = How.CLASS_NAME, using = "no-items")
	public WebElement no_items_found;
}
