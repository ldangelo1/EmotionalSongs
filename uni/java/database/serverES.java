package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class serverES {
	public static Connection connDB() {
		String url = "jdbc:postgresql://" + DBInfo.getIP() + ':' + DBInfo.getPORT() + "/" + DBInfo.getNAME();
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connDB = DriverManager.getConnection(url, DBInfo.getUser(), DBInfo.getPass());
			System.out.println("Connessione al database avvenuta con successo");
			return connDB;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Connessione al database fallita");
			throw new RuntimeException(e);
		}
	}
}