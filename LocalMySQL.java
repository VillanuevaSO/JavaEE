package MyDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author pi
 * 
 * Separates MySQL database concerns from servlet business logic.
 *
 */
public class LocalMySQL implements PreparedBaseJDBC {

	public Connection conn;  // database Connection
	private String myDB = "jdbc:mysql://localhost:3306";  // local connection string
	private String user = "root";  // local user
	private String pswd = "4043april"; // local MySQL root passwd

	/* (non-Javadoc)
	 * @see com.acc.java.BaseJDBC#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
		return this.conn == null ? false : true;
	}

	/* (non-Javadoc)
	 * @see com.acc.java.BaseJDBC#setUser(java.lang.String)
	 */
	@Override
	public void setUser(String user) {
		this.user = user;
	}

	/* (non-Javadoc)
	 * @see com.acc.java.BaseJDBC#setPswd(java.lang.String)
	 */
	@Override
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public LocalMySQL() {
		this.conn = openDB();  // if needed, user can alter userid/password and call openDB()
	}

	/* (non-Javadoc)
	 * @see com.acc.java.BaseJDBC#executeQuery(java.lang.String)
	 */
	@Override
	public ResultSet executeQuery(String query, String[] parms) throws SQLException {
		String preparedSQL = "SELECT CITIES SET POPULATION = ? WHERE NAME = ?";
		PreparedStatement statement = this.conn.prepareStatement(preparedSQL);
		statement.setString(1,"name");
		ResultSet users = statement.executeQuery(query);
		return users;
	}

	/* (non-Javadoc)
	 * @see com.acc.java.BaseJDBC#executeUpdate(java.lang.String)
	 */
	@Override
	public int executeUpdate(String query, String[] parms) throws SQLException {
		String preparedSQL = "UPDATE CITIES SET POPULATION = ? WHERE NAME = ?";
		PreparedStatement statement = this.conn.prepareStatement(preparedSQL);
		statement.setString(2,"name");
		statement.setInt(1,3);
		int count = statement.executeUpdate(query);
		return count;
	}


	/* (non-Javadoc)
	 * @see com.acc.java.BaseJDBC#openDB()
	 */
	@Override
	public Connection openDB() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(myDB, user, pswd);
		}
		catch (ClassNotFoundException ex) {
			System.out.println("<br>Can't load JDBC driver");  // log error to console
		}
		catch (SQLException ex) {
			printTrace(ex);
		}

		return conn;  
	}

	/* (non-Javadoc)
	 * @see com.acc.java.BaseJDBC#printTrace(java.sql.SQLException)
	 */
	@Override
	public void printTrace(SQLException ex) {
		for (Throwable t : ex) {
			t.printStackTrace(System.out);  // stack trace to console
		}
	}

	@Override
	public ResultSet executeQuery(String query) 
			throws SQLException {
		Statement statement = this.conn.createStatement();
		ResultSet users = statement.executeQuery(query);
		return users;
	}

	@Override
	public int executeUpdate(String query) 
			throws SQLException {
		Statement statement = this.conn.createStatement();
		int count = statement.executeUpdate(query);
		return count;
	}


}