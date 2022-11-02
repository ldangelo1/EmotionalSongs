package emotionalsongs.signup;

import emotionalsongs.objects.Indirizzo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static database.serverES.connDB;


public class SignupController {
	@FXML
	private Label welcomeText;
	
	public void SignUp(String user, String NomeCognome, String mail, String pass, String CF, Indirizzo indirizzo) throws SQLException {
		//controlla se utente già registrato
		boolean registered = false;
		Statement st = connDB().createStatement();
		ResultSet resUser = st.executeQuery("SELECT username FROM \"Utente\" WHERE username =" + user);
		
		if (resUser.next()) {
			registered = true;
		}
		
		//se non presente, registra
		/*if (!registered) {
			String query = "INSERT INTO \"Utente\"(username, password, NomeCognome, CF, mail, indirizzo) VALUES(?, ?, ?, ?, ?, ?)";
			try (PreparedStatement pst = connDB().prepareStatement(query)) {
				pst.setString(1, user);
				pst.setString(2, pass);
				pst.setString(3, NomeCognome);
				pst.setString(4, CF);
				pst.setString(5, mail);
				pst.setObject(6, indirizzo);
				pst.executeUpdate();
				System.out.println("Sucessfully created.");
				
			} catch (SQLException ex) {
				System.out.println("errore nell'inserzione dei dati");
			}
		} else {
			System.err.println("Utente già registrato!");
		}*/
	}
}