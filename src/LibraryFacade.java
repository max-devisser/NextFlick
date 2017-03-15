package src;

import java.util.ArrayList;
import java.util.HashMap;

public class LibraryFacade {
	private MovieLibrary movieLibrary;
	private ArrayList<Movie> fullMovieList;
	private ArrayList<Movie> currentMovieList;

	public LibraryFacade() {
		movieLibrary = new MovieLibrary(); 

		ArrayList<String> emptyFilterList = new ArrayList<String>();
		ArrayList<String> emptyQueryList = new ArrayList<String>();
		
		fullMovieList = getFilteredLibrary(emptyFilterList, emptyQueryList, "Title", false);
	}

	public ArrayList<Movie> getFullLibraryList(String sortType, boolean sortDescending) {
		return LibraryLogic.sortMovieList(fullMovieList, sortType, sortDescending); 
	}


	public HashMap<Integer, Movie> getFullLibraryMap() {
		return movieLibrary.getFullMovieMap();
	}

	public Movie getMovie(int tmdbID) {
		return movieLibrary.getFullMovieMap().get(tmdbID);
	}

	public ArrayList<Movie> getFilteredLibrary(ArrayList<String> filters, ArrayList<String> queries, 
										  String sortType, boolean sortDescending) {
		ArrayList<Movie> filteredList = new ArrayList<Movie>();
		String currentFilter;
		String currentQuery;

		if (filters.isEmpty())
			filteredList = movieLibrary.getFullMovieList();
		else {
			for (int currentFilterIndex = 0; currentFilterIndex < filters.size(); currentFilterIndex++) {
				if (currentFilterIndex == 0) {
					currentFilter = filters.get(currentFilterIndex);
					currentQuery = queries.get(currentFilterIndex);
					filteredList = LibraryLogic.filterLibrary(movieLibrary, currentFilter, currentQuery);
				} else {
					currentFilter = filters.get(currentFilterIndex);
					currentQuery = queries.get(currentFilterIndex);
					filteredList = LibraryLogic.filterMovieList(filteredList, currentFilter, currentQuery);
				}
			}
		}

		filteredList = LibraryLogic.sortMovieList(filteredList, sortType, sortDescending); 
		currentMovieList = filteredList;

		return filteredList;
	}
}