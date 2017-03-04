package src.movie_builder;
import java.util.ArrayList;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import src.Movie;



public class MovieParser {
	// a bunch of constant strings useful for building URLs
	private static String TMDB = "https://api.themoviedb.org/3/movie/";
	private static String API_KEY = "?api_key=d7160b15d167c28148a4b8491ff65b4d";
	private static String LANG = "&language=en-US";
	private static String APPEND = "&append_to_response=";
	private static String SEARCH_PREFIX = "https://api.themoviedb.org/3/search/movie?api_key=d7160b15d167c28148a4b8491ff65b4d&language=en-US&query=";
	private static String SEARCH_POSTFIX = "&page=1&include_adult=false";
	
	// this URL is important. Note: w500 is a pretty high quality image, this should be downgraded
	private static String IMAGE_PREFIX = "https://image.tmdb.org/t/p/w500";
	
	/**
     * Empty constructor cause it don't need to do nothin
   **/
	public MovieParser() {}
	
	/**
     * Uses Unirest to retrieve API request specified by the given URL
     * @param url The full API request URL to be retrieved
     * @return Returns StringBuilder object with full JSON response
   **/
	public StringBuilder getRawRequest(String url)
	{
		StringBuilder stringtext = new StringBuilder();
		try {
			HttpResponse<String> request = Unirest.get(url).asString();
			//System.out.println("Success");
			stringtext.append(request.getBody());
			//System.out.println(stringtext);
		} catch (UnirestException e){
			e.printStackTrace();
			System.out.println("Unirest threw an error when connecting to given URL.");
			return null;
		}
		return stringtext;
	}
	
	/**
     * Calls helper functions to create movie from the given URL
     * @param url The full API request URL to be retrieved
     * @return Returns Movie object constructed from the JSON object 
   **/
	public Movie constructMovieByUrl(String url) // must have full request URL
	{
		StringBuilder rawtext = getRawRequest(url);
		JsonSearcher search = new JsonSearcher(rawtext); //create JsonSearcher to help functions look through JSON text
		Movie movie = new Movie(grabId(search));
		movie.setLanguage(grabLanguage(search));
		movie.setTitle(grabTitle(search, movie.getLanguage())); //which title to grab depends on language
		movie.setDate(grabDate(search));
		movie.setDirector(grabDirector(search));
		movie.setGenre(grabGenres(search));
		movie.setActors(grabActors(search));
		movie.setParentalRating(grabParentalRating(search));
		movie.setRuntime(grabRuntime(search));
		movie.setCountry(grabCountry(search));
		movie.setCriticalRating(grabCriticalRating(search));
		movie.setPlot(grabPlot(search));
		movie.setImageURL(grabImage(search));
		movie.setTagline(grabTagline(search));
		movie.setPopularity(grabPopularity(search));

		return movie;
		
	}
	
	/**
     * Grabs parental rating from JSON
   **/
	private String grabParentalRating(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("releases");
		ArrayList<String> certs = new ArrayList<String>();
		certs = search.readArray("countries", "certification");
		for (String s : certs) //the "releases" part of the JSON response was confusing
								//so i just made it grab all of the different certifications and look for an MPAA rating
		{
			if (s.equals("R") || s.equals("PG-13") || s.equals("PG") || s.equals("G") || s.equals("NC-17"))
			{
				return s;
			}
		}
		return "NR/Unkown";
	
		
	}
	
	/**
     * Grabs director from JSON
   **/
	private String grabDirector(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToContainer("crew");
		search.skipToField("Director");
		search.skipToField("name");
		return search.readString();
	}
	
	/**
     * Grabs critical rating from JSON
   **/
	private double grabCriticalRating(JsonSearcher search /*int popularityBias */)
	{
		search.resetIndex();
		search.skipToField("vote_average");
		return search.readDouble();
	}
	
	/**
     * Grabs title from JSON
   **/
	private String grabTitle(JsonSearcher search, String lang)
	{
		search.resetIndex();
		if (lang != "en") //if the language isn't english we want the translated title
		{
			search.skipToField("tagline");
			search.skipToField("title");
		}
		else
		{
			search.skipToField("original_title"); //otherwise might as well get the original title
		}
		return search.readString();
	}
	
	/**
     * Grabs ID from JSON
   **/
	private int grabId(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("homepage"); //because there are other "id" fields before that
		search.skipToField("id");
		return search.readInt();
	}
	
	/**
     * Grabs runtime from JSON
   **/
	private int grabRuntime(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("runtime");
		return search.readInt();
	}
	
	/**
     * Grabs imageURL from JSON
   **/
	private String grabImage(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("popularity");
		search.skipToField("poster_path");
		return search.readString();
	}
	
	/**
     * Grabs genres from JSON
   **/
	private ArrayList<String> grabGenres(JsonSearcher search)
	{
		search.resetIndex();
		return search.readArray("genres", "name");
	}
	
	/**
     * Grabs Actor List from JSON
     * List will contain no more than 10 actors
   **/
	private ArrayList<String> grabActors(JsonSearcher search)
	{
		search.resetIndex();
		return search.readArray("cast", "name");
	}
	
	/**
     * Grabs country from JSON
     * The first country listed often was not US even for US productions, so I biased this toward being US
   **/
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
			return "Unknown";
		}
	}
	
	/**
     * Grabs language from JSON
     * NOTE: this might give unintended results, possibly might need fixing
   **/	
	private String grabLanguage(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("original_language");
		return search.readString();
	}
	
	/**
     * Grabs date from JSON
   **/
	private String grabDate(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("release_date");
		return search.readString();
	}
	
	/**
     * Grabs plot from JSON
   **/
	private String grabPlot(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("overview");
		return search.readString();
	}
	
	/**
     * Grabs tagline from JSON
   **/
	private String grabTagline(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("tagline");
		return search.readString();
	}
	
	/**
     * Grabs popularity from JSON
   **/
	private double grabPopularity(JsonSearcher search)
	{
		search.resetIndex();
		search.skipToField("popularity");
		return search.readDouble();
	}
	
	/**
	 * Constructs movie with given ID and append command(s)
     * @param id The TMDB id of the movie to be constructed
     * @param append What fields to append to response, separate commands separated by commas, should always at least include "credits,releases"
     * @return Creates request URL and passes to constructMovieByURL, then returns result
   **/
	public Movie constructMovieById(int id, String append) 
	{
		String requestUrl = TMDB + String.valueOf(id) + API_KEY + LANG;
		if (!append.isEmpty())
		{
			requestUrl = requestUrl + APPEND + append;
		}
		return constructMovieByUrl(requestUrl);
	}
	
	/**
     * Inputs default "credits,releases" value to the append parameter, otherwise identical to the other version
     * @param id The TMDB id of the movie to be constructed
     * @return Creates request URL and passes to constructMovieByURL, then returns result
   **/
	public Movie constructMovieById(int id)
	{
		return constructMovieById(id, "credits,releases");
	}
	
	/**
	 * Uses TMDB search function to search the given query and gives you the top result - NOT GUARANTEED TO BE WHAT YOU'RE LOOKING FOR
     * @param query The movie title you're searching for, formatted in URL style e.g. "Batman%20the%20dark%knight"
     * @return Returns TMDB ID of the top result for your query. If there are no results, throws exception and stops execution
   **/
	public int getIdFromSearch(String query)
	{
		String url = SEARCH_PREFIX + query + SEARCH_POSTFIX;
		return grabId(new JsonSearcher(getRawRequest(url)));
	}
//	public Movie constructMovieBySearch(String query)
//	{
//		int id = getIdFromSearch(query);
//		return constructMovieById(id);
//	}
	
}
