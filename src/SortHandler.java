package src;

import java.util.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import src.movie_builder.TestCollectionBuilder;

//Sort by Date, Critical Rating, Length, or Alphabetical 
//Works for Critical Rating, Length, Alpha
public class SortHandler
{
	//Descending = true
	//Ascending = false
	//Title, Date, Critical Rating, Length
	public static ArrayList<Movie> chooseSortingMethod(ArrayList<Movie> inputList, String sortingMethod, boolean sortDescending) {	
		if (inputList.size() <= 1)
			return inputList;

		switch (sortingMethod) {
			case "Length":
				quickSortLength(inputList, 0, inputList.size() - 1);
				break;
			case "Critical Rating":
				quickSortRating(inputList, 0, inputList.size() - 1);
				break;
			case "Title":
				quickSortAlphabetical(inputList, 0, inputList.size() - 1);
				break;
			case "Date":
				quickSortDate(inputList, 0, inputList.size() - 1);
				break;
		}

		if (sortDescending)
			Collections.reverse(inputList);

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

            while (myList.get(j).getRuntime() > pivot.getRuntime())
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

            while (myList.get(j).getCriticalRating() > pivot.getCriticalRating())
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
                        if (leftDay > rightDay || leftDay == rightDay)
    				        return false;
                    }
			    }
    		}
	    }

        return true;
	}
}