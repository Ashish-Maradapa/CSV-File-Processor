package com.ms3.dbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;


/*
 * Author: Ashish Maradapa
 * This class contains methods to establish a SQLite database connection.
 */

public class DatabaseConnection {
	
	public static final Logger logger = Logger.getLogger(DatabaseConnection.class);
	
	public Connection conn(String dbname)
	{	//This method establishes connection and creates an SQLite database
		
		Connection c = null;
		
		
		try
		{
			String Db_name = "jdbc:sqlite:"+dbname+".db";
			Class.forName("org.sqlite.JDBC");
	        c = DriverManager.getConnection(Db_name);
	        c.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	       
		}
		catch(SQLException e)
		{
			logger.error("Unable to connect to the Database"+e);
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} 
        return c;
	}
}
