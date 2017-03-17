package src;

import java.util.ArrayList;

//
// MAX DON'T YOU FUCKING TOUCH MY SWITCH STATEMENTS I SWEAR TO GOD
//

/**
 * Handles filtering and sorting of the library
 */
public class LibraryLogic {

	/**
	 * Calls on the appropriate MapSearchLogic function using the correct
	 * starter map. Only used when starting a new filtering, as it uses static
	 * maps to start
	 * 
	 * @param filterType
	 *            What movie attribute to search through (e.g. Title, Genre)
	 * @param filterQuery
	 *            What to search for within that attribute (e.g. Brad Pitt for
	 *            Actors)
	 * @return List of movies that match the filter query pair
	 */
	public static ArrayList<Movie> filterLibrary(String filterType, String filterQuery) {
		ArrayList<Movie> filteredList = new ArrayList<Movie>();

		switch (filterType) {
		case "Title":
			filteredList = MapSearchLogic.filterMovieMapTitle(MovieLibrary.getFullMovieMap(), filterQuery);
			break;
		case "Year":
			filteredList = MapSearchLogic.filterMovieMapString(MovieLibrary.getDateMap(), filterQuery);
			break;
		case "Genre":
			filteredList = MapSearchLogic.filterMovieMapString(MovieLibrary.getGenreMap(), filterQuery);
			break;
		case "Actors":
			filteredList = MapSearchLogic.filterMovieMapString(MovieLibrary.getActorsMap(), filterQuery);
			break;
		case "Director":
			filteredList = MapSearchLogic.filterMovieMapString(MovieLibrary.getDirectorMap(), filterQuery);
			break;
		case "Parental Rating":
			filteredList = MapSearchLogic.filterMovieMapString(MovieLibrary.getParentalRatingMap(), filterQuery);
			break;
		case "Length":
			filteredList = MapSearchLogic.filterMovieMapInt(MovieLibrary.getRuntimeMap(),
					Integer.parseInt(filterQuery));
			break;
		}

		return filteredList;
	}

	/**
	 * Calls on the appropriate MapSearchLogic function passing in the previous
	 * movie list to sort and the correct getter method name
	 * 
	 * @param movieList
	 *            List of movies to apply the filters to
	 * @param filterType
	 *            What movie attribute to search through (e.g. Title, Genre)
	 * @param filterQuery
	 *            What to search for within that attribute (e.g. Brad Pitt for
	 *            Actors)
	 * @return List of movies that match the filter query pair
	 */
	public static ArrayList<Movie> filterMovieList(ArrayList<Movie> movieList, String filterType, String filterQuery) {
		ArrayList<Movie> filteredList = new ArrayList<Movie>();

		switch (filterType) {
		case "Title":
			filteredList = ListSearchLogic.filterMovieListString(movieList, "getTitle", filterQuery);
			break;
		case "Year":
			filteredList = ListSearchLogic.filterMovieListString(movieList, "getDate", filterQuery);
			break;
		case "Genre":
			filteredList = ListSearchLogic.filterMovieListString(movieList, "getGenre", filterQuery);
			break;
		case "Actors":
			filteredList = ListSearchLogic.filterMovieListString(movieList, "getActors", filterQuery);
			break;
		case "Director":
			filteredList = ListSearchLogic.filterMovieListString(movieList, "getDirector", filterQuery);
			break;
		case "Parental Rating":
			filteredList = ListSearchLogic.filterMovieListString(movieList, "getParentalRating", filterQuery);
			break;
		case "Length":
			filteredList = ListSearchLogic.filterMovieListInt(movieList, "getRuntime", Integer.parseInt(filterQuery));
			break;
		}

		return filteredList;
	}

	/**
	 * Calls on SortLogic sortList
	 * 
	 * @param movieList
	 *            List of movies to sort
	 * @param sortType
	 *            What to sort by (e.g. Title, Rating)
	 * @param sortDescending
	 *            Whether to sort ascending or descending
	 * @return Sorted list of movies as specified by the input
	 */
	public static ArrayList<Movie> sortMovieList(ArrayList<Movie> movieList, String sortType, boolean sortDescending) {
		return SortLogic.sortList(movieList, sortType, sortDescending);
	}
}
