package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class GUI {
	private JFrame frame;
	private JTabbedPane tabbedPane;
	private RatingHistory ratingHistory;
	HomePanel homePanel;
	SearchPanel searchPanel;
	RecPanel recPanel;

	/**
	 * Constructor for GUI, just calls go()
	 */
	public GUI(RatingHistory ratings) {
		ratingHistory = ratings;
		go();
	}

	/**
	 * Initializes the window with multiple panels
	 */
	public void go() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 0, 0) );
		frame.setTitle("NextFlick");
		tabbedPane = new JTabbedPane();
		homePanel = new HomePanel(ratingHistory);
		searchPanel = new SearchPanel(homePanel);
		recPanel = new RecPanel(ratingHistory);
		searchPanel.setBackground(Color.WHITE);
		homePanel.setBackground(Color.WHITE);
		recPanel.setBackground(Color.WHITE);

		JPanel searchWrapper = new JPanel();
		searchWrapper.setLayout(new BoxLayout(searchWrapper, BoxLayout.Y_AXIS));
		searchWrapper.add(searchPanel);
		searchWrapper.setMaximumSize(new Dimension(400, 200));

		JScrollPane homeScroll = new JScrollPane(homePanel);
		JScrollPane searchScroll = new JScrollPane(searchWrapper);
        homeScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        homeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        searchScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        searchScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        searchScroll.setBounds(0, 0, 0, 0);

		tabbedPane.add("Search", searchPanel);
        tabbedPane.add("Home", homeScroll);
        tabbedPane.add("Recommendations", recPanel);


		frame.getContentPane().add(tabbedPane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1300, 800);
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

	public RatingHistory getRatingHistory(){
		return this.ratingHistory;
	}

}
