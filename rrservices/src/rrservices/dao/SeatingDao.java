package rrservices.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rrservices.dataconnections.DBUtil;
import rrservices.datamodel.ReservationRequest;
import rrservices.datamodel.Seating;

public class SeatingDao {

	public List<Seating> getAll() throws SQLException
	{
		String statement = "select * from seating";
		Connection con = DBUtil.connectToDB(); 
		java.sql.PreparedStatement ps = null;
		
		List<Seating> listOfSeating = new ArrayList<>();
		ps = con.prepareStatement(statement);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
		 Seating seating = new Seating();
		 seating.setCapacity(rs.getInt("capacity"));
		 seating.setPrimarykey(rs.getInt("primarykey"));
		 listOfSeating.add(seating);
		}
		return listOfSeating;
	}
	
	public List<Seating> getVacant(int timeSlot, String date, int cap) throws SQLException
	{
		String statement = "select * from seating where capacity >= ? and primarykey not in (select seatingfk from confirmations where timeslotfk = ? and date = ?)";
		Connection con = DBUtil.connectToDB(); 
		java.sql.PreparedStatement ps = null;
		
		List<Seating> listOfSeating = new ArrayList<>();
		ps = con.prepareStatement(statement);
		ps.setInt(1, cap);
		ps.setInt(2, timeSlot);
		ps.setString(3, date);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
		 Seating seating = new Seating();
		 seating.setCapacity(rs.getInt("capacity"));
		 seating.setPrimarykey(rs.getInt("primarykey"));
		 listOfSeating.add(seating);
		}
		return listOfSeating;
	}
	
	public Seating getSeat(int primarykey) 
	{
		String statement = "select * from seating where primarykey = ?";
		Connection con = DBUtil.connectToDB(); 
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
        Seating seat = new Seating();
        
        
		try {
			ps = con.prepareStatement(statement);
			ps.setInt(1, primarykey);
			
			 rs = ps.executeQuery();
			if (rs.next())
			{
			 
			 seat.setCapacity(rs.getInt("capacity"));
			 seat.setPrimarykey(rs.getInt("primarykey"));
			 return seat;
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
