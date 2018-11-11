package com.fabfour.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.fabfour.beans.DatabaseEntitiy;
import com.fabfour.beans.Movie;
import com.fabfour.database.DatabaseLogic;
/**
 * Servlet implementation class TmdbServlet
 */
public class TmdbServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DatabaseLogic dbLogic;

	public enum Media{
		movie,
		series,
		both
	}
	
	public enum Sorting{
		top_rated,
		popular,
		upcoming
	}
	

	
	/**
	 * Added for convenience @ src/main/webapp/Meta-Inf/context.xml
	 * Simplifies connection with JDBC driver, no need to use Strings to connect.
	 * example: Connection con = dataSource.getConnection(); 
	 */
	@Resource(name="jdbc/tmdb-sql")
	private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TmdbServlet() {
        super();
        
    }
    
	@Override
	public void init() throws ServletException {
		super.init();
		//TODO: setup dbLogic here
		
		try {
			dbLogic = new DatabaseLogic("jdbc:postgresql://localhost:5432/postgres",
			        "postgres",
			        "[3`Td?9=");
			dbLogic.setUp();
			
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Servlet looks what kind of media it should retrieve
		Media whatMedia = Media.valueOf( (String) request.getParameter("media"));
		//What kind of sorting the servlet should do
		Sorting whatSort = Sorting.valueOf( (String) request.getParameter("sort"));

		
		//temp to test write to response page
		List<Movie> test = new ArrayList<>();
		Movie m1 = new Movie("Die hard", "1989", "Action");
		Movie m2 = new Movie("Air Force One", "2001", "Romance");
		test.add(m1);
		test.add(m2);
		
		request.setAttribute("media", whatMedia.toString());
		request.setAttribute("sort", whatSort.toString());
		request.setAttribute("RESULT", test);

		//when queries are completed and addedto request.setAttribute, redirect to response page
		getServletContext().getRequestDispatcher("/responsepage.jsp").forward(request, response);

		
	}
	
	//Example method to query from db below -- might not be used, just skeleton code.
	private List<DatabaseEntitiy> getQuery (Media m, Sorting sort){
		
		List<DatabaseEntitiy> entities = new ArrayList<DatabaseEntitiy>();
		Connection conn = null;
		Statement stmn = null;
		ResultSet rs = null;
		
		String query;
		
		try {
			conn = dataSource.getConnection();
			//stmnt
			//query
			//iterate through resultset and create a new Movie och Series based on Media variable
			//add to list of entities
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	finally {
			close(conn, stmn, rs);
		}
		
		
		return entities;
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
				if (myRs != null) {
					myRs.close();
				}
				
				if (myStmt != null) {
					myStmt.close();
				}
				
				if (myConn != null) {
					myConn.close();   
				}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}


}
