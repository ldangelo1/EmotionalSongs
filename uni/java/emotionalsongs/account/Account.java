package emotionalsongs.account;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Account extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Account.class.getResource("account-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 800, 600);
		stage.setResizable(false);
		stage.setTitle("Account - Registrazione | Accesso");
		stage.setScene(scene);
		stage.show();
	}
}