package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class test {	
	public static void main(String[] cmdLn) throws SQLException, FileNotFoundException {
		Scanner scan = new Scanner(new FileReader("/home/rustax/Documents/dblogin.txt"));
		String user = scan.nextLine();
		user = user.substring(user.indexOf(" ")+1);
		String pw = scan.nextLine();
		pw = pw.substring(pw.indexOf(" ")+1);
		String ip = scan.nextLine();
		ip = ip.substring(ip.indexOf(" ")+1);
		scan.close();
		
		Statement stmt = null;
		Connection connection = DriverManager.getConnection("jdbc:mariadb://" + ip + "/test?user=" + user + "&password=" + pw);
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
