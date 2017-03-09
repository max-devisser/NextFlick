package src.movie_builder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


import src.Movie;


public class MovieSerializationManager 
{
	/**
	 * Inline defined class used by Gson to know how to encode Movie objects as JSON
	 **/
	public static class MovieSerializer implements JsonSerializer<Movie>
	{

		/**
		 * Not much to see here, using Gson to set up conversion to JSON from Movie
		 **/
		@Override
		public JsonElement serialize(final Movie movie, final Type arg1, final JsonSerializationContext arg2) {
			final JsonObject result = new JsonObject();
			result.add("key", new JsonPrimitive(movie.getKey()));
			result.add("title", new JsonPrimitive(movie.getTitle()));
			result.add("date", new JsonPrimitive(movie.getDate()));
			result.add("director", new JsonPrimitive(movie.getDirector()));
			final JsonArray genre = new JsonArray();
			for (String s : movie.getGenre())
			{
				genre.add(new JsonPrimitive(s));
			}
			result.add("genre", genre);
			final JsonArray actors = new JsonArray();
			for (String s : movie.getActors())
			{
				actors.add(new JsonPrimitive(s));
			}
			result.add("actors", actors);
			result.add("parentalRating", new JsonPrimitive(movie.getParentalRating()));
			result.add("runtime", new JsonPrimitive(movie.getRuntime()));
			result.add("language", new JsonPrimitive(movie.getLanguage()));
			result.add("country", new JsonPrimitive(movie.getCountry()));
			result.add("criticalRating", new JsonPrimitive(movie.getCriticalRating()));
			result.add("plot", new JsonPrimitive(movie.getPlot()));
			result.add("imageURL", new JsonPrimitive(movie.getImageURL()));
			result.add("tagline", new JsonPrimitive(movie.getTagline()));
			result.add("popularity", new JsonPrimitive(movie.getPopularity()));
			return result;
		}
	}
	
	/**
	 * Inline defined class used by Gson to know how to decode Movie objects from JSON
	 **/
	public static class MovieDeserializer implements JsonDeserializer<Movie>
	{

		/**
		 * Not much to see here, using Gson to set up conversion from JSON to Movie
		 **/
		@Override
		public Movie deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context)
				throws JsonParseException {
			final JsonObject json = element.getAsJsonObject();
			final JsonElement key = json.get("key");
			final Movie result = new Movie(key.getAsInt());
			final JsonElement title = json.get("title");
			result.setTitle(title.getAsString());
			final JsonElement date = json.get("date");
			result.setDate(date.getAsString());
			final JsonElement director = json.get("director");
			result.setDirector(director.getAsString());
			
			
			final JsonElement parentalRating = json.get("parentalRating");
			result.setParentalRating(parentalRating.getAsString());
			final JsonElement runtime = json.get("runtime");
			result.setRuntime(runtime.getAsInt());
			final JsonElement language = json.get("language");
			result.setLanguage(language.getAsString());
			final JsonElement country = json.get("country");
			result.setCountry(country.getAsString());
			final JsonElement criticalRating = json.get("criticalRating");
			result.setCriticalRating(criticalRating.getAsDouble());
			final JsonElement plot = json.get("plot");
			result.setPlot(plot.getAsString());
			final JsonElement imageURL = json.get("imageURL");
			result.setImageURL(imageURL.getAsString());
			final JsonElement tagline = json.get("tagline");
			result.setTagline(tagline.getAsString());
			final JsonElement popularity = json.get("popularity");
			result.setPopularity(popularity.getAsDouble());
			final JsonArray genreJson = json.get("genre").getAsJsonArray();
			final ArrayList<String> genre = new ArrayList<String>();
			for (int i = 0; i < genreJson.size(); ++i)
			{
				final String currGenre = genreJson.get(i).getAsString();
				genre.add(currGenre);
			}
			result.setGenre(genre);
			final JsonArray actorsJson = json.get("actors").getAsJsonArray();
			final ArrayList<String> actors = new ArrayList<String>();
			for (int i = 0; i < actorsJson.size(); ++i)
			{
				final String currGenre = actorsJson.get(i).getAsString();
				actors.add(currGenre);
			}
			result.setActors(actors);
			return result;
		}
		
	}
	
	/**
	 * Converts text file to usual HashMap of movies
	 * @param inFileName The file containing the JSON text to be decoded, looks in "res" folder
	 * @return Spits out freshly minted Movie HashMap from input JSON text
	 **/
	public HashMap<Integer, Movie> deserialize(String inFileName)
	{
		GsonBuilder gsb = new GsonBuilder();
		gsb.registerTypeAdapter(Movie.class, new MovieDeserializer()); //Gson stuff
		Gson gson = gsb.create();
		Scanner in = null;
		try
		{
			in = new Scanner(new File("res" + File.separator + inFileName)); //set up file reader for input file
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		String currLine = "";
		HashMap<Integer, Movie> results = new HashMap<Integer, Movie>();
		while(in.hasNextLine())
		{
			currLine = in.nextLine();
			if (!currLine.isEmpty())
			{
				Movie currMovie = deserialize(currLine, gson); //deserialize line by line
				if (currMovie == null)
				{
					System.err.println("Deserialization failed.");
					System.err.println("Current Json object: " + currLine);
					in.close();
					return null;
				}
				else
				{
					results.put(currMovie.getKey(), currMovie);
				}
			}
		}
		in.close();
		return results;
	}
	
	/**
	 * Converts single JSON object to Movie
	 * @param json Single JSON object read in from text file
	 **/
	private Movie deserialize(String json, Gson gson)
	{
		Movie result = gson.fromJson(json, Movie.class);
		return result;
	}
	
	/**
	 * Converts collection of Movies to JSON objects in the given text file
	 * @param movies The Movie objects to be serialized
	 * @param outFileName The output file name for your JSON objects. Goes into "res" folder. Creates file if it doesn't exist or writes over it if it does
	 * @return Returns true if all objects were successfully serialized
	 **/
	public boolean serializeAppend(Collection<Movie> movies, String outFileName) throws IOException
	{
		final GsonBuilder gsb = new GsonBuilder();
		gsb.registerTypeAdapter(Movie.class, new MovieSerializer());
		final Gson gson = gsb.create();
		final FileWriter fw = new FileWriter("res" + File.separator + outFileName, true);
		final BufferedWriter writer = new BufferedWriter(fw);
		for (Movie m : movies)
		{
			if (!serialize(m, outFileName, gson, writer))
			{
				writer.close();
				return false;
			}
		}
		writer.write('\n');
		writer.close();
		return true;
	}
	
	/**
	 * Converts a single Movie to a one-line JSON object. Makes sure that Movie object has valid Title and Genre fields before serializing it
	 **/
	private boolean serialize(Movie movie, String outFileName, Gson gson, BufferedWriter writer)
	{
		if (!checkIntegrity(movie))
		{
			System.err.println("Movie with key " + movie.getKey() + " was not grabbed properly and is being discarded.");
			return true;
		}
		String json = gson.toJson(movie);
		try {
			writer.append(json);
			writer.append('\n');
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Makes sure that Movie object has valid Title and Genre fields before serializing it
	 **/
	private boolean checkIntegrity(Movie movie)
	{
		if (movie.getGenre().isEmpty())
		{
			return false;
		}
		String title = movie.getTitle();
												// some dank regex
		title = title.replaceAll("\\s+",""); // remove all whitespace
		if (title.isEmpty() || title.matches("\\?+")) // then check if everything that remains is a question mark - this checks for movies with messed up or empty titles
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Converts HashMap of Movies to JSON objects in the given text file
	 * @param movies The Movie objects to be serialized
	 * @param outFileName The output file name for your JSON objects. Goes into "res" folder. Creates file if it doesn't exist or writes over it if it does
	 * @return Returns true if all objects were successfully serialized
	 **/
	public boolean serializeOverwrite(Collection<Movie> movies, String outFileName) throws IOException
	{
		final GsonBuilder gsb = new GsonBuilder();
		gsb.registerTypeAdapter(Movie.class, new MovieSerializer());
		final Gson gson = gsb.create();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("res" + File.separator + outFileName)));
		for (Movie m : movies)
		{
			if (!serialize(m, outFileName, gson, writer))
			{
				writer.close();
				return false;
			}
		}
		writer.close();
		return true;
	}
}
