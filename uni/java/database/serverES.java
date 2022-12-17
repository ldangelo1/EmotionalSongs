package database;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

public class serverES extends Application {
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage serverStage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(serverES.class.getResource("serverES-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 430, 150);
		serverStage.setResizable(false);
		serverStage.setTitle("Lab B - Connessione al Database");
		serverStage.setScene(scene);
		serverStage.show();
	}
	
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
	
	public static Connection connectionDB(String IP, String port, String DBName, String user, String pass) {
		String url = "jdbc:postgresql://" + IP + ':' + port + "/" + DBName;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connDB = DriverManager.getConnection(url, user, pass);
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
				case 1 -> {
					return DBInfo.isConnected.prepareStatement(query).executeQuery();
				}
				case 2 -> {
					return DBInfo.isConnected.prepareStatement(query).executeUpdate();
				}
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return null;
	}
	
	public static ResultSet select(String tabella, String tail) throws SQLException {
		String query = "SELECT * FROM \"" + tabella + "\" " + tail;
		
		return (ResultSet) eseguiQuery(query, 1);
	}
	
	public static Integer insert(String tabella, String tail) throws SQLException {
		String query = "INSERT INTO \"" + tabella + "\"";
		switch (tabella) {
			case "Playlist" -> {
				query += "(\"Nome\", \"CF\") ";
				query += tail;
				return (Integer) eseguiQuery(query, 2);
			}
			case "Contiene" -> {
				query += "(\"fk_Playlist\", \"ID\") ";
				query += tail;
				return (Integer) eseguiQuery(query, 2);
			}
		}
		return (Integer) eseguiQuery(query, 2);
	}

	public static Integer remove(String tabella, String tail) throws SQLException {
		String query = "DELETE FROM \"" + tabella + "\"";
		if ("Playlist".equals(tabella)) {
			query += tail;
		}
		return (Integer) eseguiQuery(query, 2);
	}
}