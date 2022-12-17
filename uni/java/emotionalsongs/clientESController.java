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
	private Button addPListBtn, remPListBtn, addSongBtn_song, remSongBtn_song, remSongBtn_acc;
	
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
		
		// TODO: 15/12/2022 Disabilitare campi di ricerca non interessati al momento
		// (es. se ricerca per autore disabilitare gli altri 2 campi
		
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
		
		ResultSet rset = serverES.select("Canzone", query);
		assert rset != null;
		canzoneTable.setItems(queryCanzone(rset));
		rset.close();
	}
	
	/**
	 * Metodo di creazione della playlist
	 */
	@FXML
	private void addPList() throws SQLException {
		serverES.insert("Playlist", "VALUES('" + addPListField.getText() + "', '" + getCF() + "')");
		out.println("Playlist aggiunta con successo!");
		serverES.generaAlert(Alert.AlertType.INFORMATION, "Playlist aggiunta con successo!", "Premi OK per tornare alla pagina precedente");
	}
	
	/**
	 * Metodo di eliminazione della playlist
	 */
	// TODO: 15/12/2022  metodo rimuovi playlist
	@FXML
	private void remPList() {
		//String query = "WHERE '"
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
	
	public void initialize() {
		avvisoLbl.setText(DBInfo.isConnected == null ? "Errore con il database" : "");
		
		ricercaCBox.getItems().addAll(ricercaStrings);
		ricercaCBox.setValue(ricercaStrings[0]);
		
		initTableView(canzoneTable, colTitolo_Canzone, colArtista_Canzone, colAnno_Canzone);
		initTableView(plistCanzoneTable, colTitolo_PList, colArtista_PList, colAnno_PList);
		initPListTable(plistTable, colPList);
		
		userLbl.setText("Necessita di account per sbloccare altre funzioni");
	}
	
	private void initTableView(TableView<Canzone> Table, TableColumn<Canzone, String> colTitolo, TableColumn<Canzone, String> colArtista, TableColumn<Canzone, Integer> colAnno) {
		assert Table != null;
		colTitolo.setCellValueFactory(new PropertyValueFactory<>("Titolo"));
		colArtista.setCellValueFactory(new PropertyValueFactory<>("Artista"));
		colAnno.setCellValueFactory(new PropertyValueFactory<>("Anno"));
	}
	
	private void initPListTable(TableView<Playlist> Table, TableColumn<Playlist, String> colPList) {
		assert Table != null;
		colPList.setCellValueFactory(new PropertyValueFactory<>("Playlist"));
	}
	
	/**
	 * Sfoglio canzone per canzone aggiungendole in lista,
	 * successivamente popolo la tabella con la lista.
	 *
	 * @param rset risultato della query
	 */
	private ObservableList<Canzone> queryCanzone(ResultSet rset) throws SQLException {
		ObservableList<Canzone> data = FXCollections.observableArrayList();
		
		while (rset.next()) {
			Canzone canzone = new Canzone();
			canzone.setTitolo(rset.getString("Titolo"));
			canzone.setArtista(rset.getString("Artista"));
			canzone.setAnno(rset.getInt("Anno"));
			data.add(canzone);
		}
		return data;
	}
	
	/**
	 * Sfoglio playlist per playlist aggiungendole in lista,
	 * successivamente popolo la tabella con la lista.
	 *
	 * @param rset risultato della query
	 */
	private ObservableList<Playlist> queryPList(ResultSet rset) throws SQLException {
		ObservableList<Playlist> data = FXCollections.observableArrayList();
		
		while (rset.next()) {
			Playlist playlist = new Playlist();
			playlist.setNome(rset.getString("Nome"));
			data.add(playlist);
		}
		return data;
	}
	
	public String getCF() {
		return CF;
	}
	
	public void setCF(String cf) {
		CF = cf;
	}
}