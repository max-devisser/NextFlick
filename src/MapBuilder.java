package src;

import java.util.*;
import src.movie_builder.TestCollectionBuilder;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;


public class MapBuilder
{
	//Probably useless maps
	//private HashMap<String, ArrayList<Movie>> titleMap;
	//private HashMap<String, ArrayList<Movie>> plotMap;
	//private HashMap<String, ArrayList<Movie>> imageURLMap;


	private HashMap<String, ArrayList<Movie>> dateMap = new HashMap<String, ArrayList<Movie>>();
	private HashMap<String, ArrayList<Movie>> directorMap = new HashMap<String, ArrayList<Movie>>();
	private HashMap<String, ArrayList<Movie>> genreMap = new HashMap<String, ArrayList<Movie>>();
	private HashMap<String, ArrayList<Movie>> actorMap = new HashMap<String, ArrayList<Movie>>();
	private HashMap<String, ArrayList<Movie>> parentalRatingMap = new HashMap<String, ArrayList<Movie>>();
	private HashMap<Integer, ArrayList<Movie>> runtimeMap = new HashMap<Integer, ArrayList<Movie>>();
	private HashMap<String, ArrayList<Movie>> languageMap = new HashMap<String, ArrayList<Movie>>();
	private HashMap<String, ArrayList<Movie>> countryMap = new HashMap<String, ArrayList<Movie>>();
	private HashMap<Double, ArrayList<Movie>> criticalRatingMap = new HashMap<Double, ArrayList<Movie>>();
	
	private TestCollectionBuilder TCB;
	private HashMap<Integer, Movie> database;


	// public static void main(String[] args) {
	// 	MapBuilder test = new MapBuilder();
	// }

	public MapBuilder() {

		TCB = new TestCollectionBuilder();
		database = TCB.getTestCollection();

		//Make String Maps
		makeStringMap(database, dateMap, "getDate");
		makeStringMap(database, directorMap, "getDirector");
		makeStringMap(database, parentalRatingMap, "getParentalRating");
		makeStringMap(database, languageMap, "getLanguage");
		makeStringMap(database, countryMap, "getCountry");

		//Make ArrayListMaps
		makeArrayListMap(database, genreMap, "getGenre");
		makeArrayListMap(database, actorMap, "getActors");

		//Make Double Map
		makeDoubleMap(database, criticalRatingMap, "getCriticalRating");
		//Make Integer Map
		makeIntMap(database, runtimeMap, "getRuntime");

		
	}

	//Make Filter HashMaps for String variables
	private void makeStringMap(HashMap<Integer, Movie> inputMap, HashMap<String, ArrayList<Movie>> outputMap, String methodName) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			Method m = MethodGetter.findMethod(methodName);

			try{
				String filterMapKey = (String) m.invoke(currentMovie);

				if (!outputMap.containsKey(filterMapKey)) {
					ArrayList<Movie> myList = new ArrayList<Movie>();
					myList.add(currentMovie);
	    			outputMap.put(filterMapKey, myList);
	    		}

	    		else {
	    			outputMap.get(filterMapKey).add(currentMovie);
	    		}
    		}catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			}
		}
	}

	//Make Filter HashMaps for ArrayList<String> variables
	@SuppressWarnings("unchecked")
	private void makeArrayListMap(HashMap<Integer, Movie> inputMap, HashMap<String, ArrayList<Movie>> outputMap, String methodName) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			Method m = MethodGetter.findMethod(methodName);

			try{
				ArrayList<String> currentList = (ArrayList<String>) m.invoke(currentMovie);
				
				for (String item: currentList){
					if (!outputMap.containsKey(item)) {
						ArrayList<Movie> myList = new ArrayList<Movie>();
						myList.add(currentMovie);
		    			outputMap.put(item, myList);
		    		}

		    		else {
		    			outputMap.get(item).add(currentMovie);
		    		}	
		    	}
    		}catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			}
		}
	}

	//Make Filter HashMaps for Integer variables
	private void makeIntMap(HashMap<Integer, Movie> inputMap, HashMap<Integer, ArrayList<Movie>> outputMap, String methodName) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			Method m = MethodGetter.findMethod(methodName);

			try{
				int filterMapKey = (Integer) m.invoke(currentMovie);

				if (!outputMap.containsKey(filterMapKey)) {
					ArrayList<Movie> myList = new ArrayList<Movie>();
					myList.add(currentMovie);
	    			outputMap.put(filterMapKey, myList);
	    		}

	    		else {
	    			outputMap.get(filterMapKey).add(currentMovie);
	    		}
    		}catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			}
		}
	}

	//Make Filter HashMaps for Integer variables
	private void makeDoubleMap(HashMap<Integer, Movie> inputMap, HashMap<Double, ArrayList<Movie>> outputMap, String methodName) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			Method m = MethodGetter.findMethod(methodName);

			try{
				double filterMapKey = (Double) m.invoke(currentMovie);

				if (!outputMap.containsKey(filterMapKey)) {
					ArrayList<Movie> myList = new ArrayList<Movie>();
					myList.add(currentMovie);
	    			outputMap.put(filterMapKey, myList);
	    		}

	    		else {
	    			outputMap.get(filterMapKey).add(currentMovie);
	    		}
    		}catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			}
		}
	}


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
		return countryMap;
	}

	public HashMap<Double, ArrayList<Movie>> getCriticalRatingMap() {
		return criticalRatingMap;
	}


}