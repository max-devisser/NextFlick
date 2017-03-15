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

	public boolean isEmpty() {
		return ratingMap.isEmpty();
	}

	public ArrayList<Movie>[] getRatingLists() {
		return ratingLists;
	}

	public HashMap<Integer, Integer> getRatingMap() {
		return ratingMap;
	}

	public void addRating(Movie movie, int rating) {
		ratingLists[rating].add(movie);
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
		try {
			FileOutputStream fo = new FileOutputStream("History.ser");
			ObjectOutputStream os = new ObjectOutputStream(fo);
			os.writeObject(this);
			os.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}