package uni.database;

import uni.emotionalsongs.objects.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class dbQuery extends serverES {
	ArrayList<String> playlist = new ArrayList<>();
	/*
	public ArrayList<Playlist> cercaPlaylist(String NomePlaylist) throws ClassNotFoundException, SQLException{
		ArrayList<Playlist> playlist  = new ArrayList<Playlist>();
		Statement st = Connect().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM Playlist WHERE Titolo=" + NomePlaylist);

		while(rs.next()){
			String nome = rs.getString("NomePlaylist");
			String user = rs.getString("User");

			Array Songs = rs.getArray("Canzoni");
			Song[] Brani = (Song[])Songs.getArray();
			//  playlist.add(new Playlist(Brani)); bisogna implementare la classe playlist
		}
		return playlist;
	}*/
	
	public static void searchSongs(String query) throws SQLException {
		Connection connectDB = serverES.getConnection();
		
		try {
			Statement st = connectDB.createStatement();
			ResultSet rs = st.executeQuery(query);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
}
