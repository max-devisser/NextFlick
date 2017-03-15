package src;

import javax.swing.*;
//import javax.swing.border.*;
import java.awt.Color;
import java.awt.event.*;

public class GUI {
	private JFrame frame;
	private JTabbedPane tabbedPane;
	private HistoryPanel historyPanel;
	private SearchPanel searchPanel;
	private RecommendationPanel recommendPanel;

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
		frame.getContentPane().setBackground(new Color(204, 0, 0) );
		frame.setTitle("NextFlick");
		tabbedPane = new JTabbedPane();
		historyPanel = new HistoryPanel();
		searchPanel = new SearchPanel();
		recommendPanel = new RecommendationPanel();
		
		historyPanel.setBackground(Color.WHITE);
		searchPanel.setBackground(Color.WHITE);
		recommendPanel.setBackground(Color.WHITE);

		tabbedPane.add("Search", searchPanel);
        tabbedPane.add("Home", historyPanel);
        tabbedPane.add("Recommendations", recommendPanel);

		frame.getContentPane().add(tabbedPane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1300, 800);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
	
	/**
	 * @return
	 */
	public boolean quit() { 
		return false;
	}

	// return 0 if still on home panel
	// return 1 if on search panel
	/**
	 * Getter for what tab the user is on
	 * 
	 * @return int representation of the current tab. 0 for home, 1 for search
	 */
	public int tabClick() { 
		return 0;
	}
}