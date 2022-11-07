package emotionalsongs.account;

import database.serverES;
import emotionalsongs.clientESController;
import emotionalsongs.objects.Indirizzo;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

public class AccountController {
	private final String[] typeQualifier = {"via", "piazza", "corso"};
	@FXML
	private TextField nameField, surnameField, cfField, mailField;
	@FXML
	private TextField addrField, civicField, cityField, provField, capField;
	@FXML
	private TextField userAField, passAField, userRField, passRField;
	@FXML
	private ChoiceBox<String> qualifierCBox;
	
	// TODO: 07/11/22 tabella affiancata per le playlist
	@FXML
	private void Accesso() throws Exception {
		String user = userAField.getText();
		
		String query = "WHERE \"Username\"='" + user + "' AND \"Password\"='" + passAField.getText() + "'";
		
		if (checkUtente(query)) {
			out.println("Accesso eseguito da: " + user);
			clientESController.enableAccesso(user);
			// TODO: 07/11/22 pop-up conferma
		} else {
			out.println("I dati non corrispondono");
			// TODO: 07/11/22 pop-up rifiuto
		}
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