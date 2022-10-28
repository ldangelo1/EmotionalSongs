package uni.emotionalsongs;

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
		FXMLLoader fxmlLoader = new FXMLLoader(clientES.class.getResource("clientES-view_NEW.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 800, 600);
		stage.setResizable(false);
		stage.setTitle("Search");
		stage.setScene(scene);
		stage.show();
	}
}