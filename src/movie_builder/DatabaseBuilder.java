package src.movie_builder;
import src.Movie;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class DatabaseBuilder 
{

	public static void main(String[] args) 
	{
		MovieParser parser = new MovieParser();
//		Movie fightClub = parser.constructMovieById(550, "credits");
//		System.out.println(fightClub.toString());
		Scanner scan;
		String currLine = "";
		HashMap<Integer, Movie> database = new HashMap<Integer, Movie>();
		try
		{
			scan = new Scanner(new File("Movies_Encoded.txt"));
			System.out.println("Scanner initialized");
			currLine = scan.nextLine();
			while(scan.hasNextLine())
			{
				if (!currLine.isEmpty())
				{
					currLine = scan.nextLine();
					int id = parser.getIdFromSearch(currLine);
					System.out.println("Grabbing movie with ID" + id);
					Movie result = parser.constructMovieById(id, "credits,releases");
					database.put(id, result);
				}
			}
			System.out.println("Movie list:");
			for (Movie m : database.values())
			{
				System.out.println(m.getTitle());
			}
			scan.close();
		}
		catch (Exception e)
		{
			System.out.println("Error opening text file");
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}

}
