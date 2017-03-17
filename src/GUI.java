package src;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


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
		//go();
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
        tabbedPane.add("History", historyPanel);
        tabbedPane.add("Recommendations", recommendPanel);
        tabbedPane.addChangeListener(new updateResultListener());

		frame.getContentPane().add(tabbedPane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1300, 800);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

	public class updateResultListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			JTabbedPane sourcePane = (JTabbedPane) e.getSource();
			String selectedTitle = sourcePane.getTitleAt(sourcePane.getSelectedIndex());
			switch (selectedTitle) {
				case "History":
					historyPanel.updateResultPanel();
					break;
				case "Search":
					searchPanel.updateResultPanel();
					break;
				case "Recommendations":
					recommendPanel.updateResultPanel();
			}
		}
	}
}