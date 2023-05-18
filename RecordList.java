package project3;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.String;

@SuppressWarnings("serial")
public class RecordList extends ArrayList<Record>{
	
	public RecordList () {}

	/* Returns a list of records for which the keyword is a substring of person's last name or first name. 
	 * The method should be case insensitive. 
	 * It should throw and instance of IllegalArgumentException exception if called with null argument or with an empty string. 
	 * It should return null if there are no Record objects matching the keyword.
	 * 
	 * @param keyword substring of a person's last name or first name
	 * @throws IllegalArgumentException if the keyword is a null argument or an empty string
	 * @return a list of records for which the keyword is a substring of a person's last name or first name, or null if there are no Record objects matching the keyword
	 */
	public RecordList getByName (String keyword) throws IllegalArgumentException{
		if(keyword == null || keyword.isBlank() || keyword.isEmpty()) {
			throw new IllegalArgumentException("This is not a valid query. Invalid keyword. Try again.");
		}
		RecordList list = new RecordList();
		for (Record r : this ) {
			Name name = r.getName();
			if (name == null) 
				continue; 
			if (name.contains( keyword ) ) 
				list.add(r); 
		}
		if (list.isEmpty()) {
			return null;
		}
		Collections.sort(list);
		return list;		
	}
	
	/* Returns a list of records for which the keyword is a substring of the city at which the tax preparer is located. 
	 * The method should be case insensitive. 
	 * It should throw and instance of IllegalArgumentException exception if called with null argument or with an empty string. 
	 * It should return null if there are no Record objects matching the keyword.
	 * 
	 * @param keyword substring of a taxpayer's city
	 * @throws IllegalArgumentException if the keyword is a null argument or an empty string
	 * @return a list of records for which the keyword is a substring of the city at which the tax preparer is located, or null if there are no Record objects matching the keyword
	 */
	public RecordList getByCity (String keyword) throws IllegalArgumentException{
		if(keyword == null || keyword.isBlank() || keyword.isEmpty()) {
			throw new IllegalArgumentException("This is not a valid query. Invalid keyword. Try again.");
		}
		RecordList list = new RecordList();
		for (Record r : this ) {
			Location city = r.getLocation();
			if (city == null) 
				continue; 
			if (city.contains( keyword ) ) 
				list.add(r); 
		}
		if (list.isEmpty()) {
			return null;
		}		
		Collections.sort(list);
		return list;
	}
	
	/* Returns a list of records for which the zip-code (if specified) matches the zip argument exactly. 
	 * It should throw and instance of IllegalArgumentException exception if called with null argument, 
	 * or with a string that does not represent a five digit zip code (i.e., contains any characters other than digits, 
	 * or contains fewer or more than five characters). 
	 * It should return null if there are no Record objects matching the zip exactly.
	 * 
	 * @param zip zip code
	 * @throws IllegalArgumentException if zip is a null argument or invalid (i.e., contains any characters other than digits, or contains fewer or more than five characters)
	 * @return a list of records for which the zip-code (if specified) matches the zip argument exactly, or null if there are no Record objects matching the zip exactly
	 */
	public RecordList getByZip (String zip) throws IllegalArgumentException{
		if(zip == null || zip.isBlank() || zip.isEmpty() || zip.length() != 5 || zip.matches(".*[a-z].*")) {
			throw new IllegalArgumentException("This is not a valid query. Invalid keyword. Try again.");
		}
				
		RecordList list = new RecordList();
		for (Record r : this ) {
			Location zipCode = r.getLocation();
			if (zipCode == null) 
				continue; 
			String newZip = zipCode.changeZip();
			if (newZip.contains( zip ) ) 
				list.add(r); 
		}
		if (list.isEmpty()) {
			return null;
		}
		Collections.sort(list);
		return list;
	}
	

}
