// Use OMdB or whatever to retreive information about movies, store them in Movie objects, then put them into a MovieCollection object
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MovieParser {
	private ArrayList<String> requestUrl;
	private ArrayList<JsonObject> rawJson;
	private MovieCollection movies;
	public MovieParser(String url)
	{
		
	}
	//TODO: More constructors to handle different formats of request input
	public String[] getActors()
	{
		if (rawtext == null)
		{
			System.out.println("Initial http request failed, data unavailable.");
			return null;
		}
		else
		{
			
		}
	}
	
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
		}
		catch (UnirestException e)
		{
			e.printStackTrace();
			System.out.println("Unirest threw an error when connecting to given URL.");
			return null;
		}
		rawJson.add(rawtext);
		return rawtext;
	}
	
	public Movie constuctMovieFromRequest()
	{
		
	}
}
