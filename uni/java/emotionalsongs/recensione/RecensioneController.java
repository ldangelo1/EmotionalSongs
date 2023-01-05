package emotionalsongs.recensione;

import database.serverES;
import emotionalsongs.clientESController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecensioneController extends InfoController {
	private final Integer[] votoInt = {0, 1, 2, 3, 4, 5};
	@FXML
	private AnchorPane sceneAccount;
	
	public void initialize() {
		sceneAccount.getChildren().stream().filter(cbox -> cbox instanceof ChoiceBox<?>).map(cbox -> (ChoiceBox<Integer>) cbox).forEachOrdered(this::initCBox);
	}
	
	private void initCBox(ChoiceBox<Integer> cbox) {
		cbox.getItems().addAll(votoInt);
		cbox.setValue(votoInt[0]);
	}
	
	public void addEmozione() throws SQLException {
		if (estraiValori() > 0)
			serverES.generaAlert(Alert.AlertType.INFORMATION, "Emozioni aggiunte con successo.", "Premi OK per tornare.");
		serverES.chiudiStage(sceneAccount);
	}
	
	private Integer estraiValori() throws SQLException {
		boolean jump = true;
		Integer count = 0;
		String queryTail;
		String queryCond;
		
		String nomeEmozione = "";
		Integer score = 0;
		String note;
		
		for (Node node : sceneAccount.getChildren()) {
			if (node instanceof Button) continue;
			if (node instanceof Label) {
				nomeEmozione = ((Label) node).getText().replaceAll("([\\wà]):.*", "$1");
			}
			if (node instanceof ChoiceBox<?>) {
				score = (Integer) ((ChoiceBox<?>) node).getValue();
				jump = score == 0;
			}
			if (node instanceof TextField && !jump) {
				note = ((TextField) node).getText();
				
				if (checkEmozione(nomeEmozione)) {
					queryTail = "VALUES('" + nomeEmozione + "', '" + clientESController.getCF() + "', '" + getCanzone().getID() + "', " + score + ", '" + note + "')";
					if (serverES.insert("Emozione", queryTail) == 1) count++;
				} else {
					queryTail = "\"Valutazione\"=" + score + ", \"Note\"='" + note + "'";
					queryCond = "\"Tipo\"='" + nomeEmozione + "' AND \"CF\"='" + clientESController.getCF() + "' AND \"ID\"='" + getCanzone().getID() + "'";
					if (serverES.update("Emozione", queryTail, queryCond) == 1) count++;
				}
			}
		}
		return count;
	}
	
	private Boolean checkEmozione(String nomeEmozione) throws SQLException {
		ResultSet rset = serverES.select("Emozione", "WHERE \"Tipo\"='" + nomeEmozione + "' AND \"CF\"='" + clientESController.getCF() +
				"' AND \"ID\"='" + getCanzone().getID() + "'");
		rset.next();
		return rset.getRow() == 0;
	}
}