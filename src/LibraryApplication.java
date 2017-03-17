package src;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Intermediary class (much like a facade) to handle communicating with the
 * library.
 */
public class LibraryApplication {
	private ArrayList<Movie> fullMovieList;
	// private ArrayList<Movie> currentMovieList;

	/**
	 * Constructor. Creates the movie library and generates the full movie list.
	 */
	public LibraryApplication() {
		MovieLibrary.createMovieLibrary();

		ArrayList<String> emptyFilterList = new ArrayList<String>();
		ArrayList<String> emptyQueryList = new ArrayList<String>();

		fullMovieList = getFilteredLibrary(emptyFilterList, emptyQueryList, "Title", false);
	}

	/**
	 * @param sortType
	 *            What to sort the list by (Title, rating, etc.)
	 * @param sortDescending
	 *            Whether to sort ascending or descending
	 * @return The list of all movies sorted as specified
	 */
	public ArrayList<Movie> getFullLibraryList(String sortType, boolean sortDescending) {
		return LibraryLogic.sortMovieList(fullMovieList, sortType, sortDescending);
	}

	/**
	 * Getter for map of full database of movies
	 * 
	 * @return fullMovieMap: HashMap of all movies
	 */
	public HashMap<Integer, Movie> getFullLibraryMap() {
		return MovieLibrary.getFullMovieMap();
	}

	/**
	 * Getter for an individual movie within the database
	 * 
	 * @param tmdbID
	 *            The id of the movie you wish to get
	 * @return The movie defined by the id passed in, or null if there is no
	 *         such movie
	 */
	public Movie getMovie(int tmdbID) {
		return MovieLibrary.getFullMovieMap().get(tmdbID);
	}

	/**
	 * Getter for a filtered and sorted List of movies
	 * 
	 * @param filters
	 *            List of the types of filters (e.g. Genre, Title)
	 * @param queries
	 *            List of queries associated with the filter at the same index.
	 *            A query is what to search for when looking in a filter (e.g.
	 *            Brad Pitt in Actors)
	 * @param sortType
	 *            What to sort the list by (e.g. Title, Rating)
	 * @param sortDescending
	 *            Whether to sort ascending or descending
	 * @return List of movies that match the filter query pairs, sorted in the
	 *         specified order
	 */
	public ArrayList<Movie> getFilteredLibrary(ArrayList<String> filters, ArrayList<String> queries, String sortType,
			boolean sortDescending) {
		ArrayList<Movie> filteredList = new ArrayList<Movie>();
		String currentFilter;
		String currentQuery;

		if (filters.isEmpty()) {
			filteredList = MovieLibrary.getFullMovieList();
		} else {
			filteredList = LibraryLogic.filterLibrary(filters.get(0), queries.get(0));
			for (int currentFilterIndex = 1; currentFilterIndex < filters.size(); currentFilterIndex++) {
				currentFilter = filters.get(currentFilterIndex);
				currentQuery = queries.get(currentFilterIndex);
				filteredList = LibraryLogic.filterMovieList(filteredList, currentFilter, currentQuery);
			}
		}

		filteredList = LibraryLogic.sortMovieList(filteredList, sortType, sortDescending);
		// currentMovieList = filteredList;

		return filteredList;
	}
}