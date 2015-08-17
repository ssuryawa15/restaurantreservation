package rrservices.dataconnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	private final static String URL = "jdbc:mysql://localhost:3306/restaurantdb";
	private final static String USER = "root";
	private final static String PASSWORD = "saurmaha510";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection connectToDB(){
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connection is successful");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error establishing the connection: " + e.getMessage());
		}
		return con; 
	}
	
	public static void closeResources (PreparedStatement ps, ResultSet rs, Connection con) {
		try {
			if(ps != null)
			{
				ps.close();
			}
			if(rs != null)
			{
				rs.close();
			}
			if(con != null)
			{
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DBUtil.connectToDB();
	}
}
