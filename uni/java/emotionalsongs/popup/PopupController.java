package emotionalsongs.popup;

import database.serverES;
import emotionalsongs.clientESController;
import emotionalsongs.objects.Canzone;
import emotionalsongs.objects.Playlist;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

import static java.lang.System.out;

public class PopupController extends clientESController {
	private static Canzone CANZONE = null;
	@FXML
	private AnchorPane sceneAccount;
	@FXML
	private TableView<Playlist> plistTable;
	@FXML
	private TableColumn<Playlist, String> colPList;
	
	@Override
	public void initialize() throws SQLException {
		initPListTable(colPList);
		queryPList();
	}
	
	/**
	 * Metodo di selezione della playlist a cui aggiungere la canzone scelta
	 *
	 * @param mouseEvent evento per selezionare la playlist
	 */
	public void qualePlaylist(MouseEvent mouseEvent) throws SQLException {
		Playlist nomePList = plistTable.getSelectionModel().getSelectedItem();
		if (nomePList != null && mouseEvent.getClickCount() == 2) {
			if (serverES.insert("Contiene", "VALUES(" + nomePList.getCont() + ", '" + getCanzone().getID() + "')") == 1) {
				out.println("Canzone \"" + getCanzone().getTitolo() + "\" aggiunta con successo");
				serverES.generaAlert(Alert.AlertType.INFORMATION, "Canzone aggiunta con successo.", "Torna in account e premi sulla tua " + "playlist per visualizzarla");
				serverES.chiudiStage(sceneAccount);
			}
		}
	}
	
	public Canzone getCanzone() {
		return CANZONE;
	}
	
	public static void setCanzone(Canzone canzone) {
		CANZONE = canzone;
	}
}