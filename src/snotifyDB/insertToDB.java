package snotifyDB;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

public class insertToDB {
	private static Statement stmt;
	private static PreparedStatement prpSt;
	private static PreparedStatement prpSt2;
	private static String sql;
	private static String query;
	
	/**
	 * Use this method if all the stops are inserted
	 * and you only need to insert to Vasttrafik table
	 * and TripStops table
	 */
	public static void insertInit(int distance, int totalTime, ArrayList<String> stopName) throws FileNotFoundException, SQLException {
		connectDB.connectToDB();
		query = "INSERT INTO VasttrafikTrip (distance, totalTime)" + " values (?,?)";
		prpSt = connectDB.connection.prepareStatement(query);
		prpSt.setInt(1, distance);
		prpSt.setInt(2, totalTime);
		prpSt.executeQuery();
		
		query = "SELECT LAST_INSERT_ID()";
		prpSt = connectDB.connection.prepareStatement(query);
		ResultSet rs = prpSt.executeQuery();
		int tripID = 0;
		while(rs.next()) {
			tripID = rs.getInt(1);
		}
		query = "INSERT INTO TripStops (VasttrafikTripID, stopID, number)" + " values(?,?,?)";
		prpSt = connectDB.connection.prepareStatement(query);
		for(int i=0; i<stopName.size(); i++) {
			sql = "SELECT stopID FROM Stop WHERE stopName = " + "'" + stopName.get(i) + "'";
			prpSt2 = connectDB.connection.prepareStatement(sql);
			ResultSet stopID = prpSt2.executeQuery();
			while(stopID.next()) {
				prpSt.setInt(2, stopID.getInt(1));
			}
			prpSt.setInt(1,tripID);
			prpSt.setInt(3, i+1);
			//prpSt = connectDB.connection.prepareStatement(query);
			prpSt.executeQuery();
		}
		connectDB.connection.close();
	}
	
	public static void insertToVasttrafikTrip(int distance, int totalTime) throws FileNotFoundException, SQLException {
		connectDB.connectToDB();
		query = "INSERT INTO VasttrafikTrip (distance, totalTime)" + " values (?,?)";
		prpSt = connectDB.connection.prepareStatement(query);
		prpSt.setInt(1, distance);
		prpSt.setInt(2, totalTime);
		prpSt.executeQuery();
		//query = "SELECT LAST_INSERT_ID()";
		prpSt = connectDB.connection.prepareStatement(query);
		//prpSt.executeQuery();
		//ResultSet rs = prpSt.executeQuery();
		//int tripID;
		//while(rs.next()) {
			//tripID = rs.getInt(1);
		//}
		
		connectDB.connection.close();
	}
	
	public static void insertToTripStops(int number) throws FileNotFoundException, SQLException {
		connectDB.connectToDB();
		query = "INSERT INTO TripStops (number)" + " values (?)";
		prpSt = connectDB.connection.prepareStatement(query);
		prpSt.setInt(1, number);
		prpSt.executeQuery();
		connectDB.connection.close();
	}
	
	public static void insertToStop(String stopName) throws FileNotFoundException, SQLException {
		connectDB.connectToDB();
		query = "INSERT INTO Stop(stopName)" + " values (?)";
		prpSt = connectDB.connection.prepareStatement(query);
		prpSt.setString(1, stopName);
		prpSt.executeQuery();
		connectDB.connection.close();
	}
	
	public static void insertToDelay(Time time, int delay) throws FileNotFoundException, SQLException {
		connectDB.connectToDB();
		query = "INSERT INTO Delay (time, delay)" + " values(?,?)";
		prpSt = connectDB.connection.prepareStatement(query);
		prpSt.setTime(1, time);
		prpSt.setInt(2, delay);
		prpSt.executeQuery();
		connectDB.connection.close();
	}
	
	public static void insertToJourney(int JourID, Date date, Time tStart, Time tEnd, String weekDay, int delayed, int dist, int totTime, int nrStops, String startStat) throws FileNotFoundException, SQLException {
		connectDB.connectToDB();
		query = "INSERT INTO Journey (JourneyID, date, timeStart, weekDay, delayed)" + " values(?,?,?,?,?)";
		prpSt = connectDB.connection.prepareStatement(query);
		prpSt.setInt(1, JourID);
		prpSt.setDate(2, date);
		prpSt.setTime(3, tStart);
		prpSt.setTime(4, tEnd);
		prpSt.setString(5, weekDay);
		prpSt.setInt(6, delayed);
		sql = "SELECT VasttrafikTrip.VasttrafikTripID FROM VasttrafikTrip INNER JOIN TripStops ON "
				+ "(TripStops.VasttrafikTripID = VasttrafikTrip.VasttrafikTripID) WHERE "
				+ "VasttrafikTrip.distance = " + dist + " AND VasttrafikTrip.totalTime = " + totTime +
				" VasttrafikTrip.nrOfStops = " + nrStops + " AND (TripStops.stopID = (SELECT stopID FROM Stop WHERE stopName = " + "'" + startStat
				+ "') AND TripStops.number = 1)" ;
		prpSt.executeQuery();
		connectDB.connection.close();
	}
}
