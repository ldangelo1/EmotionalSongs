package emotionalsongs.account;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.Statement;

import static database.serverES.connDB;

public class AccountController {
	public TextField userField, passField;
	public Button accediBtn, registratiBtn;
	
	/**
	 * Metodo per verificare i dati dell'utente per eseguire il login
	 * TODO: da rivedere/riprendere la vecchia query, scusa
	 * comunque è da spostare in dbQuery, abbiamo creato apposta il file.
	 */
	public void checkLogin(String user, String pass) throws Exception {
		Statement st = connDB().createStatement();
		ResultSet loginQuery = st.executeQuery("SELECT username, password FROM \"Utente\" WHERE username LIKE '" + user + "'");
		
		if (loginQuery.next()) {
			if (user.equals(loginQuery.getString(1)) && pass.equals(loginQuery.getString(2))) {
				System.out.println("Dati confermati nel DB");
			} else {
				System.out.println("Errore, i dati inseriti sono errati e non corrispondono a quelli nel DB");
			}
		} else {
			System.out.println("utente non registrato !!");
		}
		st.close();
		loginQuery.close();
	}
	
	@FXML
	public void inButtonClick() throws Exception {
		String user = userField.getText();
		String pass = passField.getText();
		checkLogin(user, pass);
	}
}