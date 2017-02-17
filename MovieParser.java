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
	private MovieCollection movies;
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
		return movie;
		
	}
	private int grabIntParameter(String rawtext, String matchtext)
	{
		int index = rawtext.indexOf(matchtext);
		if (index == -1)
		{
			return -1;
		}
		int skipChars = 4;
		for (int i = 0; i < matchtext.length(); ++i)
		{
			if (Character.isAlphabetic(matchtext.charAt(i)))
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
		int index = rawtext.indexOf("\"id\"");
		index = rawtext.indexOf("\"id\"", index + 3);
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
		int skipChars = 4;
		for (int i = 0; i < matchtext.length(); ++i)
		{
			if (Character.isAlphabetic(matchtext.charAt(i)))
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
	public Movie constructMovieById(int id, String append) // tmdb id plus append_to_request command
	{
		String requestUrl = TMDB + String.valueOf(id);
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
	public int getIdFromSearch(String query)
	{
		String url = SEARCH_PREFIX + query + SEARCH_POSTFIX;
		return grabIntParameter(getRawRequest(url), "\"id\"");
	}
	public Movie constructMovieBySearch(String query)
	{
		int id = getIdFromSearch(query);
		return constructMovieById(id, "credits");
	}
}
