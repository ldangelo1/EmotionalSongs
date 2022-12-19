package emotionalsongs;

import database.DBInfo;
import database.serverES;
import emotionalsongs.account.Account;
import emotionalsongs.objects.Canzone;
import emotionalsongs.objects.Playlist;
import emotionalsongs.recensione.Recensione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

public class clientESController {
	private static String CF;
	private final String[] ricercaStrings = {"Titolo", "Artista", "Anno", "Artista e Anno"};
	@FXML
	private Label userLbl, avvisoLbl;
	@FXML
	private TextField titoloField, artistaField, annoField, addPListField;
	
	@FXML
	private TableView<Canzone> canzoneTable, plistCanzoneTable;
	@FXML
	private TableView<Playlist> plistTable;
	@FXML
	private TableColumn<Canzone, Integer> colAnno_Canzone, colAnno_PList;
	@FXML
	private TableColumn<Canzone, String> colTitolo_Canzone, colArtista_Canzone, colTitolo_PList, colArtista_PList;
	@FXML
	private TableColumn<Playlist, String> colPList;
	
	@FXML
	private ChoiceBox<String> ricercaCBox;
	
	@FXML
	private Button addPListBtn, remPListBtn, addSongBtn, remSongBtn;
	
	@FXML
	private void account() throws IOException {
		Stage stage = new Stage();
		new Account().start(stage);
	}
	
	@FXML
	private void recensione() throws IOException {
		Stage stage = new Stage();
		new Recensione().start(stage);
	}
	
	@FXML
	private void song() throws Exception {
		String titolo = titoloField.getText();
		String artista = artistaField.getText();
		String anno = annoField.getText();
		String ricerca = ricercaCBox.getValue();
		String query = "";
		
		switch (ricerca) {
			case "Titolo" -> {
				if (!titolo.isEmpty()) query += "WHERE \"Titolo\"=" + "'" + titolo + "'";
			}
			case "Artista" -> {
				if (!artista.isEmpty()) query += "WHERE \"Artista\"=" + "'" + artista + "'";
			}
			case "Anno" -> {
				if (!anno.isEmpty()) query += "WHERE \"Anno\"=" + anno;
			}
			case "Artista e Anno" -> {
				if (!artista.isEmpty() && !anno.isEmpty())
					query += "WHERE \"Artista\"=" + "'" + artista + "' " + "AND \"Anno\"=" + anno;
			}
		}
		
		queryCanzone(query);
	}
	
	/**
	 * Metodo di creazione della playlist
	 */
	@FXML
	private void addPList() throws SQLException {
		String queryIns = "VALUES('" + addPListField.getText() + "', '" + getCF() + "')";
		if (serverES.insert("Playlist", queryIns) == 1) {
			out.println("Playlist aggiunta con successo");
			queryPList();
		}
	}
	
	/**
	 * Metodo di eliminazione della playlist
	 */
	// TODO: 15/12/2022  metodo rimuovi playlist
	@FXML
	private void remPList() throws SQLException {
		String queryIns = "\"CF\"='" + getCF() + "' AND \"Nome\"='" + plistTable.getItems() + "'";
		if (serverES.delete("Playlist", queryIns) == 1) {
			out.println("Playlist rimossa con successo");
			queryPList();
		}
	}
	
	/**
	 * Metodo di aggiunta canzone a Playlist
	 */
	// TODO: 15/12/2022  metodo aggiungi canzone
	@FXML
	private void addSong() {
	}
	
	/**
	 * Metodo di rimozione canzone da Playlist
	 */
	@FXML
	private void remSong() {
	}
	
	public void gestione() {
		funzioni();
		
	}
	
	public void initialize() {
		avvisoLbl.setText(DBInfo.isConnected == null ? "Errore con il database" : "");
		
		ricercaCBox.getItems().addAll(ricercaStrings);
		ricercaCBox.setValue(ricercaStrings[0]);
		
		initCanzoneTable(canzoneTable, colTitolo_Canzone, colArtista_Canzone, colAnno_Canzone);
		initCanzoneTable(plistCanzoneTable, colTitolo_PList, colArtista_PList, colAnno_PList);
		initPListTable(plistTable, colPList);
		
		funzioni();
	}
	
	private void initCanzoneTable(TableView<Canzone> Table, TableColumn<Canzone, String> colTitolo, TableColumn<Canzone, String> colArtista, TableColumn<Canzone, Integer> colAnno) {
		assert Table != null;
		colTitolo.setCellValueFactory(new PropertyValueFactory<>("Titolo"));
		colArtista.setCellValueFactory(new PropertyValueFactory<>("Artista"));
		colAnno.setCellValueFactory(new PropertyValueFactory<>("Anno"));
	}
	
	private void initPListTable(TableView<Playlist> Table, TableColumn<Playlist, String> colPList) {
		assert Table != null;
		colPList.setCellValueFactory(new PropertyValueFactory<>("Playlist"));
	}
	
	private void funzioni() {
		ricercaFieldCBox();
		logFunzioni(getCF() == null);
	}
	
	private void ricercaFieldCBox() {
		switch (ricercaCBox.getValue()) {
			case "Titolo" -> {
				titoloField.setDisable(false);
				artistaField.setDisable(true);
				annoField.setDisable(true);
			}
			case "Artista" -> {
				titoloField.setDisable(true);
				artistaField.setDisable(false);
				annoField.setDisable(true);
			}
			case "Anno" -> {
				titoloField.setDisable(true);
				artistaField.setDisable(true);
				annoField.setDisable(false);
			}
			case "Artista e Anno" -> {
				titoloField.setDisable(true);
				artistaField.setDisable(false);
				annoField.setDisable(false);
			}
		}
	}
	
	private void logFunzioni(Boolean log) {
		userLbl.setText(log ? "Necessita di account per sbloccare altre funzioni" : "Felice di rivederti" + getCF());
		
		addPListBtn.setDisable(log);
		remPListBtn.setDisable(log);
		addSongBtn.setDisable(log);
		remSongBtn.setDisable(log);
	}
	
	/**
	 * Sfoglio canzone per canzone aggiungendole in lista,
	 * successivamente popolo la tabella con la lista.
	 *
	 * @param tail seconda parte della query costruita dai dati in ingresso
	 */
	private void queryCanzone(String tail) throws SQLException {
		ResultSet rset = serverES.select("Canzone", tail);
		ObservableList<Canzone> data = FXCollections.observableArrayList();
		
		assert rset != null;
		while (rset.next()) {
			Canzone canzone = new Canzone();
			canzone.setTitolo(rset.getString("Titolo"));
			canzone.setArtista(rset.getString("Artista"));
			canzone.setAnno(rset.getInt("Anno"));
			data.add(canzone);
		}
		canzoneTable.setItems(data);
		rset.close();
	}
	
	/**
	 * Sfoglio playlist per playlist aggiungendole in lista,
	 * successivamente popolo la tabella con la lista.
	 */
	private void queryPList() throws SQLException {
		ResultSet rset = serverES.select("Playlist", "WHERE \"CF\"='" + getCF() + "'");
		ObservableList<Playlist> data = FXCollections.observableArrayList();
		
		assert rset != null;
		while (rset.next()) {
			Playlist playlist = new Playlist();
			playlist.setNome(rset.getString("Nome"));
			data.add(playlist);
		}
		plistTable.setItems(data);
		rset.close();
	}
	
	public String getCF() {
		return CF;
	}
	
	public void setCF(String cf) {
		CF = cf;
	}
}