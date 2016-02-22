package com.main.pof;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver driver;
	
	@FindBy(how = How.ID, using = "user_login")
	public WebElement username_textbox;
	@FindBy(how = How.ID, using = "user_pass")
	public WebElement password_textbox;
	@FindBy(how = How.ID, using = "wp-submit")
	public WebElement login_button;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
   
	public DashboardPage loginAction(String UserName, String Password){
		driver.manage().window().maximize();
		username_textbox.clear();
		username_textbox.sendKeys(UserName);
		password_textbox.clear();
		password_textbox.sendKeys(Password);
		login_button.click();
		return (PageFactory.initElements(driver,DashboardPage.class));
        
        }

	
	
	
	
	
	
	
}
