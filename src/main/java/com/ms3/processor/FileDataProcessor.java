package com.ms3.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class FileDataProcessor 
{	
	//This class contains methods that separate the input file into into good and bad records 
	public static final Logger logger = LogManager.getLogger(FileDataProcessor.class);
	public HashMap<String, Object>  processFile(File file) 
	{
		String line = "";
        BufferedReader br = null;
		FileReader reader;
		try 
		{
			reader = new FileReader(file);
			br = new BufferedReader(reader);
		} 
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		
		List<String[]> goodRecords = new ArrayList<String[]>();
		List<String[]> badRecords = new ArrayList<String[]>();
		List<String> headers = new ArrayList<String>();
		HashMap<String, Object> result= new HashMap<String, Object>();
		
		String header = "A,B,C,D,E,F,G,H,I,J";
		
		try
		{	
			//Here we are extracting all records into our two lists 
			while((line = br.readLine()) != null)
			{
				//System.out.println(line);
				if(!(header.equals(line) || line.isBlank()))
				{
					String[] Newline = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
					boolean isBadData = isBadData(Newline);
					if(isBadData) 
					{
						badRecords.add(Newline);
					}
					else 
					{
						goodRecords.add(Newline);
					}
				}
				else
				{
					headers.add(line);
				}
				
			}
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		result.put("goodRecords", goodRecords);
		result.put("badRecords", badRecords);
		result.put("headers", headers);
	 
		return result;
	}
	
	
	public boolean isBadData(String[] line)
	{	
		//Checking for bad records
		//System.out.println(line.length);
		for(String s: line) 
		{
			//System.out.println(s);
			if(s.isBlank()) 
			{
				return true;
			}
		}
		return false;
	}
}
