package rrservices.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rrservices.dataconnections.DBUtil;
import rrservices.datamodel.User;

public class UserDao {
 public User getUser(User user)
{
	Connection con = DBUtil.connectToDB();
	PreparedStatement ps = null;
	ResultSet rs = null;
    String statement = "select * from user where username = ? and password =?";
    
    
	try {
		ps = con.prepareStatement(statement);
		ps.setString(1,user.getUsername());
		ps.setString(2,user.getPassword());
		rs = ps.executeQuery();
		user.setAuthenticated(false);
		if(rs.next())
		{
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setPrimarykey(rs.getInt("primarykey"));
			user.setAuthenticated(true);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	
	return user;
	
}
}
