package uni.emotionalsongs.search;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uni.database.serverES;

import java.io.IOException;

public class Search extends Application {
	public static void main(String[] args) throws ClassNotFoundException {
		serverES.connection(serverES.getdbUser(), serverES.getdbPsw(), serverES.getdbName(), serverES.getdbIP(), serverES.getdbPort());
		launch();
	}
	
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Search.class.getResource("search-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 800, 600);
		stage.setTitle("Search");
		stage.setScene(scene);
		stage.show();
	}
}