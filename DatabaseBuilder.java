
public class DatabaseBuilder 
{

	public static void main(String[] args) 
	{
		MovieParser parser = new MovieParser();
		Movie fightClub = parser.constructMovieById(550, "credits");
		System.out.println(fightClub.getPlot());
		
		
	}

}
