package emotionalsongs.account;

import database.serverES;
import emotionalsongs.clientESController;
import emotionalsongs.objects.Indirizzo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
	private void Accesso() throws Exception {
		String user = userAField.getText();
		String query = "WHERE \"Username\"='" + user + "' AND \"Password\"='" + passAField.getText() + "'";

		if (checkUtente(query)) {
			out.println("Accesso eseguito da: " + user);
			generaAlert(Alert.AlertType.INFORMATION, "Accesso eseguito!", "Premi OK per tornare alla pagina principale");
			chiudiStage();
		} else {
			out.println("I dati non corrispondono");
			generaAlert(Alert.AlertType.WARNING, "Credenziali errate!", "Premi OK per riprovare.");
		}
	}
	
	@FXML
	private void Registrazione() throws SQLException {
		String query = "WHERE \"CF\"='" + cfField.getText() + "' OR \"Username\"='" + userRField.getText() + "'";
		
		if (!checkUtente(query)) {
			Indirizzo addr = new Indirizzo(qualifierCBox.getValue(), addrField.getText(), Integer.parseInt(civicField.getText()), cityField.getText(), provField.getText(), Integer.parseInt(capField.getText()));
			String queryIns = "VALUES('" + cfField.getText() + "', '" + nameField.getText() + " " + surnameField.getText() + "', '" + mailField.getText() + "', '" + addr + "', '" + userRField.getText() + "', '" + passRField.getText() + "')";
			
			if (serverES.insert("Utente", queryIns) == 1) {
				out.println("Registrazione eseguita");
				generaAlert(Alert.AlertType.INFORMATION, "Registrazione eseguita!", "Premi OK per tornare alla pagina principale");
				chiudiStage();
			} else {
				out.println("Errore nella registrazione");
				generaAlert(Alert.AlertType.WARNING, "Problemi con la registrazione!", "Premi OK per riprovare.");
			}
		} else {
			out.println("Utente già registrato");
			generaAlert(Alert.AlertType.INFORMATION, "Sei già registrato! o il tuo user è già stato usato", "Premi OK per riprovare.");
		}
	}
	
	/**
	 * @param tail la seconda parte della query, generata in base agli input
	 * @return se un utente è o non è registrato
	 */
	private Boolean checkUtente(String tail) throws SQLException {
		String query = "SELECT * FROM \"Utente\" " + tail;
		ResultSet rset = (ResultSet) serverES.eseguiQuery(query, 1);
		assert rset != null;
		rset.next();
		setCF(rset.getString(1));
		rset.close();
		return getCF() != null;
	}
	
	/**
	 * @param tail la seconda parte della query, generata in base agli input
	 * @return il numero di tuple aggiornate
	 */
	
	// TODO: 29/10/22 scrivere un solo metodo che fa la regex su tutti i campi
	public void regex() {
	}
	
	public void initialize() {
		qualifierCBox.getItems().addAll(typeQualifier);
		qualifierCBox.setValue(typeQualifier[0]);
	}
	
	/**
	 * Genera l'alert adeguato da mostrate al cliente
	 *
	 * @param alertType il tipo di alert da mostrare
	 * @param head      il messaggio da notificare
	 * @param msg       il commento al messaggio
	 */
	public static void generaAlert(Alert.AlertType alertType, String head, String msg) {
		Alert alert = new Alert(alertType);
		alert.setTitle("Informazioni");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		alert.show();
	}
	
	private void chiudiStage() {
		Stage stage = (Stage) sceneAccount.getScene().getWindow();
		stage.close();
	}
}