package emotionalsongs;

import database.DBInfo;
import database.serverES;
import emotionalsongs.account.Account;
import emotionalsongs.objects.Canzone;
import emotionalsongs.objects.Playlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class clientESController {
	private final String[] ricercaStrings = {"Titolo", "Autore", "Anno", "Autore e Anno"};
	private final String[] emozioneStrings = {""};
	private Boolean isLogged;
	
	@FXML
	private Label userLbl, avvisoLbl;
	@FXML
	private TextField titoloField, autoreField, annoField;
	
	@FXML
	private TableView<Canzone> canzoneTable, plistCanzoneTable;
	@FXML
	private TableView<Playlist> plistTable;
	@FXML
	private TableColumn<Canzone, Integer> colAnno;
	@FXML
	private TableColumn<Canzone, String> colTitolo, colAutore, colAlbum, colEmozione;
	
	@FXML
	private ChoiceBox<String> ricercaCBox;
	
	@FXML
	private Button addPListBtn, remPListBtn, addSongBtn_song, remSongBtn_song, remSongBtn_acc;
	
	@FXML
	private void account() throws IOException {
		Stage stage = new Stage();
		new Account().start(stage);
	}
	
	@FXML
	private void song() throws Exception {
		String titolo = titoloField.getText();
		String autore = autoreField.getText();
		String anno = annoField.getText();
		String ricerca = ricercaCBox.getValue();
		String query = "SELECT * FROM \"Canzone\" ";
		
		switch (ricerca) {
			case "Titolo" -> {
				if (!titolo.isEmpty()) query += "WHERE \"Titolo\"=" + "'" + titolo + "'";
			}
			case "Autore" -> {
				if (!autore.isEmpty()) query += "WHERE \"Autore\"=" + "'" + autore + "'";
			}
			case "Anno" -> {
				if (!anno.isEmpty()) query += "WHERE \"Anno\"=" + anno;
			}
			case "Autore e Anno" -> {
				if (!autore.isEmpty() && !anno.isEmpty())
					query += "WHERE \"Autore\"=" + "'" + autore + "' " + "AND \"Anno\"=" + anno;
			}
		}
		
		ResultSet rset = (ResultSet) serverES.eseguiQuery(query, 1);
		assert rset != null;
		printCanzoneTable(rset);
		rset.close();
	}
	
	/**
	 * Sfoglio canzone per canzone aggiungendole in lista,
	 * successivamente popolo canzoneTable con la lista.
	 *
	 * @param rset risultato della query
	 * @throws SQLException rset.next() genera l'eccezione
	 */
	private void printCanzoneTable(ResultSet rset) throws SQLException {
		ObservableList<Canzone> data = FXCollections.observableArrayList();
		
		while (rset.next()) {
			Canzone canzone = new Canzone();
			canzone.setAnno(rset.getInt("Anno"));
			canzone.setID(rset.getString("ID"));
			canzone.setAutore(rset.getString("Autore"));
			canzone.setTitolo(rset.getString("Titolo"));
			canzone.setAlbum(rset.getString("Album"));
			data.add(canzone);
		}
		
		canzoneTable.setItems(data);
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
		avvisoLbl.setText(DBInfo.isConnected == null ? "Errore con il database" : "");
		
		ricercaCBox.getItems().addAll(ricercaStrings);
		ricercaCBox.setValue(ricercaStrings[0]);
		
		assert canzoneTable != null;
		colAnno.setCellValueFactory(new PropertyValueFactory<>("Anno"));
		colEmozione.setCellValueFactory(new PropertyValueFactory<>("ID"));
		colAutore.setCellValueFactory(new PropertyValueFactory<>("Autore"));
		colTitolo.setCellValueFactory(new PropertyValueFactory<>("Titolo"));
		colAlbum.setCellValueFactory(new PropertyValueFactory<>("Album"));
		
		userLbl.setText("Necessita di account per sbloccare altre funzioni");
	}
	
	public Boolean getLogged() {
		return isLogged;
	}
	
	public void setLogged(Boolean logged) {
		isLogged = logged;
	}
}