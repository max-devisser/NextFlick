package src;

import java.util.*;

import src.movie_builder.MovieSerializationManager;
import java.lang.reflect.Method;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class MapBuilderLogic {

	public static HashMap<Integer, Movie> generateFullMap() {
		MovieSerializationManager MSM = new MovieSerializationManager(null);
		HashMap<Integer, Movie> partialMap = null;
		HashMap<Integer, Movie> fullMovieMap = new HashMap<Integer, Movie>();
		final int CHUNKS = 82;	//CAP: exceptions for values > 30
		for (int i = 1; i <= CHUNKS; ++i) {
			partialMap = new HashMap<Integer, Movie>(MSM.deserialize("database" + File.separator + "DatabaseChunk" + i + ".ser"));
			for (Movie m : partialMap.values())
				fullMovieMap.put(m.getKey(), m);
		}

		return fullMovieMap;
	}

	@SafeVarargs
	public static void createMaps(HashMap<Integer, Movie> fullMovieMap, HashMap<Integer, ArrayList<Movie>> intMap, 
		HashMap<Double, ArrayList<Movie>> doubleMap, HashMap<String, ArrayList<Movie>>... stringMaps) {
		
		assert(stringMaps.length == 7);

		//Make Integer Map
		makeIntMap(fullMovieMap, intMap, "getRuntime");

		//Make Double Map 
		makeDoubleMap(fullMovieMap, doubleMap, "getCriticalRating");

		//Make String Maps
		makeStringMap(fullMovieMap, stringMaps[0], "getDate");
		makeStringMap(fullMovieMap, stringMaps[1], "getDirector");
		makeStringMap(fullMovieMap, stringMaps[2], "getParentalRating");
		makeStringMap(fullMovieMap, stringMaps[3], "getLanguage");
		makeStringMap(fullMovieMap, stringMaps[4], "getCountry");

		//Make ArrayListMaps
		makeArrayListMap(fullMovieMap, stringMaps[5], "getGenre");
		makeArrayListMap(fullMovieMap, stringMaps[6], "getActors");	
	}

	//Make Filter HashMaps for String variables
	private static void makeStringMap(HashMap<Integer, Movie> inputMap, HashMap<String, ArrayList<Movie>> outputMap, String methodName) {
		for (Integer key: inputMap.keySet()) {
			Movie currentMovie = inputMap.get(key);
			Method m = findMethod(methodName);

			try{
				String filterMapKey = (String) m.invoke(currentMovie);
				
				if (methodName == "getDate")
					filterMapKey = filterMapKey.substring(0,4);

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
	private static void makeArrayListMap(HashMap<Integer, Movie> inputMap, HashMap<String, ArrayList<Movie>> outputMap, String methodName) {
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
	private static void makeIntMap(HashMap<Integer, Movie> inputMap, HashMap<Integer, ArrayList<Movie>> outputMap, String methodName) {
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
	private static void makeDoubleMap(HashMap<Integer, Movie> inputMap, HashMap<Double, ArrayList<Movie>> outputMap, String methodName) {
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