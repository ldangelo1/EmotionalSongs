package emotionalsongs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Popup extends Application {
	@Override
	public void start(Stage clientStage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Popup.class.getResource("popup-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 170, 445);
		clientStage.setResizable(false);
		clientStage.setTitle("Lab B - Scelta Playlist");
		clientStage.setScene(scene);
		clientStage.show();
	}
}