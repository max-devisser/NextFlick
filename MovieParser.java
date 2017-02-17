// Use OMdB or whatever to retreive information about movies, store them in Movie objects, then put them into a MovieCollection object
import java.io.IOException;
import java.util.ArrayList;

//private int key;
//private String title;
//private int year;
//private String director;
//private ArrayList<String> genre;
//private ArrayList<String> actors;
//private String parentalRating; // TODO: enum this shit
//private int runtime;
//private String language;
//private String country;
//private double criticalRating;
//private String plot;
//private String imageURL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MovieParser {
	private static String TMDB = "https://api.themoviedb.org/3/movie/";
	private static String API_KEY = "?api_key=d7160b15d167c28148a4b8491ff65b4d";
	private static String LANG = "&language=en-US";
	private static String APPEND = "&append_to_response=";
	private Set<Map.Entry<String, JsonElement>>;
	private MovieCollection movies;
	public MovieParser() {}
	
	public JsonObject getRawRequest(String url)
	{
		JsonObject rawtext;
		try {
			HttpResponse<String> request = Unirest.get("https://api.themoviedb.org/3/movie/550?api_key=d7160b15d167c28148a4b8491ff65b4d")
			.asString();
			System.out.println("Success");
			String stringtext = request.getBody();
			JsonParser parser = new JsonParser();
			rawtext = (JsonObject) parser.parse(stringtext);
			System.out.println(rawtext);
			Unirest.shutdown();
		} catch (IOException e) {
			System.out.println("Unirest shutdown failed.");
			e.printStackTrace();
			return null;
		} catch (UnirestException e){
			e.printStackTrace();
			System.out.println("Unirest threw an error when connecting to given URL.");
			return null;
		}
		rawJson.add(rawtext);
		return rawtext;
	}
	
	public Movie constructMovieByUrl(String url) // must have full request URL
	{
		JsonObject rawJson = getRawRequest(url);
		if (url.contains(APPEND)) //handle extra stuff
		{
			
		}
		else
		{
			
		}
		Movie movie;
	}
	public Movie constructMovieById(int id, String append) // tmdb id plus append_to_request command
	{
		String requestUrl = TMDB + String.valueOf(id) + APPEND + append;
		return constructMovieByUrl(requestUrl);
	}
	public Movie constructMovieById(int id)
	{
		return constructMovieById(id, "");
	}
	
}
