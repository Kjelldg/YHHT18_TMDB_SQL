package com.fabfour.beans;

//should be used to save json data in a POJO to return to jsp.
public class Movie implements DatabaseEntitiy {

	String name;
	String year;
	String genre;
	//TODO: insert additional(?) fields for movies and getters and setters
	public Movie(String name, String year, String genre) {
		super();
		this.name = name;
		this.year = year;
		this.genre = genre;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
}
