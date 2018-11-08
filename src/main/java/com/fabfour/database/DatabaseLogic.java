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
						.prepareStatement("INSERT INTO " + genre + " (idr, title) VALUES (?, ?)");
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
	public Handler getMovies(String genre) {

		String id = null, title = null;

		try {
			PreparedStatement statement = con.prepareStatement("SELECT id, title FROM" + genre + ";");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				id = rs.getString(1);
				title = rs.getString(2);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Handler(id, title);
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
