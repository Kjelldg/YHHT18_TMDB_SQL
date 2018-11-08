package api_logic;

import org.json.JSONArray;
import org.json.JSONObject;

public class Movie {

	private int Id;
	private String hero_Image;
	private String movie_Name;
	private String movie_Thumbnail;
	private int year_Released;

	public Movie(int Id, String hero_Image, String movie_Name, String movie_Thumbnail, int year_Released) {
		this.Id = Id;
		this.movie_Name = movie_Name;
		this.movie_Thumbnail = movie_Thumbnail;
		this.year_Released = year_Released;
	}

	public int get_Id(JSONArray movies) {

		int id;

		JSONObject firstMovie = movies.getJSONObject(0);

		String popular_Movie_HeroImage = firstMovie.getString("backdrop_path");

		return id;
	}

}
