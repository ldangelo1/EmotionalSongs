package database;

import emotionalsongs.clientES;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class serverESController {
	@FXML
	private AnchorPane sceneAccount;
	
	@FXML
	private TextField ipField, portField, nameField, userField;
	@FXML
	private PasswordField passField;
	
	public void Connessione() throws IOException {
		DBInfo.isConnected = serverES.connessioneDB(ipField.getText(), portField.getText(), nameField.getText(), userField.getText(), passField.getText());
		if (DBInfo.isConnected != null) {
			client();
			serverES.chiudiStage(sceneAccount);
		}
	}
	
	private void client() throws IOException {
		Stage stage = new Stage();
		new clientES().start(stage);
	}
}