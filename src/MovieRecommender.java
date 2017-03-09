package src;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class MovieRecommender {

	public static void main(String[] args) throws IOException, TasteException {
		DataModel model = new FileDataModel(new File("res/ml-latest-small/ratings_formatted.csv"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model); 
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model); 
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		List<RecommendedItem> recommendations = recommender.recommend(6, 10);
		
		System.out.println("User 6's top rated movies: (not all, just a few ");
		System.out.println("Leon: The Professional");
		System.out.println("Lawrence of Arabia");
		System.out.println("LotR: The Two Towers");
		System.out.println("The Iron Giant");
		System.out.println("Stand by Me");
		System.out.println("Theme: adventure");
		
		System.out.println("Number values of recommendations");
		for (RecommendedItem recommendation : recommendations) {
		  System.out.println(recommendation);
		}
		
		System.out.println("Recommendation names");
		System.out.println("Les Diaboliques, Horror/mystery/thriller");
		System.out.println("A Passage to India, Adventure/drama");
		System.out.println("Hello, Dolly!, Comedy/musical/romance");
		System.out.println("The Unvanquished, Drama");
		System.out.println("'night, Mother, Drama");
		System.out.println("Trouble in Paradise, Comedy/romance");
		System.out.println("The Holy Mountain, Drama");
		System.out.println("Shall we Dance, Comedy/musical/romance");
		System.out.println("A Face in the Crowd, Drama");
		System.out.println("Black Cat, White Cat, Comedy/Romance");

		
	}

}
