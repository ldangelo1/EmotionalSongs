package uni.emotionalsongs.signin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import uni.emotionalsongs.objects.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static uni.database.serverES.connect;

public class SigninController {
	public TextField userField;
	public TextField passField;
	public Button signinBtn;

	/**Metodo per verificare i dati dell'utente per eseguire il login
	 * */
	public boolean checkLogin(String user, String pass) throws Exception{
		boolean check = false;
		Statement st = connect().createStatement();
		ResultSet loginQuery = st.executeQuery("SELECT username,password FROM public.\"Utenti\" WHERE username LIKE '" + user +"'");

		if(loginQuery.next()){

			if(user.equals(loginQuery.getString(1)) && pass.equals(loginQuery.getString(2))){
				check = true;
				System.out.println("Dati confermati nel DB");
			}
			else{
				System.out.println("Errore, i dati inseriti sono errati e non corrispondono a quelli nel DB");
			}
		}

		else{
			System.out.println("utente non registrato !!");
		}

		st.close();
		loginQuery.close();

		return check;
	}

    @FXML
	public void inButtonClick() throws Exception {
		String user = userField.getText();
		String pass = passField.getText();
		checkLogin(user,pass);
	}
}