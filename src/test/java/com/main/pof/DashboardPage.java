package com.main.pof;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	
	public WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//div[@class='wrap']/h1")
	public WebElement dashboard_heading;
	@FindBy(how = How.XPATH, using = "//li[@id='wp-admin-bar-my-account']/a")
	public WebElement admin_menu;
	@FindBy(how = How.LINK_TEXT, using = "Log Out")
	public WebElement log_out_link;
	
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void signOut(){
		Actions action = new Actions(driver);
		action.moveToElement(admin_menu).build().perform();
		WebDriverWait wait=  new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(log_out_link));
//		if(wait.until(ExpectedConditions.alertIsPresent())!=null)
//		{
//			Alert alert = driver.switchTo().alert();
//			alert.dismiss();
//		}
//		wait.until(ExpectedConditions.elementToBeClickable(log_out_link)).click();;
		log_out_link.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
