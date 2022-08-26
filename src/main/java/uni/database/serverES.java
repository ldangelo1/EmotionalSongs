package uni.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class serverES {
	public static Connection dbLink;
	
	public static Connection getConnection() {
		String url = "jdbc:postgresql://" + getDBIP() + /*getDBPort() +*/ "/" + getDBName();
		
		try {
			Class.forName("org.postgresql.Driver");
			dbLink = DriverManager.getConnection(url, getDBUser(), getDBPsw());
			System.out.println("connessione avvenuta con successo");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Errore nella connessione");
		}
		return dbLink;
	}
	
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