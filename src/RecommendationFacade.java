package src;

import java.util.ArrayList;

public class RecommendationFacade {
	public ArrayList<Movie> generateRecommendations() {
		RatingStorage ratingStorage = Controller.rateStorageFacade.getRatingStorage();
		ArrayList<Movie> recommendedMovies = RecommendationLogic.getRecommendationList(ratingStorage);
		return recommendedMovies;
	}
}