package src.movie_builder;
// Use OMdB or whatever to retreive information about movies, store them in Movie objects, then put them into a MovieCollection object
import java.io.IOException;
import java.util.ArrayList;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

import src.Movie;



public class MovieParser {
	private static String TMDB = "https://api.themoviedb.org/3/movie/";
	private static String API_KEY = "?api_key=d7160b15d167c28148a4b8491ff65b4d";
	private static String LANG = "&language=en-US";
	private static String APPEND = "&append_to_response=";
	private static String SEARCH_PREFIX = "https://api.themoviedb.org/3/search/movie?api_key=d7160b15d167c28148a4b8491ff65b4d&language=en-US&query=";
	private static String SEARCH_POSTFIX = "&page=1&include_adult=false";
	private static String IMAGE_PREFIX = "https://image.tmdb.org/t/p/w500";
	public MovieParser() {}
	
	public String getRawRequest(String url)
	{
		String stringtext;
		try {
			HttpResponse<String> request = Unirest.get(url).asString();
			//System.out.println("Success");
			stringtext = request.getBody();
			//System.out.println(stringtext);
			//Unirest.shutdown();
//		} catch (IOException e) {
//			System.out.println("Unirest shutdown failed.");
//			e.printStackTrace();
//			return null;
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
		JsonSearcher search = new JsonSearcher(rawtext);
		Movie movie = new Movie(grabId(search));
		movie.setTitle(grabTitle(search));
		movie.setDate(grabDate(search));
		movie.setDirector(grabDirector(search));
		movie.setGenre(grabGenres(search));
		movie.setActors(grabActors(search));
		movie.setParentalRating("Unrated"); //TODO you know the drill
		movie.setRuntime(grabRuntime(search));
		movie.setLanguage(grabLanguage(search));
		movie.setCountry(grabCountry(search));
		movie.setCriticalRating(grabCriticalRating(search));
		movie.setPlot(grabPlot(search));
		movie.setImageURL(grabImage(search));

		return movie;
		
	}
	private String grabDirector(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("\"director\"");
		search.skipToField("name");
		return search.readString();
	}
	private double grabCriticalRating(JsonSearcher search /*int popularityBias */)
	{
		search.resetIndex();
		search.skipToField("vote_average");
		return search.readDouble();
	}
	private String grabTitle(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("original_title");
		return search.readString();
	}
	private int grabId(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("homepage");
		search.skipToField("id");
		return search.readInt();
	}
	private int grabRuntime(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("runtime");
		return search.readInt();
	}
	private String grabImage(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("poster_path");
		return IMAGE_PREFIX + search.readString();
	}
	private ArrayList<String> grabGenres(JsonSearcher search)
	{
		search.resetIndex();
		return search.readArray("genres", "name");
	}
	private ArrayList<String> grabActors(JsonSearcher search)
	{
		search.resetIndex();
		return search.readArray("cast", "name");
	}
	private String grabCountry(JsonSearcher search)
	{
		search.resetIndex();
		ArrayList<String> countries = search.readArray("production_countries", "iso_3166_1");
		if (countries.contains("US"))
		{
			return "US";
		}
		else if (!countries.isEmpty())
		{
			return countries.get(0);
		}
		else
		{
			return "Unkown";
		}
	}
	//NOTE: this might give unintended results, possibly might need fixing
	private String grabLanguage(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("original_language");
		return search.readString();
	}
	private String grabDate(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("release_date");
		return search.readString();
	}
	private String grabPlot(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("overview");
		return search.readString();
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
	public int getIdFromSearch(String query)
	{
		String url = SEARCH_PREFIX + query + SEARCH_POSTFIX;
		return grabId(new JsonSearcher(getRawRequest(url)));
	}
	public Movie constructMovieBySearch(String query)
	{
		int id = getIdFromSearch(query);
		return constructMovieById(id, "credits");
	}
	
}
