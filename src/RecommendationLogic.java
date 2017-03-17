package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * Uses Apache Mahout to generate recommendations based on similarity of users.
 * Pulls rating histories of 671 users from a csv file of 100,000 ratings, then
 * finds users with similar taste profiles to our user, and generates
 * recommendations based on movies that similar users enjoyed that our user has
 * not rated.
 */
public class RecommendationLogic {

	/**
	 * Creates a list of recommended movies
	 * 
	 * @param ratingStorage
	 *            Lists of user's previous ratings
	 * @return List of recommended movies
	 */
	public static ArrayList<Movie> getRecommendationList(RatingStorage ratingStorage) {

		if (ratingStorage.getRatingMap().size() == 0) { // There are no previous ratings to base recommendations on
			return new ArrayList<Movie>();
		}

		// 1. add user to database first create copy the data to a new file
		// which will be overwritten every time with the user's ratings
		// appended to the end. Need to overwrite so that the same rating
		// does not get added multiple times

		File sourceFile = new File("res/ml-latest-small/ratings_id_replaced2.csv");
		File destFile = new File("res/ml-latest-small/ratings_id_replaced3.csv");

		/* if file not exist then create one */
		if (!destFile.exists()) {
			try {
				destFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(sourceFile);
			output = new FileOutputStream(destFile);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != input) {
					input.close();
				}
				if (null != output) {
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			FileWriter fw = new FileWriter("res/ml-latest-small/ratings_id_replaced3.csv", true);
			BufferedWriter bw = new BufferedWriter(fw);
			HashMap<Integer, Integer> history = ratingStorage.getRatingMap();
			for (Integer currentID : history.keySet()) {
				bw.newLine();
				String append = "672," + currentID + "," + history.get(currentID);
				bw.write(append, 0, append.length());
			}
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Unable to write user ratings to file: ");
			System.out.println(e.getMessage());
		}

		// 2. Get the recommendations
		List<RecommendedItem> recommendations = new ArrayList<RecommendedItem>();
		try {
			DataModel model = new FileDataModel(new File("res/ml-latest-small/ratings_id_replaced3.csv"));
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.01, similarity, model);
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			recommendations = recommender.recommend(672, 100);
		} catch (Exception ex) {
			System.out.println("Unable to create recommendations: ");
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

		// 3. Parse the RecommendedItems into Movies
		ArrayList<Movie> result = new ArrayList<Movie>();
		HashMap<Integer, Movie> library = Controller.libraryApplication.getFullLibraryMap();
		for (RecommendedItem recommendation : recommendations) {
			Movie recommendedMovie = library.get((int) recommendation.getItemID());
			if (recommendedMovie != null) { // make sure the movie is in our database as well
				result.add(recommendedMovie);
			}
		}

		return result;
	}
}