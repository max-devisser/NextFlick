package src.movie_builder;
import src.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class DatabaseBuilder 
{
	public static void main(String[] args) 
	{
		MovieParser parser = new MovieParser();
		Scanner scan;
		String currLine = "";
		ArrayList<Movie> database = new ArrayList<Movie>();
		MovieSerializationManager msm = new MovieSerializationManager();
		try
		{
			scan = new Scanner(new File("Movies_encoded.txt"));
			currLine = scan.nextLine();
			while(scan.hasNextLine())
			{
				if (!currLine.isEmpty())
				{
					Thread.sleep(375);
					System.out.println("Current query: " + currLine);
					int id = parser.getIdFromSearch(currLine);
					System.out.println("Grabbing movie with ID " + id);
					Movie result = parser.constructMovieById(id, "credits,releases");
					database.add(result);
					currLine = scan.nextLine();
				}
			}
			msm.serializeOverwrite(database, "Top_250_serialized5.txt");
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
