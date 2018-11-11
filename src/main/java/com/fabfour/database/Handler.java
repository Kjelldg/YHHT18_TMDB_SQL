package com.fabfour.database;

public class Handler {
	
	private String id, title;
	
	public Handler(String id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public String getId() {
        return this.id;
    }
	
	public String getTitle() {
        return this.title;
    }

}
