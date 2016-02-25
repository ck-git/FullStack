package com.main.pof;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

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
	
	
	public AllPostsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public AllPostsPage loadAllPostsPage(){
		LeftMenuPanel leftmenupanel=PageFactory.initElements(driver, LeftMenuPanel.class);
		Actions action = new Actions(driver);
		action.moveToElement(leftmenupanel.posts_menu).build().perform();
		leftmenupanel.all_posts.click();
		return (PageFactory.initElements(driver, AllPostsPage.class));

	}
	
	
	public Boolean searchPost(String titleToSearch)
	{
		post_search_textbox.sendKeys(titleToSearch);
		search_post_button.click();
			try
			{
				System.out.println("Before try");
				if(no_items_found.isDisplayed())
					return false;
				
				System.out.println("After try");
			}catch(ElementNotFoundException e){
				
				System.out.println("insidecatch--------"+e.getMessage());
				
				return true;
			}catch(Exception ex){
				System.out.println("inside catch------"+ex.getMessage());				
			}
			
		return true;	
		}
	
	public void deletePost(String postTitle)
	{
		List<WebElement> rowList = driver.findElements(By.tagName("tr"));
		
		for( WebElement rowElement: rowList){
			List<WebElement> linkElementList = rowElement.findElements(By.linkText(postTitle));
			
			if( linkElementList.size() > 0){
				Actions action = new Actions(driver);
				action.moveToElement(linkElementList.get(0));
				action.perform();
				rowElement.findElement(By.className("submitdelete")).click();
				return;
			}
		}
	}
	
	
}
