package emotionalsongs;

import database.DBInfo;
import database.serverES;
import emotionalsongs.account.Account;
import emotionalsongs.objects.Canzone;
import emotionalsongs.objects.Playlist;
import emotionalsongs.objects.Regex;
import emotionalsongs.recensione.Info;
import emotionalsongs.recensione.InfoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.out;

public class clientESController {
	private static String CF;
	private static Playlist PLIST;
	private final ObservableList<Canzone> dataCanzone = FXCollections.observableArrayList();
	private final String[] ricercaStrings = {"Titolo", "Artista", "Anno", "Artista e Anno"};
	@FXML
	private AnchorPane sceneAccount;
	@FXML
	private Tab Account;
	
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
	private Button accountBtn, addPListBtn, remPListBtn, addSongBtn, remSongBtn;
	
	public static Playlist getPlaylist() {
		return PLIST;
	}
	
	public static void setPlaylist(Playlist playlist) {
		PLIST = playlist;
	}
	
	public static String getCF() {
		return CF;
	}
	
	public void setCF(String cf) {
		CF = cf;
	}
	
	private void popup() throws IOException {
		Stage stage = new Stage();
		new Popup().start(stage);
	}
	
	@FXML
	private void account() throws IOException {
		if (DBInfo.isConnected == null) return;
		
		if (getCF() == null) {
			Stage stage = new Stage();
			new Account().start(stage);
		} else {
			CF = null;
			plistTable.getItems().clear();
			plistCanzoneTable.getItems().clear();
		}
		
	}
	
	@FXML
	private void info(MouseEvent mouseEvent) throws IOException {
		TableView<Canzone> table = (TableView<Canzone>) mouseEvent.getSource();
		Canzone nomeCanzone = table.getSelectionModel().getSelectedItem();
		
		if (mouseEvent.getClickCount() == 2 && nomeCanzone != null) {
			InfoController.setHide(table.getId().equals("plistCanzoneTable"));
			InfoController.setCanzone(nomeCanzone);
			Stage stage = new Stage();
			new Info().start(stage);
		}
	}
	
	/**
	 * Metodo di ricerca delle canzoni:
	 * <br>
	 * Tramite i dati inseriti dall'utente compongo la query, da eseguire poi e
	 * le canzoni trovate vengono gestite in queryCanzone.
	 */
	@FXML
	private void song() throws Exception {
		AtomicReference<Boolean> validSearch = new AtomicReference<>(true);
		
		sceneAccount.getChildren().stream().filter(label -> label instanceof Label).forEachOrdered(label -> ((Label) label).getChildrenUnmodifiable().stream().filter(field -> field instanceof TextField).forEachOrdered(field -> {
			if (field.getStyle().contains("red")) validSearch.set(false);
		}));
		
		String titolo = titoloField.getText();
		String artista = artistaField.getText();
		String anno = annoField.getText();
		String ricerca = ricercaCBox.getValue();
		String query = "";
		
		switch (ricerca) {
			case "Titolo" -> {
				if (!titolo.isEmpty()) query += "WHERE \"Titolo\"=" + "'" + titolo + "'";
				else {
					serverES.generaAlert(Alert.AlertType.INFORMATION, "Il campo selezionato risulta vuoto.", "Premere OK per riprovare.");
					return;
				}
			}
			case "Artista" -> {
				if (!artista.isEmpty()) query += "WHERE \"Artista\"=" + "'" + artista + "'";
				else {
					serverES.generaAlert(Alert.AlertType.INFORMATION, "Il campo selezionato risulta vuoto.", "Premere OK per riprovare.");
					return;
				}
			}
			case "Anno" -> {
				if (!anno.isEmpty()) query += "WHERE \"Anno\"=" + anno;
				else {
					serverES.generaAlert(Alert.AlertType.INFORMATION, "Il campo selezionato risulta vuoto.", "Premere OK per riprovare.");
					return;
				}
			}
			case "Artista e Anno" -> {
				if (!artista.isEmpty() && !anno.isEmpty()) query += "WHERE \"Artista\"=" + "'" + artista + "' " + "AND \"Anno\"=" + anno;
				else {
					serverES.generaAlert(Alert.AlertType.INFORMATION, "Entrambi o uno dei due campi risultano vuoti.", "Premere OK per riprovare.");
					return;
				}
			}
		}
		dataCanzone.clear();
		if (validSearch.get()) queryCanzone(canzoneTable, query);
		else serverES.generaAlert(Alert.AlertType.WARNING, "La formattazione dei campi è errata.", "Premere OK per riprovare.");
	}
	
	/**
	 * Metodo di creazione della playlist
	 */
	@FXML
	private void addPList() throws SQLException {
		String nomePList = addPListField.getText();
		if (!nomePList.isEmpty()) {
			String queryIns = "VALUES('" + nomePList + "', '" + getCF() + "')";
			if (serverES.insert("Playlist", queryIns) == 1) {
				out.println("Playlist \"" + nomePList + "\" aggiunta con successo");
				addPListField.clear();
				queryPList();
			}
		} else serverES.generaAlert(Alert.AlertType.INFORMATION, "Il campo a fianco risulta vuoto.", "Premere OK per riprovare.");
	}
	
	/**
	 * Metodo di eliminazione della playlist
	 */
	@FXML
	private void remPList() throws SQLException {
		Playlist nomePList = plistTable.getSelectionModel().getSelectedItem();
		if (nomePList != null) {
			String queryIns = "\"CF\"='" + getCF() + "' AND \"Contatore\"='" + nomePList.getCont() + "'";
			if (serverES.delete("Playlist", queryIns) == 1) {
				out.println("Playlist \"" + nomePList.getNome() + "\" rimossa con successo");
				plistCanzoneTable.getItems().clear();
				queryPList();
			}
		} else serverES.generaAlert(Alert.AlertType.INFORMATION, "Non è stata selezionata alcuna playlist.", "Premere OK per riprovare.");
	}
	
	/**
	 * Metodo di aggiunta canzone a Playlist
	 */
	@FXML
	private void addSong() throws IOException {
		Canzone nomeCanzone = canzoneTable.getSelectionModel().getSelectedItem();
		if (nomeCanzone != null) {
			PopupController.setCanzone(nomeCanzone);
			popup();
		} else serverES.generaAlert(Alert.AlertType.INFORMATION, "Non è stata selezionata alcuna canzone.", "Premere OK per riprovare.");
	}
	
	/**
	 * Metodo di rimozione canzone da Playlist
	 */
	@FXML
	private void remSong() throws SQLException {
		Canzone nomeCanzone = plistCanzoneTable.getSelectionModel().getSelectedItem();
		if (nomeCanzone != null) {
			if (serverES.delete("Contiene", "\"fk_Playlist\"=" + getPlaylist().getCont() + " AND \"ID\"='" + nomeCanzone.getID() + "'") == 1) {
				out.println("Canzone \"" + nomeCanzone.getTitolo() + "\" rimossa con successo");
				qualeCanzone();
			}
		} else serverES.generaAlert(Alert.AlertType.INFORMATION, "Non è stata selezionata alcuna canzone.", "Premere OK per riprovare.");
	}
	
	public void initialize() throws SQLException {
		avvisoLbl.setText(DBInfo.isConnected == null ? "Errore con il database" : "");
		
		ricercaCBox.getItems().addAll(ricercaStrings);
		ricercaCBox.setValue(ricercaStrings[0]);
		
		logFunzioni();
		ricercaFieldCBox();
		
		initCanzoneTable(colTitolo_Canzone, colArtista_Canzone, colAnno_Canzone);
		initCanzoneTable(colTitolo_PList, colArtista_PList, colAnno_PList);
		initPListTable(colPList);
	}
	
	private void initCanzoneTable(TableColumn<Canzone, String> colTitolo, TableColumn<Canzone, String> colArtista, TableColumn<Canzone, Integer> colAnno) {
		colTitolo.setCellValueFactory(new PropertyValueFactory<>("Titolo"));
		colArtista.setCellValueFactory(new PropertyValueFactory<>("Artista"));
		colAnno.setCellValueFactory(new PropertyValueFactory<>("Anno"));
	}
	
	protected void initPListTable(TableColumn<Playlist, String> colPList) {
		colPList.setCellValueFactory(new PropertyValueFactory<>("Nome"));
	}
	
	/**
	 * Metodo che gestisce la limitazione delle funzioni
	 * <br>
	 * LISTENER
	 * onMouseMoved: TabPane
	 * onMouseEntered: canzoneTable, plistTable, plistCanzoneTable
	 */
	@FXML
	private void logFunzioni() throws SQLException {
		boolean log = getCF() == null;
		ResultSet rset = null;
		
		if (!log) {
			rset = serverES.select("Utente", "WHERE \"CF\"='" + getCF() + "'");
			rset.next();
		}
		userLbl.setText(log ? "Necessita di account per sbloccare altre funzioni" : "Felice di rivederti, " + rset.getString("Username").toUpperCase());
		
		accountBtn.setText(log ? "Accedi / Registrati" : "Esci");
		addPListField.setDisable(log);
		addPListBtn.setDisable(log);
		remPListBtn.setDisable(log);
		addSongBtn.setDisable(log);
		remSongBtn.setDisable(log);
	}
	
	/**
	 * Metodo di oscuramento dei campi non necessari,
	 * selezionando la ricerca desiderata
	 * <br>
	 * LISTENER
	 * onMouseMoved: AnchorPane
	 */
	@FXML
	private void ricercaFieldCBox() {
		if (ricercaCBox.isFocused())
			sceneAccount.getChildren().stream().filter(label -> label instanceof Label).forEachOrdered(label -> ((Label) label).getChildrenUnmodifiable().stream().filter(field -> field instanceof TextField).forEachOrdered(field -> whatColor((TextField) field, "white")));
		
		switch (ricercaCBox.getValue()) {
			case "Titolo" -> {
				artistaField.clear();
				annoField.clear();
				fieldCBox(false, true, true);
			}
			case "Artista" -> {
				titoloField.clear();
				annoField.clear();
				fieldCBox(true, false, true);
			}
			case "Anno" -> {
				titoloField.clear();
				artistaField.clear();
				fieldCBox(true, true, false);
			}
			case "Artista e Anno" -> {
				titoloField.clear();
				fieldCBox(true, false, false);
			}
		}
	}
	
	private void fieldCBox(Boolean bTitolo, Boolean bArtista, Boolean bAnno) {
		titoloField.setDisable(bTitolo);
		artistaField.setDisable(bArtista);
		annoField.setDisable(bAnno);
	}
	
	/**
	 * LISTENER
	 * onSelectionChanged: Account
	 */
	@FXML
	private void aggiornaTable() throws Exception {
		if (!Account.isSelected() && !canzoneTable.getItems().isEmpty()) song();
		else plistCanzoneTable.getItems().clear();
	}
	
	/**
	 * Metodo di estrapolazione degli ID di Canzone:
	 * <br>
	 * Identifico il nome della Playlist a cui fare riferimento e ne ottengo l'id,
	 * ottenendo poi le canzoni contenute in essa e le gestisco in queryCanzone.
	 */
	@FXML
	private void qualeCanzone() throws SQLException {
		dataCanzone.clear();
		Playlist nomePList = plistTable.getSelectionModel().getSelectedItem();
		
		if (nomePList != null) {
			setPlaylist(nomePList);
			ResultSet rsetContiene = serverES.select("Contiene", "WHERE \"fk_Playlist\"=" + nomePList.getCont());
			while (rsetContiene.next()) {
				queryCanzone(plistCanzoneTable, "WHERE \"ID\"='" + rsetContiene.getString(2) + "'");
			}
		}
	}
	
	/**
	 * Metodo di popolazione per tabella contenente l'oggetto Canzone:
	 * <br>
	 * Sfoglio canzone per canzone aggiungendole in lista,
	 * successivamente popolo la tabella con la lista.
	 *
	 * @param tail seconda parte della query costruita dai dati in ingresso
	 */
	private void queryCanzone(TableView<Canzone> table, String tail) throws SQLException {
		if (DBInfo.isConnected == null) return;
		
		ResultSet rset = serverES.select("Canzone", tail);
		if (!rset.next())
			serverES.generaAlert(Alert.AlertType.INFORMATION, "Nessuna canzone trovata dalla ricerca.", "Premere OK per riprovare.");
		else {
			do {
				Canzone canzone = new Canzone();
				canzone.setTitolo(rset.getString("Titolo"));
				canzone.setArtista(rset.getString("Artista"));
				canzone.setAnno(rset.getInt("Anno"));
				canzone.setID(rset.getString("ID"));
				dataCanzone.add(canzone);
			} while (rset.next());
		}
		table.setItems(dataCanzone);
		rset.close();
	}
	
	/**
	 * Metodo di popolazione per tabella contenente l'oggetto Playlist:
	 * <br>
	 * Sfoglio playlist per playlist aggiungendole in lista,
	 * successivamente popolo la tabella con la lista.
	 */
	@FXML
	protected void queryPList() throws SQLException {
		if (DBInfo.isConnected == null) return;
		
		ResultSet rset = serverES.select("Playlist", "WHERE \"CF\"='" + getCF() + "'");
		ObservableList<Playlist> data = FXCollections.observableArrayList();
		
		assert rset != null;
		while (rset.next()) {
			Playlist playlist = new Playlist();
			playlist.setNome(rset.getString("Nome"));
			playlist.setCont(rset.getInt("Contatore"));
			data.add(playlist);
		}
		plistTable.setItems(data);
		rset.close();
	}
	
	protected void whatColor(TextField field, String colorBG) {
		if (field.getText().isBlank()) colorBG = "white";
		String property = "-fx-background-color: derive(" + colorBG + ", 99%);";
		field.setStyle(property);
	}
	
	@FXML
	private void inRegex(KeyEvent keyEvent) {
		TextField field = (TextField) keyEvent.getSource();
		whatColor(field, Regex.regexStandard(field.getText()) ? "#007160" : "red");
	}
	
	@FXML
	private void inRegexNum(KeyEvent keyEvent) {
		TextField field = (TextField) keyEvent.getSource();
		switch (field.getId()) {
			case "civicField" -> whatColor(field, Regex.regexNumber(field.getText(), "1,3") ? "#007160" : "red");
			case "capField" -> whatColor(field, Regex.regexNumber(field.getText(), "5") ? "#007160" : "red");
			case "annoField" -> whatColor(field, Regex.regexNumber(field.getText(), "4") ? "#007160" : "red");
		}
	}
}