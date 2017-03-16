package src;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

public class RecommendationPanel extends RatePanel {
	private JButton generateRecButton;
	private JLabel recommendLabel;
	private JPanel recommendPanel;
	private JScrollPane recommendScrollPane;

	public RecommendationPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		generateRecButton = new JButton("Click to generate a list of recommended movies");
		recommendLabel = new JLabel("You might like these movies: ");

		updateResultPanel();

		this.add(generateRecButton);
		this.add(recommendLabel);
		this.add(recommendScrollPane);
	}

	public void updateResultPanel() {
		if (recommendPanel != null) {
			recommendPanel = createMovieListPanel(Controller.recommendFacade.generateRecommendations());
			this.remove(recommendScrollPane);
			recommendScrollPane = new JScrollPane(recommendPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			this.add(recommendScrollPane);
		} else {
			recommendPanel = createMovieListPanel(Controller.recommendFacade.generateRecommendations());
			recommendScrollPane = new JScrollPane(recommendPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			recommendScrollPane.setViewportView(recommendPanel);
			recommendScrollPane.setMaximumSize(new Dimension(800, 400));
			recommendScrollPane.getVerticalScrollBar().setUnitIncrement(30);

			recommendScrollPane.getViewport().add(recommendPanel);
			recommendScrollPane.setViewportView(recommendPanel);
		}

		this.validate();
		this.repaint();
	}

	public class recommendationActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			updateResultPanel();
		}
	}

}