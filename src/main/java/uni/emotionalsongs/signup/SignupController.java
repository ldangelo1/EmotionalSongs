package uni.emotionalsongs.signup;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SignupController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}