package project3;


/* This class represents a location associated with each record. 
 * It should store information about city, state, country and zip code.
 * This class should provide a single constructors with four parameters Name ( String city, String state, String country, String zip ) 
 * The city is a non-empty String object. The state, country and zip code can be, possibly empty or null, String objects.
 * Valid zip code values are 5-digit strings (i.e., any string that has non-digit characters is not a valid zip code, any string that has more than 5 digits is not a valid zip code). If the zip argument contains fewer than 5 digits (and no other non-digit characters), then the leading digits should be assumed to be zeros. For example, if zip="1301" then the actual zip-code is 01301.
 * It should implement Comparable<Location> interface.
 * 
 * @author Gabriella Codrington 
 */

public class Location implements Comparable<Location> {
	private String city;
	private String state;
	private String country;
	private String zip;
	
	/* Constructs a new Location object with specified city, state, country, and zip code
	 * @param city city
     * @param state state
     * @param country country
     * @param zip zip code
     * @throws IllegalArgumentException if the constructor is called with null or and empty string as the city
     * @throws IllegalArgumentException if the constructor is called with zip value that is not valid. 
     */
	public Location (String city, String state, String country, String zip) throws IllegalArgumentException{
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
	}

	/* Returns the city of the location
	 * @return the city of the location
	 */
	public String getCity() {
		return city;
	}
	
	/* Returns the state of the location
	 * @return the state of the location
	 */
	public String getState() {
		return state;
	}
	
	/* Returns the country of the location
	 * @return the country of the location
	 */
	public String getCountry() {
		return country;
	}
	
	/* This class should override the equals methods. 
	 * The two Location objects are equal if all of their components (city, state, country, zip code) are the same. 
	 * This comparison should be case insensitive.
	 * The comparison should be case insensitive and consistent with alphabetic ordering of words in an English dictionary. 
	 * The Location objects should be compared by the zip code, city name, then the state, then country. 
	 * If any of the values is missing, the comparison should be based on the following value in the list. 
	 * 
	 * @param other other Location object to be compared to
	 * @override equals method of Object class
	 * @return true if Location objects are equal, false if not
	 */
	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Location)) return false;
		
		Location other = (Location) obj;
		
		if (this.zip != null && other.zip != null) {
			int compareZip = this.zip.compareToIgnoreCase(other.zip);
			if (compareZip == 0) {
				int compareCity = this.city.compareToIgnoreCase(other.city);
				if (compareCity == 0) {
					int compareState = this.state.compareToIgnoreCase(other.state);
					if (compareState == 0) {
						int compareCountry = this.country.compareToIgnoreCase(other.country);
						if (compareCountry == 0)
							return true;
					}
				}
			}
		}
		return false;
	}

	/* Compares this Location object with another
	 * The Location objects should be compared by the zip code, city name, then the state, then country
	 * @param o other Location object to be compared to 
	 * @override compareTo method of String class
	 * @return 0 if locations are equal, a negative number if this location is less than the other, and a positive number if this location is greater than the other
	 */
	@Override
	public int compareTo(Location o) {
		if(this.zip.compareToIgnoreCase(o.zip) != 0)
			return this.zip.compareToIgnoreCase(o.zip);
		else if (this.city.compareToIgnoreCase(o.city) != 0)
			return this.city.compareToIgnoreCase(o.city);
		else if (this.state.compareToIgnoreCase(o.state) != 0)
			return this.state.compareToIgnoreCase(o.state);
		else if (this.country.compareToIgnoreCase(o.country) != 0)
			return this.country.compareToIgnoreCase(o.country);
		return 0;
	}
	
	/* Returns the location in string format
	 * @return string version of the Location object
	 */
	public String toString() {
		return city + ", " + state + ", " + country + ", " + zip;
	}

	/* Determines whether the city of the Location object contains specified keyword
	 * @param keyword location or location substring that we are looking for
	 * @return true if the keyword is in the city, false if it is not
	 */
	public boolean contains(String keyword) {
		if(this.city.toLowerCase().contains(keyword.toLowerCase()))
			return true;
		if(this.zip.contains(keyword))
			return true;
		return false;
	}
	
	/* Determines whether the zip code of the Location object contains specified keyword
	 * @param keyword location or location substring that we are looking for
	 * @return true if the keyword is in the zip code, false if it is not
	 */
	public String changeZip() {
		if(this.zip.length() == 3) {
			String newZip = "00".concat(this.zip);
			return newZip;
		}
		else if(this.zip.length() == 4) {
			String newZip = "0".concat(this.zip);
			return newZip;
		}
		return this.zip;
	}

	
}
