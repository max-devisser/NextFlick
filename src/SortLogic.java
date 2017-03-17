package src;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Sorts movie lists by Title, Critical Rating, Date, and Length
 */
public class SortLogic {

	/**
	 * Sort of list of movies by the specified parameters
	 * 
	 * @param inputList
	 *            List of movies to sort
	 * @param sortingMethod
	 *            What to sort the list by (title, date, critical rating, or
	 *            length)
	 * @param sortDescending
	 *            Whether to sort descending or ascending
	 * @return A sorted list of movies
	 */
	public static ArrayList<Movie> sortList(ArrayList<Movie> inputList, String sortingMethod, boolean sortDescending) {
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

	/**
	 * Quicksort implementation for movie length
	 * 
	 * @param myList
	 *            List to sort
	 * @param lowerIndex
	 * @param higherIndex
	 */
	private static void quickSortLength(ArrayList<Movie> myList, int lowerIndex, int higherIndex) {
		int i = lowerIndex;
		int j = higherIndex;

		Movie pivot = myList.get(lowerIndex + (higherIndex - lowerIndex) / 2);

		while (i <= j) {
			while (myList.get(i).getRuntime() < pivot.getRuntime()) {
				i++;
			}

			while (myList.get(j).getRuntime() > pivot.getRuntime()) {
				j--;
			}

			if (i <= j) {
				Collections.swap(myList, i, j);
				i++;
				j--;
			}
		}

		if (lowerIndex < j) {
			quickSortLength(myList, lowerIndex, j);
		}

		if (i < higherIndex) {
			quickSortLength(myList, i, higherIndex);
		}
	}

	/**
	 * Quicksort implementation for movie rating
	 * 
	 * @param myList
	 *            List to sort
	 * @param lowerIndex
	 * @param higherIndex
	 */
	private static void quickSortRating(ArrayList<Movie> myList, int lowerIndex, int higherIndex) {
		int i = lowerIndex;
		int j = higherIndex;

		Movie pivot = myList.get(lowerIndex + (higherIndex - lowerIndex) / 2);

		while (i <= j) {
			while (myList.get(i).getCriticalRating() < pivot.getCriticalRating()) {
				i++;
			}

			while (myList.get(j).getCriticalRating() > pivot.getCriticalRating()) {
				j--;
			}

			if (i <= j) {
				Collections.swap(myList, i, j);
				i++;
				j--;
			}
		}

		if (lowerIndex < j) {
			quickSortRating(myList, lowerIndex, j);
		}

		if (i < higherIndex) {
			quickSortRating(myList, i, higherIndex);
		}
	}

	/**
	 * Quicksort implementation for movie title
	 * 
	 * @param myList
	 *            List to sort
	 * @param lowerIndex
	 * @param higherIndex
	 */
	private static void quickSortAlphabetical(ArrayList<Movie> myList, int lowerIndex, int higherIndex) {
		int i = lowerIndex;
		int j = higherIndex;

		Movie pivot = myList.get(lowerIndex + (higherIndex - lowerIndex) / 2);

		while (i <= j) {
			while (pivot.getTitle().compareTo(myList.get(i).getTitle()) > 0) {
				i++;
			}

			while (pivot.getTitle().compareTo(myList.get(j).getTitle()) < 0) {
				j--;
			}

			if (i <= j) {
				Collections.swap(myList, i, j);
				i++;
				j--;
			}
		}

		if (lowerIndex < j) {
			quickSortAlphabetical(myList, lowerIndex, j);
		}

		if (i < higherIndex) {
			quickSortAlphabetical(myList, i, higherIndex);
		}
	}

	/**
	 * Quicksort implementation for movie date
	 * 
	 * @param myList
	 *            List to sort
	 * @param lowerIndex
	 * @param higherIndex
	 */
	private static void quickSortDate(ArrayList<Movie> myList, int lowerIndex, int higherIndex) {
		int i = lowerIndex;
		int j = higherIndex;

		Movie pivot = myList.get(lowerIndex + (higherIndex - lowerIndex) / 2);

		while (i <= j) {
			while (compareDates(myList.get(i), pivot)) {
				i++;
			}

			while (compareDates(pivot, myList.get(j))) {
				j--;
			}

			if (i <= j) {
				Collections.swap(myList, i, j);
				i++;
				j--;
			}
		}

		if (lowerIndex < j) {
			quickSortDate(myList, lowerIndex, j);
		}

		if (i < higherIndex) {
			quickSortDate(myList, i, higherIndex);
		}
	}

	/**
	 * Compares two dates
	 * 
	 * @param left
	 *            First date to compare
	 * @param right
	 *            Second date to compare
	 * @return Whether the left date is older than the right date, returns false
	 *         if both dates are the same
	 */
	private static boolean compareDates(Movie left, Movie right) {
		int leftYear = Integer.parseInt(left.getDate().substring(0, 4));
		int leftMonth = Integer.parseInt(left.getDate().substring(5, 7));
		int leftDay = Integer.parseInt(left.getDate().substring(8, 10));

		int rightYear = Integer.parseInt(right.getDate().substring(0, 4));
		int rightMonth = Integer.parseInt(right.getDate().substring(5, 7));
		int rightDay = Integer.parseInt(right.getDate().substring(8, 10));

		if (leftYear > rightYear) {
			return false;
		}

		else {
			if (leftYear == rightYear) {
				if (leftMonth > rightMonth) {
					return false;
				} else {
					if (leftMonth == rightMonth) {
						if (leftDay > rightDay || leftDay == rightDay) {
							return false;
						}
					}
				}
			}
		}

		return true;
	}
}