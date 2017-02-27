package src;

// Filter a movie collection based on a parameter
// Pass in a MovieCollection and a parameter
// Returns smaller MovieCollection of only Movies that match the parameter

//Parameters for functions: HashMap and A parameter
import java.util.*;

import src.movie_builder.TestCollectionBuilder;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class FilterHandler {
	private ArrayList<Filter> filterList;
	private TestCollectionBuilder TCB;
	private HashMap<Integer, Movie> fullMovieList;

	public FilterHandler() {
		filterList = new ArrayList<Filter>();
		TCB = new TestCollectionBuilder();
		fullMovieList = TCB.getTestCollection();
	}

	public ArrayList<Filter> getFilterList() {
		return filterList;
	}

	public HashMap<Integer, Movie> addToFilterList(String filterType, String filterQuery) {
		HashMap<Integer, Movie> resultList = fullMovieList;
		Filter newFilter = new Filter(filterType, filterQuery);

		if (newFilter.getType() != "getActors" && newFilter.getType() != "getGenre") {
			int positionOfFilterToRemove = -1;
			for (int i = 0; i < filterList.size(); i++) {
				if (filterList.get(i).getType().equals(newFilter.getType())) {
					positionOfFilterToRemove = i;
				}
			}
			if (positionOfFilterToRemove >= 0) {
				filterList.remove(positionOfFilterToRemove);
			}
		}
		filterList.add(newFilter);
		resultList = applyFilterList(resultList);
		return resultList;
	}

	public HashMap<Integer, Movie> removeFromFilterList(String filterType, String filterQuery) {
		HashMap<Integer, Movie> resultList = fullMovieList;
		Filter newFilter = new Filter(filterType, filterQuery);
		
		filterList.remove(newFilter);
		resultList = applyFilterList(resultList);
		return resultList;
	}

	private HashMap<Integer, Movie> applyFilterList(HashMap<Integer, Movie> resultList) {
		for (Filter filter : filterList) {
			System.out.print(filter.getType() + ": " + filter.getQuery() + ", ");
			if (filter.getType() == "getRuntime") {
				resultList = filterMovieListInt(resultList, filter.getType(), Integer.parseInt(filter.getQuery()));
			} else if (filter.getType() == "getCriticalRating") {
				resultList = filterMovieListDouble(resultList, filter.getType(), Double.parseDouble(filter.getQuery()));
			} else {
				resultList = filterMovieListString(resultList, filter.getType(), filter.getQuery());
			}
		}
		System.out.println();
		return resultList;
	}

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
	private static HashMap<Integer, Movie> filterMovieListString(HashMap<Integer, Movie> inputList, String methodName,
			String filterParameter) {
		HashMap<Integer, Movie> resultMap = new HashMap<Integer, Movie>();

		for (Integer key : inputList.keySet()) {
			Movie currentMovie = inputList.get(key);
			Method m = findMethod(methodName);

			try {
				if (methodName.equals("getActors") || methodName.equals("getGenre")) {
					ArrayList<String> currentArrayList = (ArrayList<String>) m.invoke(currentMovie);
					for (String item : currentArrayList) {
						if (item.toLowerCase().contains(filterParameter.toLowerCase()))
							resultMap.put(key, inputList.get(key));
					}
				} else {
					if (((String) m.invoke(currentMovie)).toLowerCase().contains(filterParameter.toLowerCase()))
						resultMap.put(key, inputList.get(key));
				}
			} catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			}

		}

		return resultMap;
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
	private static HashMap<Integer, Movie> filterMovieListInt(HashMap<Integer, Movie> inputList, String methodName,
			int filterParameter) {
		HashMap<Integer, Movie> resultMap = new HashMap<Integer, Movie>();

		for (Integer key : inputList.keySet()) {
			Movie currentMovie = inputList.get(key);
			Method m = findMethod(methodName);

			try {
				if (((Integer) m.invoke(currentMovie)) == filterParameter) {
					resultMap.put(key, inputList.get(key));
				}
			} catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			}
		}

		return resultMap;
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
	private static HashMap<Integer, Movie> filterMovieListDouble(HashMap<Integer, Movie> inputList, String methodName,
			double filterParameter) {
		HashMap<Integer, Movie> resultMap = new HashMap<Integer, Movie>();

		for (Integer key : inputList.keySet()) {
			Movie currentMovie = inputList.get(key);
			Method m = findMethod(methodName);

			try {
				if (((double) m.invoke(currentMovie)) == filterParameter) {
					resultMap.put(key, inputList.get(key));
				}
			} catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			}
		}

		return resultMap;
	}

	/**
	 * Private helper method that looks for a specified method within the Movie
	 * class and returns a Method object of it. Will be used to find getters.
	 * 
	 * @param methodName
	 *            Name of method you want to find
	 * @return Method object that references the specified method
	 */
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
