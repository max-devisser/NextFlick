package src;

import java.util.ArrayList;
import java.util.HashMap;

public class LibraryFacade {
	private ArrayList<Movie> fullMovieList;
	private ArrayList<Movie> currentMovieList;

	public LibraryFacade() {
		MovieLibrary.createMovieLibrary(); 

		ArrayList<String> emptyFilterList = new ArrayList<String>();
		ArrayList<String> emptyQueryList = new ArrayList<String>();
		
		fullMovieList = getFilteredLibrary(emptyFilterList, emptyQueryList, "Title", false);
	}

	public ArrayList<Movie> getFullLibraryList(String sortType, boolean sortDescending) {
		return LibraryLogic.sortMovieList(fullMovieList, sortType, sortDescending); 
	}


	public HashMap<Integer, Movie> getFullLibraryMap() {
		return MovieLibrary.getFullMovieMap();
	}

	public Movie getMovie(int tmdbID) {
		return MovieLibrary.getFullMovieMap().get(tmdbID);
	}

	public ArrayList<Movie> getFilteredLibrary(ArrayList<String> filters, ArrayList<String> queries, 
										  String sortType, boolean sortDescending) {
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
		currentMovieList = filteredList;

		return filteredList;
	}
}