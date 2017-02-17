// Filter a movie collection based on a parameter
// Pass in a MovieCollection and a parameter
// Returns smaller MovieCollection of only Movies that match the parameter

//Parameters for functions: HashMap and A parameter

/*
	private Integer key;
	private String title;
	private int year;
	private String director;
	private ArrayList<String> genre;
	private ArrayList<String> actors;
	private String parentalRating;
	private String runtime;
	private String language;
	private String country;
	private double criticalRating;
	private String plot;
	private String imageURL;
*/
import java.util.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class FilterHandler
{
	//String Version
	public static HashMap<Integer, Movie> searchParameter(HashMap<Integer, Movie> inputMap, String functionName, String filterParameter) 
		throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, ClassNotFoundException
	{
		HashMap<Integer, Movie> resultMap = new HashMap<Integer, Movie>();

		for (Integer key: inputMap.keySet())
		{
			Movie currentMovie = inputMap.get(key);
			Method m = Class.forName(Movie.class.getName()).getDeclaredMethod(functionName);

			if (((String) m.invoke(currentMovie)).contains(filterParameter))
			{
				resultMap.put(key, inputMap.get(key));
			}
		}

		return resultMap;
	}


	//ArrayList<String> Version
	@SuppressWarnings("unchecked")
	public static HashMap<Integer, Movie> searchParameter(HashMap<Integer, Movie> inputMap, String functionName, ArrayList<String> filterParameter) 
		throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, ClassNotFoundException
	{
		HashMap<Integer, Movie> resultMap = new HashMap<Integer, Movie>();

		for (Integer key: inputMap.keySet())
		{
			Movie currentMovie = inputMap.get(key);
			Method m = Class.forName(Movie.class.getName()).getDeclaredMethod(functionName);

			if (((ArrayList<String>) m.invoke(currentMovie)).contains(filterParameter))
			{
				resultMap.put(key, inputMap.get(key));
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
			Method m = Class.forName(Movie.class.getName()).getDeclaredMethod(functionName);

			if (((Integer) m.invoke(currentMovie)) == filterParameter)
			{
				resultMap.put(key, inputMap.get(key));
			}
		}

		return resultMap;
	}	

	//Double Version
	public static HashMap<Integer, Movie> searchParameter(HashMap<Integer, Movie> inputMap, String functionName, double filterParameter) 
		throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, ClassNotFoundException
	{
		HashMap<Integer, Movie> resultMap = new HashMap<Integer, Movie>();

		for (Integer key: inputMap.keySet())
		{
			Movie currentMovie = inputMap.get(key);
			Method m = Class.forName(Movie.class.getName()).getDeclaredMethod(functionName);

			if (((double) m.invoke(currentMovie)) == filterParameter)
			{
				resultMap.put(key, inputMap.get(key));
			}
		}

		return resultMap;
	}	
}
