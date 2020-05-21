package com.ms3.dbo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/*
 * Author: Ashish Maradapa
 * This class contains methods to create table in the database and data insertion are present here.
 */
public class DatabaseOperations 
{
	
	public static final Logger logger = LogManager.getLogger(DatabaseOperations.class);
	
	public Connection createSqliteTable(Connection c)
	//This method creates the table with required attributes
	{
		Statement stmt = null;

		try {
			stmt = c.createStatement();
		
			String sql = "CREATE TABLE IF NOT EXISTS Candidates " +
	                "(A VARCHAR," +
	                " B VARCHAR, " + 
	                " C VARCHAR, " + 
	                " D VARCHAR(6)," +
	                " E VARCHAR(255)," +
	                " F VARCHAR," +
	                " G DECIMAL(2,2)," +
	                " H VARCHAR(5)," +
	                " I VARCHAR(5)," +
	                " J VARCHAR)"; 
			stmt.executeUpdate(sql);
			stmt.close();
		} 
		catch (SQLException e) 
		{
			logger.debug("Error occured during Table creation"+e);
			e.printStackTrace();
		}
		System.out.println("Created a table in the Database.\n");
		return c;
	}
	
	public void insertToTable( List<String[]> recordslist, Connection c)
	{	//All the successful records are transferred here and are prepared into a SQL statement and are inserted into the table
			
		try 
		{
			for (String[] sublist : recordslist) 
			{	
				String sql = "INSERT INTO Candidates (A,B,C,D,E,F,G,H,I,J) " +
						"VALUES (?,?,?,?,?,?,?,?,?,?)";
			         PreparedStatement preparedStmt;
					
						preparedStmt = c.prepareStatement(sql);
					
			         preparedStmt.setString (1, sublist[0]);
			         preparedStmt.setString (2, sublist[1]);
			         preparedStmt.setString (3, sublist[2]);
			         preparedStmt.setString (4, sublist[3]);
			         preparedStmt.setString (5, sublist[4]);
			         preparedStmt.setString (6, sublist[5]);
			         preparedStmt.setString (7, sublist[6]);
			         preparedStmt.setString (8, sublist[7]);
			         preparedStmt.setString (9, sublist[8]);
			         preparedStmt.setString (10, sublist[9]);
			         
			        
			         preparedStmt.executeUpdate();
			         
			         preparedStmt.close();
	
					} 
				 c.commit();
		         c.close();

		}
		catch (SQLException e) 
		{
			logger.debug("Error occured during insertion into the database"+e);
			e.printStackTrace();
		}
		System.out.println("Insertion of records into Database is complete.");
	}
}
