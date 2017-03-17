package src;

import java.util.ArrayList;

public class RecommendationApplication {
	public ArrayList<Movie> generateRecommendations() {
		RatingStorage ratingStorage = Controller.rateStorageApplication.getRatingStorage();
		ArrayList<Movie> recommendedMovies = RecommendationLogic.getRecommendationList(ratingStorage);
		return recommendedMovies;
	}
}