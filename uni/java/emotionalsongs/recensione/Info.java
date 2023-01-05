package emotionalsongs.recensione;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Info extends Application {
	
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Info.class.getResource("info-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 600, 310);
		stage.setResizable(false);
		stage.setTitle("Info");
		stage.setScene(scene);
		stage.show();
	}
}