package src.movie_builder;
import Movie;

public class DatabaseBuilder 
{

	public static void main(String[] args) 
	{
		MovieParser parser = new MovieParser();
		Movie fightClub = parser.constructMovieById(550, "credits");
		System.out.println(fightClub.getPlot());
		
		
	}

}
