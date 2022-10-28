package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class serverES {
	public static Connection dbLink;
	
	public static Connection connection(String user, String pass, String DB, String IP, String PORT) throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		try {
			Connection connect = DriverManager.getConnection("jdbc:postgresql://" + "localhost:" + PORT + "/" + DB, user, pass);
			System.out.println("connessione avvenuta con successo");
			return connect;
		} catch (SQLException e) {
			System.out.println("Errore nella connessione");
			return null;
		}
	}
	
	public static Connection connect() throws ClassNotFoundException {
		String user = DBInfo.getUSERNAME();
		String pass = DBInfo.getPASS();
		String DBName = DBInfo.getDBNAME();
		String IP = DBInfo.getIP();
		String PORT = DBInfo.getDBPORT();
		
		return connection(user, pass, DBName, IP, PORT);
	}
	
	//Per una qualche ragione sto metood non si connette
	
	/**
	 * public static Connection getConnection() {
	 * try {
	 * Class.forName("org.postgresql.Driver");
	 * dbLink = DriverManager.getConnection("jdbc:postgresql://" + "localhost:"+ getDBPort() + "/" + getDBName(), getDBUser(), getDBPsw());
	 * System.out.println("connessione avvenuta con successo");
	 * return dbLink;
	 * } catch (SQLException | ClassNotFoundException e) {
	 * System.out.println("Errore nella connessione");
	 * }
	 * return null;
	 * }
	 **/
	
	public static String getDBUser() {
		return "postgres";
	}
	
	public static String getDBPsw() {
		return "password";
	}
	
	public static String getDBName() {
		return "EmotionalSongs";
	}
	
	public static String getDBIP() {
		return "localhost";
	}
	
	public static String getDBPort() {
		return "5432";
	}
}