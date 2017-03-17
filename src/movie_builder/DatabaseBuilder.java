package src.movie_builder;

import src.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Reads from ratings_id_replaced2.csv and writes the movie keys to recommendation_ids.txt
 */
public class DatabaseBuilder {
	
	/**
	 * Reads from ratings_id_replaced2.csv and writes the movie keys to recommendation_ids.txt
	 * @param args Not used
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		//MovieParser parser = new MovieParser();
		Scanner scan;
		String currLine = "i";
		ArrayList<String> keys = new ArrayList<String>();
		// MovieSerializationManager msm = new MovieSerializationManager();
		scan = new Scanner(
				new File("res" + File.separator + "ml-latest-small" + File.separator + "ratings_id_replaced2.csv"));
		String currId = "";
		while (scan.hasNextLine()) {
			if (!currLine.isEmpty()) {
				currLine = scan.nextLine();
				currId = "";
				int index = 0;
				while (currLine.charAt(index) != ',') {
					++index;
				}
				++index;
				while (currLine.charAt(index) != ',') {
					currId += currLine.charAt(index);
					++index;
				}
				if (!keys.contains(currId)) {
					keys.add(currId);
				}
			}
		}
		scan.close();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("recommendation_ids.txt")));
		for (String key : keys) {
			writer.write(key);
			writer.write('\n');
		}
		writer.close();
	}

}
