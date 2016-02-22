package com.main.pof;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import au.com.bytecode.opencsv.CSVReader;


public class GenericFunctions {
	
	public static Boolean VerifyPageTitle(WebDriver driver,String titleToVerify)
    {
           String pageTitle;
           pageTitle = driver.getTitle();

           if(pageTitle.equals(titleToVerify))
                  return true;
           else
                  return false;
    }
    
    public static Boolean VerifyPageHeading(WebElement pageHeadingObject,String headingToVerify)
    {
           String pageHeading;
           pageHeading = pageHeadingObject.getText();

           if(pageHeading.equals(headingToVerify))
                  return true;
           else
                  return false;
    }
      
    public static Boolean VerifyLinksOnPage(WebElement hyperlinkObject,String linkUrlToVerify)
    {
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
	
	public static Boolean VerifyPostBody(WebElement post_body,String bodyToVerify)
    {
           String body;
           body = post_body.getText();

           if(body.equals(bodyToVerify))
                  return true;
           else
                  return false;
    }
	
	public static String createRandomPostBody(String body)
	{
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
		return tabArray;
	}
}	
	
	
	



