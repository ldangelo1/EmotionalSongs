package uni.emotionalsongs;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class clientESController_NEW {
	public TextField titleField, authorField, yearField;
	public Label userLbl;
	public ChoiceBox<String> queryCBox, plistCBox;
	private final String[] typeQuery = {"Titolo", "Autore", "Anno", "Autore e Anno"};
	public static TableView<ObservableList> songTable, plistTable = new TableView<>();
	public Button songBtn, accountBtn;
	public Button addPListBtn, remPListBtn;
	public Button addSongBtn_song, remSongBtn_song, remSongBtn_acc;
	
	
	public void song(ActionEvent actionEvent) {
	}
	
	public void addSong(ActionEvent actionEvent) {
	}
	
	public void remSong(ActionEvent actionEvent) {
	}
	
	public void account(ActionEvent actionEvent) {
	}
	
	public void addPList(ActionEvent actionEvent) {
	}
	
	public void remPList(ActionEvent actionEvent) {
	}
	
	public void initialize() {
		queryCBox.getItems().addAll(typeQuery);
		queryCBox.setValue(typeQuery[0]);
	}
	
	// @todo Possiamo scrivere un solo metodo che fa la regex su tutti i campi
	public void regexTitle() {
	}
	
	public void regexAuthor() {
	}
	
	public void regexYear() {
	}
}
