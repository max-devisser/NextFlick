package src;

import java.util.ArrayList;
import java.util.HashMap;

// Filter a movie collection based on a parameter
// Pass in a MovieCollection and a parameter
// Returns smaller MovieCollection of only Movies that match the parameter

//Parameters for functions: HashMap and A parameter



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

		// Maybe make inputMap have all keys in lower case to allow non case sensitive searching
		// in the if statement below
		if (inputMap.containsKey(filterParameter)) {
			filteredList = inputMap.get(filterParameter);
		} else if (!inputMap.equals(MovieLibrary.getParentalRatingMap())){
			for (String key : inputMap.keySet()) {
				if (key.toLowerCase().contains(filterParameter.toLowerCase()) && key != filterParameter)
					filteredList.addAll(inputMap.get(key));
			}
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