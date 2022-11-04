package emotionalsongs;

import database.DBInfo;
import database.serverES;
import emotionalsongs.account.Account;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class clientESController {
	private final String[] typeQuery = {"Titolo", "Autore", "Anno", "Autore e Anno"};
	public TableView<ObservableList> songTable, plistTable = new TableView<>();
	public TextField titleField, authorField, yearField;
	public Label userLbl, alertLbl;
	public ChoiceBox<String> queryCBox, plistCBox;
	public Button songBtn, accountBtn;
	public Button addPListBtn, remPListBtn;
	public Button addSongBtn_song, remSongBtn_song, remSongBtn_acc;
	
	public void song() throws Exception {
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
		
		ResultSet rset = serverES.eseguiQuery(query, DBInfo.isConnected);
		printTable(rset);
		rset.close();
	}
	
	private void printTable(ResultSet rset) {
		System.out.println("arrivati");
		// TODO: 29/10/22 qui arriva, metodo di stampa su tabella
	}
	
	/**
	 * Apertura finestra per registrazione/accesso
	 */
	public void account() throws IOException {
		Stage stage = new Stage();
		new Account().start(stage);
	}
	
	/**
	 * Metodo di creazione della playlist
	 */
	public void addPList() {
	}
	
	/**
	 * Metodo di eliminazione della playlist
	 */
	public void remPList() {
	}
	
	/**
	 * Metodo di aggiunta canzone a Playlist
	 */
	public void addSong() {
	}
	
	/**
	 * Metodo di rimozione canzone da Playlist
	 */
	public void remSong() {
	}
	
	public void initialize() {
		alertLbl.setText(DBInfo.isConnected == null ? "Errore con il database" : "");
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