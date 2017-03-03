package src.movie_builder;

import java.util.ArrayList;
//pulls out relevant information from TMDB JSON requests
public class JsonSearcher {
	private StringBuilder input; //full JSON text
	private int index = 0;

	
	public JsonSearcher(String in)
	{
		input.append(in);
	}
	public JsonSearcher(StringBuilder in)
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
		index  = input.indexOf('\"' + containedBy + '\"' + ":[", index);
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
		try
		{
			while (!Character.isDigit(input.charAt(index)))
			{
				++index;
			}
		}
		catch (StringIndexOutOfBoundsException e)
		{
			System.out.println(input);
			e.printStackTrace();
			System.exit(2);
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
		++index;
		String result = "";
		int len = input.length(); //dynamic programming
		while (index < len) //im a god i cut out like 1000 function calls
		{
			if (input.charAt(index) == '\"' && (input.charAt(index + 1) == ',' || input.charAt(index + 1) == '}'))
			{
				break;
			}
			else
			{
				result += input.charAt(index);
				++index;
			}
		}
		return result;
	}
	public ArrayList<String> readArray(String container, String field)
	{
		skipToContainer(container);
		int count = 0;
		int endIndex = input.indexOf("]", index);
		ArrayList<String> results = new ArrayList<String>();
		while (index < endIndex && index != -1 && count <= 10)
		{
			++count;
			skipToField(field);
			if (index < endIndex && index != -1)
			{
				results.add(readString());
			}
		}
		return results;
	}
}
