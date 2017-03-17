package src;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieLibrary {
	private static HashMap<String, ArrayList<Movie>> dateMap;
	private static HashMap<String, ArrayList<Movie>> directorMap;
	private static HashMap<String, ArrayList<Movie>> parentalRatingMap;
	private static HashMap<String, ArrayList<Movie>> genreMap;
	private static HashMap<String, ArrayList<Movie>> actorMap;
	private static HashMap<Integer, ArrayList<Movie>> runtimeMap;

	private static HashMap<Integer, Movie> fullMovieMap;
	private static ArrayList<Movie> fullMovieList;

	public static void createMovieLibrary() {
		fullMovieMap = MapBuilderLogic.generateFullMap();

		fullMovieList = new ArrayList<Movie>();
		for (Integer key: fullMovieMap.keySet())
			fullMovieList.add(fullMovieMap.get(key));

		dateMap = new HashMap<String, ArrayList<Movie>>();
		directorMap = new HashMap<String, ArrayList<Movie>>();
		parentalRatingMap = new HashMap<String, ArrayList<Movie>>();
		genreMap = new HashMap<String, ArrayList<Movie>>();
		actorMap = new HashMap<String, ArrayList<Movie>>();
		runtimeMap = new HashMap<Integer, ArrayList<Movie>>();


		MapBuilderLogic.createMaps(fullMovieMap, runtimeMap, dateMap, directorMap, parentalRatingMap, genreMap, actorMap);
	}

	public static HashMap<Integer, Movie> getFullMovieMap() {
		return fullMovieMap;
	}

	public static ArrayList<Movie> getFullMovieList() {
		return fullMovieList;
	}

	public static HashMap<String, ArrayList<Movie>> getDateMap() {
		return dateMap;
	}

	public static HashMap<String, ArrayList<Movie>> getDirectorMap() {
		return directorMap;
	}

	public static HashMap<String, ArrayList<Movie>> getGenreMap() {
		return genreMap;
	}

	public static HashMap<String, ArrayList<Movie>> getActorsMap() {
		return actorMap;
	}

	public static HashMap<String, ArrayList<Movie>> getParentalRatingMap() {
		return parentalRatingMap;
	}

	public static HashMap<Integer, ArrayList<Movie>> getRuntimeMap() {
		return runtimeMap;
	}
}