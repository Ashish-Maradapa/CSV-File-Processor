# ms3Interview
Coding challenge project

Purpose:

This project aims to take a csv file and seperate the records with proper formatting from those which 
dont have all the required information. Through this project a CSV file is being analyzed and the records with the proper 
format are being inserted into a custom database and the other records are being transferred into another csv file.


-- Technologies/Tools used:

	- Eclipse 2019-06 (4.12.0)	
	- Java version 12.0.1 
	- Git for Windows v2.25.1


Steps to get this application to run:

	1. Start the program from CSVFileProcessor.java file in the dbo package. This has the main 
	method of the application. 
	2. Make sure there are no databases with the name <input-file-name>.db or 
	<input-file-name>-bad.csv in the project folder.
	3. The applictation is going to create 3 new files(1 database file, 1 csv file, 1 log file)
	so it needs some available memory depending on the input file.
	4. If you are running the application with a different input file the table name has to be changed or
	it has to be in the same format as the first file. In other case there will be an error in the database
	as the database and the table are already present from the previous run. he database will be appended
	with data for every re run.
	5. I have used DB.Browser.for.SQLite-3.11.2 to view the database.
	(https://download.sqlitebrowser.org/DB.Browser.for.SQLite-3.11.2-win64.zip)
	6. The application receives the input file from the path "/src/main/resources/input/ms3Interview.csv".
	7. The output files and log files are named according to the instructions.



--- Overview:

- My Approach
	I started with a prototype which created a database with the required name and then started working with the input file.
	Then i took the whole file into a list of string list and then split the records into appropriate sublists(good records, bad records).
	Once the file processing is done i have returned to the main process using a hashmap function. Then the database is being initialized and an empty table is created first. Then good records are prepared into an sql statement and then inserted into the database. After the insertion is complete the error records are being written into a bad.csv file. After this the statistics are logged with the required parameters. 



-- During the development of this project I have taken the following assumtions:

	- There is not change of the format of the data that is being given in the input file
	- The columns H and I are storing only boolean data(I proceded with this because there are
	  no specific instructions on what data types each cell holds, an exception will be raised
	  if the row has a value other than true or false)
	- I assumed that a bad row is something which does not have any data in one or more columns.
	  I am not doing any field level validations to determine bad rows as it might be out of the
	  scope of this project
 	- The bad csv data file is being deleted and created everytime the application is run. 
	- Input file is being taken from the resources folder. 
	- A 'candidates' table is being generated in the database for every run and assumes that the
	  order of the the input csv file is the same. 


-- Design Choices:

	- I have used Bufferedreader to read the input files.
	- I have tried to browse for the input file but i ran into some issues and continued 
	  with a static file location.
	- For splitting the good records and bad records list, the records were checked for the proper format
    	  and then proceded with the processing.
	- The splitting and database operations could be done simultaneously but it would take a long time to 
    	  shift from one process to another. 
	- The same applies to bad.csv file. Once the input file was split into lists the database
    	  operations and file operations were done in different methods.
	- During the creation of bad.csv File the existing file with the same name is deleted and 
	  new file is created everytime the application is run.
	- Similarly the database will have the table named 'candidates'. For every re-run the table will be appended.





