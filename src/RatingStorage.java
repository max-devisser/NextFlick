package src;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores ratings of movies in two member variables, ratingLists (array of five
 * lists of movies, one for each rating) and ratingMap (map with key as movie ID
 * and value as rating associated with that movie)
 */
public class RatingStorage implements Serializable {
	private ArrayList<Movie>[] ratingLists;
	private HashMap<Integer, Integer> ratingMap;

	/**
	 * Constructor, initializes both member variables to be empty
	 */
	public RatingStorage() {
		ratingLists = (ArrayList<Movie>[]) new ArrayList[5];
		for (int currentList = 0; currentList < 5; currentList++)
			ratingLists[currentList] = new ArrayList<Movie>();
		ratingMap = new HashMap<Integer, Integer>();
	}

	/**
	 * Checks if ratingMap contains a movie
	 * 
	 * @param tmdbID
	 *            ID of movie to check for
	 * @return Whether the movie is rated and in the ratingMap
	 */
	public boolean containsMovie(int tmdbID) {
		return ratingMap.containsKey(tmdbID);
	}

	/**
	 * Getter
	 * 
	 * @return ratingLists, array of five ArrayLists of movies, organized by
	 *         rating
	 */
	public ArrayList<Movie>[] getRatingLists() {
		return ratingLists;
	}

	/**
	 * Getter
	 * 
	 * @return ratingMap, map of all movies that have ratings in the form <ID,
	 *         rating>
	 */
	public HashMap<Integer, Integer> getRatingMap() {
		return ratingMap;
	}

	/**
	 * Adds rating to a movie
	 * 
	 * @param movie
	 *            Movie to add rating for
	 * @param rating
	 *            Rating to give
	 */
	public void addRating(Movie movie, int rating) {
		if (ratingMap.containsKey(movie.getKey())) {
			int previousRating = ratingMap.get(movie.getKey());
			ratingLists[previousRating - 1].remove(movie);
			ratingMap.remove(movie.getKey());
		}

		ratingLists[rating - 1].add(movie);
		ratingMap.put(movie.getKey(), rating);
		serialize();
	}

	/**
	 * Removes rating from a movie
	 * 
	 * @param movie
	 *            Movie to remove rating from
	 */
	public void deleteRating(Movie movie) {
		if (ratingMap.containsKey(movie.getKey())) {
			int previousRating = ratingMap.get(movie.getKey());
			ratingLists[previousRating - 1].remove(movie);
			ratingMap.remove(movie.getKey());
			serialize();
		}
	}

	/*
	 * (non-Javadoc) Returns string representation of all rated movies
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String libraryStringList = "";
		for (Integer currentID : ratingMap.keySet()) {
			String title = Controller.libraryApplication.getMovie(currentID).getTitle();
			String rating = ratingMap.get(currentID).toString();
			libraryStringList += "\n" + title + ": " + rating;
		}
		return libraryStringList;
	}

	/**
	 * Writes this class to this file res/History.ser for storage of ratings
	 * between uses
	 */
	public void serialize() {
		try {
			FileOutputStream fo = new FileOutputStream("res/History.ser");
			ObjectOutputStream os = new ObjectOutputStream(fo);
			os.writeObject(this);
			os.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}