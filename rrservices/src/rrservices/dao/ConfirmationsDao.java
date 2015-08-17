package rrservices.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Util;

import rrservices.dataconnections.DBUtil;
import rrservices.datamodel.Confirmations;
import rrservices.datamodel.Seating;
import rrservices.util.MyUtils;

public class ConfirmationsDao {
public List<Confirmations> getAll()
{
 String statement = "select * from confirmations group by reservationfk";
 Connection con = DBUtil.connectToDB();
 ResultSet rs = null;
 try {
	PreparedStatement ps = con.prepareStatement(statement);
	 rs = ps.executeQuery();
	SeatingDao sdao = new SeatingDao();
	TimeSlotDao tsdao = new TimeSlotDao();
	ReservationRequestDao rrdao = new ReservationRequestDao();
	List<Confirmations> confirmationsList = new ArrayList<>();
	while(rs.next())
	{
		int rrfk = rs.getInt("reservationfk");
		int tsfk = rs.getInt("timeslotfk");
		Confirmations confirmation = new Confirmations();
		confirmation.setDate(rs.getString("date"));
		confirmation.setPrimarykey(rs.getInt("primarykey"));
		confirmation.setReservation(rrdao.getReservationRequest(rrfk));
		confirmation.setListOfSeating(this.getSeatsForReservation(confirmation.getReservation().getPrimarykey()));
		confirmation.setTimeslot(tsdao.getTimeSlot(tsfk));
		confirmationsList.add(confirmation);
	}
	return confirmationsList;
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 finally
 {
	 try {
		con.close();
		 rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
return null;
 
}


private List<Seating> getSeatsForReservation(int primarykey) {
	String statement = "select seatingfk from confirmations where reservationfk = ?";
	Connection con = DBUtil.connectToDB();
	ResultSet rs = null;
	try {
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setInt(1, primarykey);
		rs = ps.executeQuery();
		List<Seating> seatings = new ArrayList<>();
		while(rs.next())
		{
			SeatingDao sdao = new SeatingDao();
			
			Seating seating = sdao.getSeat(rs.getInt(1));
			seatings.add(seating);
		
		}
		return seatings;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally
	{
		try {
			con.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return null;
}


public Confirmations addConfirmations(Confirmations confirmation)
{
	String statement = "INSERT INTO confirmations (primarykey, reservationfk, seatingfk, timeslotfk,date) VALUES (?,?,?,?,?)";
	Connection con = DBUtil.connectToDB();
	PreparedStatement ps = null;
	
	try {
		for(int i=0;i<confirmation.getListOfSeating().size();i++)
		{
		int pk = MyUtils.getPrimarykey();
	    ps = con.prepareStatement(statement);		
		confirmation.setPrimarykey(pk);
		ps.setInt(1, pk);
		ps.setInt(2, confirmation.getReservation().getPrimarykey());
		ps.setInt(3, confirmation.getListOfSeating().get(i).getPrimarykey());
		ps.setInt(4, confirmation.getReservation().getTimeslot());
		ps.setString(5, confirmation.getReservation().getDate());
		ps.executeUpdate();
		}
		return confirmation;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
			
	
	
	return null;
	
}


public Confirmations delConfirmations(Confirmations confirmation)
{
	String statement = "delete from confirmations where reservationfk = ?";
	Connection con = DBUtil.connectToDB();
	PreparedStatement ps = null;
	
	try {
		
	    ps = con.prepareStatement(statement);		
		
		ps.setInt(1, confirmation.getReservation().getPrimarykey());
		ps.executeUpdate();
		
		return confirmation;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
			
	
	
	return null;
	
}




}
