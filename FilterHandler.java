// Filter a movie collection based on a parameter
// Pass in a MovieCollection and a parameter
// Returns smaller MovieCollection of only Movies that match the parameter

//Parameters for functions: HashMap and A parameter
import java.util.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class FilterHandler
{
	private static Method findFunction(String functionName)
	{
		Method m = null;
		try
		{
			m = Class.forName(Movie.class.getName()).getDeclaredMethod(functionName);
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

	//String Version
	public static HashMap<Integer, Movie> searchParameter(HashMap<Integer, Movie> inputMap, String functionName, String filterParameter) 
	{
		HashMap<Integer, Movie> resultMap = new HashMap<Integer, Movie>();

		for (Integer key: inputMap.keySet())
		{
			Movie currentMovie = inputMap.get(key);
			Method m = findFunction(functionName);

			try{
				if (((String) m.invoke(currentMovie)).contains(filterParameter))
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

	//ArrayList<String> Version
	@SuppressWarnings("unchecked")
	public static HashMap<Integer, Movie> searchParameter(HashMap<Integer, Movie> inputMap, String functionName, ArrayList<String> filterParameter) 
	{
		HashMap<Integer, Movie> resultMap = new HashMap<Integer, Movie>();

		for (Integer key: inputMap.keySet())
		{
			Movie currentMovie = inputMap.get(key);
			Method m = findFunction(functionName);

			try{
				if (((ArrayList<String>) m.invoke(currentMovie)).contains(filterParameter))
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

	//Integer Version
	public static HashMap<Integer, Movie> searchParameter(HashMap<Integer, Movie> inputMap, String functionName, int filterParameter) 
		throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, ClassNotFoundException
	{
		HashMap<Integer, Movie> resultMap = new HashMap<Integer, Movie>();

		for (Integer key: inputMap.keySet())
		{
			Movie currentMovie = inputMap.get(key);
			Method m = findFunction(functionName);

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

	//Double Version
	public static HashMap<Integer, Movie> searchParameter(HashMap<Integer, Movie> inputMap, String functionName, double filterParameter) 
	{
		HashMap<Integer, Movie> resultMap = new HashMap<Integer, Movie>();

		for (Integer key: inputMap.keySet())
		{
			Movie currentMovie = inputMap.get(key);
			Method m = findFunction(functionName);

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
