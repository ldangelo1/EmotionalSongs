package uni.emotionalsongs.signup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Signup extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Signup.class.getResource("signup-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 320, 240);
		stage.setTitle("Sign UP");
		stage.setScene(scene);
		stage.show();
	}
}