package src;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

public class RecommendationPanel extends RatePanel {
	private JLabel recommendLabel;
	private JPanel recommendPanel;
	private JScrollPane recommendScrollPane;

	public RecommendationPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		recommendLabel = new JLabel("You might like these movies: ");

		updateResultPanel();

		this.add(recommendLabel);
		this.add(recommendScrollPane);
	}

	public void updateResultPanel() {
		if (recommendPanel != null) {
			recommendPanel = createMovieListPanel(Controller.recommendApplication.generateRecommendations());
			this.remove(recommendScrollPane);
			recommendScrollPane = new JScrollPane(recommendPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			this.add(recommendScrollPane);
		} else {
			recommendPanel = createMovieListPanel(Controller.recommendApplication.generateRecommendations());
			recommendScrollPane = new JScrollPane(recommendPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}
		recommendScrollPane.setViewportView(recommendPanel);
		recommendScrollPane.setPreferredSize(new Dimension(800, 800));
		recommendScrollPane.getVerticalScrollBar().setUnitIncrement(30);
		this.validate();
		this.repaint();
	}

}