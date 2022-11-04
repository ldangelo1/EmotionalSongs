package emotionalsongs;

import database.DBInfo;
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
		DBInfo.isConnected = database.serverES.connectionDB();
		FXMLLoader fxmlLoader = new FXMLLoader(clientES.class.getResource("clientES-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 800, 600);
		stage.setResizable(false);
		stage.setTitle("Lab B - Emotional Songs");
		stage.setScene(scene);
		stage.show();
	}
}