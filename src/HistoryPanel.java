package src;

import javax.swing.*;
import java.awt.Dimension;

/**
 * JPanel to display user's rating history
 */
public class HistoryPanel extends RatePanel {

	private boolean[] ratingsToShow = {true, true, true, true, true};
	private JPanel resultPanel;
	private JScrollPane resultScrollPane;

	
	/**
	 * Constructor. Initializes the panel and sets up the scroll pane 
	 */
	public HistoryPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(new JLabel("Movies you've rated: "));

		resultPanel = createMovieListPanel(Controller.rateStorageApplication.filterByRating(ratingsToShow));

		resultScrollPane = new JScrollPane(resultPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		resultScrollPane.setViewportView(resultPanel);
		resultScrollPane.setPreferredSize(new Dimension(800, 400));
		resultScrollPane.getVerticalScrollBar().setUnitIncrement(30);

		this.add(resultScrollPane);
	}

	
	/**
	 * Removes, refresh, and display the result panel
	 */
	public void updateResultPanel() {
		if (resultPanel != null)
			resultScrollPane.getViewport().remove(resultPanel);

		resultPanel = createMovieListPanel(Controller.rateStorageApplication.filterByRating(ratingsToShow));
		resultScrollPane.getViewport().add(resultPanel);
		resultScrollPane.setViewportView(resultPanel);	
	}
}