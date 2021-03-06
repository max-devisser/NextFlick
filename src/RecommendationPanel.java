package src;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Panel for displaying recommended movies
 */
public class RecommendationPanel extends RatePanel {
	private JLabel recommendLabel;
	private JPanel recommendPanel;
	private JScrollPane recommendScrollPane;

	/**
	 * Constructor, calls updatResultPanel and adds the scroll pane to the panel
	 */
	public RecommendationPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		recommendLabel = new JLabel("You might like these movies: ");

		updateResultPanel();

		this.add(recommendLabel);
		this.add(recommendScrollPane);
	}

	/**
	 * Generates new recommendations and displays them
	 */
	public void updateResultPanel() {
		if (recommendPanel != null) {
			recommendPanel = createMovieListPanel(Controller.recommendApplication.generateRecommendations());
			this.remove(recommendScrollPane);
			recommendScrollPane = new JScrollPane(recommendPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			this.add(recommendScrollPane);
		} else {
			recommendPanel = createMovieListPanel(Controller.recommendApplication.generateRecommendations());
			recommendScrollPane = new JScrollPane(recommendPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}
		recommendScrollPane.setViewportView(recommendPanel);
		recommendScrollPane.setPreferredSize(new Dimension(800, 800));
		recommendScrollPane.getVerticalScrollBar().setUnitIncrement(30);
		this.validate();
		this.repaint();
	}

}