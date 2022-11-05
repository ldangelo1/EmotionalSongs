package emotionalsongs.account;

import database.DBInfo;
import database.serverES;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

public class AccountController {
	private final String[] typeQualifier = {"via", "piazza", "corso"};
	public TextField nameField, surnameField, cfField;
	public TextField addrField, civicField, cityField, provField, capField;
	public TextField userAField, passAField, userRField, passRField;
	public ChoiceBox<String> qualifierCBox;
	public Button accessoBtn, registrazioneBtn;
	
	/**
	 * Metodo per verificare i dati dell'utente per eseguire il login
	 * gestione disable bottoni interfacce
	 * nuova tabella plist affiancata
	 */
	public Boolean checkAccesso(String tail) throws SQLException {
		String query = "SELECT * FROM \"Utente\" " + tail;
		ResultSet rset = serverES.eseguiQuery(query, DBInfo.isConnected);
		
		rset.next();
		Boolean check = rset.getRow() == 1;
		rset.close();
		return check;
	}
	
	@FXML
	public void Accesso() throws Exception {
		String query = "WHERE \"Username\"='" + userAField.getText() + "' " +
				"AND \"Password\"='" + passAField.getText() + "'";
		out.println(checkAccesso(query) ? "Accesso eseguito" : "I dati non corrispondono");
	}
	
	@FXML
	public void Registrazione() throws Exception {
		String query = "WHERE \"CF\"='" + cfField.getText() + "'";
		
		if (!checkAccesso(query)) {
			String queryIns = "INSERT INTO \"Utente\"(username, password, NomeCognome, CF, mail, indirizzo) VALUES(?, ?, ?, ?, ?, ?)";
			// TODO: 05/11/22 registrazione utente
		}
		
	}
	
	public void initialize() {
		qualifierCBox.getItems().addAll(typeQualifier);
		qualifierCBox.setValue(typeQualifier[0]);
	}
}