package src;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores static maps for each filter type that makes sense (full, date,
 * director, parental rating, genre, actors, runtime). The maps store each
 * possible value for the filter as keys, and have a list of movies that match
 * the key as the value
 */
public class MovieLibrary {
	private static HashMap<String, ArrayList<Movie>> dateMap;
	private static HashMap<String, ArrayList<Movie>> directorMap;
	private static HashMap<String, ArrayList<Movie>> parentalRatingMap;
	private static HashMap<String, ArrayList<Movie>> genreMap;
	private static HashMap<String, ArrayList<Movie>> actorMap;
	private static HashMap<Integer, ArrayList<Movie>> runtimeMap;

	private static HashMap<Integer, Movie> fullMovieMap;
	private static ArrayList<Movie> fullMovieList;

	/**
	 * Initializes all of the maps
	 */
	public static void createMovieLibrary() {
		fullMovieMap = MapBuilderLogic.generateFullMap();

		fullMovieList = new ArrayList<Movie>();
		for (Integer key : fullMovieMap.keySet())
			fullMovieList.add(fullMovieMap.get(key));

		dateMap = new HashMap<String, ArrayList<Movie>>();
		directorMap = new HashMap<String, ArrayList<Movie>>();
		parentalRatingMap = new HashMap<String, ArrayList<Movie>>();
		genreMap = new HashMap<String, ArrayList<Movie>>();
		actorMap = new HashMap<String, ArrayList<Movie>>();
		runtimeMap = new HashMap<Integer, ArrayList<Movie>>();

		MapBuilderLogic.createMaps(fullMovieMap, runtimeMap, dateMap, directorMap, parentalRatingMap, genreMap,
				actorMap);
	}

	/**
	 * Getter
	 * 
	 * @return fullMovieMap
	 */
	public static HashMap<Integer, Movie> getFullMovieMap() {
		return fullMovieMap;
	}

	/**
	 * Getter
	 * 
	 * @return fullMovieList
	 */
	public static ArrayList<Movie> getFullMovieList() {
		return fullMovieList;
	}

	/**
	 * Getter
	 * 
	 * @return dateMap
	 */
	public static HashMap<String, ArrayList<Movie>> getDateMap() {
		return dateMap;
	}

	/**
	 * Getter
	 * 
	 * @return directorMap
	 */
	public static HashMap<String, ArrayList<Movie>> getDirectorMap() {
		return directorMap;
	}

	/**
	 * Getter
	 * 
	 * @return genreMap
	 */
	public static HashMap<String, ArrayList<Movie>> getGenreMap() {
		return genreMap;
	}

	/**
	 * Getter
	 * 
	 * @return actorMap
	 */
	public static HashMap<String, ArrayList<Movie>> getActorsMap() {
		return actorMap;
	}

	/**
	 * Getter
	 * 
	 * @return fullMovieMap
	 */
	public static HashMap<String, ArrayList<Movie>> getParentalRatingMap() {
		return parentalRatingMap;
	}

	/**
	 * Getter
	 * 
	 * @return runtimeMap
	 */
	public static HashMap<Integer, ArrayList<Movie>> getRuntimeMap() {
		return runtimeMap;
	}
}