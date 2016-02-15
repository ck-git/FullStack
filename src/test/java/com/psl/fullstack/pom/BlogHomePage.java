package com.psl.fullstack.pom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BlogHomePage {
	
	public WebDriver driver;
	@FindBy(how = How.LINK_TEXT, using = "Log in")
	public WebElement login_link;
	@FindBy(how = How.CLASS_NAME, using = "searchfield")
	public WebElement search_box;
	@FindBy(how = How.LINK_TEXT, using = "My Blog")
	public WebElement my_blog_link;
	@FindBy(how = How.ID, using = "comment")
	public WebElement comment_textbox;
	@FindBy(how = How.ID, using = "author")
	public WebElement name_textbox;
	@FindBy(how = How.ID, using = "email")
	public WebElement email_textbox;
	@FindBy(how = How.ID, using = "submit")
	public WebElement post_comment_button;
	@FindBy(how = How.CLASS_NAME, using = "entry-title")
	public WebElement post_heading;
	@FindBy(how = How.CLASS_NAME, using = "entry-content")
	public WebElement post_body;
	@FindBy(how = How.CLASS_NAME, using = "post-edit-link")
	public WebElement post_edit_link;
	@FindBy(how = How.CLASS_NAME, using = "page-title")
	public WebElement nothing_found;
}
