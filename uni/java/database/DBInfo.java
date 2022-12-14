package database;

import java.sql.Connection;

public class DBInfo {
	// TODO: 29/10/22 Ho settato il mio db con diverse credenziali: true è il vostro db
	private static final Boolean dbDefault = true;
	
	public static Connection isConnected;
	
	public static String getIP() {
		return "localhost";
	}
	
	public static String getPORT() {
		return "5432";
	}
	
	public static String getNAME() {
		return dbDefault ? "EmotionalSongs" : "postgres";
	}
	
	public static String getUser() {
		return "postgres";
	}
	
	public static String getPass() {
		return "password";
	}
}