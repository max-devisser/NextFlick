package src;

import java.util.*;
import src.movie_builder.TestCollectionBuilder;


//Sort by Date, Rating, Length, or Alphabetical 
public class SortHandler
{
	public static void main(String[] args)
	{
		TestCollectionBuilder TCB = new TestCollectionBuilder();
		HashMap<Integer, Movie> movies = TCB.getTestCollection();
		ArrayList<Movie> myList = new ArrayList<Movie>();
		for (Integer key: movies.keySet())
			myList.add(movies.get(key));

		myList = sortOldestToNewest(myList);

		for (Movie item: myList)
			System.out.println(item.getDate());

		System.out.println();

		myList = sortNewestToOldest(myList);

		for (Movie item: myList)
			System.out.println(item.getDate());
	}

	public static ArrayList<Movie> sortOldestToNewest(ArrayList<Movie> inputList)
	{
		inputList = sortByDate(inputList);
		return inputList;
	}

	public static ArrayList<Movie> sortNewestToOldest(ArrayList<Movie> inputList)
	{
		inputList = sortByDate(inputList);
		Collections.reverse(inputList);
		return inputList;
	}

	public static ArrayList<Movie> sortByDate(ArrayList<Movie> inputList)
	{
		if (inputList.size() <= 1)
			return inputList;

		quickSort(inputList, 0, inputList.size() - 1);

		return inputList;
	}

	private static void quickSortDate(ArrayList<Movie> myList, int lowerIndex, int higherIndex)
	{
		int i = lowerIndex;
        int j = higherIndex;
        int pivotDay, pivotMonth, pivotYear;

        //Get pivot
        Movie pivot = myList.get(lowerIndex + (higherIndex - lowerIndex) / 2);

        while (i <= j) {
            while (compareDates(myList.get(i), pivot)) 
            {
                i++;
            }

            while (compareDates(pivot, myList.get(j))) 
            {
                j--;
            }

            if (i <= j) {
            	Collections.swap(myList, i, j);
                i++;
                j--;
            }
        }

        if (lowerIndex < j)
            quickSort(myList, lowerIndex, j);

        if (i < higherIndex)
            quickSort(myList, i, higherIndex);
	}

	//returns true is the left hand side is earlier than the right hand side
	private static boolean compareDates(Movie left, Movie right)
	{
        int leftYear = Integer.parseInt(left.getDate().substring(0,4));
        int leftMonth = Integer.parseInt(left.getDate().substring(5,7));
        int leftDay = Integer.parseInt(left.getDate().substring(8,10));

        int rightYear = Integer.parseInt(right.getDate().substring(0,4));
        int rightMonth = Integer.parseInt(right.getDate().substring(5,7));
        int rightDay = Integer.parseInt(right.getDate().substring(8,10));

        if (leftYear > rightYear)
            return false;

        else
	    {
    		if (leftYear == rightYear)
    		{
    			if (leftMonth > rightMonth)
    			    return false;

    			else
			    {
                    if (leftMonth == rightMonth)
    				{ 
                        if (leftDay > rightMonth)
    				        return false;
                    }
			    }
    		}
	    }

        return true;
	}
}












