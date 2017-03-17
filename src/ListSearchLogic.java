package src;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

public class ListSearchLogic {

	@SuppressWarnings("unchecked")
	public static ArrayList<Movie> filterMovieListString(ArrayList<Movie> inputList, String methodName,
			String filterParameter) {
		ArrayList<Movie> resultList = new ArrayList<Movie>();
		Method m = findMethod(methodName);

		for (Movie currentMovie : inputList) {
			try {
				if (methodName.equals("getActors") || methodName.equals("getGenre")) {
					ArrayList<String> currentArrayList = (ArrayList<String>) m.invoke(currentMovie);
					for (String item : currentArrayList) {
						if (item.toLowerCase().contains(filterParameter.toLowerCase())) {
							resultList.add(currentMovie);
						}
					}
				} else if (((String) m.invoke(currentMovie)).toLowerCase().contains(filterParameter.toLowerCase())) {
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

	public static ArrayList<Movie> filterMovieListInt(ArrayList<Movie> inputList, String methodName,
			int filterParameter) {
		ArrayList<Movie> resultList = new ArrayList<Movie>();

		for (Movie currentMovie : inputList) {
			Method m = findMethod(methodName);

			try {
				if (((Integer) m.invoke(currentMovie)) == filterParameter) {
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

	public static ArrayList<Movie> filterMovieListDouble(ArrayList<Movie> inputList, String methodName,
			double filterParameter) {
		ArrayList<Movie> resultList = new ArrayList<Movie>();

		for (Movie currentMovie : inputList) {
			Method m = findMethod(methodName);

			try {
				if (((Double) m.invoke(currentMovie)) == filterParameter) {
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