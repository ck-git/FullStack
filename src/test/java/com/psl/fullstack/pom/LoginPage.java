package com.psl.fullstack.pom;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
	public WebDriver driver;
	@FindBy(how = How.ID, using = "user_login")
	public WebElement username_textbox;
	@FindBy(how = How.ID, using = "user_pass")
	public WebElement password_textbox;
	@FindBy(how = How.ID, using = "wp-submit")
	public WebElement login_button;
	
	
	

	public void loginAction(String UserName, String Password){
		 
		username_textbox.clear();
		username_textbox.sendKeys(UserName);
		password_textbox.clear();
		password_textbox.sendKeys(Password);
		login_button.click();
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        }

	
	
	
	
	
	
	
}
