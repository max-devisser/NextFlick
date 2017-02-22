package src.movie_builder;

import java.util.ArrayList;

public class JsonSearcher {
	private String input;
	private int index = 0;

	
	public JsonSearcher(String in)
	{
		input = in;
	}
	public double readDouble() throws NumberFormatException
	{
		while (!Character.isDigit(input.charAt(index)))
		{
			++index;
		}
		String result = "";
		while (input.charAt(index) != ',')
		{
			result += input.charAt(index);
			++index;
		}
		return Double.parseDouble(result);
	}
	public boolean skipToContainer(String containedBy)
	{
		index  = input.indexOf(containedBy);
		return (index != -1);
	}
	public boolean skipToField(String targetField)
	{
		index = input.indexOf('\"' + targetField + '\"', index);
		return (index != -1);
	}
	public void resetIndex()
	{
		index = 0;
	}
	public int readInt() throws NumberFormatException
	{
		while (!Character.isDigit(input.charAt(index)))
		{
			++index;
		}
		String result = "";
		while (input.charAt(index) != ',')
		{
			result += input.charAt(index);
			++index;
		}
		return Integer.parseInt(result);
	}
	public String readString()
	{
		while (input.charAt(index) != ':') ++index;
		while (input.charAt(index) != '\"') ++index;
		String result = "";
		while (input.charAt(index) != '\"')
		{
			result += input.charAt(index);
			++index;
		}
		return result;
	}
	public ArrayList<String> readArray(String container, String field)
	{
		
	}
}
