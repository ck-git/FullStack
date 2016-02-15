package com.psl.fullstack.pom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreatePostPage {
	
	public WebDriver driver;
	@FindBy(how = How.ID, using = "title-prompt-text")
	public WebElement posttitle_textbox;
	@FindBy(how = How.ID, using = "insert-media-button")
	public WebElement add_media_button;
	@FindBy(how = How.ID, using = "	tinymce")
	public WebElement postbody_textbox;
	@FindBy(how = How.ID, using = "publish")
	public WebElement publish_button;
	@FindBy(how = How.ID, using = "View post")
	public WebElement viewpost_link;
	@FindBy(how = How.LINK_TEXT, using = "Move to Trash")
	public WebElement move_To_trash_link;
	
	
	

}
