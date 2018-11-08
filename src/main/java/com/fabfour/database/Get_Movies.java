package com.fabfour.database;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Get_Movies {
	
	// Returns a list of the most popular movie titles from TMDB.
		public List<String> get_Movie_Titles(JSONArray movies) {

			List<String> popularTitles = new ArrayList<>();

			for (int i = 0; i < movies.length(); i++) {
				JSONObject product = movies.getJSONObject(i);
				popularTitles.add(product.getString("title"));
			}

			return popularTitles;
		}


}
