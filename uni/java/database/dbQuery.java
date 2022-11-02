package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class dbQuery extends serverES {
	public static ResultSet eseguiQuery(String query) throws SQLException {
		try {
			return connDB().prepareStatement(query).executeQuery();
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
}