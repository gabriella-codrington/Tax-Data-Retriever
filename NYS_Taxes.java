package project3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner; 

/**
 * This class provides an implementation of a program that allows 
 * the user to explore the data set of the registered NYS tax preparers and facilitators.
 * The data set is located at the New York State open data site: 
 * href="https://data.ny.gov/Government-Finance/New-York-State-Registered-Tax-Return-Preparers-and/b7jj-bh4g 
 * 
 * @author Joanna Klukowska 
 *
 */

public class NYS_Taxes {

	public static void main(String[] args) {
	
		//verify that the command line argument exists
		if (args.length == 0 ) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}

		//verify that command line argument contains a name of an existing file
		File recordsFile = new File(args[0]);
		if (!recordsFile.exists()){
			System.err.println("Error: the file "+recordsFile.getAbsolutePath()+" does not exist.\n");
			System.exit(1);
		}
		if (!recordsFile.canRead()){
			System.err.println("Error: the file "+recordsFile.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}

		//open the file for reading
		Scanner inRecords = null;
		try {
			inRecords = new Scanner (recordsFile ) ;
		} catch (FileNotFoundException e) {
			System.err.println("Error: the file "+recordsFile.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}
		
		CSV csvData = new CSV(inRecords);
		inRecords.close();  //close the input file since it is no longer needed 
		
		RecordList recordList = new RecordList(); 
		
		
		//read and ignore the column header row 
		csvData.getNextRow();
		int lineNum = 1; 
		
		//read data rows 
		ArrayList<String> row = null; 
		//column indexes 
		final int REQUIRED_COLS = 6;  //since the city name is in the sixth column and it is one of the required elements 
		final int FIRST_NAME = 1;
		final int INITIAL = 2; 
		final int LAST_NAME = 3;
		final int BUSINESS_NAME = 4;
		final int CITY = 5;
		final int STATE = 6;
		final int COUNTRY = 7;
		final int ZIP = 8; 
		
		for ( ; lineNum < csvData.getNumOfRows(); lineNum++) {
			
			
			row = csvData.getNextRow();
			
			if (row.size() < REQUIRED_COLS) continue; //skip incomplete rows 
			
			int size = row.size(); 
			
			//create Name object 
			String fName=row.get(FIRST_NAME);
			String lName=row.get(LAST_NAME); 
			String middle = row.get(INITIAL);
			char mInitial = middle.length() >= 1 ? middle.charAt(0) : '\0'; 
			Name name = null; 
			try {
				name = new Name (lName, fName, mInitial) ;
				
			}
			catch (IllegalArgumentException ex ) {	
				continue; //skip records with invalid names
			}
			
			//create Location object 
			String city = row.get(CITY); 
			String state = size > STATE ? row.get(STATE) : null;
			String country = size > COUNTRY ? row.get(COUNTRY) : null ;
			String zip = size > ZIP ? row.get(ZIP) : null; 
			
			Location location = null; 
			try {
				location = new Location(city, state, country, zip  ) ; 
			}
			catch (IllegalArgumentException ex ) { 	
				continue; //skip records with invalid locations 
			}
 			
			//create Record object 
			Record record = null; 
			try { 
				record = new Record (name, row.get(BUSINESS_NAME), location); 
				recordList.add(record); 
			}
			catch (IllegalArgumentException ex ) {
				continue; //skip invalid records (this really should not happen in this context 
						  //since we validated both name and location before 
			}	
		
			
		} // end of reading and parsing data 


		///////////////////////////////////////////////
		

		
		// interactive mode 
		
		
		System.out.println("Enter one of the following instructions.\n"
				+ "name NAME_KEYWORD\n"
				+ "zip ZIP_CODE\n"
				+ "city CITY_KEYWORD\n"
				+ "quit\n\n");
		
		Scanner in = new Scanner(System.in) ;
		
		String userInputString = null; 
		Scanner userInput = null;
		
		do {
			//get and parse the query from the user 
			System.out.println("Enter your query: ");
			userInputString = in.nextLine(); 
			userInput = new Scanner(userInputString ); 
			String  command=null, keyword=null;
			boolean queryExecuted = false;
			RecordList matches = null; 
			
			if (userInput.hasNext() ) {
				command = userInput.next(); 
			}
			if (userInput.hasNext() ) {
				
				keyword = userInput.next(); 
			}
			if ( command == null || 	//command should not be null 
					! (                 //and it should be one of the four keywords
					command.equalsIgnoreCase("name" ) || 
					command.equalsIgnoreCase("zip" ) || 
					command.equalsIgnoreCase("city" ) || 
					command.equalsIgnoreCase("quit" )
					) 
				  ) 
			{
				System.out.println("This is not a valid query. Try again.");
				continue; 
			}
			
			try {  //process each command by calling the corresponding function 
				if (command.equalsIgnoreCase("name") && keyword != null  ) {
					matches = recordList.getByName( keyword ); 
					queryExecuted = true; 
				}
				else if (command.equalsIgnoreCase("zip") && keyword != null ) {
					matches = recordList.getByZip( keyword ); 
					queryExecuted = true; 
				}
				else if (command.equalsIgnoreCase("city")) {
					matches = recordList.getByCity( keyword ); 
					queryExecuted = true; 
				}
			}
			catch (IllegalArgumentException ex ) {
				System.out.println("This is not a valid query. Invalid keyword. Try again."); 
				continue;
			}


			if (queryExecuted && matches == null ) {
				System.out.println("No matching results.\n" );
				continue; 
			}

			if (queryExecuted ) { //display the results from the matches list 
				
				for ( Record r : matches)	{
					System.out.printf ( "%s%n%n", r); 
				}
				
			}
			
		} while (!userInputString.equalsIgnoreCase("quit"));

		in.close();
		userInput.close(); 
		
		
	}

}
