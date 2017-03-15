package src;

// Filter a movie collection based on a parameter
// Pass in a MovieCollection and a parameter
// Returns smaller MovieCollection of only Movies that match the parameter

//Parameters for functions: HashMap and A parameter

import java.util.*;

import src.movie_builder.TestCollectionBuilder;

public class MapSearchLogic {

	public static ArrayList<Movie> filterMovieMapTitle(HashMap<Integer, Movie> inputMap, String filterParameter) {
		ArrayList<Movie> filteredList = new ArrayList<Movie>();
		filterParameter = filterParameter.toLowerCase();

		for (Integer key : inputMap.keySet()) {
			String currentTitle = inputMap.get(key).getTitle().toLowerCase();
			if (currentTitle.contains(filterParameter))
				filteredList.add(inputMap.get(key));
		}

		return filteredList;
	}

	public static ArrayList<Movie> filterMovieMapString(HashMap<String, ArrayList<Movie>> inputMap, String filterParameter) {
		ArrayList<Movie> filteredList = new ArrayList<Movie>();

		if (inputMap.containsKey(filterParameter))
			filteredList = inputMap.get(filterParameter);

		for (String key : inputMap.keySet()) {
			if (key.toLowerCase().contains(filterParameter.toLowerCase()) && key != filterParameter)
				filteredList.addAll(inputMap.get(key));
		}

		return filteredList;
	}

	public static ArrayList<Movie> filterMovieMapInt(HashMap<Integer, ArrayList<Movie>> inputMap, int filterParameter) {
		ArrayList<Movie> filteredList = new ArrayList<Movie>();

		if (inputMap.containsKey(filterParameter))
			filteredList = inputMap.get(filterParameter);

		return filteredList;
	}

	public static ArrayList<Movie> filterMovieMapDouble(HashMap<Double, ArrayList<Movie>> inputMap, double filterParameter) {
		ArrayList<Movie> filteredList = new ArrayList<Movie>();

		if (inputMap.containsKey(filterParameter))
			filteredList = inputMap.get(filterParameter);

		return filteredList;
	}
}