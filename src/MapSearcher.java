package src;

// Filter a movie collection based on a parameter
// Pass in a MovieCollection and a parameter
// Returns smaller MovieCollection of only Movies that match the parameter

//Parameters for functions: HashMap and A parameter
import java.util.*;

import src.movie_builder.TestCollectionBuilder;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class MapSearcher {
	private static MapBuilder filterMaps = new MapBuilder();

	//HashMap Parsers

	//Title Parser
	public static ArrayList<Movie> filterMovieMapTitle(HashMap<Integer, Movie> inputList, String filterParameter) {
		ArrayList<Movie> resultList = new ArrayList<Movie>();

		for (Integer key : inputList.keySet()) {
			Movie currentMovie = inputList.get(key);

			if (currentMovie.getTitle().toLowerCase().contains(filterParameter.toLowerCase()))			
				resultList.add(currentMovie);
		}

		return resultList;
	}


	//Change so that checks to see if key exisits, if not search whole list by contains
	/**
	 * String version. Method that takes a set of Movie objects and a String
	 * parameter to filter by and returns a set with only movies that match the
	 * filter
	 * 
	 * @param inputList
	 *            Map of Movie objects to be filtered
	 * @param methodName
	 *            Name of method that will be used to access the variable in the
	 *            Movie object that corresponds to the filterParameter e.g.
	 *            getDirector when filtering for movies by Michael Bay
	 * @param filterParameter
	 *            String to filter movies by
	 * @return Map of smaller or equal size that only contains movies that match
	 *         the filter
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Movie> filterMovieMapString(String methodName, String filterParameter) {
		ArrayList<Movie> resultList = new ArrayList<Movie>();
		Method m = findMethod(methodName);

		try{
			HashMap<String, ArrayList<Movie>> currentMap = (HashMap<String, ArrayList<Movie>>) m.invoke(filterMaps);
			resultList = currentMap.get(filterParameter);
		}catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			}

		return resultList;
	}

	/**
	 * int version. Method that takes a set of Movie objects and an int
	 * parameter to filter by and returns a set with only movies that match the
	 * filter
	 * 
	 * @param inputList
	 *            Map of Movie objects to be filtered
	 * @param methodName
	 *            Name of method that will be used to access the variable in the
	 *            Movie object that corresponds to the filterParameter e.g.
	 *            getDirector when filtering for movies by Michael Bay
	 * @param filterParameter
	 *            int to filter movies by
	 * @return Map of smaller or equal size that only contains movies that match
	 *         the filter
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Movie> filterMovieMapInt(String methodName, int filterParameter) {
		ArrayList<Movie> resultList = new ArrayList<Movie>();
		Method m = findMethod(methodName);

		try{
			HashMap<Integer, ArrayList<Movie>> currentMap = (HashMap<Integer, ArrayList<Movie>>) m.invoke(filterMaps);
			resultList = currentMap.get(filterParameter);
		}catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			}

		return resultList;
	}

	/**
	 * double version. Method that takes a set of Movie objects and a double
	 * parameter to filter by and returns a set with only movies that match the
	 * filter
	 * 
	 * @param inputList
	 *            Map of Movie objects to be filtered
	 * @param methodName
	 *            Name of method that will be used to access the variable in the
	 *            Movie object that corresponds to the filterParameter e.g.
	 *            getDirector when filtering for movies by Michael Bay
	 * @param filterParameter
	 *            double to filter movies by
	 * @return Map of smaller or equal size that only contains movies that match
	 *         the filter
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Movie>filterMovieMapDouble(String methodName, double filterParameter) {
		ArrayList<Movie> resultList = new ArrayList<Movie>();
		Method m = findMethod(methodName);

		try{
			HashMap<Double, ArrayList<Movie>> currentMap = (HashMap<Double, ArrayList<Movie>>) m.invoke(filterMaps);
			resultList = currentMap.get(filterParameter);
		}catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			}

		return resultList;
	}

	// /**
	//  * Private helper method that looks for a specified method within the Movie
	//  * class and returns a Method object of it. Will be used to find getters.
	//  * 
	//  * @param methodName
	//  *            Name of method you want to find
	//  * @return Method object that references the specified method
	//  */
	private static Method findMethod(String methodName) {
		Method m = null;
		try {
			m = Class.forName(MapBuilder.class.getName()).getDeclaredMethod(methodName);
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