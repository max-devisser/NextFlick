package src;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

/**
 * Contains methods for filtering movie lists
 */
public class ListSearchLogic {

	/**
	 * Filters lists for filters with string values
	 * 
	 * @param inputList
	 *            List of movies to filter
	 * @param getterMethodName
	 *            Name of what getter method to use to access Movie attributes
	 *            (e.g. getTitle)
	 * @param filterQuery
	 *            What to search for within that attribute
	 * @return List of movies filtered as specified
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Movie> filterMovieListString(ArrayList<Movie> inputList, String getterMethodName,
			String filterQuery) {
		ArrayList<Movie> resultList = new ArrayList<Movie>();
		Method getterMethod = findMethod(getterMethodName);

		for (Movie currentMovie : inputList) {
			try {
				if (getterMethodName.equals("getActors") || getterMethodName.equals("getGenre")) {
					ArrayList<String> currentArrayList = (ArrayList<String>) getterMethod.invoke(currentMovie);
					for (String item : currentArrayList) {
						if (item.toLowerCase().contains(filterQuery.toLowerCase())) {
							resultList.add(currentMovie);
						}
					}
				} else if (((String) getterMethod.invoke(currentMovie)).toLowerCase()
						.contains(filterQuery.toLowerCase())) {
					resultList.add(currentMovie);
				}
			} catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			}
		}
		System.out.println("end movielist loop");
		return resultList;
	}

	/**
	 * Filters lists for filters with integer values
	 * 
	 * @param inputList
	 *            List of movies to filter
	 * @param getterMethodName
	 *            Name of what getter method to use to access Movie attributes
	 *            (e.g. getTitle)
	 * @param filterQuery
	 *            What to search for within that attribute
	 * @return List of movies filtered as specified
	 */
	public static ArrayList<Movie> filterMovieListInt(ArrayList<Movie> inputList, String getterMethodName,
			int filterQuery) {
		ArrayList<Movie> resultList = new ArrayList<Movie>();

		for (Movie currentMovie : inputList) {
			Method m = findMethod(getterMethodName);

			try {
				if (((Integer) m.invoke(currentMovie)) == filterQuery) {
					resultList.add(currentMovie);
				}
			} catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			}
		}

		return resultList;
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