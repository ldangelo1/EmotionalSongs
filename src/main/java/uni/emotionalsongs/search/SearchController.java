package uni.emotionalsongs.search;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
	private final String[] typeSearch = {"Title", "Author", "Year", "Author and Year"};
	@FXML
	public TextField titleField;
	@FXML
	public TextField authorField;
	@FXML
	public TextField yearField;
	@FXML
	public ChoiceBox<String> choiceBox;
	private String query = "";
	
	public void regexTitle(InputMethodEvent inputMethodEvent) {
	}
	
	public void regexAuthor(InputMethodEvent inputMethodEvent) {
	}
	
	public void regexYear(InputMethodEvent inputMethodEvent) {
	}
	
	public void search(ActionEvent actionEvent) {
		String title = titleField.getText();
		String author = authorField.getText();
		String year = yearField.getText();
		
		switch (choiceBox.getValue()) {
			case "Title" -> this.query = "SELECT * FROM Canzoni WHERE Titolo=" + title;
			case "Author" -> this.query = "SELECT * FROM Canzoni WHERE Autore=" + author;
			case "Year" -> this.query = "SELECT * FROM Canzoni WHERE Anno=" + year;
			case "Author and Year" -> this.query = "SELECT * FROM Canzoni WHERE Autore=" + author + "AND Anno=" + year;
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		choiceBox.getItems().addAll(typeSearch);
	}
}