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
	public static class MovieSerializer implements JsonSerializer<Movie>
	{

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
			return result;
		}
	}
	public static class MovieDeserializer implements JsonDeserializer<Movie>
	{

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
	public HashMap<Integer, Movie> deserialize(String inFileName) throws FileNotFoundException
	{
		GsonBuilder gsb = new GsonBuilder();
		gsb.registerTypeAdapter(Movie.class, new MovieDeserializer());
		Gson gson = gsb.create();
		Scanner in = new Scanner(new File("res" + File.separator + inFileName));
		String currLine = "";
		HashMap<Integer, Movie> results = new HashMap<Integer, Movie>();
		while(in.hasNextLine())
		{
			currLine = in.nextLine();
			if (!currLine.isEmpty())
			{
				Movie currMovie = deserialize(currLine, gson);
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
	private Movie deserialize(String json, Gson gson)
	{
		Movie result = gson.fromJson(json, Movie.class);
		return result;
	}
	public boolean serialize(Collection<Movie> movies, String outFileName) throws IOException
	{
		final GsonBuilder gsb = new GsonBuilder();
		gsb.registerTypeAdapter(Movie.class, new MovieSerializer());
		final Gson gson = gsb.create();
		final BufferedWriter writer = new BufferedWriter(new FileWriter(new File("res" + File.separator + outFileName)));
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
	private boolean serialize(Movie movie, String outFileName, Gson gson, BufferedWriter writer)
	{
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
	public boolean serialize(Map<Integer, Movie> movies, String outFileName) throws IOException
	{
		final GsonBuilder gsb = new GsonBuilder();
		gsb.registerTypeAdapter(Movie.class, new MovieSerializer());
		final Gson gson = gsb.create();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("res" + File.separator + outFileName)));
		for (Movie m : movies.values())
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
