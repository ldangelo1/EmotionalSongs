package uni.database;

import javafx.collections.ObservableList;
import uni.emotionalsongs.objects.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class dbQuery extends serverES {
	static Connection connectDB;
	
	static {
		try {
			connectDB = connect();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static ObservableList<ObservableList> data;
	
	/**
	 * Query per la ricerca dei brani
	 *
	 * @param query l'interrogazione sui brani al db
	 */
	public static void cercaBranoMusicale(String query) {
		ArrayList<Song> brano = new ArrayList<>();
		
		try {
			Statement st = connectDB.createStatement();
			ResultSet rs = st.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}