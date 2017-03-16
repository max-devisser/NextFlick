package src;

import java.io.*;
import java.util.*;

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

public class RecommendationLogic {

	public static ArrayList<Movie> getRecommendationList(RatingStorage ratingStorage) {
//		if(ratingStorage.getRatingMap().size() < 4) {	//no recommendations if user has rated less than 4 movies
//			return new ArrayList<Movie>();
//		}
		//1. add user to database
		//first create copy the data to a new file which will be overwritten every time with
		//the user's ratings appended to the end.
		//need to overwrite so that the same rating does not get added multiple times
		
		//File sourceFile = new File("res/ml-latest-small/ratings_id_replaced2.csv");
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
		}
		finally {
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

		try{
			FileWriter fw = new FileWriter("res/ml-latest-small/ratings_id_replaced3.csv", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    HashMap<Integer, Integer> history = ratingStorage.getRatingMap();
		    for(Integer currentID : history.keySet()){
		   		bw.newLine();
		   		String append = "672,"+ currentID +","+ history.get(currentID);
	 			bw.write(append,0,append.length());
			}
			bw.flush();
			bw.close();
			fw.close();

		} catch (IOException e) {
			System.out.println("Unable to write user ratings to file: ");
		    System.out.println(e.getMessage());
		}
		finally{

		}
		
		//2. Get the recs
		List<RecommendedItem> recommendations = new ArrayList();
		try{
			DataModel model = new FileDataModel(new File("res/ml-latest-small/ratings_id_replaced3.csv"));
			//DataModel model = new FileDataModel(sourceFile);
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model); 
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.01, similarity, model); 
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			recommendations = recommender.recommend(672, 10);
		}
		catch(Exception ex){
			System.out.println("Unable to create recommendations: ");
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

		//3. Parse the RecommendedItems into Movies
		ArrayList<Movie> result = new ArrayList<Movie>();
		System.out.println(recommendations.size());
		HashMap<Integer, Movie> library = Controller.libraryFacade.getFullLibraryMap();
		for (RecommendedItem recommendation : recommendations) {
			long id = recommendation.getItemID();
			Integer intID = (int) id;
			Movie recommendedMovie = library.get(intID);
			if (recommendedMovie != null) {	//make sure the movie is in our database as well
				result.add(recommendedMovie);
			}
			
		}

		return result;
	}
}