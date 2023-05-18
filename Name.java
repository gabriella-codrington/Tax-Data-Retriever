package project3;

import java.lang.String;


/*This class represents a person's name. 
 * It stores information about first name, last name, and the middle initial.
 * This class provides three constructors:
 * one parameter constructor that takes last name only
 * two parameter constructor that takes last name and first name
 * three parameter constructor that takes last name, first name and the middle initial
 * Implement Comparable<Name> interface. 
 * 
 * @author Gabriella Codrington 
 */

public class Name implements Comparable<Name> {
		
	private String lName;
	private char mInitial;
	private String fName;
	
	/* Constructs a new Name object with specified last name
	 * @param lastName last name of person
	 * @throws IllegalArgumentException if called with null or an empty string as the last name
	 */
	public Name (String lName) throws IllegalArgumentException{
		this.lName = lName;
	}
	
	/* Constructs a new Name object with specified last name and first name
	 * @param lastName last name of person
	 * @param firstName first name of person
	 * @throws IllegalArgumentException if called with null or an empty string as the last name
	 */
	public Name (String lName, String fName) throws IllegalArgumentException{
		this.lName = lName;
		this.fName = fName;
	}
	


	/* Constructs a new Name object with specified last name, first name, and middle initial
	 * @param lastName last name of person
	 * @param firstName first name of person
	 * @param middleInitial middle initial of person
	 * @throws IllegalArgumentException if called with null or an empty string as the last name
	 */
	public Name (String lName, String fName, char mInitial) throws IllegalArgumentException{
		this.lName = lName;
		this.fName = fName;
		this.mInitial = mInitial;
	}
	
	
	/* Two Name objects are equal if their first name, last name and middle initial are the same.
	 * The comparison should be case insensitive and consistent with alphabetic ordering of words in an English dictionary. 
     * The Name objects should be compared by the last name first, then the first name, then the middle initial. 
	 * 
	 * @param obj other the other Name object
	 * @override equals method for the Object class
	 * @return true if two Name objects are equal, false if they are not 
	 */
	
	@Override
	public boolean equals(Object obj){		
		if (this == obj) return true;
		if (!(obj instanceof Name)) return false;
		
		Name other = (Name) obj;
				
		int compareLastName = this.lName.compareToIgnoreCase(other.lName);
		if (compareLastName == 0){
			int compareFirstName = this.fName.compareToIgnoreCase(other.fName);
			if (compareFirstName == 0){
				int compareMiddleInitial = Character.compare(this.mInitial, other.mInitial);
				if (compareMiddleInitial == 0){
					return true;
				}
			}
		}
		return false;
	}

	/* Compares this Name object with another
	 * @param o other Name object to be compared to 
	 * @override compareTo method of String class
	 * @return 0 if names are equal, a negative number if this name is less than the other, and a positive number if this name is greater than the other
	 */
	@Override
	public int compareTo(Name o) {
		if(this.lName.compareToIgnoreCase(o.lName) != 0)
			return this.lName.compareToIgnoreCase(o.lName);
		else if (this.fName.compareToIgnoreCase(o.fName) != 0)
			return this.fName.compareToIgnoreCase(o.fName);
		else if (Character.compare(this.mInitial, o.mInitial) != 0)
			return Character.compare(this.mInitial, o.mInitial);
		return 0;
	}
	
	/* Returns the name in string format
	 * @return string version of the Name object
	 */
	public String toString() {
		return lName + ", " + fName + ", " + mInitial;
	}
	
	/* Determines whether Name object contains specified keyword
	 * @param keyword name or name substring that we are looking for
	 * @return true if the keyword is in the Name object, false if it is not
	 */
	public boolean contains(String keyword) {
		if(lName.toLowerCase().contains(keyword.toLowerCase()))
			return true;
		else if(fName.toLowerCase().contains(keyword.toLowerCase()))
			return true;
		return false;
	}
	
	
}



