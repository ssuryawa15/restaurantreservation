package rrservices.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	int primarykey = 0;
	String username = null;
	String password = null;
	boolean isAuthenticated=false;
	
	
	
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	public int getPrimarykey() {
		return primarykey;
	}
	public void setPrimarykey(int primarykey) {
		this.primarykey = primarykey;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
