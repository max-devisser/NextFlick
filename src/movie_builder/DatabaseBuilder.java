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
//		Movie seven = parser.constructMovieById(parser.getIdFromSearch("Se7en"), "credits");
//		System.out.println(seven.toString());
//		System.out.println(fightClub.toString());
		Scanner scan;
		String currLine = "";
		HashMap<Integer, Movie> database = new HashMap<Integer, Movie>();
		MovieSerializationManager msm = new MovieSerializationManager();
		try
		{
			scan = new Scanner(new File("Movies_Encoded.txt"));
			//System.out.println("Scanner initialized");
			currLine = scan.nextLine();
			while(scan.hasNextLine())
			{
				if (!currLine.isEmpty())
				{
					Thread.sleep(300);
					System.out.println("Current query: " + currLine);
					int id = parser.getIdFromSearch(currLine);
					System.out.println("Grabbing movie with ID " + id);
					Movie result = parser.constructMovieById(id, "credits,releases");
					database.put(id, result);
					currLine = scan.nextLine();
				}
			}
			msm.serialize(database, "Top_250_serialized3.txt");
//			System.out.println("Movie list:");
//			for (Movie m : database.values())
//			{
//				System.out.println(m.getTitle());
//			}
			scan.close();
		}
		catch (Exception e)
		{
			System.out.println("Something went wrong");
			System.out.println("Current query: " + currLine);
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}

}
