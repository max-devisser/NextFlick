package src;

//Controller class
//Starts the program and manages interactions between the GUI and backend
//Barbara Korycki
import java.util.HashMap;

public class Controller {
	private GUI gui;
	private SearchController searchController;

	/**
	 * Constructor for Controller object
	 * @param g GUI object that is passed to SearchController for use
	 */
	public Controller(GUI g) {
		gui = g;
		searchController = new SearchController(g);
	}

	/**
	 * Helper method to let the thread sleep
	 */
	public static void sleep() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param gui
	 */
	public void wait(GUI gui) {
		while ((gui.getSearch()).isEmpty() || !gui.quit()) { // HomeGUI class needs a getSearch() method that returns a string
			// return empty string if user has not entered anything?
			// needs to stop user from searching an empty string
			Controller.sleep();
		}
	}

	/**
	 * Currently calls on SearchController's go method
	 */
	public void go() {
		while (!gui.quit()) { // genGUI quit() method checks if user quit
			wait(gui);
			// if(searchGUI.tabClick()){
			// searchController.reset(); //need to clear search in case user
			// comes back to search page
			// //open new page
			// }
			searchController.searchGo();
		}
	}

	/**
	 * Main method. Initializes GUI object, initializes and starts Controller object
	 * @param args Not used here
	 */
	public static void main(String[] args) {
		GUI gui = new GUI(); // Home panel opens by default
		Controller controller = new Controller(gui);
		controller.go();
	}
}