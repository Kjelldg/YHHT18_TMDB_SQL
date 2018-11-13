package com.fabfour.database;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Get_Movies {
	
	public Get_Movies() {
	}

	/**
	 * Return list of movies
	 * 
	 * @param movies JSSONArray
	 * @return List<String>
	 */
	public List<String> get_Movie_Titles(JSONArray movies) {

		List<String> movieTitles = new ArrayList<>();

		for (int i = 0; i < movies.length(); i++) {
			JSONObject product = movies.getJSONObject(i);
			movieTitles.add(product.getString("title"));
		}

		return movieTitles;
	}

}
