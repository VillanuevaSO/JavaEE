package MyDB;


import java.sql.ResultSet;
import java.sql.SQLException;

public interface PreparedBaseJDBC extends BaseJDBC{
	
	/**
	 * *
	 * @return true if DB is available
	 */

	
	ResultSet executeQuery(String query, String[] parms) throws SQLException;
	/**
	 * 
	 * @param query the SQL syntax query to execute
	 * @return int for number of rows affected
	 * @throws SQLException
	 */
	int executeUpdate(String query, String[] parms) throws SQLException;
	/**
	 * 
	 * @return Connection object if DB opened successfully, else null
	 */
	
}
