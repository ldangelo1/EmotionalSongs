package emotionalsongs.account;

import database.serverES;
import emotionalsongs.clientESController;
import emotionalsongs.objects.Indirizzo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

public class AccountController extends clientESController {
	private final String[] typeQualifier = {"via", "piazza", "corso"};
	@FXML
	private AnchorPane sceneAccount;
	
	@FXML
	private TextField nameField, surnameField, cfField, mailField;
	@FXML
	private TextField addrField, civicField, cityField, provField, capField;
	@FXML
	private TextField userAField, passAField, userRField, passRField;
	
	@FXML
	private ChoiceBox<String> qualifierCBox;
	
	@FXML
	private void registrazione() throws SQLException {
		String query = "WHERE \"CF\"='" + cfField.getText() + "' OR \"Username\"='" + userRField.getText() + "'";
		
		if (!checkUtente(query)) {
			Indirizzo addr = new Indirizzo(qualifierCBox.getValue(), addrField.getText(), Integer.parseInt(civicField.getText()), cityField.getText(), provField.getText(), Integer.parseInt(capField.getText()));
			String queryIns = "VALUES('" + cfField.getText() + "', '" + nameField.getText() + " " + surnameField.getText() + "', '" + mailField.getText() + "', '" + addr + "', '" + userRField.getText() + "', '" + passRField.getText() + "')";
			
			if (serverES.insert("Utente", queryIns) == 1) {
				out.println("Registrazione eseguita");
				serverES.generaAlert(Alert.AlertType.INFORMATION, "Registrazione eseguita!", "Premi OK per eseguire l'accesso.");
			} else {
				out.println("Errore nella registrazione");
				serverES.generaAlert(Alert.AlertType.WARNING, "Errore nella registrazione!", "Premi OK per riprovare.");
			}
		} else {
			out.println("Utente già registrato");
			serverES.generaAlert(Alert.AlertType.INFORMATION, "Sei già registrato! o l'username è già stato usato.", "Premi OK per riprovare.");
		}
	}
	
	@FXML
	private void accesso() throws Exception {
		String user = userAField.getText();
		String query = "WHERE \"Username\"='" + user + "' AND \"Password\"='" + passAField.getText() + "'";
		
		if (checkUtente(query)) {
			out.println("Accesso eseguito da: " + user);
			serverES.generaAlert(Alert.AlertType.INFORMATION, "Accesso eseguito!", "Premi OK per tornare alla pagina principale.");
			serverES.chiudiStage(sceneAccount);
		} else {
			out.println("Credenziali errate");
			serverES.generaAlert(Alert.AlertType.WARNING, "Credenziali errate!", "Premi OK per riprovare.");
		}
	}
	
	/**
	 * @param tail la seconda parte della query, generata in base ai dati in ingresso
	 * @return se un utente è o non è registrato
	 */
	private Boolean checkUtente(String tail) throws SQLException {
		ResultSet rset = serverES.select("Utente", tail);
		assert rset != null;
		if (rset.next()) setCF(rset.getString(1));
		rset.close();
		return getCF() != null;
	}
	
	// TODO: 29/10/22 scrivere un solo metodo che fa la regex su tutti i campi
	public void regex() {
	}
	
	public void initialize() {
		qualifierCBox.getItems().addAll(typeQualifier);
		qualifierCBox.setValue(typeQualifier[0]);
	}
}