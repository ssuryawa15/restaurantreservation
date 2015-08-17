package rrservices.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rrservices.dataconnections.DBUtil;
import rrservices.datamodel.Profile;
import rrservices.util.MyUtils;

public class ProfileDao {
	public void addProfile(Profile profile){
		String statement="INSERT INTO ownerprofile (primarykey,restaurantname,address,openingtime,closingtime)VALUES (?,?,?,?,?)";
		Connection con = null;
		int pk = MyUtils.getPrimarykey();
		try {
			con = DBUtil.connectToDB();
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setInt(1, pk);
			ps.setString(2, profile.getRestaurantName());
			ps.setString(3, profile.getAddress());
			ps.setString(4, profile.getOpeningTime());
			ps.setString(5, profile.getClosingTime());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Profile getProfile(){
		String statement = "SELECT * FROM ownerprofile";
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBUtil.connectToDB();
			PreparedStatement ps = con.prepareStatement(statement);
			Profile pr = new Profile();
			rs = ps.executeQuery();
			if(rs.next())
			{
			pr.setPrimarykey(rs.getInt("primarykey"));
			pr.setAddress(rs.getString("address"));
			pr.setOpeningTime(rs.getString("openingtime"));
			pr.setClosingTime(rs.getString("closingtime"));
			pr.setRestaurantName(rs.getString("restaurantname"));
			
			return pr;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
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
	
	public void updateProfile(Profile profile){
		String statement="update ownerprofile set restaurantname = ?,address=?,openingtime=?,closingtime=? where primarykey = ?";
		Connection con = null;
		try {
			con = DBUtil.connectToDB();
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setString(1, profile.getRestaurantName());
			ps.setString(2, profile.getAddress());
			ps.setString(3, profile.getOpeningTime());
			ps.setString(4, profile.getClosingTime());
			ps.setInt(5,profile.getPrimarykey());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
