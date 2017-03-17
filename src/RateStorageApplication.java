package src;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Intermediary class (much like a facade) that handles movie ratings and storing them
 */
public class RateStorageApplication {
	private static RatingStorage ratingStorage;
	
	/**
	 * Constructor, either reads from History file or makes a new RatingStorage
	 */
	public RateStorageApplication() {
		try {
			FileInputStream fs = new FileInputStream("res/History.ser");
			ObjectInputStream is = new ObjectInputStream(fs);
			ratingStorage = (RatingStorage) is.readObject();
			is.close();
		} catch (Exception ex) {
			ratingStorage = new RatingStorage();
		}
	}

	/**
	 * Getter
	 * @return ratingStorage
	 */
	public RatingStorage getRatingStorage() {
		return ratingStorage;
	}

	/**
	 * Getter
	 * @param movie Movie to get the rating of
	 * @return Rating of the specified movie
	 */
	public int getRating(Movie movie) {
		return RateLogic.getRating(movie);
	}

	/**
	 * Calls on RateLogic's addRating, adds a rating to a movie
	 * @param movie Movie to rate
	 * @param rating Rating to give
	 */
	public void rateMovie(Movie movie, int rating) {
		RateLogic.addRating(movie, rating);
	}
	
	/**
	 * Calls on RateLogic's removeRating, removes a rating from a movie
	 * @param movie Movie to remove the rating from
	 */
	public void unRateMovie(Movie movie) {
		RateLogic.removeRating(movie);
	}


	/**
	 * 
	 * @param ratingsToShow
	 * @return
	 */
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
		public static void removeRating(Movie movie) {
			ratingStorage.deleteRating(movie);
		}
	}
}