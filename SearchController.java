import java.util.HashMap;

public class SearchController {
	private String input;
	private String filter;
	private String sort;
	private HashMap collection;
	private GUI gui;

	/**
	 * Constructor for SearchController
	 * @param g GUI object
	 */
	public SearchController(GUI g) {
		gui = g;
		input = gui.getSearch();
		filter = gui.getFilter();
		sort = gui.getSort();
		collection = new HashMap();
	}

	/**
	 * Reads user input and filters results based on that
	 */
	public void searchGo() {
		collection = FilterHandler.searchParameter(collection, filter, sort);
		gui.setResults(collection);
	}

	/**
	 * Sets all fields back to zero
	 */
	public void reset() {
		input = "";
		filter = "";
		sort = "";
		collection = new HashMap();
	}
}
