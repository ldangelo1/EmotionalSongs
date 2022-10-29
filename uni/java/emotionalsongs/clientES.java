package emotionalsongs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class clientES extends Application {
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(clientES.class.getResource("clientES-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 800, 600);
		stage.setResizable(false);
		stage.setTitle("Cerca canzoni");
		stage.setScene(scene);
		stage.show();
	}
}