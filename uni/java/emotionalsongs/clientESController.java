package emotionalsongs;

import database.DBInfo;
import database.serverES;
import emotionalsongs.account.Account;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

import static java.lang.System.out;

public class clientESController {
	@FXML
	private static Label userLbl;
	@FXML
	private static Button addPListBtn, remPListBtn, addSongBtn_song, remSongBtn_song, remSongBtn_acc;
	@FXML
	private final String[] typeQuery = {"Titolo", "Autore", "Anno", "Autore e Anno"};
	@FXML
	private TableView<ObservableList> songTable, plistTable = new TableView<>();
	@FXML
	private TextField titleField, authorField, yearField;
	@FXML
	private Label alertLbl;
	@FXML
	private ChoiceBox<String> queryCBox, plistCBox;
	
	// TODO: 07/11/22 Sblocca funzionalità dopo il login
	public static void enableAccesso(String user) {
		userLbl.setText("Ciao, " + user);
		addPListBtn.setDisable(false);
		remPListBtn.setDisable(false);
		addSongBtn_song.setDisable(false);
		remSongBtn_song.setDisable(false);
		remSongBtn_acc.setDisable(false);
	}
	
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
	
	private void printTable(ResultSet rset) {
		out.println("arrivati");
		// TODO: 29/10/22 stampa risultati su tabella
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
		
		userLbl = new Label("Necessario accedere per sbloccare altre funzionalità");
		
		addPListBtn = remPListBtn = addSongBtn_song = remSongBtn_song = remSongBtn_acc = new Button();
		addPListBtn.setDisable(true);
		remPListBtn.setDisable(true);
		addSongBtn_song.setDisable(true);
		remSongBtn_song.setDisable(true);
		remSongBtn_acc.setDisable(true);
		
		queryCBox.getItems().addAll(typeQuery);
		queryCBox.setValue(typeQuery[0]);
	}
	
	// TODO: 29/10/22 scrivere un solo metodo che fa la regex su tutti i campi
	public void regexTitle() {
	}
	
	public void regexAuthor() {
	}
	
	public void regexYear() {
	}
}