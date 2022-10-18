package uni.emotionalsongs;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

	//ArrayList per la visualizzazione dei brani dalla query
	public static TableView<ObservableList> searchTable = new TableView<>();
	@FXML
	//tipo di query da eseguire
	private final String[] typeSearch = {"Title", "Author", "Year", "Author and Year"};
	@FXML
	public TextField titleField, authorField, yearField;
	@FXML
	public ChoiceBox<String> choiceBox;
	@FXML
	public Label loggedLabel;
	@FXML
	public Button searchBtn, signinBtn, signupBtn, addPListBtn, remPListBtn, addSongBtn, remSongBtn;

	/**
	 * Metodo per selezionare il tipo di Query da eseguire
	 * Viene in seguito restituita la stringa della query da eseguire sulla classe ServerES
	 * */
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

	/** Porta alla finestra per il login
	 */

	public void signin() throws IOException, ClassNotFoundException {
		Stage stage = new Stage();
		new Signin().start(stage);
	}

	/** Porta alla finestra per la registrazione
	 */
	public void signup() throws IOException {
		Stage stage = new Stage();
		new Signup().start(stage);
	}

	/** Metodo per creare Playlist
	 */
	public void addPList(ActionEvent actionEvent) {

	}

	/** Metodo per eliminare Playlist
	 */

	public void remPList(ActionEvent actionEvent) {

	}

	/** Metodo per aggiungere canzoni alle playlist
	 */
	public void addSong(ActionEvent actionEvent) {

	}

	/** Metodo per rimuovere canzoni dalle playlist
	 */
	public void remSong(ActionEvent actionEvent) {

	}

	//Possiamo scrivere un solo metodo che fa la regex su tutti i campi
	public void regexTitle(InputMethodEvent inputMethodEvent) { }
	public void regexAuthor(InputMethodEvent inputMethodEvent) { }
	public void regexYear(InputMethodEvent inputMethodEvent) { }
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		choiceBox.getItems().addAll(typeSearch);
		choiceBox.setValue(typeSearch[0]);
	}
}