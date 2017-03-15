package src;

import java.util.ArrayList;
import java.util.Collections;

public class RateStorageFacade {
	public static RatingStorage ratingStorage;

	public RateStorageFacade() {
		ratingStorage = new RatingStorage();
	}

	// public boolean ratingStorageEmpty() {
	// 	if (ratingStorage.getRatingMap().isEmpty())
	// 		return true;
	// 	else
	// 		return false;
	// }

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
}