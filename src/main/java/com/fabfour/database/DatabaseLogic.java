package com.fabfour.database;

import java.sql.*;
import java.util.*;

public class DatabaseLogic {

	// THIS INFO IS NEEDED WHEN CONNECTING FROM SERVER!
	// String url = "jdbc:postgresql://localhost:5432/postgres";
	// String user name = "postgres";
	// String password = "[3`Td?9=";
	// Database database = new Database(url, user name, password);
	// database.connectDatabase();
	// database.setUp();

	private API_parser parser;
	private Connection con;
	private String url;
	private String databaseUsername;
	private String databasePassword;

	public DatabaseLogic(String url, String databaseUsername, String databasePassword) throws SQLException {
		this.url = url;
		this.databaseUsername = databaseUsername;
		this.databasePassword = databasePassword;

		connectDatabase();
	}

	public DatabaseLogic() {
	}

	/**
	 * Method for connecting our database
	 * 
	 * @throws SQLException
	 *             SQLException
	 */
	public void connectDatabase() throws SQLException {
		con = DriverManager.getConnection(url, databaseUsername, databasePassword);

		System.out.println("Connected to database.");
	}

	/**
	 * Creating necessary tables if they don't already exist
	 * Adding content if tables are empty
	 */
	public void setUp() {
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS thriller (id VARCHAR(10) PRIMARY KEY NOT NULL, title VARCHAR(80) NOT NULL;");
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS comedy (id VARCHAR(10) PRIMARY KEY NOT NULL, title VARCHAR(80) NOT NULL;");
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS drama (id VARCHAR(10) PRIMARY KEY NOT NULL, title VARCHAR(80) NOT NULL;");
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS sci-fi (id VARCHAR(10) PRIMARY KEY NOT NULL, title VARCHAR(80) NOT NULL;");
			
			if (!statement.execute("SELECT * FROM thriller WHERE EXISTS (SELECT 1 FROM id);")) {
				List<String> thriller = parser.getListOfMovies("53");
				addMovies(thriller, "thriller");
			}
			if (!statement.execute("SELECT * FROM comedy WHERE EXISTS (SELECT 1 FROM id);")) {
				List<String> comedy = parser.getListOfMovies("35");
				addMovies(comedy, "comedy");
			}
			if (!statement.execute("SELECT * FROM drama WHERE EXISTS (SELECT 1 FROM id);")) {
				List<String> drama = parser.getListOfMovies("18");
				addMovies(drama, "drama");
			}
			if (!statement.execute("SELECT * FROM sci-fi WHERE EXISTS (SELECT 1 FROM id);")) {
				List<String> scifi = parser.getListOfMovies("878");
				addMovies(scifi, "sci-fi");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add movies to correct table in DB
	 * 
	 * @param movies
	 *            list containing movies
	 * @param genre
	 *            genre
	 */
	public void addMovies(List<String> movies, String genre) {

		int id = 1;
		for (String movie : movies) {
			try {
				PreparedStatement statement = con
						.prepareStatement("INSERT INTO " + genre + " (id, title) VALUES (?, ?)");
				statement.setLong(1, id);
				statement.setString(2, movie);

				statement.execute();

				id += 1;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Retrieve movies from desired genre
	 * 
	 * @param genre
	 *            genre
	 * @return Handler object containing id and title
	 */
	public List<Handler> getMovies(String genre) {

		List<Handler> movies = new ArrayList<>();
		
		String id = null, title = null;

		try {
			PreparedStatement statement = con.prepareStatement("SELECT id, title FROM" + genre + ";");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				id = rs.getString(1);
				title = rs.getString(2);
				movies.add( new Handler(id, title) );

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movies;
	}

	/**
	 * Close our database when we are done
	 * 
	 * @return true if succeed, false otherwise
	 */
	public boolean closeDatabase() {
		try {
			con.close();
			System.out.println("Database closed.");
		} catch (SQLException e) {
			System.err.println("Error: Failed to close database.");
			return false;
		}
		return true;
	}

}
