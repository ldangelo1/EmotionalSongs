package uni.database;

import uni.emotionalsongs.objects.Song;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class serverES {
	public static void connection(String user, String psw, String dbName, String dbIP, String dbPort) throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		try {
			DriverManager.getConnection("jdbc:postgresql://" + "localhost:" + dbPort + "/" + dbName, user, psw);
			System.out.println("connessione avvenuta con successo");
		} catch (SQLException e) {
			System.out.println("Errore nella connessione");
		}
	}
	
	/// Cerco un brano dal titolo
	/// Aggiungere le emozioni relative alla canzone
	public static String searchSong(String query) throws ClassNotFoundException, SQLException {
		ArrayList<Song> brano = new ArrayList<Song>();
		//Connection connect;
		//assert connect != null;
		//Statement st = connect.createStatement();
		//ResultSet rs = st.executeQuery(query);
		//
		//while (rs.next()) {
		//	String title = rs.getString("Titolo");
		//	String author = rs.getString("Autore");
		//	int year = rs.getInt("Anno");
		//	String album = rs.getString("Album");
		//	Double duration = rs.getDouble("Durata");
		//	String genre = rs.getString("Genere");
		//	brano.add(new Song(title, author, year, album, duration, genre));
		//}
		return "abc";
	}
	
	public static String getdbUser() {
		return "postgres";
	}
	
	public static String getdbPsw() {
		return "password";
	}
	
	public static String getdbPort() {
		return "5432";
	}
	
	public static String getdbName() {
		return "EmotionalSongs";
	}
	
	public static String getdbIP() {
		return "localhost";
	}
}