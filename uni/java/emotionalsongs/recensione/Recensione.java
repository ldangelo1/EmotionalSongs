package emotionalsongs.recensione;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Recensione extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Info.class.getResource("recensione-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 600, 405);
		stage.setResizable(false);
		stage.setTitle("Recensione");
		stage.setScene(scene);
		stage.show();
	}
}