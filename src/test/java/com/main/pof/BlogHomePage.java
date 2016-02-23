package com.main.pof;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;


public class BlogHomePage {
	
	public WebDriver driver;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Log in")
	public WebElement login_link;
	@FindBy(how = How.CLASS_NAME, using = "search-field")
	public WebElement search_box;
	@FindBy(how = How.XPATH, using = "//section[1]/form/button")
	public WebElement search_button;
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
	@FindBy(how = How.XPATH, using = "//body//h1")
	public WebElement search_result_text;
	
	public BlogHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public BlogHomePage loadBlogHomePage(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost/wordpress/");
		return (PageFactory.initElements(driver, BlogHomePage.class));

	}
	
	public LoginPage clickLoginInLink(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions builder = new Actions(this.driver);
		builder.moveToElement(login_link);
		builder.click();
		builder.build().perform();
		return (PageFactory.initElements(driver,LoginPage.class));
		
	}
	public void postComment(){
		
		comment_textbox.sendKeys("Comment");
		name_textbox.sendKeys("name");
		email_textbox.sendKeys("email");
		post_comment_button.click();
	}
	public Boolean searchPost(String titleToSearch)
	{
		search_box.sendKeys(titleToSearch);
		search_button.click();
		if(search_result_text.getText().contains("Nothing Found")){
			return false;
		}
		else{
		return true;
		}
				
	}
	
}
