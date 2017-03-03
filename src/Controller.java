package src;

//Controller class
//Starts the program and manages interactions between the GUI and backend
//Barbara Korycki
import java.util.HashMap;

public class Controller {
	private GUI gui;
	private RatingHistory ratingHistory;

	/**
	 * Constructor for Controller object
	 * @param g GUI object 
	 */
	public Controller(GUI g) {
		gui = g;
		ratingHistory = g.getRatingHistory();		//originally empty--no ratings s
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
	 * Will be used to switch between getting data from User account and GUI
	 */
	public void go() {
		while (!gui.quit()) { // genGUI quit() method checks if user quit
			wait(gui);

		}
	}

	/**
	 * Main method. Initializes GUI object, initializes and starts Controller object
	 * @param args Not used here
	 */
	public static void main(String[] args) {
		RatingHistory history = new RatingHistory();
		GUI gui = new GUI(history); // Home panel opens by default
		Controller controller = new Controller(gui);
		controller.go();
	}
}