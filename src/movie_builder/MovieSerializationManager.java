package src.movie_builder;

import java.util.ArrayList;
import java.util.HashMap;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonDeserializationContext;
//import com.google.gson.JsonDeserializer;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParseException;
//import com.google.gson.JsonPrimitive;
//import com.google.gson.JsonSerializationContext;
//import com.google.gson.JsonSerializer;
//import com.google.gson.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import src.Movie;
//import src.MovieCollection;

/**
 * Serializes movies
 */
public class MovieSerializationManager implements Serializable {
	private ArrayList<Movie> movies;

	/**
	 * Constructor, sets member variable movies
	 * 
	 * @param mov
	 *            List of movies for serialization
	 */
	public MovieSerializationManager(ArrayList<Movie> mov) {
		movies = mov;
	}

	/**
	 * Serializes movies to res/Top_250_serialize.ser
	 */
	public void serialize() {
		try {
			FileOutputStream fo = new FileOutputStream("res" + File.separator + "Top_250_serialized.ser");
			ObjectOutputStream os = new ObjectOutputStream(fo);
			os.writeObject(this);
			os.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Serializes movies to res/'outputfilename'.ser
	 * 
	 * @param outfilename
	 *            File to serialize movies to
	 */
	public void serialize(String outfilename) {
		try {
			FileOutputStream fo = new FileOutputStream("res" + File.separator + outfilename);
			ObjectOutputStream os = new ObjectOutputStream(fo);
			os.writeObject(this);
			os.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Deserializes movies
	 * 
	 * @param inFileName
	 *            File to deserialize from
	 * @return Map<ID, movie> of all movies in the file that was deserialized
	 */
	public HashMap<Integer, Movie> deserialize(String inFileName) {
		HashMap<Integer, Movie> result = new HashMap<Integer, Movie>();
		MovieSerializationManager mc = null;
		try {
			FileInputStream fs = new FileInputStream("res" + File.separator + inFileName);
			ObjectInputStream is = new ObjectInputStream(fs);
			mc = (MovieSerializationManager) is.readObject();
			is.close();
		} catch (Exception ex) { // file has not yet been created: start from
									// scratch
			ex.printStackTrace();
			mc = new MovieSerializationManager(null);
		}
		for (Movie m : mc.movies) {
			result.put(m.getKey(), m);
		}
		return result;
	}
}
