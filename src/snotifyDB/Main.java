package snotifyDB;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
	private static Statement stmt;
	private static String sql;
	
	public static void main(String[] cmdLn) throws SQLException, IOException, ParseException {
		
		System.out.println(insertToDB.convertToTime("17:23"));
		
//		String fileName = "E:\\Dokument\\Skola\\Examensarbete\\allstops.csv";
//		InputStream myFileStream = new FileInputStream(fileName);
//		
//		ArrayList<String> Stops = new ArrayList<String>();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(myFileStream,"ISO-8859-1"));
//		String stop = null;
//		while ((stop = reader.readLine()) != null) {
//		    //insertToDB.insertToStop(stop);
//			Stops.add(stop);
//		}
//		
//		for(String s : Stops) {
//			System.out.println("Stop: " + s);
//		}
		
//		ArrayList<String> stops = new ArrayList<String>();
//		stops.add("Gamlestaden Station, Göteborg");
//		stops.add("Åmål station, Åmål");
//		stops.add("Hällekis station, Götene");
//		stops.add("Aspedalen station, Lerum");
//		
//		insertToDB.insertInit(4000, 50, stops);
		
		
		
//		java.sql.Time time = getCurrentJavaSqlTime();
//		//insertToDB.insertToStop("Helsingborg C");
//		//insertToDB.insertToVasttrafikTrip(5000, time);
//		//insertToDB.insertToTripStops(5);
//		insertToDB.insertToStop("Helsingborg C");
//		insertToDB.insertToStop("Kungsbacka C");
//		insertToDB.insertToStop("Gustav Adolfs Torg");
//		insertToDB.insertToStop("Halmstad C");
//		insertToDB.insertToStop("Västergård");
//		insertToDB.insertToStop("Halmstad Högskola");
//		
//		ArrayList<String> stops = new ArrayList();
//		stops.add("Helsingborg C");
//		stops.add("Kungsbacka C");
//		stops.add("Gustav Adolfs Torg");
//		stops.add("Halmstad C");
//		stops.add("Västergård");
//		stops.add("Halmstad Högskola");
//		
//		insertToDB.insertInit(6000, time, stops);
		
	}
	public static java.sql.Time getCurrentJavaSqlTime() {
	    java.util.Date date = new java.util.Date();
	    return new java.sql.Time(date.getTime());
	  }
}

