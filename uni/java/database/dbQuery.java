package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class dbQuery extends serverES {
	/**
	 * Query per la ricerca dei brani
	 *
	 * @param query l'interrogazione sui brani al db
	 */
	public static ResultSet cercaCanzone(String query) throws SQLException {
		try {
			return connDB().prepareStatement(query).executeQuery();
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
}