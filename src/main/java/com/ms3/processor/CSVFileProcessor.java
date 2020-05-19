package com.ms3.processor;

import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ms3.dbo.DatabaseConnection;
import com.ms3.dbo.DatabaseOperations;

public class CSVFileProcessor 
	{
		
		public static void main(String args[]) 
		{	
			//Main method for the whole application
			String fileName="";
			File file = new File(CSVFileProcessor.class.getClassLoader().getResource("files/input/ms3Interview.csv").getFile());
			fileName = FilenameUtils.removeExtension(file.getName());//File name is retrieved here.	
			
			System.setProperty("logfilename", fileName);//This passes the custom name for the .log file
			Logger logger = LogManager.getLogger(CSVFileProcessor.class);
			FileDataProcessor fileDataProcessor = new FileDataProcessor();
			
			HashMap<String, Object> processedResults=fileDataProcessor.processFile(file);
			List<String[]> goodRecords = (List<String[]>) processedResults.get("goodRecords");
			List<String[]> badRecords = (List<String[]>) processedResults.get("badRecords");
			List<String> headers = (List<String>) processedResults.get("headers");
			
			try
			{
			writeValidRecordsToDb(goodRecords,fileName);
			writeBadRecordsToFile(badRecords,fileName);
			
			logger.info("Total number of records in input fle including headers (duplicates) and blank records - " + (headers.size() + goodRecords.size() + badRecords.size()));
			logger.info("Total number of records in input fle - " + (goodRecords.size() + badRecords.size()));
			logger.info("Total number of Good Records - " + goodRecords.size());
			logger.info("Total number of Bad Records - " + badRecords.size());
			}
			catch (IOException e) 
	    	{ 
	        	logger.debug("Exception Occured: Unable to open file.", e);
	            e.printStackTrace(); 
	        } 
			
		}
		
		public static void writeValidRecordsToDb(List<String[]> goodRecords,String dbName)
		{
			// Records that match the proper format are inserted into the database here
			DatabaseOperations dbOperations = new DatabaseOperations();
			DatabaseConnection newcon = new DatabaseConnection();
			Connection con = newcon.conn(dbName);//Database Connection
			dbOperations.createSqliteTable(con);
			dbOperations.insertToTable(goodRecords, con);
			
		}
		
		
		public static void writeBadRecordsToFile(List<String[]> badRecords, String fileName) throws IOException
		{
			//Writing all the failed records into -bad.csv file
			Path path = Paths.get("src/main/resources/files/output/"+fileName+"-bad.csv"); 
		  
			//deleteIfExists File 
		    		Files.deleteIfExists(path); 
					File badrowsfile = new File("src/main/resources/files/output/"+fileName+"-bad.csv");
					badrowsfile.createNewFile();
					BufferedWriter bw = null;
					FileWriter fw;
					fw = new FileWriter(badrowsfile,false);
					bw = new BufferedWriter(fw);
					
					
				      for(String[] badRecord: badRecords) 
				      {
				    	  StringBuffer sb = new StringBuffer();
				    	  int count = 0;
				    	  for(String s: badRecord) 
				    	  {		
				    		  sb.append(s);
				    		  if(((badRecord.length)-1)!=count)
				    		  {
				    			  sb.append(",");  
				    		  }
				    		  count++;
				    	  }
				    	  String str = sb.toString();	 
				    	  bw.write(str);
				    	  bw.newLine();
				      }
				      
				     bw.close();
				     System.out.println("Error records file created: \t"+fileName+"-bad.csv\n");
		} 
				
}


