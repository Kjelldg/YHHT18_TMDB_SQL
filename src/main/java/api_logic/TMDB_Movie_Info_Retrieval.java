package api_logic;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDB_Movie_Info_Retrieval {

	/*
	 * Returns the image path for the most popular movie, usable for creating hero
	 * images for the website.
	 */
	public String get_Hero_Image(JSONArray movies) {
		String url = "http://image.tmdb.org/t/p/w1280";

		JSONObject firstMovie = movies.getJSONObject(0);

		String popular_Movie_HeroImage = firstMovie.getString("backdrop_path");

		return url + popular_Movie_HeroImage;
	}

	// Returns a list of the most popular movie titles from TMDB.
	public List<String> get_Movie_Titles(JSONArray movies) {

		List<String> popularTitles = new ArrayList<>();

		for (int i = 0; i < movies.length(); i++) {
			JSONObject product = movies.getJSONObject(i);
			popularTitles.add(product.getString("title"));
		}

		return popularTitles;
	}

	// Returns a list of the URLs for the movie thumbnails, 154 px wide.
	public List<String> get_Movie_Thumb(JSONArray movies) {

		String url = "http://image.tmdb.org/t/p/w154";

		List<String> popularTitles_Thumbs = new ArrayList<>();

		for (int i = 0; i < movies.length(); i++) {
			JSONObject product = movies.getJSONObject(i);
			popularTitles_Thumbs.add(url + product.getString("poster_path"));
		}

		return popularTitles_Thumbs;
	}

}
