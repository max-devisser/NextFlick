package src.movie_builder;

import java.util.ArrayList;

/**
 * Pulls out relevant information from TMDB JSON requests
 */
public class JsonSearcher {
	private StringBuilder input; // full JSON text
	private int index; // current search index

	/**
	 * Constructs JsonSearcher with the given String-formatted JSON
	 * 
	 * @param in
	 *            The JSON text in String format
	 **/
	public JsonSearcher(String in) {
		input = new StringBuilder();
		input.append(in);
		index = 0;
	}

	/**
	 * Constructs JsonSearcher with the given StringBuilder-formatted JSON
	 * 
	 * @param in
	 *            The JSON text in StringBuilder format
	 **/
	public JsonSearcher(StringBuilder in) {
		input = in;
		index = 0;
	}

	/**
	 * Assumes index is right before a double field. Reads in next double value
	 * from JSON
	 * 
	 * @return Returns next double object after current index
	 **/
	public double readDouble() throws NumberFormatException {
		while (!Character.isDigit(input.charAt(index))) {
			++index;
		}
		String result = "";
		while (input.charAt(index) != ',') {
			result += input.charAt(index);
			++index;
		}
		return Double.parseDouble(result);
	}

	/**
	 * Skips to JSON Array object denoted by the given field
	 * 
	 * @param containedBy
	 *            The name of the JSON Array object to skip to
	 * @return Returns true if container is found or false if not
	 **/
	public boolean skipToContainer(String containedBy) {
		index = input.indexOf('\"' + containedBy + '\"' + ":[", index);
		return (index != -1);
	}

	/**
	 * Skips to JSON Primitive object [String, int, double] denoted by the given
	 * field
	 * 
	 * @param targetField
	 *            The name of the JSON Primitive object to skip to
	 * @return Returns true if field is found or false if not
	 **/
	public boolean skipToField(String targetField) {
		index = input.indexOf('\"' + targetField + '\"' + ':', index);
		return (index != -1);
	}

	/**
	 * Skips to next instance of given text, within a field or value
	 * 
	 * @param target
	 *            The string to match
	 * @return Returns true if target is found or false if not
	 **/
	public boolean skipTo(String target) {
		index = input.indexOf(target, index);
		return (index != 1);
	}

	/**
	 * Resets index to zero. Should be called before each new field or array
	 * search
	 **/
	public void resetIndex() {
		index = 0;
	}

	/**
	 * Assumes index is right before an int field. Reads in next int value from
	 * JSON
	 * 
	 * @return Returns next int object after current index
	 **/
	public int readInt() throws NumberFormatException {
		try {
			while (!Character.isDigit(input.charAt(index))) {
				++index;
			}
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println(input);
			e.printStackTrace();
			System.exit(2);
		}
		String result = "";
		while (input.charAt(index) != ',') {
			result += input.charAt(index);
			++index;
		}
		return Integer.parseInt(result);
	}

	/**
	 * Assumes index is right before a String field. Reads in next String value
	 * from JSON
	 * 
	 * @return Returns next String object after current index
	 **/
	public String readString() {
		while (input.charAt(index) != ':')
			++index;
		while (input.charAt(index) != '\"')
			++index;
		++index;
		String result = "";
		int len = input.length();
		while (index < len) { // im a god i cut out like 1000 function calls
			if (input.charAt(index) == '\"' && (input.charAt(index + 1) == ',' || input.charAt(index + 1) == '}')) {
				break;
			} else {
				result += input.charAt(index);
				++index;
			}
		}
		return result;
	}

	/**
	 * Reads in an array of the given field from the given container
	 * 
	 * @param container
	 *            The name of the JSON array which contains the field you are
	 *            looking for. Skips to this container first
	 * @param field
	 *            The name of the field whose array values you would like to
	 *            read in.
	 * @return Returns Array object with all instances of the given field within
	 *         the given JSON array
	 **/
	public ArrayList<String> readArray(String container, String field) {
		skipToContainer(container);
		int count = 0;
		int endIndex = input.indexOf("]", index); // find where the array ends
		ArrayList<String> results = new ArrayList<String>();
		while (index < endIndex && index != -1 && count <= 10) { // don't want to read in more than 10 actors nor
																 // try to read an invalid index
			++count;
			skipToField(field);
			if (index < endIndex && index != -1) {
				results.add(readString());
			}
		}
		return results;
	}

	/**
	 * Reads IDs
	 * 
	 * @return List of IDs
	 */
	public ArrayList<Integer> readSearchArray() {
		skipToContainer("results");
		int endIndex = input.indexOf("\"total_results\"");
		ArrayList<Integer> results = new ArrayList<Integer>();
		while (index < endIndex && index != -1) {
			skipToField("id");
			if (index < endIndex && index != -1) {
				results.add(readInt());
			}
		}
		return results;
	}
}
