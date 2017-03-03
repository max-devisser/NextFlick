package src;

import java.util.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

//Sort by Date, Rating, Length, or Alphabetical 
public class SortHandler
{
	/*
	public static void main(String[] args)
	{
		TestCollectionBuilder TCB = new TestCollectionBuilder();
		HashMap<Integer, Movie> movies = TCB.getTestCollection();
		ArrayList<Movie> myList = new ArrayList<Movie>();
		for (Integer key: movies.keySet())
			myList.add(movies.get(key));

		myList = sortOldestToNewest(myList);

		System.out.println("sortOldestToNewest");
		for (Movie item: myList)
			System.out.println(item.getDate());

		System.out.println();
		System.out.println("sortNewestToOldest");
		myList = sortNewestToOldest(myList);

		for (Movie item: myList)
			System.out.println(item.getDate());
	}
	*/
	//Descending = true
	//Ascending = false
	//Title, Date, Critical Rating, Length
	public static ArrayList<Movie> chooseSortingMethod(ArrayList<Movie> inputList, String sortingMethod, boolean sortDescending) {
		switch (sortingMethod) {
			case "Length":
				sortShortestToLongest(inputList);
				break;
			case "Critical Rating":
				sortLowestToHighest(inputList);
				break;
			case "Title":
				sortAlphabetical(inputList);
				break;
			case "Date:":
				sortOldestToNewest(inputList);
				break;
		}

		if (!sortDescending)
			Collections.reverse(inputList);

		return inputList;
	}


	//Public methods to be used by SearchPanel
	private static ArrayList<Movie> sortShortestToLongest(ArrayList<Movie> inputList)
	{
		inputList = sortByLength(inputList);
		return inputList;
	}

	private static ArrayList<Movie> sortLowestToHighest(ArrayList<Movie> inputList)
	{
		inputList = sortByRating(inputList);
		return inputList;
	}

	private static ArrayList<Movie> sortAlphabetical(ArrayList<Movie> inputList)
	{
		inputList = sortByAlphabetical(inputList);
		return inputList;
	}

	private static ArrayList<Movie> sortOldestToNewest(ArrayList<Movie> inputList)
	{
		inputList = sortByDate(inputList);
		return inputList;
	}

	//Start sorting methods
	private static ArrayList<Movie> sortByLength(ArrayList<Movie> inputList)
	{
		if (inputList.size() <= 1)
			return inputList;

		quickSortLength(inputList, 0, inputList.size() - 1);

		return inputList;
	}

	private static ArrayList<Movie> sortByRating(ArrayList<Movie> inputList)
	{
		if (inputList.size() <= 1)
			return inputList;

		quickSortRating(inputList, 0, inputList.size() - 1);

		return inputList;
	}
	
	private static ArrayList<Movie> sortByAlphabetical(ArrayList<Movie> inputList)
	{
		if (inputList.size() <= 1)
			return inputList;

		quickSortAlphabetical(inputList, 0, inputList.size() - 1);

		return inputList;
	}

	private static ArrayList<Movie> sortByDate(ArrayList<Movie> inputList)
	{
		if (inputList.size() <= 1)
			return inputList;

		quickSortDate(inputList, 0, inputList.size() - 1);

		return inputList;
	}


	//Sorting Methods
	private static void quickSortLength(ArrayList<Movie> myList, int lowerIndex, int higherIndex)
	{
		int i = lowerIndex;
        int j = higherIndex;

        //Get pivot
        Movie pivot = myList.get(lowerIndex + (higherIndex - lowerIndex) / 2);

        while (i <= j) {
            while (myList.get(i).getRuntime() < pivot.getRuntime()) 
            {
                i++;
            }

            while (pivot.getRuntime() > myList.get(j).getRuntime())
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
            quickSortLength(myList, lowerIndex, j);

        if (i < higherIndex)
            quickSortLength(myList, i, higherIndex);
	}

	private static void quickSortRating(ArrayList<Movie> myList, int lowerIndex, int higherIndex)
	{
		int i = lowerIndex;
        int j = higherIndex;

        //Get pivot
        Movie pivot = myList.get(lowerIndex + (higherIndex - lowerIndex) / 2);

        while (i <= j) {
            while (myList.get(i).getCriticalRating() < pivot.getCriticalRating()) 
            {
                i++;
            }

            while (pivot.getCriticalRating() > myList.get(j).getCriticalRating())
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
            quickSortRating(myList, lowerIndex, j);

        if (i < higherIndex)
            quickSortRating(myList, i, higherIndex);
	}

	private static void quickSortAlphabetical(ArrayList<Movie> myList, int lowerIndex, int higherIndex)
	{
		int i = lowerIndex;
        int j = higherIndex;

        //Get pivot
        Movie pivot = myList.get(lowerIndex + (higherIndex - lowerIndex) / 2);

        while (i <= j) {
            while (pivot.getTitle().compareTo(myList.get(i).getTitle()) > 0) 
            {
                i++;
            }

            while (pivot.getTitle().compareTo(myList.get(j).getTitle()) < 0) 
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
            quickSortAlphabetical(myList, lowerIndex, j);

        if (i < higherIndex)
            quickSortAlphabetical(myList, i, higherIndex);
	}

	private static void quickSortDate(ArrayList<Movie> myList, int lowerIndex, int higherIndex)
	{
		int i = lowerIndex;
        int j = higherIndex;

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
            quickSortDate(myList, lowerIndex, j);

        if (i < higherIndex)
            quickSortDate(myList, i, higherIndex);
	}

	//returns true is the left hand side is an older date than the right hand side
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