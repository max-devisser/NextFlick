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
	private HashMap<Integer, Movie> fullMovieMap;
	private ArrayList<Movie> fullMovieList = new ArrayList<Movie>();


	// public static void main(String[] args) {
	// 	MapBuilder test = new MapBuilder();
	// }

	public MapBuilder() {

		TCB = new TestCollectionBuilder();
		fullMovieMap = TCB.getTestCollection();

		for (Integer key: fullMovieMap.keySet())
			fullMovieList.add(fullMovieMap.get(key));

		//Make String Maps
		makeStringMap(fullMovieMap, dateMap, "getDate");
		makeStringMap(fullMovieMap, directorMap, "getDirector");
		makeStringMap(fullMovieMap, parentalRatingMap, "getParentalRating");
		makeStringMap(fullMovieMap, languageMap, "getLanguage");
		makeStringMap(fullMovieMap, countryMap, "getCountry");

		//Make ArrayListMaps
		makeArrayListMap(fullMovieMap, genreMap, "getGenre");
		makeArrayListMap(fullMovieMap, actorMap, "getActors");

		//Make Double Map
		makeDoubleMap(fullMovieMap, criticalRatingMap, "getCriticalRating");
		//Make Integer Map
		makeIntMap(fullMovieMap, runtimeMap, "getRuntime");

		
	}

	//Make Filter HashMaps for String variables
	private void makeStringMap(HashMap<Integer, Movie> inputMap, HashMap<String, ArrayList<Movie>> outputMap, String methodName) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			Method m = findMethod(methodName);

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
			Method m = findMethod(methodName);

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
			Method m = findMethod(methodName);

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
			Method m = findMethod(methodName);

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

	public HashMap<Integer, Movie> getFullMovieMap() {
		return fullMovieMap;
	}

	public ArrayList<Movie> getFullMovieList() {
		return fullMovieList;
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

	public HashMap<String, ArrayList<Movie>> getActorsMap() {
		return actorMap;
	}

	public HashMap<String, ArrayList<Movie>> getParentalRatingMap() {
		return parentalRatingMap;
	}

	public HashMap<Integer, ArrayList<Movie>> getRuntimeMap() {
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

	private static Method findMethod(String methodName) {
		Method m = null;
		try {
			m = Class.forName(Movie.class.getName()).getDeclaredMethod(methodName);
		} catch (NoSuchMethodException e) {
			System.err.println("NoSuchMethodException: " + e.getMessage());
		} catch (SecurityException e) {
			System.err.println("SecurityException: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e.getMessage());
		}

		return m;
	}


}