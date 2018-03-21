package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Scanner;

public class test {	
	public static void main(String[] cmdLn) throws SQLException, FileNotFoundException {
		Scanner scan = new Scanner(new FileReader("E:\\Dokument\\dblogin.txt"));
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
		String query;
		String sql;
		sql = "SELECT VasttrafikTripID, distance, totalTIme FROM VasttrafikTrip";
		query = "INSERT INTO VasttrafikTrip (distance, totalTime)" + " values (?,?)";
		PreparedStatement prpSt = connection.prepareStatement(query);
		prpSt.setInt(1, 5001);
		java.sql.Time time = getCurrentJavaSqlTime();
		prpSt.setTime(2, time);
		prpSt.executeQuery();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			int VasttrafikTripID = rs.getInt("VasttrafikTripID");
			int distance = rs.getInt("distance");
			Time totalTime = rs.getTime("totalTime");
			//String address = rs.getString("Address");
			//int phNr = rs.getInt("phoneNr");
			
			System.out.println("VasttrafikTripID: " + VasttrafikTripID);
			System.out.println("Distance: " + distance);
			System.out.println("Total Time: " + totalTime);
			//System.out.println("Address: " + address);
			//System.out.println("Phone Number: " + phNr);
			
		}
		rs.close();
		stmt.close();
		connection.close();
	}
	
	public static java.sql.Time getCurrentJavaSqlTime() {
	    java.util.Date date = new java.util.Date();
	    return new java.sql.Time(date.getTime());
	  }
}
