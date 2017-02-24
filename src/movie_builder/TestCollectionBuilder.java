package src.movie_builder;

import java.util.ArrayList;
import java.util.HashMap;
import src.Movie;

public class TestCollectionBuilder {
	private HashMap<Integer, Movie> movies;
	public HashMap<Integer, Movie> getTestCollection()
	{
		movies = new HashMap<Integer, Movie>();
		Movie shawshank = new Movie(278);
		ArrayList<String> genres = new ArrayList<String>();
		genres.add("Drama");
		genres.add("Crime");
		ArrayList<String> actors = new ArrayList<String>();
		actors.add("Tim Robbins");
		actors.add("Morgan Freeman");
		actors.add("Bob Gunton");
		actors.add("Clancy Brown");
		shawshank.setActors(actors);
		shawshank.setCountry("US");
		shawshank.setCriticalRating(8.4);
		shawshank.setDate("1994-09-10");
		shawshank.setDirector("Frank Darabont");
		shawshank.setGenre(genres);
		shawshank.setImageURL("/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg");
		shawshank.setLanguage("en");
		shawshank.setParentalRating("R");
		shawshank.setPlot("Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.");
		shawshank.setRuntime(142);
		shawshank.setTitle("The Shawshank Redemption");
		movies.put(1, shawshank);
		
		Movie godfather = new Movie(238);
		//happens to be same genres
		ArrayList<String> actors2 = new ArrayList<String>();
		actors2.add("Al Pacino");
		actors2.add("Marlon Brando");
		actors2.add("James Caan");
		actors2.add("Robert Duvall");
		actors2.add("Diane Keaton");
		godfather.setActors(actors2);
		godfather.setCountry("US");
		godfather.setCriticalRating(8.3);
		godfather.setDate("1972-03-24");
		godfather.setDirector("Francis Ford Coppola");
		ArrayList<String> godGenres = new ArrayList<String>();
		godGenres.add("Drama");
		godGenres.add("Crime");
		godGenres.add("Gangster");
		godfather.setGenre(godGenres);
		godfather.setImageURL("/d4KNaTrltq6bpkFS01pYtyXa09m.jpg");
		godfather.setLanguage("en");
		godfather.setParentalRating("R");
		godfather.setPlot("Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.");
		godfather.setRuntime(178);
		godfather.setTitle("The Godfather");
		movies.put(2, godfather);
		
		Movie batmaaaan = new Movie(155);
		ArrayList<String> actors3 = new ArrayList<String>();
		actors3.add("Christian Bale");
		actors3.add("Heath Ledger");
		actors3.add("Gary Oldman");
		actors3.add("Michael Caine");
		actors3.add("Morgan Freeman");
		batmaaaan.setActors(actors3);
		batmaaaan.setCountry("US");
		batmaaaan.setCriticalRating(8.1);
		batmaaaan.setDate("2008-07-18");
		batmaaaan.setDirector("Christopher Nolan");
		ArrayList<String> batGenres = new ArrayList<String>();
		batGenres.add("Thriller");
		batGenres.add("Action");
		batmaaaan.setGenre(batGenres);
		batmaaaan.setImageURL("/1hRoyzDtpgMU7Dz4JF22RANzQO7.jpg");
		batmaaaan.setLanguage("en");
		batmaaaan.setParentalRating("PG-13");
		batmaaaan.setPlot("Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to dismantle the remaining criminal organizations that plague the streets. The partnership proves to be effective, but they soon find themselves prey to a reign of chaos unleashed by a rising criminal mastermind known to the terrified citizens of Gotham as the Joker.");
		batmaaaan.setRuntime(152);
		batmaaaan.setTitle("Batman");
		movies.put(3, batmaaaan);
		
		Movie whitedudes = new Movie(389);
		ArrayList<String> actors4 = new ArrayList<String>();
		actors4.add("Henry Fonda");
		actors4.add("Lee J. Cobb");
		actors4.add("Martin Balsam");
		actors4.add("Jack Klugman");
		actors4.add("E. G. Marshall");
		whitedudes.setActors(actors4);
		whitedudes.setCountry("US");
		whitedudes.setCriticalRating(8.1);
		whitedudes.setDate("1957-03-25");
		whitedudes.setDirector("Sidney Lumet");
		ArrayList<String> whiteGenres = new ArrayList<String>();
		whiteGenres.add("Drama");
		whitedudes.setGenre(whiteGenres);
		whitedudes.setImageURL("/3W0v956XxSG5xgm7LB6qu8ExYJ2.jpg");
		whitedudes.setLanguage("en");
		whitedudes.setParentalRating("Unrated XXX Steamy Hot");
		whitedudes.setPlot("The defense and the prosecution have rested and the jury is filing into the jury room to decide if a young Spanish-American is guilty or innocent of murdering his father. What begins as an open and shut case soon becomes a mini-drama of each of the jurors' prejudices and preconceptions about the trial, the accused, and each other.");
		whitedudes.setRuntime(96);
		whitedudes.setTitle("12 Angry Men");
		movies.put(4, whitedudes);
		
		Movie schindler = new Movie(424);
		ArrayList<String> actors5 = new ArrayList<String>();
		actors5.add("Liam Neeson");
		actors5.add("Ralph Fiennes");
		actors5.add("Ben Kingsley");
		actors5.add("Embeth Davidtz");
		actors5.add("Caroline Goodall");
		schindler.setActors(actors5);
		schindler.setCountry("US");
		schindler.setCriticalRating(8.2);
		schindler.setDate("1993-11-29");
		schindler.setDirector("Steven Spielberg");
		ArrayList<String> schindlerGenres = new ArrayList<String>();
		schindlerGenres.add("Drama");
		schindlerGenres.add("Crime");
		schindler.setGenre(schindlerGenres);
		schindler.setImageURL("/yPisjyLweCl1tbgwgtzBCNCBle.jpg");
		schindler.setLanguage("en");
		schindler.setParentalRating("R");
		schindler.setPlot("The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory during World War II.");
		schindler.setRuntime(195);
		schindler.setTitle("Schindler's List");
		movies.put(5, schindler);
		
		
		
		return movies;
	}
}
