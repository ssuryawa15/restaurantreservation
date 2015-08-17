package rrservices.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Confirmations {
int primarykey = 0;
ReservationRequest reservation = null;
List<Seating> listOfSeating = null;
String date = null;
TimeSlot timeslot = null;


public List<Seating> getListOfSeating() {
	return listOfSeating;
}
public void setListOfSeating(List<Seating> listOfSeating) {
	this.listOfSeating = listOfSeating;
}

public int getPrimarykey() {
	return primarykey;
}
public void setPrimarykey(int primarykey) {
	this.primarykey = primarykey;
}
public ReservationRequest getReservation() {
	return reservation;
}
public void setReservation(ReservationRequest reservation) {
	this.reservation = reservation;
}

public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public TimeSlot getTimeslot() {
	return timeslot;
}
public void setTimeslot(TimeSlot timeslotfk) {
	this.timeslot = timeslotfk;
}


}
