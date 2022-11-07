package emotionalsongs.account;

import database.serverES;
import emotionalsongs.objects.Indirizzo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

public class AccountController {
	private final String[] typeQualifier = {"via", "piazza", "corso"};
	public TextField nameField, surnameField, cfField, mailField;
	public TextField addrField, civicField, cityField, provField, capField;
	public TextField userAField, passAField, userRField, passRField;
	public ChoiceBox<String> qualifierCBox;
	public Button accessoBtn, registrazioneBtn;
	
	/**
	 * gestione disable bottoni interfacce
	 * nuova tabella plist affiancata
	 */
	
	@FXML
	private void Accesso() throws SQLException {
		String query = "WHERE \"Username\"='" + userAField.getText() + "' " +
				"AND \"Password\"='" + passAField.getText() + "'";
		out.println(checkUtente(query) ? "Accesso eseguito" : "I dati non corrispondono");
	}
	
	@FXML
	private void Registrazione() throws SQLException {
		String query = "WHERE \"CF\"=" + cfField.getText();
		
		if (!checkUtente(query)) {
			Indirizzo addr = new Indirizzo(qualifierCBox.getValue(), addrField.getText(), Integer.parseInt(civicField.getText()), cityField.getText(), provField.getText(), Integer.parseInt(capField.getText()));
			String queryIns = "VALUES(" + Integer.parseInt(cfField.getText()) + ", '" +
					nameField.getText() + " " + surnameField.getText() + "', '" + mailField.getText() + "', '" +
					userRField.getText() + "', '" + passRField.getText() + "', '" +
					addr + "')";
			
			// TODO: 06/11/22 Username unico quindi da gestire
			/// questo l'errore altrimenti
			/// org.postgresql.util.PSQLException: ERROR: duplicate key value violates unique constraint "Username_pk"
			out.println(InsRegistrazione(queryIns) > 0 ? "Registrazione eseguita" : "Errore nella registrazione");
		} else {
			out.println("Utente già registrato");
		}
	}
	
	private Boolean checkUtente(String tail) throws SQLException {
		String query = "SELECT * FROM \"Utente\" " + tail;
		ResultSet rset = (ResultSet) serverES.eseguiQuery(query, 1);
		
		assert rset != null;
		rset.next();
		Boolean check = rset.getRow() == 1;
		rset.close();
		return check;
	}
	
	private Integer InsRegistrazione(String tail) throws SQLException {
		String query = "INSERT INTO \"Utente\"(\"CF\", \"Nome\", \"Email\", \"Username\", \"Password\", \"Indirizzo\") " + tail;
		return (Integer) serverES.eseguiQuery(query, 2);
	}
	
	public void initialize() {
		qualifierCBox.getItems().addAll(typeQualifier);
		qualifierCBox.setValue(typeQualifier[0]);
	}
}