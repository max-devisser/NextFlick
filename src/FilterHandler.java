package src;

// Filter a movie collection based on a parameter
// Pass in a MovieCollection and a parameter
// Returns smaller MovieCollection of only Movies that match the parameter

//Parameters for functions: HashMap and A parameter
import java.util.*;

import src.movie_builder.TestCollectionBuilder;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class FilterHandler {
	private ArrayList<Filter> filterList;
	private MapBuilder filterMaps;
	private HashMap<Integer, Movie> fullMovieMap;
	private ArrayList<Movie> fullMovieList = new ArrayList<Movie>();

	public FilterHandler() {
		filterList = new ArrayList<Filter>();
		filterMaps = new MapBuilder();
		fullMovieMap = filterMaps.getFullMovieMap();
		fullMovieList = filterMaps.getFullMovieList();
	}

	public ArrayList<Filter> getFilterList() {
		return filterList;
	}

	// Not sure if needed...
	// public MapBuilder getFilterMaps() {
	// 	return filterMaps;
	// }

	// public HashMap<Integer, Movie> getFullMovieMap() {
	// 	return fullMovieMap;
	// }

	public ArrayList<Movie> getFullMovieList() {
		return fullMovieList;
	}

	public ArrayList<Movie> addToFilterList(String filterType, String filterQuery) {
		ArrayList<Movie> resultList = fullMovieList;
		Filter newFilter = new Filter(filterType, filterQuery);

		if (newFilter.getFilterType() != "getActors" && newFilter.getFilterType() != "getGenre") {
			int positionOfFilterToRemove = -1;
			for (int i = 0; i < filterList.size(); i++) {
				if (filterList.get(i).getFilterType().equals(newFilter.getFilterType())) {
					positionOfFilterToRemove = i;
				}
			}
			if (positionOfFilterToRemove >= 0) {
				filterList.remove(positionOfFilterToRemove);
			}
		}
		filterList.add(newFilter);
		resultList = applyFilterList(resultList);
		return resultList;
	}

	public ArrayList<Movie> removeFromFilterList(String filterType, String filterQuery) {
		ArrayList<Movie> resultList = fullMovieList;
		Filter newFilter = new Filter(filterType, filterQuery);
		
		filterList.remove(newFilter);
		resultList = applyFilterList(resultList);
		return resultList;
	}

	private ArrayList<Movie> applyFilterList(ArrayList<Movie> resultList) {
		for (int i = 0; i < filterList.size(); i++) {
			Filter filter = filterList.get(i);
			if (i == 0) {

				if (filter.getFilterType().equals("getTitle")) {
					resultList = MapSearcher.filterMovieMapTitle(fullMovieMap, filter.getQuery());
				}
				else if (filter.getFilterType().equals("getRuntime")) {
					resultList = MapSearcher.filterMovieMapInt(filter.getMapType(), Integer.parseInt(filter.getQuery()));
				} 
				else if (filter.getFilterType().equals("getCriticalRating")) {
					resultList = MapSearcher.filterMovieMapDouble(filter.getMapType(), Double.parseDouble(filter.getQuery()));
				} 
				else {
					resultList = MapSearcher.filterMovieMapString(filter.getMapType(), filter.getQuery());
				}
			}

			else {
				if (filter.getFilterType().equals("getRuntime")) {
					resultList = ListSearcher.filterMovieListInt(resultList, filter.getFilterType(), Integer.parseInt(filter.getQuery()));
				} 
				else if (filter.getFilterType().equals("getCriticalRating")) {
					resultList = ListSearcher.filterMovieListDouble(resultList, filter.getFilterType(), Double.parseDouble(filter.getQuery()));
				} 
				else {
					resultList = ListSearcher.filterMovieListString(resultList, filter.getFilterType(), filter.getQuery());
				}
			}

		}

		return resultList;
	}
}