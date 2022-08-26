package uni.emotionalsongs;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import uni.database.dbQuery;
import uni.emotionalsongs.signin.Signin;
import uni.emotionalsongs.signup.Signup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class clientESController implements Initializable {
	@FXML
	public static TableView<ObservableList> searchTable = new TableView<>();
	private final String[] typeSearch = {"Title", "Author", "Year", "Author and Year"};
	@FXML
	public TextField titleField, authorField, yearField;
	@FXML
	public ChoiceBox<String> choiceBox;
	@FXML
	public Label loggedLabel;
	@FXML
	public Button searchBtn, signinBtn, signupBtn, addPListBtn, remPListBtn, addSongBtn, remSongBtn;
	
	public void search() {
		String title = titleField.getText();
		String author = authorField.getText();
		String year = yearField.getText();
		String choice = choiceBox.getValue();
		String query = "SELECT * FROM Canzoni";
		
		switch (choice) {
			case "Title" -> {
				if (!title.isEmpty()) query += " WHERE Titolo=" + title;
			}
			case "Author" -> {
				if (!title.isEmpty()) query += " WHERE Autore=" + author;
			}
			case "Year" -> {
				if (!title.isEmpty()) query += " WHERE Anno=" + year;
			}
			case "Author and Year" -> {
				if (!title.isEmpty()) query += " WHERE Autore=" + author + " AND Anno=" + year;
			}
		}
		
		dbQuery.cercaBranoMusicale(query);
	}
	
	public void regexTitle(InputMethodEvent inputMethodEvent) {
	}
	
	public void regexAuthor(InputMethodEvent inputMethodEvent) {
	}
	
	public void regexYear(InputMethodEvent inputMethodEvent) {
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		choiceBox.getItems().addAll(typeSearch);
		choiceBox.setValue(typeSearch[0]);
	}
	
	public void signin() throws IOException {
		Stage stage = new Stage();
		new Signin().start(stage);
	}
	
	public void signup() throws IOException {
		Stage stage = new Stage();
		new Signup().start(stage);
	}
}