package emotionalsongs;

import database.DBInfo;
import database.serverES;
import emotionalsongs.account.Account;
import emotionalsongs.objects.Canzone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

public class clientESController {
	private final String[] typeQuery = {"Titolo", "Autore", "Anno", "Autore e Anno"};
	private Boolean isLogged;
	@FXML
	private TableView<Canzone> songTable;
	@FXML
	private TableView<ObservableList> plistTable, plistSongTable = new TableView<>();
	@FXML
	private TextField titleField, authorField, yearField;
	@FXML
	private Label userLbl, alertLbl;
	@FXML
	private ChoiceBox<String> queryCBox;
	@FXML
	private Button addPListBtn, remPListBtn, addSongBtn_song, remSongBtn_song, remSongBtn_acc;
	
	@FXML
	private void song() throws Exception {
		String title = titleField.getText();
		String author = authorField.getText();
		String year = yearField.getText();
		String choice = queryCBox.getValue();
		String query = "SELECT * FROM \"Canzone\" ";
		
		switch (choice) {
			case "Titolo" -> {
				if (!title.isEmpty()) query += "WHERE \"Titolo\"=" + "'" + title + "'";
			}
			case "Autore" -> {
				if (!author.isEmpty()) query += "WHERE \"Autore\"=" + "'" + author + "'";
			}
			case "Anno" -> {
				if (!year.isEmpty()) query += "WHERE \"Anno\"=" + year;
			}
			case "Autore e Anno" -> {
				if (!author.isEmpty() && !year.isEmpty())
					query += "WHERE \"Autore\"=" + "'" + author + "' " + "AND \"Anno\"=" + year;
			}
		}
		
		ResultSet rset = (ResultSet) serverES.eseguiQuery(query, 1);
		assert rset != null;
		printTable(rset);
		rset.close();
	}
	
	private void printTable(ResultSet rset) throws SQLException {
		// TODO: 29/10/22 stampa risultati su tabella
		out.println("arrivati");
		
		ObservableList<Canzone> data = FXCollections.observableArrayList();
		
		while (rset.next()) {
			data.add(new Canzone(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
		}
		
		songTable.setItems(data);
	}
	
	/**
	 * Apertura finestra per registrazione/accesso
	 */
	@FXML
	private void account() throws IOException {
		Stage stage = new Stage();
		new Account().start(stage);
	}
	
	/**
	 * Metodo di creazione della playlist
	 */
	@FXML
	private void addPList() {
	}
	
	/**
	 * Metodo di eliminazione della playlist
	 */
	@FXML
	private void remPList() {
	}
	
	/**
	 * Metodo di aggiunta canzone a Playlist
	 */
	@FXML
	private void addSong() {
	}
	
	/**
	 * Metodo di rimozione canzone da Playlist
	 */
	@FXML
	private void remSong() {
	}
	
	public void initialize() {
		alertLbl.setText(DBInfo.isConnected == null ? "Errore con il database" : "");
		userLbl.setText("Necessita di account per sbloccare altre funzioni");
		
		queryCBox.getItems().addAll(typeQuery);
		queryCBox.setValue(typeQuery[0]);
	}
	
	public Boolean getLogged() {
		return isLogged;
	}
	
	public void setLogged(Boolean logged) {
		isLogged = logged;
	}
}