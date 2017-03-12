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

public class MovieRecommender {

	public static void main(String[] args) throws IOException, TasteException {
		DataModel model = new FileDataModel(new File("res/ml-latest-small/ratings_id_replaced2.csv"));
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
	public static ArrayList<Movie> recommend(RatingHistory ratings){
		if(ratings.isEmpty()){	//no recommendations if user has not rated any movies
			return new ArrayList<Movie>();
		}
		//1. add user to database
		try{
			FileWriter fw = new FileWriter("res/ml-latest-small/ratings_id_replaced2.csv", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    HashMap<Movie, Integer> history = ratings.getHistory();
		    for(Movie movie: history.keySet()){
		    	int id = movie.getKey();
		   		bw.newLine();
		    	String append = "672,"+id+","+history.get(movie); //userID,movieID,rating
	 			bw.write(append,0,append.length());
			}
			bw.flush();

		} catch (IOException e) {
			System.out.println("Unable to write user ratings to file: ");
		    System.out.println(e.getMessage());
		}


		//2. Get the recs
		List<RecommendedItem> recommendations = new ArrayList();
		try{
			DataModel model = new FileDataModel(new File("res/ml-latest-small/ratings_id_replaced2.csv"));
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model); 
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model); 
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			recommendations = recommender.recommend(6, 10);
		}
		catch(Exception ex){
			System.out.println("Unable to create recommendations: ");
			System.out.println(ex.getMessage());
		}
		//3. Remove user from database

		//4. Parse the RecommendedItems into Movies
		ArrayList<Movie> result = new ArrayList<Movie>();
		System.out.println(recommendations.size());
		for (RecommendedItem recommendation : recommendations) {
			Movie movie = parse(recommendation.getItemID());
			result.add(movie);
		}

		return result;
	}
	public static Movie parse(long id){
		System.out.println("parse");
		MapBuilder maps = new MapBuilder();
		HashMap<Integer, Movie> data = maps.getFullMovieMap();
				System.out.println("parse2");
		for(Integer i: data.keySet()){
			if(i==id){
				return data.get(i);
			}
		}
		return new Movie(0);
	}
}
