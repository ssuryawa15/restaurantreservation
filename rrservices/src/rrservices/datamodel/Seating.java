package rrservices.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Seating {
	
	int primarykey = 0;
	int capacity = 0;
	public int getPrimarykey() {
		return primarykey;
	}
	public void setPrimarykey(int primarykey) {
		this.primarykey = primarykey;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	

}
