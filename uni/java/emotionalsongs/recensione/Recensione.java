package emotionalsongs.recensione;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Recensione extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Recensione.class.getResource("Recensione-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 600, 310);
		stage.setResizable(false);
		stage.setTitle("Account - Recensione");
		stage.setScene(scene);
		stage.show();
	}
}