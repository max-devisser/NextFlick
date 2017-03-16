package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.io.*;

public class RatingStorage implements Serializable {
	private ArrayList<Movie>[] ratingLists;
	private HashMap<Integer, Integer> ratingMap;

	public RatingStorage() {
		ratingLists = (ArrayList<Movie>[])new ArrayList[5];
		for (int currentList = 0; currentList < 5; currentList++)
			ratingLists[currentList] = new ArrayList<Movie>();
		ratingMap = new HashMap<Integer, Integer>();
	}

	public boolean containsMovie(int tmdbID) {
		return ratingMap.containsKey(tmdbID);
	}

	public ArrayList<Movie>[] getRatingLists() {
		return ratingLists;
	}

	public HashMap<Integer, Integer> getRatingMap() {
		return ratingMap;
	}

	public void addRating(Movie movie, int rating) {
		ratingLists[rating - 1].add(movie);
		ratingMap.put(movie.getKey(), rating);
		serialize();
	}

	public String toString() {
		String libraryStringList = "";
		for (Integer currentID : ratingMap.keySet()) {
			String title = Controller.libraryFacade.getMovie(currentID).getTitle();
			String rating = ratingMap.get(currentID).toString();
			libraryStringList+= "\n" + title + ": " + rating;
		}
		return libraryStringList;
	}

	public void serialize() {
		System.out.println("serialized");
		try {
					System.out.println("try");

			FileOutputStream fo = new FileOutputStream("res/History.ser");
			ObjectOutputStream os = new ObjectOutputStream(fo);
			os.writeObject(this);
			os.close();
					System.out.println("success");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}