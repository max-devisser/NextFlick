package src;

import java.util.ArrayList;

/**
 * Intermediary class (much like a facade) to handle communicating with the
 * recommendation logic
 */
public class RecommendationApplication {

	/**
	 * Creates a list of recommended movies
	 * 
	 * @return List of recommended movies
	 */
	public ArrayList<Movie> generateRecommendations() {
		RatingStorage ratingStorage = Controller.rateStorageApplication.getRatingStorage();
		ArrayList<Movie> recommendedMovies = RecommendationLogic.getRecommendationList(ratingStorage);
		return recommendedMovies;
	}
}