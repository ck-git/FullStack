package com.main.pof;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AllPostsPage {
	
	public WebDriver driver;
	Date date = new Date();
	
	@FindBy(how = How.ID, using = "message")
	public WebElement message_area;
	@FindBy(how = How.ID, using = "post-search-input")
	public WebElement post_search_textbox;
	@FindBy(how = How.ID, using = "search-submit")
	public WebElement search_post_button;
	@FindBy(how = How.CLASS_NAME, using = "no-items")
	public WebElement no_items_found;
	@FindBy(how = How.CLASS_NAME, using = "displaying-num")
	public WebElement num_of_items_lable;
	@FindBy(how = How.XPATH, using = "tbody[@id='the-list']/tr/td")
	public WebElement post_table_row;
	@FindBy(how = How.XPATH, using = "//tbody[@id='the-list']/tr/td//a[@class='submitdelete']")
	public WebElement post_trash_button;
	
	
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
		System.out.println("Search Post: Starting search for "+titleToSearch+" - "+date);
		post_search_textbox.clear();
		post_search_textbox.sendKeys(titleToSearch);
		
		search_post_button.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (num_of_items_lable.isDisplayed()){
			String[] row_count = num_of_items_lable.getText().split(" ");
			System.out.println("Search Post: Number of matching post: " + row_count[0] +" - "+ date);
			return true;
		}
		else if(no_items_found.isDisplayed()){
			System.out.println("Search Post: No matching post found! - "+date);
			return false;
		}
		else{
			System.out.println("Search Post: I am sure it's bad day, returning false! - "+date);
			return false;
		}
		
		/*try
			{
				System.out.println("Before try");
				if(no_items_found.isDisplayed())
					return false;
				
				System.out.println("After try");
			}catch(ElementNotFoundException e){
				{
				
				System.out.println("insidecatch--------"+e.getMessage());
				
				return true;
			}catch(Exception ex){	
				System.out.println("inside catch------"+ex.getMessage());				
			}
			
		return true;	*/
	}
	

	public void deletePost(String postTitle)
	{
		System.out.println("Delete Post: Looping through... - "+date);
/*		List<WebElement> rowList = driver.findElements(By.tagName("tr"));
				
		//TODO: Need to fix this loop when no record is found
		
		for( WebElement rowElement: rowList){
			List<WebElement> linkElementList = rowElement.findElements(By.linkText(postTitle));
			
			if( linkElementList.size() > 0){
				System.out.println("Delete Post: The post found for deleting.");
				Actions action = new Actions(driver);
				action.moveToElement(linkElementList.get(0));
				action.perform();
				rowElement.findElement(By.className("submitdelete")).click();
				System.out.println("Delete Post: Post deleted! - "+ date);
				//return;
				break;
			}
			else{
				System.out.println("Delete Post: No rows present! - "+ date);
			}
		}*/
		
		try {
			Boolean search = searchPost(postTitle);
			if (search){
				System.out.println("Delete Post: Looking for first row to delete. - "+date);
				//WebElement row = driver.findElement(By.xpath("//tbody[@id='the-list']/tr/td"));
				Actions action = new Actions(driver);
				action.moveToElement(post_table_row);
				action.perform();

				/*WebElement del = driver.findElement(By.xpath("//tbody[@id='the-list']/tr/td//a[@class='submitdelete']"));
				del.click();*/
				
				post_trash_button.click();
				System.out.println("Delete Post: Post deleted! - "+ date);
			}
			else{
				System.out.println("Nothing to delete");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
