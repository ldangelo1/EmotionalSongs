package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	
	public static ResultSet eseguiQuery(String query, Connection connDB) throws SQLException {
		try {
			return connDB.prepareStatement(query).executeQuery();
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
}