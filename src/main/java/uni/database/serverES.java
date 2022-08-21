package uni.database;

import java.sql.*;

public class serverES {
	
	public static Connection connection(String user, String psw, String dbName, String dbIP, String dbPort) throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		try {
			DriverManager.getConnection("jdbc:postgresql://" + dbIP + dbPort + "/" + dbName, user, psw);
			System.out.println("connessione avvenuta con successo");
		} catch (SQLException e) {
			System.out.println("Errore nella connessione");
		}
	}
	
	public static String getdbUser() {
		return "postgres";
	}
	
	public static String getdbPsw() {
		return "password";
	}
	
	public static String getdbPort() {
		return "5432";
	}
	
	public static String getdbName() {
		return "EmotionalSongs";
	}
	
	public static String getdbIP() {
		return "localhost";
	}
	
	public static void setConn() throws ClassNotFoundException {
		Connection conn = connection(getdbUser(), getdbPsw(), getdbName(), getdbIP(), getdbPort());
	}
}