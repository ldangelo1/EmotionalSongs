package uni.database;

/**
 * @author ricky
 */
public class DBInfo {
	private static String USERNAME = "postgres";
	private static String PASS = "password";
	private static String DBPORT = "5432";
	private static String DBNAME = "EmotionalSongs";
	private static String IP = "localhost";
	
	/**
	 * @return the USERNAME
	 */
	public static String getUSERNAME() {
		return USERNAME;
	}
	
	/**
	 * @return the PASS
	 */
	public static String getPASS() {
		return PASS;
	}
	
	/**
	 * @return the DBPORT
	 */
	public static String getDBPORT() {
		return DBPORT;
	}
	
	/**
	 * @return the DBNAME
	 */
	public static String getDBNAME() {
		return DBNAME;
	}
	
	/**
	 * @return the IP
	 */
	public static String getIP() {
		return IP;
	}
	
	/**
	 * @param aUSERNAME the USERNAME to set
	 */
	public static void setUSERNAME(String aUSERNAME) {
		USERNAME = aUSERNAME;
	}
	
	/**
	 * @param aPASS the PASS to set
	 */
	public static void setPASS(String aPASS) {
		PASS = aPASS;
	}
	
	/**
	 * @param aDBPORT the DBPORT to set
	 */
	public static void setDBPORT(String aDBPORT) {
		DBPORT = aDBPORT;
	}
}