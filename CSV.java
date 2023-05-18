package project3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implementation of a simple CSV (comma separated values) files parser. 
 * 
 * <p>CSV files consist of rows with multiple entries. The number of entries in each row does not 
 * need to be the same. Each entry is separated with a comma character. 
 * Simple entries are not delimited, for example <br>  
 * {@code hello, 5, 3.1415, http://nyu.edu} <br>
 * is a row with four entries. 
 * Entries that contain reserved characters are assumed to be surrounded by double quotes. Reserved 
 * characters are commas and newlines, for example <br> 
 * {@code "hello\nworld!", 5, "5,000,000 miles away", http://nyu.edu} <br>
 * is a row with four entries, two of which have reserved characters. <br>
 * Note: smart quotes or Unicode quotes are treated like regular characters not entries delimiters.  
 * 
 * <p>Sample use of this class:  
 * 
 * <pre>{@code 
 * 
 * 		//print each row from the file FILE_NAME.CSV
 * 		File f = new File("FILE_NAME.CSV"); 
 *		CSV csv = new CSV( new Scanner(f) ); 
 *		for (int i = 0; i < csv.getNumOfRows(); i++ ) {
 *			System.out.println( csv.getNextRow() + "\n"); 
		}
 * }</pre>
 * 
 * 
 * <pre>{@code 
 * 
 * 		//print fifth entry from each row from the file FILE_NAME.CSV 
 * 		//(assumes that each row has at least five entries)
 * 		File f = new File("FILE_NAME.CSV"); 
 *		CSV csv = new CSV( new Scanner(f) ); 
 *		for (int i = 0; i < csv.getNumOfRows(); i++ ) {
 *			System.out.println( csv.getNextRow().get(4) + "\n"); 
		}
 * }</pre>
 * 
 * 
 * 
 * @author Joanna Klukowska 
 * 
 *
 */


public class CSV {
	
	//a list of lists to store each row of the file in a list of Strings 
	private ArrayList<ArrayList<String>> lines; 
	
	//keeps track of what line number should be returned by the next call to getNextRow() 
	private int nextLine; 
	
	
	/** 
	 * Constructs a CSV object using the input from the provided Scanner object. 
	 * Closes the provided Scanner object on successful termination. 
	 * @param in stream from which the data should be read 
	 * @throws NullPointerException when {@code in} is null
	 * @throws IllegalArgumentException when no data could be read from {@code in} 
	 */
	public CSV( Scanner in ) {
		
		
		//validate the parameter 
		if (in == null ) throw new NullPointerException("Scanner in is null");  
		
		// read the entire file into a single String 
		in.useDelimiter("\\Z");   //
		String csvFile = in.next(); 
		
		lines = new ArrayList<ArrayList<String>> (); 
		
		parseFile( csvFile ); 
		
		if (lines.size() < 1 ) throw new IllegalArgumentException ("no valid lines present");
		
		nextLine = 0; 

		in.close(); 
	}
	
	/**
	 * Returns the number of rows successfully read from the CSV file. 
	 * @returns number of rows in this object 
	 */
	public int getNumOfRows () { 
		return lines.size(); 
	}
	
	/**
	 * Returns a list of entries from the next row. 
	 * This function provides cyclical access to the rows in this object. 
	 * The initital call to this function returns the first (index 0) row, 
	 * the next call returns second row, the next call returns third row, etc, until 
	 * all the rows are returned. After the last row is requested the following call
	 * to this function will return first row again. 
	 * @return the next row from this object 
	 */
	public ArrayList<String> getNextRow() {
		ArrayList<String> next = lines.get(nextLine);
		nextLine = (nextLine+1) % lines.size(); 
		
		return next; 
	}
	
	
	/* 
	 * Helper method that parses through the string obtained from the input stream 
	 * and constructs the list of all the rows. 
	 */
	private void parseFile ( String csvFile ) {
		
		int charCounter = 0; 
		int charTotal = csvFile.length();
		
		while (charCounter < charTotal ) {
			
			ArrayList<String> currentLine = new ArrayList<String>(); 
					
			StringBuffer nextWord = new StringBuffer(); 
			char nextChar; 
			boolean insideQuotes = false; 
			boolean insideEntry= false; 
			boolean endOfLine = false; 
			
			// iterate over all characters in the textLine
			while (!endOfLine && charCounter < charTotal) {
				nextChar = csvFile.charAt(charCounter);
				charCounter++; 
				
				// handle regular quotes as field separators, 
				// (smart quotes are used within entries) 
				if (nextChar == '"' ) {//|| nextChar == '\u201C' || nextChar =='\u201D') {
						
					// change insideQuotes flag when nextChar is a quote
					if (insideQuotes) {
						insideQuotes = false; 
						insideEntry = false;
					}else {
						insideQuotes = true; 
						insideEntry = true;
					}
				} else if (Character.isWhitespace(nextChar)) {
					// if new line outside of quotes, this is the end of this row 
					if (nextChar == '\n' && !insideQuotes) {
						endOfLine = true; 
					}
					else if ( insideQuotes || insideEntry ) {
					// add it to the current entry 
						nextWord.append( nextChar );
					}else { 
						// skip all spaces between entries
						continue; 
					}
				} else if ( nextChar == ',') {
					if (insideQuotes){ // comma inside an entry
						nextWord.append(nextChar); 
					} else { // end of entry found
						insideEntry = false;
						currentLine.add(nextWord.toString());
						nextWord = new StringBuffer();
					}
				} else {
					// add all other characters to the nextWord
					nextWord.append(nextChar);
					insideEntry = true;
				} 
				
			}
			// add the last word ( assuming not empty ) 
			// trim the white space before adding to the list 
			if (!nextWord.toString().equals("")) {
				currentLine.add(nextWord.toString().trim());
			}
			
			lines.add(currentLine);			
		}
		
	}
    /**
     * Returns a string representation of this object.  The string
     * representation consists of a list of each row entries enclosed in square brackets
     * ({@code "[]"}).  Adjacent entries are separated by the characters
     * {@code ", "} (comma and space). 
     * The rows are separated by a newline character {@code \n}. 
     *
     * @return a string representation of this object
     */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (ArrayList<String> line : lines ) {
			sb.append(line); 
			sb.append("\n\n"); 
		}
		
		return sb.toString(); 
		
	}
	
}
