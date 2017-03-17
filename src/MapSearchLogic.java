package src;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Provides methods for filtering movie maps
 */
public class MapSearchLogic {

	/**
	 * Filtering by title
	 * 
	 * @param inputMap
	 *            Map (with movie id as the key) to be filtered
	 * @param movieTitle
	 *            What title to search for
	 * @return Filtered list of movies
	 */
	public static ArrayList<Movie> filterMovieMapTitle(HashMap<Integer, Movie> inputMap, String movieTitle) {
		ArrayList<Movie> filteredList = new ArrayList<Movie>();
		movieTitle = movieTitle.toLowerCase();

		for (Integer key : inputMap.keySet()) {
			String currentTitle = inputMap.get(key).getTitle().toLowerCase();
			if (currentTitle.contains(movieTitle))
				filteredList.add(inputMap.get(key));
		}

		return filteredList;
	}

	/**
	 * Filtering for most types of filters
	 * 
	 * @param inputMap
	 *            Map (with filter value as key and list of movies that have
	 *            said value) to be filtered
	 * @param filterParameter
	 *            What to search for
	 * @return Filtered list of movies
	 */
	public static ArrayList<Movie> filterMovieMapString(HashMap<String, ArrayList<Movie>> inputMap,
			String filterParameter) {
		ArrayList<Movie> filteredList = new ArrayList<Movie>();
		filterParameter = filterParameter.toLowerCase();

		if (inputMap.containsKey(filterParameter)) {
			filteredList = inputMap.get(filterParameter);
		} else if (!inputMap.equals(MovieLibrary.getParentalRatingMap())) {
			for (String key : inputMap.keySet()) {
				if (key.toLowerCase().contains(filterParameter) && key != filterParameter)
					filteredList.addAll(inputMap.get(key));
			}
		}

		return filteredList;
	}

	/**
	 * Filtering for year and length
	 * 
	 * @param inputMap
	 *            Map (with filter value as key and list of movies that have
	 *            said value) to be filtered
	 * @param filterParameter
	 *            What to search for
	 * @return Filtered list of movies
	 */
	public static ArrayList<Movie> filterMovieMapInt(HashMap<Integer, ArrayList<Movie>> inputMap, int filterParameter) {
		ArrayList<Movie> filteredList = new ArrayList<Movie>();

		if (inputMap.containsKey(filterParameter))
			filteredList = inputMap.get(filterParameter);

		return filteredList;
	}
}