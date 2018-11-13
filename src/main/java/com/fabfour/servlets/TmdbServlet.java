package com.fabfour.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.fabfour.database.*;
/**
 * Servlet implementation class TmdbServlet
 */
public class TmdbServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DatabaseLogic dbLogic;
	private List<Handler> movies;
	
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
		
		try {
			dbLogic = new DatabaseLogic(dataSource);
			dbLogic.setUp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*try {
			dbLogic = new DatabaseLogic("jdbc:postgresql://localhost:5432/postgres",
			        "postgres",
			        "postgres"); //[3`Td?9=

			//dbLogic.setUp();
			
			
		} catch (SQLException e) {
			e.getMessage();
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		movies = new ArrayList<>();
		
		//Servlet checks what kind of media it should retrieve (always movies, currently)
		String whatMedia = request.getParameter("media");
		//What kind of genre selected
		String whatGenre = request.getParameter("genre");
		
		//get a list of movies from the selected genre from the db
		movies = dbLogic.getMovies(whatGenre);
		
		//send list and other attributes to responsepage.jsp
		request.setAttribute("RESULT", movies);
		request.setAttribute("media", whatMedia);
		request.setAttribute("genre", whatGenre);
		

		//when queries are completed and added to request, redirect to response page
		getServletContext().getRequestDispatcher("/responsepage.jsp").forward(request, response);

		
	}
	
}
