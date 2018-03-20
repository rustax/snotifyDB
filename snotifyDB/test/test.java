package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {	
	public static void main(String[] cmdLn) throws SQLException {
		Statement stmt = null;
		Connection connection = DriverManager.getConnection("jdbc:mariadb://mrjosu9112.synology.me:3307/test?user=root&password=sn0t1fy.123");
		stmt = connection.createStatement();
		String sql;
		sql = "SELECT userID, fName, lName FROM Users";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			int userID = rs.getInt("userID");
			String fName = rs.getString("fName");
			String lName = rs.getString("lName");
			//String address = rs.getString("Address");
			//int phNr = rs.getInt("phoneNr");
			
			System.out.println("UserID: " + userID);
			System.out.println("First Name: " + fName);
			System.out.println("Last Name: " + lName);
			//System.out.println("Address: " + address);
			//System.out.println("Phone Number: " + phNr);
			
		}
		rs.close();
		stmt.close();
		connection.close();
	}
}
