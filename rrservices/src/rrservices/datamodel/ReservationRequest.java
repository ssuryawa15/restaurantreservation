package rrservices.datamodel;

public class ReservationRequest {
	int primarykey = 0;
	String guestname = null;
	String emailid = null;
	String phonenumber = null;
	int partysize = 0;
	String date = null;
	int timeslot = 0;
	String status = null;
	public int getPrimarykey() {
		return primarykey;
	}
	public void setPrimarykey(int primarykey) {
		this.primarykey = primarykey;
	}
	public String getGuestname() {
		return guestname;
	}
	public void setGuestname(String guestname) {
		this.guestname = guestname;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public int getPartysize() {
		return partysize;
	}
	public void setPartysize(int partysize) {
		this.partysize = partysize;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTimeslot() {
		return timeslot;
	}
	public void setTimeslot(int timeslot) {
		this.timeslot = timeslot;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	

}
