package com.main.pof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Database extends GenericFunctions {

	Connection con;
	public void connectDatabase(String url, String username,String password)
	{
        try {
			Class.forName("com.mysql.jdbc.Driver");
			 DriverManager.setLoginTimeout(10);
			this.con = DriverManager.getConnection(url,username,password);
		
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
	}
	
	public Boolean verifyPostTitleAndContent(String postTitle)
	{
		Boolean isPostFound = false;;
		try {
			String query = "select post_title, post_content,post_date from wp_posts";
			Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
	           String title = rs.getString("post_title");
	           String content = rs.getString("post_content");
	           if(title == postTitle){
	        	   isPostFound = true;
	        	   break;
	          }
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isPostFound;
	}
}
