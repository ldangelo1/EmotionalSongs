package database;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
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
	
	public static Connection connessioneDB() {
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
	
	public static Connection connessioneDB(String IP, String port, String DBName, String user, String pass) {
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
	
	/**
	 * Metodo di chiusura interfaccia
	 *
	 * @param scene l'interfaccia da chiudere
	 */
	public static void chiudiStage(AnchorPane scene) {
		Stage stage = (Stage) scene.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Genera alert adeguato da mostrate al cliente
	 *
	 * @param alertType il tipo di alert da mostrare
	 * @param head      il messaggio da notificare
	 * @param msg       il commento al messaggio
	 */
	public static void generaAlert(Alert.AlertType alertType, String head, String msg) {
		Alert alert = new Alert(alertType);
		alert.setTitle("Informazioni");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		alert.show();
	}
	
	// TODO: 24/12/22 gestire la concorrenza
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
	
	/**
	 * Metodo che gestisce i comandi di "SELECT"
	 *
	 * @param tabella la tabella dove operare
	 * @param tail    la seconda parte della query, generata in base ai dati in ingresso
	 * @return le tuple trovate
	 */
	public static ResultSet select(String tabella, String select, String tail) throws SQLException {
		String query = "SELECT " + select + " FROM \"" + tabella + "\" " + tail;
		return (ResultSet) eseguiQuery(query, 1);
	}
	
	/**
	 * Metodo che gestisce i comandi di "INSERT"
	 *
	 * @param tabella la tabella dove operare
	 * @param tail    la seconda parte della query, generata in base ai dati in ingresso
	 * @return il numero di tuple aggiornate
	 */
	public static Integer insert(String tabella, String tail) throws SQLException {
		String query = "INSERT INTO \"" + tabella + "\"";
		query += tabella.equals("Playlist") ? "(\"Nome\", \"CF\") " + tail : " " + tail;
		
		return (Integer) eseguiQuery(query, 2);
	}
	
	/**
	 * Metodo che gestisce i comandi di "DELETE"
	 *
	 * @param tabella la tabella dove operare
	 * @param tail    la seconda parte della query, generata in base ai dati in ingresso
	 * @return il numero di tuple aggiornate
	 */
	public static Integer delete(String tabella, String tail) throws SQLException {
		String query = "DELETE FROM \"" + tabella + "\" WHERE " + tail;
		return (Integer) eseguiQuery(query, 2);
	}
	
	/**
	 * Metodo che gestisce i comandi di "UPDATE"
	 *
	 * @param tabella   la tabella dove operare
	 * @param tail      la seconda parte della query, generata in base ai dati in ingresso
	 * @param condition specifica la tupla da aggiornare
	 * @return il numero di tuple aggiornate
	 */
	public static Integer update(String tabella, String tail, String condition) throws SQLException {
		String query = "UPDATE \"" + tabella + "\" SET " + tail + " WHERE " + condition;
		return (Integer) eseguiQuery(query, 2);
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
}