# ms3Interview
Coding challenge project

Purpose:

This project aims to take a csv file and seperate the records with proper formatting from those which 
dont have all the required information. Through this project a CSV file is being analyzed and the records with the proper 
format are being inserted into a custom database and the other records are being transferred into another csv file.


Steps to get this application to run:

	1. Start the program from CSVFileProcessor.java file in the dbo package. This has the main 
	method of the application. 
	2. Make sure there are no databases with the name <input-file-name>.db or 
	<input-file-name>-bad.csv in the project folder.
	3. The applictation is going to create 3 new files(1 database file, 1 csv file, 1 log file)
	so it needs some available memory depending on the input file.
	4. If you are running the application with a different input file the table name has to be changed or
	it has to be in the same format as the first file. In other case there will be an error in the database
	as the database and the table are already present. he database will be appended with data for every re run.
	5. I have used DB.Browser.for.SQLite-3.11.2 to view the database.
	(https://download.sqlitebrowser.org/DB.Browser.for.SQLite-3.11.2-win64.zip)
	6.The application receives the input file from "/src/main/resources/input/ms3Interview.csv".
	7. The output files and log files are named according to the instructions.



--- Overview:

- My Approach
	I started with a prototype which created a database with the required name and then started working with the input file.
	Then i took the whole file into a list of string list and then split the records into appropriate sublists(good records, bad records).
	Once the file ios split into lists, I have logged the statistics into custom named log file. The bad records were then written to a csv file with the name <input-file>-bad.csv. The database was then initialized with a table aand the required header. Then good records were prepared into an sql statement and then inserted into the database.


-- During the development of this project I have taken the following assumtions:

	- There is not change of the format of the data that is being given in the input file
	- The columns H and I are storing only boolean data(But i didnt want to change the 
	  input data so i stored them as VARCHAR)
 	- The bad csv data file is being deleted and created everytime the application is run. 


-- Design Choices:

	- I have used Bufferedreader to read the input files.
	- I have tried to browse for the input file but i ran into some issues and continued 
	  with a static file location.
	- For splitting the good records and bad records list, the records were checked for the proper format
    	  and then proceded with the processing.
	- The splitting and database operations could be done simultaneously but it would take a long time to 
    	  shift from one process to Another. 
	- The same applies to bad.csv file. Once the input file was split into lists database
    	  operations and file operations were done in different classes.
	- During the creation of bad.csv File i have deleted any file which already exists
    	  with the same name and created a new file everytime the application is run.
	- Similarly the database will have the table named candidates. For every rerun the table will be appended.
	
-- Technologies:

	- Eclipse 2019-06 (4.12.0)	
	- Java version 12.0.1 
	- Git for Windows v2.25.1


-- Issues occured:

	- image data cell has a comma in the data itself which has to be joined when inserting into a bad csv.
	- SQLite Datatypes
	- files appending/ Replace
	- Re-runnablilty issues(drop table, delete bad rows file) 


