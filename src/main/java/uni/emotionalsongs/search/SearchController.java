package uni.emotionalsongs.search;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import uni.database.dbQuery;

import java.net.URL;
import java.sql.SQLException;
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
	
	public void regexTitle(InputMethodEvent inputMethodEvent) {
	}
	
	public void regexAuthor(InputMethodEvent inputMethodEvent) {
	}
	
	public void regexYear(InputMethodEvent inputMethodEvent) {
	}
	
	public void search(ActionEvent actionEvent) throws SQLException {
		String title = titleField.getText();
		String author = authorField.getText();
		String year = yearField.getText();
		
		String query = "SELECT * FROM Canzoni";
		switch (choiceBox.getValue()) {
			case "Title" -> query += " WHERE Titolo=" + title;
			case "Author" -> query += " WHERE Autore=" + author;
			case "Year" -> query += " WHERE Anno=" + year;
			case "Author and Year" -> query += " WHERE Autore=" + author + " AND Anno=" + year;
		}
		
		dbQuery.searchSongs(query);
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		choiceBox.getItems().addAll(typeSearch);
	}
}