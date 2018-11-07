package api_logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TMDB_Movie_Info_Retrieval {

	public String get_Movie_Title() {

		return "";
	}

	public String get_Hero_Image(JSONArray movies) {
		String url = "http://image.tmdb.org/t/p/w1280";

		JSONObject firstMovie = movies.getJSONObject(0);

		String popular_Movie_HeroImage = firstMovie.getString("backdrop_path");

		// System.out.println(popular_Movie_HeroImage);

		return url + popular_Movie_HeroImage;
	}

}
