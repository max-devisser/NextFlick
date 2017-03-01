package src.movie_builder;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;

import src.Movie;


public class MovieSerializationManager 
{
	public static class MovieSerializer implements JsonSerializer<Movie>
	{

		@Override
		public JsonElement serialize(final Movie movie, final Type arg1, final JsonSerializationContext arg2) {
			JsonObject result = new JsonObject();
			result.add("key", new JsonPrimitive(movie.getKey()));
			result.add("title", new JsonPrimitive(movie.getTitle()));
			result.add("date", new JsonPrimitive(movie.getDate()));
			result.add("director", new JsonPrimitive(movie.getDirector()));
			
		//   private int key;
		//   private String title;
		//   private String date;
		//   private String director;
		//   private ArrayList<String> genre;
		//   private ArrayList<String> actors;
		//   private String parentalRating;
		//   private int runtime;
		//   private String language;
		//   private String country;
		//   private double criticalRating;
		//   private String plot;
		//   private String imageURL;
		}
		
	}
	public HashMap<Integer, Movie> deserialize()
	{
		
	}
	public boolean serialize(Collection<Movie> movies, String outFileName)
	{
		for (Movie m : movies)
		{
			if (!serialize(m, outFileName))
			{
				return false;
			}
		}
		return true;
	}
	public boolean serialize(Movie movie, String outFileName)
	{
		
	}
	public boolean serialize(Map<Integer, Movie> movies, String outFileName)
	{
		for (Movie m : movies.values())
		{
			if (!serialize(m, outFileName))
			{
				return false;
			}
		}
		return true;
	}
}
