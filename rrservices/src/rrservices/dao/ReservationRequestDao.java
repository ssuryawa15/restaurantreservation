package rrservices.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;

import rrservices.dataconnections.DBUtil;
import rrservices.datamodel.ReservationRequest;
import rrservices.util.MyUtils;
public class ReservationRequestDao {
	
	public List<ReservationRequest> getAllReservationRequests() throws SQLException{
		String statement = "select * from request_reservation";
		Connection con = DBUtil.connectToDB(); 
		java.sql.PreparedStatement ps = null;
		
		ArrayList<ReservationRequest> listOfrR = new ArrayList<>();
		ps = con.prepareStatement(statement);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			ReservationRequest rR = new ReservationRequest();
			rR.setDate(rs.getString("date"));
			rR.setEmailid(rs.getString("emailid"));
			rR.setGuestname(rs.getString("guestname"));
			rR.setPartysize(rs.getInt("partysize"));
			rR.setPhonenumber(rs.getString("phonenumber"));
			rR.setPrimarykey(rs.getInt("primaykey"));
			rR.setStatus(rs.getString("status"));
			rR.setTimeslot(rs.getInt("timeslot"));
			listOfrR.add(rR);
		}
		con.close();
		rs.close();
		return listOfrR;
	}
	
	public ReservationRequest addReservationRequest(ReservationRequest req)
	{
		String statement = "INSERT INTO `restaurantdb`.`request_reservation` (`primaykey`, `guestname`, `partysize`, `phonenumber`, `emailid`, `timeslot`, `date`, `status`) VALUES (?,?,?,?,?,?,?,?)";
		Connection con = DBUtil.connectToDB(); 
		java.sql.PreparedStatement ps = null;
		try {
			int pk = MyUtils.getPrimarykey();
			req.setPrimarykey(pk);
			ps = con.prepareStatement(statement);
			ps.setInt(1, pk);
			ps.setString(2, req.getGuestname());
			ps.setInt(3, req.getPartysize());
			ps.setString(4, req.getPhonenumber());
			ps.setString(5, req.getEmailid());// TODO Auto-generated catch block
			ps.setInt(6, req.getTimeslot());
			ps.setString(7, req.getDate());
			ps.setString(8, req.getStatus());
			ps.executeUpdate();
			return req;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	public ReservationRequest getReservationRequest(int primarykey){
		String statement = "select * from request_reservation where primaykey = ?";
		Connection con = DBUtil.connectToDB(); 
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		ReservationRequest rR = new ReservationRequest();
		
		
		try {
			ps = con.prepareStatement(statement);
			ps.setInt(1, primarykey);
			
			 rs = ps.executeQuery();
			if(rs.next())
			{
				
				rR.setDate(rs.getString("date"));
				rR.setEmailid(rs.getString("emailid"));
				rR.setGuestname(rs.getString("guestname"));
				rR.setPartysize(rs.getInt("partysize"));
				rR.setPhonenumber(rs.getString("phonenumber"));
				rR.setPrimarykey(rs.getInt("primaykey"));
				rR.setStatus(rs.getString("status"));
				rR.setTimeslot(rs.getInt("timeslot"));
				return rR;
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
	
	
	
	public ReservationRequest updateReservationRequest(ReservationRequest rr){
		
		String statement = "UPDATE request_reservation SET status=?,guestname=?,phonenumber=?,emailid=?,timeslot=?,date=? WHERE primaykey=?";
		Connection con = DBUtil.connectToDB(); 
		java.sql.PreparedStatement ps = null;
		ReservationRequest rR = new ReservationRequest();
		
		
		try {
			ps = con.prepareStatement(statement);
			ps.setString(1, rr.getStatus());
			ps.setString(2, rr.getGuestname());
			ps.setString(3, rr.getPhonenumber());
			ps.setString(4, rr.getEmailid());
			ps.setInt(5, rr.getTimeslot());
			ps.setString(6, rr.getDate());
			ps.setInt(7, rr.getPrimarykey());
			
			 ps.executeUpdate();
			return getReservationRequest(rr.getPrimarykey());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
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
