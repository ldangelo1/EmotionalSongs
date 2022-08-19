package uni.emotionalsongs.signin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SigninController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}