package src;

import java.util.ArrayList;

//
// MAX DON'T YOU FUCKING TOUCH MY SWITCH STATEMENTS I SWEAR TO GOD
//

public class LibraryLogic {
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
				filteredList = MapSearchLogic.filterMovieMapInt(MovieLibrary.getRuntimeMap(), Integer.parseInt(filterQuery));
				break;
			case "Language":
				filteredList = MapSearchLogic.filterMovieMapString(MovieLibrary.getLanguageMap(), filterQuery);
				break;
			case "Country":
				filteredList = MapSearchLogic.filterMovieMapString(MovieLibrary.getLanguageMap(), filterQuery);
				break;
			case "Rating":
				filteredList = MapSearchLogic.filterMovieMapDouble(MovieLibrary.getCriticalRatingMap(), Double.parseDouble(filterQuery));
				break;
		}

		return filteredList;
	}

	public static ArrayList<Movie> filterMovieList(ArrayList<Movie> movieList, String filterType, String filterQuery) {
		ArrayList<Movie> filteredList  = new ArrayList<Movie>();

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
			case "Language":
				filteredList = ListSearchLogic.filterMovieListString(movieList, "getLanguage", filterQuery);
				break;
			case "Country":
				filteredList = ListSearchLogic.filterMovieListString(movieList, "getCountry", filterQuery);
				break;
			case "Rating":
				filteredList = ListSearchLogic.filterMovieListDouble(movieList, "getCriticalRating", Double.parseDouble(filterQuery));
				break;
		}

		return filteredList;
	}

	public static ArrayList<Movie> sortMovieList(ArrayList<Movie> movieList, String sortType, boolean sortDescending) {
		return SortLogic.sortList(movieList, sortType, sortDescending);
	}
}

