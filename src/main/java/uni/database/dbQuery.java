package uni.database;

import uni.emotionalsongs.objects.Song;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class dbQuery extends serverES {
	ArrayList<String> playlist = new ArrayList<>();
	
	/// Search playlist
	/*public ArrayList<Playlist> cercaPlaylist(String NomePlaylist) throws ClassNotFoundException, SQLException {
		playlist;
		Statement st = Connect().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM Playlist WHERE Titolo=" + NomePlaylist);
		
		while (rs.next()) {
			String nome = rs.getString("NomePlaylist");
			String user = rs.getString("User");
			
			Array Songs = rs.getArray("Canzoni");
			Song[] Brani = (Song[]) Songs.getArray();
			//  playlist.add(new Playlist(Brani)); bisogna implementare la classe playlist
		}
		return playlist;
	}*/
}
