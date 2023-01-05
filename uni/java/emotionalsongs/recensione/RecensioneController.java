package emotionalsongs.recensione;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RecensioneController {
	private final Integer[] votoInt = {1, 2, 3, 4, 5};
	@FXML
	private AnchorPane sceneAccount;
	
	@FXML
	private ChoiceBox<Integer> stupCBox, soleCBox, teneCBox, nostCBox, calmCBox, poteCBox, gioiCBox, tensCBox, trisCBox;
	@FXML
	private TextField stupField, soleField, teneField, nostField, calmField, poteField, gioiField, tensField, trisField;
	
	@FXML
	private Button addEmozioneBtn;
	
	public void initialize() {
		for (Object cbox : sceneAccount.getChildren()) {
			if (cbox instanceof ChoiceBox<?>) initCBox((ChoiceBox<Integer>) cbox);
		}
	}
	
	private void initCBox(ChoiceBox<Integer> cbox) {
		cbox.getItems().addAll(votoInt);
		cbox.setValue(votoInt[0]);
	}
}