package src;

import java.util.ArrayList;
import java.util.HashMap;

public class RateLogic {
	public static int getRating(Movie movie) {
		int userRating = 0;

		if (RateStorageFacade.ratingStorage.containsMovie(movie.getKey())) {
			HashMap<Integer, Integer> ratingMap = RateStorageFacade.ratingStorage.getRatingMap();
			userRating = ratingMap.get(movie.getKey());
		}
		return userRating;
	}

	public static ArrayList<Movie> getRatingList(int rateListIndex) {
		ArrayList<Movie> ratingList = RateStorageFacade.ratingStorage.getRatingLists()[rateListIndex];
		return ratingList;
	}

	public static void addRating(Movie movie, int rating) {
		RateStorageFacade.ratingStorage.addRating(movie, rating);
	}
}