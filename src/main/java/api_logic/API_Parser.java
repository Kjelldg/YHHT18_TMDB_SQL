package api_logic;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class API_Parser {

	public static void main(String[] args) {

		TMDB_Movie_Info_Retrieval movie_Info_Retriever = new TMDB_Movie_Info_Retrieval();

		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/octet-stream");
		RequestBody body = RequestBody.create(mediaType, "{}");
		Request request = new Request.Builder().url(
				"https://api.themoviedb.org/3/movie/popular?api_key=eecc8ae5b1c378032fe7a8ca2ce11da9&language=en-US&page=1")
				.get().build();

		try {
			Response response = client.newCall(request).execute();

			String responseData = response.body().string();
			JSONObject jsonObject = new JSONObject(responseData);

			// The array for all the popular movies on page 1.
			JSONArray moviesArray = jsonObject.getJSONArray("results");

			// Prints the full path to the hero image.
			System.out.println(movie_Info_Retriever.get_Hero_Image(moviesArray));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
