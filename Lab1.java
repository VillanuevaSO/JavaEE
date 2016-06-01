package MyDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Lab1
 */
@WebServlet("/Lab1")
public class Lab1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Connection connection = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lab1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Connection connection = openDB();
		if (connection == null){
			out.println("error");
			return;
		}

		
	openDB();

	
	}
	private Connection openDB() {
		String dbURL = "jdbc:mysql://localhost:3306";
		String username = "root";
		String password = "4043april";
		Connection connection = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		try{
			
			connection = DriverManager.getConnection(dbURL, username, password);
			Statement statement = connection.createStatement();
			ResultSet userTable = statement.executeQuery("SELECT * FROM mysql.user");
			String user = userTable.getString("user");
			String host = userTable.getString("host");
			
		}
		catch(SQLException e){
			for (Throwable t : e)
				t.printStackTrace();		
		}
		return connection;
	}
	
	

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
