package src.movie_builder;
// Use OMdB or whatever to retreive information about movies, store them in Movie objects, then put them into a MovieCollection object
import java.io.IOException;
import java.util.ArrayList;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;



public class MovieParser {
	private static String TMDB = "https://api.themoviedb.org/3/movie/";
	private static String API_KEY = "?api_key=d7160b15d167c28148a4b8491ff65b4d";
	private static String LANG = "&language=en-US";
	private static String APPEND = "&append_to_response=";
	private static String SEARCH_PREFIX = "https://api.themoviedb.org/3/search/movie?api_key=d7160b15d167c28148a4b8491ff65b4d&language=en-US&query=";
	private static String SEARCH_POSTFIX = "page=1&include_adult=false";
	public MovieParser() {}
	
	public String getRawRequest(String url)
	{
		String stringtext;
		try {
			HttpResponse<String> request = Unirest.get(url).asString();
			System.out.println("Success");
			stringtext = request.getBody();
			System.out.println(stringtext);
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
		return stringtext;
	}
	
	public Movie constructMovieByUrl(String url) // must have full request URL
	{
		String rawtext = getRawRequest(url);
		Movie movie = new Movie(grabId(rawtext));
		movie.setTitle(grabStringParameter(rawtext, "\"title\""));
		movie.setPlot(grabStringParameter(rawtext, "\"overview\""));
		movie.setRuntime(grabIntParameter(rawtext, "\"runtime\""));
		movie.setDate(grabStringParameter(rawtext, "\"release_date\""));
		movie.setLanguage(grabStringParameter(rawtext, "\"iso_639_1\""));
		movie.setCountry("US"); // murica
		movie.setDirector(grabDirector(rawtext));
		movie.setCriticalRating(grabCriticalRating(rawtext));
		
		
		//TODO: refactor, refactor. this overcomplicated mess could be much improved by a separate custom "JsonSearcher" class
		// JsonSearcher(int targetlevel, String matchtext, Object fieldtype, String containedBy)
		
		//private String title;
		//private int year;
		//private String director;
		//private ArrayList<String> genre;
		//private ArrayList<String> actors;
		//private String parentalRating; // TODO: enum this beauty
		//private int runtime;
		//private String language;
		//private String country;
		//private double criticalRating;
		//private String plot;
		//private String imageURL;
		return movie;
		
	}
	private String grabDirector(String rawtext)
	{
		int index = rawtext.indexOf("\"director\"");
		index = rawtext.indexOf("\"name\"", index);
		if (index == -1)
		{
			return "";
		}
		String director = "";
		while (rawtext.charAt(index) != '\"')
		{
			director += rawtext.charAt(index);
			++index;
		}
		return director;
	}
	private double grabCriticalRating(String rawtext /*int popularityBias */)
	{
		int index = rawtext.indexOf("\"vote_average\"");
		index+= 16;
		String rating = "";
		while (rawtext.charAt(index) != ',')
		{
			rating += rawtext.charAt(index);
			++index;
		}
		return Double.parseDouble(rating);
	}
	private int grabIntParameter(String rawtext, String matchtext)
	{
		int index = rawtext.indexOf(matchtext);
		if (index == -1)
		{
			return -1;
		}
		int skipChars = 4; //filler characters
		for (int i = 0; i < matchtext.length(); ++i)
		{
			if (matchtext.charAt(i) != '\"')
			{
				++skipChars;
			}
		}
		index += skipChars;
		String param = "";
		while (rawtext.charAt(index) != ',')
		{
			param += rawtext.charAt(index);
			++index;
		}
		return Integer.valueOf(param);
	}
	private int grabId(String rawtext)
	{
		int index = rawtext.indexOf("\"homepage\"");
		if (index == -1) 
		{
			System.out.println("Error: Id grab failed because it assumed homepage existed");
			return -1;
		}
		index = rawtext.indexOf("\"id\"", index);
		if (index == -1)
		{
			return -1;
		}
		int skipChars = 6;
		index += skipChars;
		String param = "";
		while (rawtext.charAt(index) != ',')
		{
			param += rawtext.charAt(index);
			++index;
		}
		return Integer.valueOf(param);
	}
	private String grabStringParameter(String rawtext, String matchtext)
	{
		int index = rawtext.indexOf(matchtext);
		if (index == -1)
		{
			return "";
		}
		int skipChars = 5; //filler characters plus open quote
		for (int i = 0; i < matchtext.length(); ++i)
		{
			if (matchtext.charAt(i) != '\"')
			{
				++skipChars;
			}
		}
		index += skipChars;
		String param = "";
		while (rawtext.charAt(index) != '\"')
		{
			param += rawtext.charAt(index);
			++index;
		}
		return param;
	}
	// tmdb id plus append_to_request command
	// @param append If multiple append commands, separate with commas and no spaces, e.g. "credits,releases"
	public Movie constructMovieById(int id, String append) 
	{
		String requestUrl = TMDB + String.valueOf(id) + API_KEY + LANG;
		if (!append.isEmpty())
		{
			requestUrl = requestUrl + APPEND + append;
		}
		return constructMovieByUrl(requestUrl);
	}
	public Movie constructMovieById(int id)
	{
		return constructMovieById(id, "");
	}
	private int getIdFromSearch(String query)
	{
		String url = SEARCH_PREFIX + query + SEARCH_POSTFIX;
		return grabId(getRawRequest(url));
	}
	public Movie constructMovieBySearch(String query)
	{
		int id = getIdFromSearch(query);
		return constructMovieById(id, "credits");
	}
	
}
