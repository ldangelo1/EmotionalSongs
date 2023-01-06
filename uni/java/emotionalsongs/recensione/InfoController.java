package emotionalsongs.recensione;

import database.serverES;
import emotionalsongs.objects.Canzone;
import emotionalsongs.objects.Emozione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoController {
	private static Boolean hide = false;
	private static Canzone canzone;
	
	@FXML
	private Label titoloLbl;
	
	@FXML
	private TableView<Emozione> emozioneTable;
	@FXML
	private TableColumn<Emozione, String> colEmozione, colNote;
	@FXML
	private TableColumn<Emozione, Integer> colUtente, colValutazione;
	
	@FXML
	private Button recensioneBtn;
	
	public Boolean getHide() {
		return hide;
	}
	
	public static void setHide(Boolean hide) {
		InfoController.hide = hide;
	}
	
	public Canzone getCanzone() {
		return canzone;
	}
	
	public static void setCanzone(Canzone canzone) {
		InfoController.canzone = canzone;
	}
	
	@FXML
	private void emozioneList() throws SQLException {
		ObservableList<Emozione> dataEmozione = FXCollections.observableArrayList();
		
		ResultSet rset = serverES.select("Emozione", "\"ID\", \"Tipo\", count(\"Valutazione\") AS utente, avg(\"Valutazione\") AS score"
				, "WHERE \"ID\"='" + canzone.getID() + "' GROUP BY \"ID\", \"Tipo\"");
		
		assert rset != null;
		while (rset.next()) {
			Emozione emozione = new Emozione();
			emozione.setCount(rset.getInt(3));
			emozione.setNome(rset.getString(2));
			emozione.setValutazione(rset.getInt(4));
			//emozione.setNote(rset.getString(5));
			dataEmozione.add(emozione);
		}
		emozioneTable.setItems(dataEmozione);
		rset.close();
	}
	
	public void initialize() throws SQLException {
		recensioneBtn.setVisible(getHide());
		titoloLbl.setText(getCanzone().getTitolo() + " · " + getCanzone().getArtista());
		
		initTable(colEmozione, colNote, colValutazione);
		emozioneList();
	}
	
	private void initTable(TableColumn<Emozione, String> colEmozione, TableColumn<Emozione, String> colNote, TableColumn<Emozione, Integer> colValutazione) {
		colUtente.setCellValueFactory(new PropertyValueFactory<>("Count"));
		colEmozione.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		colNote.setCellValueFactory(new PropertyValueFactory<>("Note"));
		colValutazione.setCellValueFactory(new PropertyValueFactory<>("Valutazione"));
	}
	
	public void recensione() throws IOException {
		Stage stage = new Stage();
		new Recensione().start(stage);
	}
}