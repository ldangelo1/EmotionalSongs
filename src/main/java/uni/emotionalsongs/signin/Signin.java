package uni.emotionalsongs.signin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static uni.database.serverES.connect;

public class Signin extends Application {
	@Override
	public void start(Stage stage) throws IOException, ClassNotFoundException {
		FXMLLoader fxmlLoader = new FXMLLoader(Signin.class.getResource("signin-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 320, 240);
		stage.setResizable(false);
		stage.setTitle("Signin");
		stage.setScene(scene);
		stage.show();
	}
}