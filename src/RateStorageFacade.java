package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.io.*;

public class RateStorageFacade {
	private static RatingStorage ratingStorage;

	public RateStorageFacade() {
		// Deserialize rating storage object if it exists
		try {
			FileInputStream fs = new FileInputStream("res/History.ser");
			ObjectInputStream is = new ObjectInputStream(fs);
			ratingStorage = (RatingStorage) is.readObject();
			is.close();
		} catch (Exception ex) { // file has not yet been created: start from scratch
			ratingStorage = new RatingStorage();
		}
	}

	public RatingStorage getRatingStorage() {
		return ratingStorage;
	}

	public int getRating(Movie movie) {
		return RateLogic.getRating(movie);
	}

	public void rateMovie(Movie movie, int rating) {
		RateLogic.addRating(movie, rating); // If movie is not rated, add to appropriate sub list
											// Otherwise, remove from prev list and re add
	}

	public ArrayList<Movie> filterByRating(boolean[] ratingsToShow) {
		ArrayList<Movie> filteredRatingHistory = new ArrayList<Movie>();

		for (int rateListIndex = 0; rateListIndex < 5; rateListIndex++) {
			if (ratingsToShow[rateListIndex])
				filteredRatingHistory.addAll(RateLogic.getRatingList(rateListIndex));
		}

		Collections.reverse(filteredRatingHistory);

		return filteredRatingHistory;
	}

	
	
	private static class RateLogic {
		public static int getRating(Movie movie) {
			int userRating = 0;

			if (ratingStorage.containsMovie(movie.getKey())) {
				HashMap<Integer, Integer> ratingMap = ratingStorage.getRatingMap();
				userRating = ratingMap.get(movie.getKey());
			}
			return userRating;
		}

		public static ArrayList<Movie> getRatingList(int rateListIndex) {
			ArrayList<Movie> ratingList = ratingStorage.getRatingLists()[rateListIndex];
			return ratingList;
		}

		public static void addRating(Movie movie, int rating) {
			ratingStorage.addRating(movie, rating);
		}
	}
}