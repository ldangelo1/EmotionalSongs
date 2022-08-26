package uni.database;

import uni.emotionalsongs.objects.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class dbQuery extends serverES {
	static Connection connectDB = serverES.getConnection();
	private static ObservableList<ObservableList> data;
	
	public static void cercaBranoMusicale(String query) {
		ArrayList<Song> brano = new ArrayList<>();
		
		try {
			Statement st = connectDB.createStatement();
			ResultSet rs = st.executeQuery(query);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
}
