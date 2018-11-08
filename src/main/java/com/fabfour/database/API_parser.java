package com.fabfour.database;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import api_logic.TMDB_Movie_Info_Retrieval;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class API_parser {
	
	public static void main(String[] args) {

		Get_Movies getMovies = new Get_Movies();

		OkHttpClient client = new OkHttpClient();

		String API_Key = "eecc8ae5b1c378032fe7a8ca2ce11da9";

		MediaType mediaType = MediaType.parse("application/octet-stream");
		RequestBody body = RequestBody.create(mediaType, "{}");
		Request request = new Request.Builder()
				.url("https://api.themoviedb.org/3/discover/movie?api_key=" + API_Key + "&with_genres=53&year=2010&language=en-US&page=1").get()
				.build();

		try {
			Response response = client.newCall(request).execute();

			String responseData = response.body().string();
			JSONObject jsonObject = new JSONObject(responseData);

			// The array for all the popular movies on page 1.
			JSONArray moviesArray = jsonObject.getJSONArray("results");

			// Returns a list of the most popular movie titles from TMDB.
			for (String string : getMovies.get_Movie_Titles(moviesArray)) {
				System.out.println(string);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
