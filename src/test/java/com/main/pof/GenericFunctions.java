package com.main.pof;

import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import au.com.bytecode.opencsv.CSVReader;


public class GenericFunctions {
	
	public WebDriver driver = null;
	public BlogHomePage bloghomepage;
	public LoginPage loginpage;
	static String screenshot_name = "";
	public static File scrFile;
	
	@Parameters({"browser","ip","port"})
	@BeforeClass
	public void setup(String browser, String ip, String port) throws MalformedURLException{
		
		
		DesiredCapabilities capability;
		
		if (browser.equalsIgnoreCase("firefox")){
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			driver = new RemoteWebDriver(new URL("http://".concat(ip).concat(":").concat(port).concat("/wd/hub")),capability);
			
		}
		else if (browser.equalsIgnoreCase("chrome")){
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			driver = new RemoteWebDriver(new URL("http://".concat(ip).concat(":").concat(port).concat("/wd/hub")),capability);	
		}
		else if ((browser.equalsIgnoreCase("internet explorer")) || (browser.equalsIgnoreCase("ie"))){
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			driver = new RemoteWebDriver(new URL("http://".concat(ip).concat(":").concat(port).concat("/wd/hub")),capability);
		}
		else{
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		driver.manage().window().maximize();		
		bloghomepage = new BlogHomePage(driver);
	}
	
	@AfterClass
	public void tearDown(){
		driver.close();
		driver.quit();
	}
	
	
	public static void takeScreenShot(WebDriver driver, String pathofscreenshot, String nameofscreenshot, String browsername) throws Exception{
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File((pathofscreenshot) + (nameofscreenshot) + "_" +  (browsername) + ".png"));
	}
	
	@Parameters({"browser"})
    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult, String browser) throws Exception {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			screenshot_name = testResult.getName().toString().trim();
			GenericFunctions.takeScreenShot(driver, ".\\screenshots\\", screenshot_name,browser);
			Reporter.log("Test" + (screenshot_name) + "Failed");	
		}
    }
	
	public static Boolean VerifyPageTitle(WebDriver driver,String titleToVerify){
           String pageTitle;
           pageTitle = driver.getTitle();

           if(pageTitle.contains(titleToVerify))
                  return true;
           else
                  return false;
    }
    
    public static Boolean VerifyPageHeading(WebElement pageHeadingObject,String headingToVerify){
           String pageHeading;
           pageHeading = pageHeadingObject.getText();

           if(pageHeading.equals(headingToVerify))
                  return true;
           else
                  return false;
    }
      
    public static Boolean VerifyLinksOnPage(WebElement hyperlinkObject,String linkUrlToVerify){
           String hyperLink;
           hyperLink = hyperlinkObject.getAttribute("href");

           if(hyperLink.equals(linkUrlToVerify))
                  return true;
           else
                  return false;
    }

	public static Boolean validateText(WebDriver driver, String expectedtext){
		
		if(driver.getPageSource().contains(expectedtext)){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public static Boolean VerifyPostBody(WebElement post_body,String bodyToVerify){
           String body;
           body = post_body.getText();

           if(body.equals(bodyToVerify))
                  return true;
           else
                  return false;
    }
	
	public static String createRandomPostBody(String body){
		 Random r= new Random();
		 int i = r.nextInt();
		 body = body + " " + String.valueOf(i) ;
		 return body;
	}
	
	
	public static String[][] getCSV(String filePath) throws Exception{
		  
		String[][] tabArray = null;
		CSVReader reader = new CSVReader(new FileReader(filePath));
		
		List<String[]> getData = new ArrayList<String[]>();
		getData = reader.readAll();
		
		int rowSize = getData.size()-1;
		int colSize = getData.get(0).length;
		
		tabArray = new String [rowSize][colSize];		
		
		for(int i=1; i<getData.size(); i++){
			
			String [] nextData;
			nextData = getData.get(i);
			for(int j=0; j<colSize; j++){
				tabArray[i-1][j] = nextData[j];
			}
		}
		reader.close();
		return tabArray;
	}
	
}	