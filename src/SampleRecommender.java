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

public class SampleRecommender {

	public static void main(String[] args) throws IOException, TasteException {
		DataModel model = new FileDataModel(new File("res/dataset.csv"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model); // For users X and Y, the following values are calculated:
					// sumX2: sum of the square of all X's preference values
					// sumY2: sum of the square of all Y's preference values
					// sumXY: sum of the product of X and Y's preference value for all items for which both X and Y express a preference
					// The correlation is then: sumXY / sqrt(sumX2 * sumY2)
					// Note that this correlation "centers" its data, shifts the user's preference values so that each of their means is 0. 
					// This is necessary to achieve expected behavior on all data sets.
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model); // Initializes a neighborhood, 
					// which is a group of users that are similar, which will be used to give recommendations based on what other 
					// members of the neighborhood liked
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		List<RecommendedItem> recommendations = recommender.recommend(2, 3); // Get 3 recommendations for user 2
		
		for (RecommendedItem recommendation : recommendations) {
		  System.out.println(recommendation);
		}
		// Look at res/dataset.csv and you'll see that user 2 like movies 15 16 18, so it'll match him with users who share
		// those tastes. User 1 also rated those high, so it'll recommend movies that user 1 rated highly that user 2 hasn't seen
		// AKA movies 12 13 14. This also aligns with correlations between users 3 and 4 too.
	}

}
