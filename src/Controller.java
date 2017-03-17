package src;

public class Controller {
	private GUI nextFlickGUI;
	public static RateStorageApplication rateStorageApplication;
	public static LibraryApplication libraryApplication;
	public static RecommendationApplication recommendApplication;


	/**
	 * Constructor for Controller object
	 * @param gui GUI object 
	 */
	public Controller() {
		rateStorageApplication = new RateStorageApplication();
		libraryApplication = new LibraryApplication();
		recommendApplication = new RecommendationApplication();
	}

	public void addGUI(GUI gui) {
		nextFlickGUI = gui;
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
	 * Will be used to switch between getting data from User account and GUI
	 */
	public void go() {
		// while (!nextFlickGUI.quit()) { // genGUI quit() method checks if user quit
		// 	wait(nextFlickGUI);
		// }
		nextFlickGUI.go();
	}

	/**
	 * Main method. Initializes GUI object, initializes and starts Controller object
	 * @param args Not used here
	 */

	public static void main(String[] args) {
		Controller controller = new Controller();
		GUI gui = new GUI(); // Home panel opens by default
		controller.addGUI(gui);
		controller.go();
	}
}

