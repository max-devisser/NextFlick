package src;

import java.io.*;

/**
 * Main class that initializes program and provides static member variables
 * for communicating with rating storage, library, and recommendations
 */
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

	
	/**
	 * Add GUI to controller
	 * @param gui GUI to tie to the controller
	 */
	public void addGUI(GUI gui) {
		nextFlickGUI = gui;
	}

	/**
	 * Calls on go() within GUI associated with this controller. Will be used to switch between 
	 * getting data from User account and GUI
	 */
	public void go() {
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

