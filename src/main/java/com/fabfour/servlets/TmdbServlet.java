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
/**
 * Servlet implementation class TmdbServlet
 */
public class TmdbServlet extends HttpServlet {
	
	public enum Media{
		MOVIE,
		SERIES,
		BOTH
	}
	
	public enum Sorting{
		TOP_RATED,
		POPULAR,
		UPCOMING
	}
	
	private static final long serialVersionUID = 1L;
	
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Servlet looks what kind of media it should retrieve
		Media whatMedia = Media.valueOf((String) request.getParameter("media"));
		//What kind of sorting the servlet should do
		Sorting whatSort = Sorting.valueOf((String) request.getParameter("sort"));

		
		//Get data here
		List<DatabaseEntitiy> queryResult = getQuery(whatMedia, whatSort);
		request.setAttribute("RESULT", queryResult);
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
