package src;

import java.util.*;
import src.movie_builder.TestCollectionBuilder;

public class MapBuilder
{
	//Probably useless maps
	//private HashMap<String, ArrayList<Movie>> titleMap;
	//private HashMap<String, ArrayList<Movie>> plotMap;
	//private HashMap<String, ArrayList<Movie>> imageURLMap;

	//Done
	private HashMap<String, ArrayList<Movie>> dateMap = new HashMap<String, ArrayList<Movie>>();
	//Done
	private HashMap<String, ArrayList<Movie>> directorMap = new HashMap<String, ArrayList<Movie>>();
	//Done
	private HashMap<String, ArrayList<Movie>> genreMap = new HashMap<String, ArrayList<Movie>>();
	//Done
	private HashMap<String, ArrayList<Movie>> actorMap = new HashMap<String, ArrayList<Movie>>();
	//Done
	private HashMap<String, ArrayList<Movie>> parentalRatingMap = new HashMap<String, ArrayList<Movie>>();
	//Done
	private HashMap<Integer, ArrayList<Movie>> runtimeMap = new HashMap<Integer, ArrayList<Movie>>();
	//Done
	private HashMap<String, ArrayList<Movie>> languageMap = new HashMap<String, ArrayList<Movie>>();
	//Done
	private HashMap<String, ArrayList<Movie>> counrtryMap = new HashMap<String, ArrayList<Movie>>();
	//Not Done
	private HashMap<Double, ArrayList<Movie>> criticalRatingMap = new HashMap<Double, ArrayList<Movie>>();
	


	private TestCollectionBuilder TCB;
	private HashMap<Integer, Movie> database;


	// public static void main(String[] args) {
	// 	MapBuilder test = new MapBuilder();
	// }

	public MapBuilder() {

		TCB = new TestCollectionBuilder();
		database = TCB.getTestCollection();
		makeCriticalRatingMap(database);

		for (Double key: criticalRatingMap.keySet()) {

			System.out.println(key + ": ");

			for (Movie item: criticalRatingMap.get(key))
				System.out.print(item.getCriticalRating() + " ");

			System.out.println();
		}
	}

	private void makeStringMap(HashMap<Integer, Movie> inputMap) {
		
	}

	//Make the HashMap to store movies with the same date
	private void makeDateMap(HashMap<Integer, Movie> inputMap) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			String dateMapKey = currentMovie.getDate();

			if (!dateMap.containsKey(dateMapKey)) {
				ArrayList<Movie> myList = new ArrayList<Movie>();
				myList.add(currentMovie);
    			dateMap.put(dateMapKey, myList);
    		}

    		else {
    			dateMap.get(dateMapKey).add(currentMovie);
    		}	
		}
	}

	//Make the HashMap to store movies with the same director
	private void makeDirectorMap(HashMap<Integer, Movie> inputMap) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			String directorMapKey = currentMovie.getDirector();

			if (!directorMap.containsKey(directorMapKey)) {
				ArrayList<Movie> myList = new ArrayList<Movie>();
				myList.add(currentMovie);
    			directorMap.put(directorMapKey, myList);
    		}

    		else {
    			directorMap.get(directorMapKey).add(currentMovie);
    		}	
		}
	}

	//Make the HashMap to store movies with the same genre
	//Create entry for each genre 
	private void makeGenreMap(HashMap<Integer, Movie> inputMap) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			ArrayList<String> currentGenres = currentMovie.getGenre();

			for (String genre: currentGenres){
				if (!genreMap.containsKey(genre)) {
					ArrayList<Movie> myList = new ArrayList<Movie>();
					myList.add(currentMovie);
	    			genreMap.put(genre, myList);
	    		}

	    		else {
	    			genreMap.get(genre).add(currentMovie);
	    		}	
	    	}
		}
	}

	//Make the HashMap to store movies with the same actor
	//Create entry for each actor in the movie
	private void makeActorMap(HashMap<Integer, Movie> inputMap) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			ArrayList<String> currentActors = currentMovie.getActors();

			for (String actor: currentActors){
				if (!actorMap.containsKey(actor)) {
					ArrayList<Movie> myList = new ArrayList<Movie>();
					myList.add(currentMovie);
	    			actorMap.put(actor, myList);
	    		}

	    		else {
	    			actorMap.get(actor).add(currentMovie);
	    		}	
	    	}
		}
	}


	//Make the HashMap to store movies with the same parentalRating
	private void makeParentalRatingMap(HashMap<Integer, Movie> inputMap) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			String parentalRatingMapKey = currentMovie.getParentalRating();

			if (!parentalRatingMap.containsKey(parentalRatingMapKey)) {
				ArrayList<Movie> myList = new ArrayList<Movie>();
				myList.add(currentMovie);
    			parentalRatingMap.put(parentalRatingMapKey, myList);
    		}

    		else {
    			parentalRatingMap.get(parentalRatingMapKey).add(currentMovie);
    		}	
		}
	}

	//Make the HashMap to store movies with the same runtime
	private void makeRuntimeMap(HashMap<Integer, Movie> inputMap) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			int runtimeMapKey = currentMovie.getRuntime();

			if (!runtimeMap.containsKey(runtimeMapKey)) {
				ArrayList<Movie> myList = new ArrayList<Movie>();
				myList.add(currentMovie);
    			runtimeMap.put(runtimeMapKey, myList);
    		}

    		else {
    			runtimeMap.get(runtimeMapKey).add(currentMovie);
    		}	
		}
	}

	//Make the HashMap to store movies with the same language
	private void makeLanguageMap(HashMap<Integer, Movie> inputMap) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			String languageMapKey = currentMovie.getLanguage();

			if (!languageMap.containsKey(languageMapKey)) {
				ArrayList<Movie> myList = new ArrayList<Movie>();
				myList.add(currentMovie);
    			languageMap.put(languageMapKey, myList);
    		}

    		else {
    			languageMap.get(languageMapKey).add(currentMovie);
    		}	
		}
	}

	//Make the HashMap to store movies with the same country
	private void makeCountryMap(HashMap<Integer, Movie> inputMap) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			String countryMapKey = currentMovie.getCountry();

			if (!counrtryMap.containsKey(countryMapKey)) {
				ArrayList<Movie> myList = new ArrayList<Movie>();
				myList.add(currentMovie);
    			counrtryMap.put(countryMapKey, myList);
    		}

    		else {
    			counrtryMap.get(countryMapKey).add(currentMovie);
    		}	
		}
	}

	//Make the HashMap to store movies with the same critical rating
	private void makeCriticalRatingMap(HashMap<Integer, Movie> inputMap) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			double criticalRatingMapKey = currentMovie.getCriticalRating();

			if (!criticalRatingMap.containsKey(criticalRatingMapKey)) {
				ArrayList<Movie> myList = new ArrayList<Movie>();
				myList.add(currentMovie);
    			criticalRatingMap.put(criticalRatingMapKey, myList);
    		}

    		else {
    			criticalRatingMap.get(criticalRatingMapKey).add(currentMovie);
    		}	
		}
	}


	// public HashMap<String, ArrayList<Movie>> getTitleMap() {
	// 	return titleMap;
	// }

	public HashMap<String, ArrayList<Movie>> getDateMap() {
		return dateMap;
	}

	public HashMap<String, ArrayList<Movie>> getDirectorMap() {
		return directorMap;
	}

	public HashMap<String, ArrayList<Movie>> getGenreMap() {
		return genreMap;
	}

	public HashMap<String, ArrayList<Movie>> getActorMap() {
		return actorMap;
	}

	public HashMap<String, ArrayList<Movie>> getParentalRatingMap() {
		return parentalRatingMap;
	}

	public HashMap<Integer, ArrayList<Movie>> getruntimeMap() {
		return runtimeMap;
	}

	public HashMap<String, ArrayList<Movie>> getLanguageMap() {
		return languageMap;
	}

	public HashMap<String, ArrayList<Movie>> getCountryMap() {
		return counrtryMap;
	}

	public HashMap<Double, ArrayList<Movie>> getCriticalRatingMap() {
		return criticalRatingMap;
	}


}