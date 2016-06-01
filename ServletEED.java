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

import MyDB.LocalMySQL;
/**
 * Servlet implementation class ServletEED
 */
@WebServlet("/ServletEED")
public class ServletEED extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TEMPORARYERROR = "Sorry, we are experiencing a temporary error...please retry later";
	public Connection conn;	   
	
	private Connection openDB() {
		return null;
	}
	
	private ResultSet executeQuery(Connection conn, String sql) {
		return null;
	}

	private void printTrace(SQLException ex) {
	}

	private int executeUpdate(Connection conn, String string) {
		return 0;
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEED() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		LocalMySQL myDB = new LocalMySQL();
		String url= "/addCityForm.html";
		String sql;  // SQL to execute 
		String query = null;
		ResultSet result = executeQuery(conn, query);           // results of executeQuery() 
		String name = request.getParameter("name");
		String population = request.getParameter("population");
		
		//get current Action
		String action = request.getParameter("action");
		if 	(action == null){
			action = "join";
		}
		
		//perform action and set URL to appropriate page
		if(action.equals("join")){
			url = "/addCityForm.html";  //the "join" page
		}
		else if (action.equals("add")){
			//get parameters from the request
			name = request.getParameter("name");
			population = request.getParameter("population");
		}
		
		if (name.equals("name")){
			sql = "UPDATE INTO cities (name, population) values "; 
		}
		if (conn == null) {
			out.println(TEMPORARYERROR);
			return;
		}

		
		
		// assert DB is open 
		try {
			int rowCount = 0;
			rowCount = executeUpdate(conn, "create table cities" 
					+ "(id int auto_increment primary key,"
					+ "name varchar(30),"
					+ "population int)");
			out.println("<br>create table cities ");
			// table creation done
			Statement statement = conn.createStatement();
			rowCount = statement.executeUpdate(query);
			// insert a little data 
			sql = "INSERT INTO cities (name, population) values "; 
			rowCount = executeUpdate(conn, sql);
			out.println("<br>insert city data ");

			
			while (result.next()) {
				out.println("<br>" + result.getString("name") 
						+ " [ population: " 
						+ result.getString("population") 
				        + "]");
			}
		}
		catch (SQLException ex) {
			out.println("<p>" + TEMPORARYERROR);
			printTrace(ex);
		}
		
		
		this.getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
