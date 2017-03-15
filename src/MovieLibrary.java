package src;

import java.util.*;
import src.movie_builder.MovieSerializationManager;
import java.io.IOException;
import java.io.File;

public class MovieLibrary {
	private HashMap<String, ArrayList<Movie>> dateMap;
	private HashMap<String, ArrayList<Movie>> directorMap;
	private HashMap<String, ArrayList<Movie>> parentalRatingMap;
	private HashMap<String, ArrayList<Movie>> languageMap;
	private HashMap<String, ArrayList<Movie>> countryMap;
	private HashMap<String, ArrayList<Movie>> genreMap;
	private HashMap<String, ArrayList<Movie>> actorMap;
	private HashMap<Integer, ArrayList<Movie>> runtimeMap;
	private HashMap<Double, ArrayList<Movie>> criticalRatingMap;

	private HashMap<Integer, Movie> fullMovieMap;
	private ArrayList<Movie> fullMovieList;

	public MovieLibrary() {
		fullMovieMap = MapBuilderLogic.generateFullMap();

		fullMovieList = new ArrayList<Movie>();
		for (Integer key: fullMovieMap.keySet())
			fullMovieList.add(fullMovieMap.get(key));

		dateMap = new HashMap<String, ArrayList<Movie>>();
		directorMap = new HashMap<String, ArrayList<Movie>>();
		parentalRatingMap = new HashMap<String, ArrayList<Movie>>();
		languageMap = new HashMap<String, ArrayList<Movie>>();
		countryMap = new HashMap<String, ArrayList<Movie>>();
		genreMap = new HashMap<String, ArrayList<Movie>>();
		actorMap = new HashMap<String, ArrayList<Movie>>();
		runtimeMap = new HashMap<Integer, ArrayList<Movie>>();
		criticalRatingMap = new HashMap<Double, ArrayList<Movie>>();

		MapBuilderLogic.createMaps(fullMovieMap, runtimeMap, criticalRatingMap, dateMap, directorMap, parentalRatingMap, countryMap, languageMap, genreMap, actorMap);
	}

	public HashMap<Integer, Movie> getFullMovieMap() {
		return fullMovieMap;
	}

	public ArrayList<Movie> getFullMovieList() {
		return fullMovieList;
	}

	public HashMap<String, ArrayList<Movie>> getDateMap() {
		return dateMap;
	}

	public HashMap<String, ArrayList<Movie>> getDirectorMap() {
		return directorMap;
	}

	public HashMap<String, ArrayList<Movie>> getGenreMap() {
		return genreMap;
	}

	public HashMap<String, ArrayList<Movie>> getActorsMap() {
		return actorMap;
	}

	public HashMap<String, ArrayList<Movie>> getParentalRatingMap() {
		return parentalRatingMap;
	}

	public HashMap<Integer, ArrayList<Movie>> getRuntimeMap() {
		return runtimeMap;
	}

	public HashMap<String, ArrayList<Movie>> getLanguageMap() {
		return languageMap;
	}

	public HashMap<String, ArrayList<Movie>> getCountryMap() {
		return countryMap;
	}

	public HashMap<Double, ArrayList<Movie>> getCriticalRatingMap() {
		return criticalRatingMap;
	}
}