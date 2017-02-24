package src;

// Filter a movie collection based on a parameter
// Pass in a MovieCollection and a parameter
// Returns smaller MovieCollection of only Movies that match the parameter

//Parameters for functions: HashMap and A parameter
import java.util.*;

import src.movie_builder.TestCollectionBuilder;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class FilterHandler
{
	/**
	 * Private helper method that looks for a specified method within the Movie class
	 * and returns a Method object of it. Will be used to find getters.
	 * @param methodName Name of method you want to find
	 * @return Method object that references the specified method
	 */
	private static Method findMethod(String methodName)
	{
		Method m = null;
		try
		{
			m = Class.forName(Movie.class.getName()).getDeclaredMethod(methodName);
		}

		catch (NoSuchMethodException e) {
			System.err.println("NoSuchMethodException: " + e.getMessage());
		} 

		catch (SecurityException e) {
			System.err.println("SecurityException: " + e.getMessage());
		}

		catch (ClassNotFoundException e) {
    		System.err.println("ClassNotFoundException: " + e.getMessage());
		} 

		return m;
	}

	/**
	 * String version. 
	 * Method that takes a set of Movie objects and a String parameter to filter by and returns a set
	 * with only movies that match the filter
	 * @param inputMap Map of Movie objects to be filtered
	 * @param methodName Name of method that will be used to access the variable in the Movie object 
	 * that corresponds to the filterParameter e.g. getDirector when filtering for movies by Michael Bay
	 * @param filterParameter String to filter movies by
	 * @return Map of smaller or equal size that only contains movies that match the filter
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<Integer, Movie> searchParameter(HashMap<Integer, Movie> inputMap, 
			String methodName, String filterParameter) 
	{
		HashMap<Integer, Movie> resultMap = new HashMap<Integer, Movie>();

		for (Integer key: inputMap.keySet())
		{
			Movie currentMovie = inputMap.get(key);
			Method m = findMethod(methodName);

			try{
				if (methodName.equals("getActors") || methodName.equals("getGenre")) 
				{
					ArrayList<String> currentArrayList = (ArrayList<String>) m.invoke(currentMovie);
					for (String item: currentArrayList)
					{
						if (item.toLowerCase().contains(filterParameter.toLowerCase()))
							resultMap.put(key, inputMap.get(key));
					}
				} 

				else 
				{
					if (((String) m.invoke(currentMovie)).toLowerCase().contains(filterParameter.toLowerCase()))
						resultMap.put(key, inputMap.get(key));
				}
			}
			catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} 
			catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			} 

		}

		return resultMap;
	}

	/**
	 * int version. 
	 * Method that takes a set of Movie objects and an int parameter to filter by and returns a set
	 * with only movies that match the filter
	 * @param inputMap Map of Movie objects to be filtered
	 * @param methodName Name of method that will be used to access the variable in the Movie object 
	 * that corresponds to the filterParameter e.g. getDirector when filtering for movies by Michael Bay
	 * @param filterParameter int to filter movies by
	 * @return Map of smaller or equal size that only contains movies that match the filter
	 */
	public static HashMap<Integer, Movie> searchParameter(HashMap<Integer, Movie> inputMap, String methodName, int filterParameter) 
	{
		HashMap<Integer, Movie> resultMap = new HashMap<Integer, Movie>();

		for (Integer key: inputMap.keySet())
		{
			Movie currentMovie = inputMap.get(key);
			Method m = findMethod(methodName);

			try{
				if (((Integer) m.invoke(currentMovie)) == filterParameter)
				{
					resultMap.put(key, inputMap.get(key));
				}
			}

			catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} 

			catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			} 
		}

		return resultMap;
	}	

	/**
	 * double version. 
	 * Method that takes a set of Movie objects and a double parameter to filter by and returns a set
	 * with only movies that match the filter
	 * @param inputMap Map of Movie objects to be filtered
	 * @param methodName Name of method that will be used to access the variable in the Movie object 
	 * that corresponds to the filterParameter e.g. getDirector when filtering for movies by Michael Bay
	 * @param filterParameter double to filter movies by
	 * @return Map of smaller or equal size that only contains movies that match the filter
	 */
	public static HashMap<Integer, Movie> searchParameter(HashMap<Integer, Movie> inputMap, String methodName, double filterParameter) 
	{
		HashMap<Integer, Movie> resultMap = new HashMap<Integer, Movie>();

		for (Integer key: inputMap.keySet())
		{
			Movie currentMovie = inputMap.get(key);
			Method m = findMethod(methodName);

			try{
				if (((double) m.invoke(currentMovie)) == filterParameter)
				{
					resultMap.put(key, inputMap.get(key));
				}
			}
			catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException: " + e.getMessage());
			} 
			catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException: " + e.getMessage());
			} 
		}

		return resultMap;
	}	
}
