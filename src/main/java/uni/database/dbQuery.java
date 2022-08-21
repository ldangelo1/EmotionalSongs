package uni.database;

import uni.emotionalsongs.objects.Song;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class dbQuery extends serverES {
	ArrayList<String> playlist = new ArrayList<>();
	
	//Cerco una playlist con il nome della playlist
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
	}
	
	//Cerco un brano dal titolo
	//Aggiungere le emozioni relative alla canzone
	public static String searchSongs(String query) throws SQLException {
		ArrayList<Song> songs = new ArrayList<Song>();
		Statement st = conn.createStatement();
		ResultSet resSet = st.executeQuery(query);
		
		while (resSet.next()) {
			String title = resSet.getString("Titolo");
			String author = resSet.getString("Autore");
			int year = resSet.getInt("Anno");
			String album = resSet.getString("Album");
			Double duration = resSet.getDouble("Durata");
			String genre = resSet.getString("Genere");
			brano.add(new Song(title, author, year, album, duration, genre));
		}
		return "abc";
	}
}
