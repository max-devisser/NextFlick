package src;

import src.movie_builder.MovieSerializationManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.lang.reflect.InvocationTargetException;


/**
 * Makes maps of movies that share attributes (e.g. all movies for efficiency
 */
public class MapBuilderLogic {

	/**
	 * Builds the map of all movies by deserializing chunks
	 * @return Map of all movies
	 */
	public static HashMap<Integer, Movie> generateFullMap() {
		MovieSerializationManager MSM = new MovieSerializationManager(null);
		HashMap<Integer, Movie> partialMap = null;
		HashMap<Integer, Movie> fullMovieMap = new HashMap<Integer, Movie>();
		
		final int CHUNKS = 90;
		for (int i = 1; i <= CHUNKS; ++i) {
			partialMap = new HashMap<Integer, Movie>(MSM.deserialize("database" + File.separator + "DatabaseChunk" + i + ".ser"));
			for (Movie m : partialMap.values())
				fullMovieMap.put(m.getKey(), m);
		}

		return fullMovieMap;
	}

	/**
	 * Creates maps with for all different filters with the key being the movie's value for that filter (e.g. the actors map will have 'Vin Diesel' as a key, and the associated value will be a list of all the movies he starred in
	 * @param fullMovieMap Full database map
	 * @param intMap 
	 * @param stringMaps
	 */
	@SafeVarargs
	public static void createMaps(HashMap<Integer, Movie> fullMovieMap, HashMap<Integer, ArrayList<Movie>> intMap, 
		HashMap<String, ArrayList<Movie>>... stringMaps) {
		
		assert(stringMaps.length == 5);

		//Make Integer Map
		makeIntMap(fullMovieMap, intMap, "getRuntime");

		//Make String Maps
		makeStringMap(fullMovieMap, stringMaps[0], "getDate");
		makeStringMap(fullMovieMap, stringMaps[1], "getDirector");
		makeStringMap(fullMovieMap, stringMaps[2], "getParentalRating");

		//Make ArrayListMaps
		makeArrayListMap(fullMovieMap, stringMaps[3], "getGenre");
		makeArrayListMap(fullMovieMap, stringMaps[4], "getActors");	
	}

	//Make Filter HashMaps for String variables
	private static void makeStringMap(HashMap<Integer, Movie> inputMap, HashMap<String, ArrayList<Movie>> outputMap, String getterMethodName) {
		for (Integer key : inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			Method getterMethod = findMethod(getterMethodName);

			try{
				String filterMapKey = ((String) getterMethod.invoke(currentMovie)).toLowerCase();
				
				if (getterMethodName.equals("getDate")) {
					filterMapKey = filterMapKey.substring(0,4);
				}
				
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
	private static void makeArrayListMap(HashMap<Integer, Movie> inputMap, HashMap<String, ArrayList<Movie>> outputMap, String getterMethodName) {
		for (Integer key : inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			Method getterMethod = findMethod(getterMethodName);

			try{
				ArrayList<String> currentList = (ArrayList<String>) getterMethod.invoke(currentMovie);
				
				for (String item : currentList){
					item = item.toLowerCase();
					if (!outputMap.containsKey(item)) {
						ArrayList<Movie> myList = new ArrayList<Movie>();
						myList.add(currentMovie);
		    			outputMap.put(item, myList);
		    		}
		    		else {
		    			outputMap.get(item).add(currentMovie);
		    		}	
		    	}
    		} catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			}
		}
	}

	//Make Filter HashMaps for Integer variables
	private static void makeIntMap(HashMap<Integer, Movie> inputMap, HashMap<Integer, ArrayList<Movie>> outputMap, String getterMethodName) {
		for (Integer key : inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			Method getterMethod = findMethod(getterMethodName);

			try{
				int filterMapKey = (Integer) getterMethod.invoke(currentMovie);

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

	/**
	 * Private helper method that looks for a specified method within the Movie
	 * class and returns a Method object of it. Will be used to find getters.
	 * 
	 * @param getterMethodName
	 *            Name of what getter method to find (e.g. getTitle)
	 * @return Method object that references the specified method
	 */
	private static Method findMethod(String getterMethodName) {
		Method getterMethod = null;
		try {
			getterMethod = Class.forName(Movie.class.getName()).getDeclaredMethod(getterMethodName);
		} catch (NoSuchMethodException e) {
			System.err.println("NoSuchMethodException: " + e.getMessage());
		} catch (SecurityException e) {
			System.err.println("SecurityException: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e.getMessage());
		}

		return getterMethod;
	}
}