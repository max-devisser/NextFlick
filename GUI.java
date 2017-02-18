import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class GUI {
	private JFrame frame;
	private JTabbedPane tabbedPane;
	HomePanel homePanel;
	SearchPanel searchPanel;

	/**
	 * Constructor for GUI, just calls go()
	 */
	public GUI() {
		go();
	}

	/**
	 * Initializes the window with multiple panels
	 */
	public void go() {
		frame = new JFrame();
		tabbedPane = new JTabbedPane();
		homePanel = new HomePanel();
		searchPanel = new SearchPanel();

		tabbedPane.add("Home", homePanel);
		tabbedPane.add("Search", searchPanel);
		frame.getContentPane().add(tabbedPane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 800);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

	/**
	 * Sets results field to display the input map
	 * 
	 * @param collection
	 *            HashMap of movies to be displayed
	 */
	public void setResults(HashMap<Integer, Movie> collection) {

	}

	/**
	 * Getter for search field
	 * 
	 * @return What is currently in the search field
	 */
	public String getSearch() { // STUB
		return "";
	}

	/**
	 * Getter for applied filters
	 * 
	 * @return Currently applied filters
	 */
	public String getFilter() { // STUB
		return "";

	}

	/**
	 * Getter for current sort method
	 * 
	 * @return Currently applied sort
	 */
	public String getSort() { // STUB
		return "";
	}

	/**
	 * 
	 * @return
	 */
	public boolean quit() { // STuB
		return false;
	}

	// return 0 if still on home panel
	// return 1 if on search panel
	/**
	 * Getter for what tab the user is on
	 * 
	 * @return int representation of the current tab. 0 for home, 1 for search
	 */
	public int tabClick() { // STUB
		return 0;
	}
	// public static void main(String[] args){ //TESTING GUI
	// GUI g = new GUI();
	// }
}
