package uni.emotionalsongs.signin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Signin extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Signin.class.getResource("signin-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 320, 240);
		stage.setTitle("Sign IN");
		stage.setScene(scene);
		stage.show();
	}
}