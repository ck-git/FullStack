package com.main.pof;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatePostPage {
	
	public WebDriver driver;
	
	
	@FindBy(how = How.ID, using = "title-prompt-text")
	public WebElement posttitle_textbox;
	@FindBy(how = How.ID, using = "insert-media-button")
	public WebElement add_media_button;
	@FindBy(how = How.ID, using = "content_ifr")
	public WebElement postbody_frame;
	@FindBy(how = How.ID, using = "publish")
	public WebElement publish_button;
	@FindBy(how = How.ID, using = "View post")
	public WebElement viewpost_link;
	@FindBy(how = How.LINK_TEXT, using = "Move to Trash")
	public WebElement move_To_trash_link;
	
	public CreatePostPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public CreatePostPage loadCreatePostPage(){
		LeftMenuPanel leftmenupanel=PageFactory.initElements(driver, LeftMenuPanel.class);
		Actions action = new Actions(driver);
		action.moveToElement(leftmenupanel.posts_menu).build().perform();
		leftmenupanel.add_new_post.click();
		return (PageFactory.initElements(driver, CreatePostPage.class));

	}
	
	public void createPost(String title){
		
		posttitle_textbox.sendKeys(title);
		driver.switchTo().frame(postbody_frame);
		driver.switchTo().activeElement().sendKeys(GenericFunctions.createRandomPostBody("Body"));
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		if(wait.until(ExpectedConditions.alertIsPresent())!=null)
		{
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		}
			
		
		//Need to add explicit wait here
		//WebDriverWait wait=  new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(publish_button)).click();;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
