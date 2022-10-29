package database;


public class DBInfo {
	// TODO: 29/10/22 Ho settato il mio db con diverse credenziali:
	// la variabile le gestisce, se true è il vostro db.
	private static final Boolean dbDefault = false;
	
	public static String getIP() {
		return "localhost";
	}
	
	public static String getPORT() {
		return "5432";
	}
	
	public static String getNAME() {
		return dbDefault ? "EmotionalSongs" : "lab";
	}
	
	public static String getUser() {
		return dbDefault ? "postgres" : "lab";
	}
	
	public static String getPass() {
		return dbDefault ? "password" : "postLab";
	}
}