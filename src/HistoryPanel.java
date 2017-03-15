package src;

import javax.swing.*;
import java.awt.Dimension;

public class HistoryPanel extends RatePanel {

	private boolean[] ratingsToShow = {true, true, true, true, true};
	private JPanel resultPanel;
	private JScrollPane resultScrollPane;

	public HistoryPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(new JLabel("Movies you've rated: "));

		//resultPanel = createMovieListPanel(Controller.rateStorageFacade.filterByRating(ratingsToShow));
		resultPanel = new JPanel();

		resultScrollPane = new JScrollPane(resultPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		resultScrollPane.setViewportView(resultPanel);
		resultScrollPane.setMaximumSize(new Dimension(800, 400));
		resultScrollPane.getVerticalScrollBar().setUnitIncrement(30);

		this.add(resultScrollPane);
	}
}