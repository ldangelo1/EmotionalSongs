package emotionalsongs;

import database.dbQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.sql.ResultSet;

public class clientESController_NEW {
	private final String[] typeQuery = {"Titolo", "Autore", "Anno", "Autore e Anno"};
	public TableView<ObservableList> songTable, plistTable = new TableView<>();
	public TextField titleField, authorField, yearField;
	public Label userLbl;
	public ChoiceBox<String> queryCBox, plistCBox;
	public Button songBtn, accountBtn;
	public Button addPListBtn, remPListBtn;
	public Button addSongBtn_song, remSongBtn_song, remSongBtn_acc;
	
	
	public void song() throws Exception {
		String title = titleField.getText();
		String author = authorField.getText();
		String year = yearField.getText();
		String choice = queryCBox.getValue();
		String query = "SELECT * FROM \"Canzoni\" ";
		
		switch (choice) {
			case "Titolo" -> {
				if (!title.isEmpty()) query += "WHERE \"Titolo\"=" + "'" + title + "'";
			}
			case "Autore" -> {
				if (!author.isEmpty()) query += "WHERE \"Autore\"=" + "'" + author + "'";
			}
			case "Anno" -> {
				if (!year.isEmpty()) query += "WHERE \"Anno\"=" + year;
			}
			case "Autore e Anno" -> {
				if (!author.isEmpty() && !year.isEmpty())
					query += "WHERE \"Autore\"=" + "'" + author + "' " + "AND \"Anno\"=" + year;
			}
		}
		
		ResultSet rset = dbQuery.cercaCanzone(query);
		printTable(rset);
	}
	
	private void printTable(ResultSet rset) {
		System.out.println("arrivati");
		
		// TODO: 29/10/22 qui arriva, metodo di stampa su tabella
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
	
	// @todo scrivere un solo metodo che fa la regex su tutti i campi
	public void regexTitle() {
	}
	
	public void regexAuthor() {
	}
	
	public void regexYear() {
	}
}