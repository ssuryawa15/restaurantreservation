package rrservices.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rrservices.dataconnections.DBUtil;
import rrservices.datamodel.TimeSlot;

public class TimeSlotDao {
public List<TimeSlot> getAll()
{
 String statement = "select * from time_slot";
 Connection con = DBUtil.connectToDB();
 PreparedStatement ps = null;
	ResultSet rs = null;
 try {
	 ps = con.prepareStatement(statement);
	 rs = ps.executeQuery();
	List<TimeSlot> listOfTimeSlots = new ArrayList<>();
	while(rs.next())
	{
		TimeSlot ts = new TimeSlot();
		ts.setPrimarykey(rs.getInt("primarykey"));
		ts.setTimeslot(rs.getString("timestring"));
		listOfTimeSlots.add(ts);
	}
	return listOfTimeSlots;
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


public TimeSlot getTimeSlot(int primarykey)
{
	String statement = "select * from time_slot where primarykey = ?";
	Connection con = DBUtil.connectToDB();
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	try {
		ps = con.prepareStatement(statement);
		ps.setInt(1, primarykey);
		rs = ps.executeQuery();
		TimeSlot ts = new TimeSlot();
		if(rs.next())
		{
			ts.setPrimarykey(rs.getInt("primarykey"));
			ts.setTimeslot(rs.getString("timestring"));
			return ts;
		}
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



}
