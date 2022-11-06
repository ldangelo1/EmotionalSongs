package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.out;

public class serverES {
	
	public static Connection connectionDB() {
		String url = "jdbc:postgresql://" + DBInfo.getIP() + ':' + DBInfo.getPORT() + "/" + DBInfo.getNAME();
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connDB = DriverManager.getConnection(url, DBInfo.getUser(), DBInfo.getPass());
			out.println("Connessione al database avvenuta con successo");
			return connDB;
		} catch (SQLException | ClassNotFoundException e) {
			out.println("Connessione al database fallita");
			return null;
		}
	}
	
	public static Object eseguiQuery(String query, Integer code) throws SQLException {
		try {
			switch (code) {
				case 1:
					return DBInfo.isConnected.prepareStatement(query).executeQuery();
				case 2:
					return DBInfo.isConnected.prepareStatement(query).executeUpdate();
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return null;
	}
}