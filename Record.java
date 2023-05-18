package project3;


/* The Record class stores information about a particular record/row from the input file. 
 * Stores person name, the business associated with the record, and the location associated with the record
 * This class provides a three parameter constructor: Record ( Name name, String business, Location location) 
 * The class provides public getters as follows: 
 * Name getName()
 * String getBusiness()
 * Location getLocation()
 * This class should implement Comparable<Record> interface. 
 */

public class Record implements Comparable<Record>{
	private Name name;
	private String business;
	private Location location;
	
	
	/* Constructs a new Record object specified with name, business, and location
	 * @param name a Name object
     * @param business business
     * @param location a Location object
     */
	public Record ( Name name, String business, Location location){
		this.name = name;
		this.business = business;
		this.location = location;
	}
	
	/* Returns name on the record
	 * @return the name on the record
	 */
	public Name getName(){
		return name;
	}
	
	/* Returns the name of the business
	 * @return the name of the business
	 */
	public String getBusiness(){
		return business;
	}
	
	/* Returns the location on the record
	 * @return the location on the record
	 */
	public Location getLocation(){
		return location;
	}
	
	/* Compares two Record objects are equal if all of their components
	 * @override equals method of Object class.
	 * @return true if name, business, and location are all equal, false if not
	 */
	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (! (obj instanceof Record)) return false;
		
		Record other = (Record) obj;
		
		int compareName = this.name.compareTo(other.name);
		if (compareName != 0 ) return false;
		int compareBusiness = this.business.compareToIgnoreCase(other.business);
		if(compareBusiness != 0 ) return false;
		int compareLocation = this.location.compareTo(other.location);
		if (compareLocation != 0) return false;
		
		return true;
	}
	
	/* This gives a string representation of a person's record
	 * @override toString method of String class
	 * @return two-line string in the following format: LAST_NAME, FIRST_NAME, INITIAL 
	 *                                                  <tab>BUSINESS_NAME, CITY, STATE, COUNTRY, ZIP 
     */
	
	@Override
	public String toString(){
		return name.toString() + "\n" + "\t" + getBusiness() + ", " + location.getCity() + ", " + location.getState() + ", " + location.getCountry() + ", " + location.changeZip();
	}

	/* Compared two Record objects
	 * @param o other Record object to be compared to
	 * @override compareTo method of String class
	 * @return 0 if Record objects are equal, a negative number if this object is less than the other, and a positive number if this object is greater than the other
	 */
	@Override
	public int compareTo(Record o) {
		if(this.name.compareTo(o.name) != 0)
			return this.name.compareTo(o.name);
		else if (this.location.compareTo(o.location) != 0)
			return this.location.compareTo(o.location);
		return 0;
	}
}
