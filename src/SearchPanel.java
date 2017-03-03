package src;

import javax.swing.*;
import java.util.HashMap;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import src.movie_builder.TestCollectionBuilder;

public class SearchPanel extends JPanel {
	private JLabel searchLabel; // "Search by *filter type*: ""
	private JPanel filterOptions; // menu of filters to select from
	private JPanel filterSelectionPanel; // displays user's previous filters that they input
	private JPanel resultPanel; // what displays the results
	private JPanel centerPanel;
	private JScrollPane resultScroller; // contains resultPanel
	private String[] filters = { "Title", "Year", "Genre", "Actors", "Director", "Parental Rating", "Length",
			"Language", "Country", "Rating" };
	private JTextField searchQuery;
	private JButton searchButton;
	private ArrayList<Movie> result; // the list of results returned by FilterHandler
	private JLabel errorMessage = new JLabel("Please enter input");
	private FilterHandler filterHandler;

	/**
	 * Constructor for SearchPanel. Adds fields for filters and displaying of
	 * results
	 */
	public SearchPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //Might need this to be able to do scrolling
		filterOptions = new JPanel();
		filterOptions.setBackground(Color.WHITE);
		filterSelectionPanel = new JPanel();
		filterHandler = new FilterHandler();

		for (String filter : filters) {
			JButton button = new JButton(filter);
			button.setPreferredSize(new Dimension(120, 30));
			button.setActionCommand(filter); // allows us to be able to update
												// search label based on filter
												// selected
			button.addActionListener(new filterActionListener());
			filterOptions.add(button);
		}
		filterOptions.setMaximumSize(new Dimension(1000, filterOptions.getMinimumSize().height));
		this.add(filterOptions);

		String filterSearchWord = "Title ";
		searchLabel = new JLabel(
				"Select one of the displayed filters to search for a movie according to " + filterSearchWord + ":");
		searchQuery = new JTextField(40);
		searchQuery.setPreferredSize(new Dimension(1000, searchQuery.getMinimumSize().height));
		searchButton = new JButton("Enter");
		searchButton.addActionListener(new searchActionListener());
		result = filterHandler.getFullMovieList(); // NORMALLY WILL BE INITIALIZED TO CONTAIN WHOLE DATABASE
		
		resultPanel = new JPanel();
		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS)); // results display vertically
		resultPanel.setBackground(Color.WHITE);
		for (Movie item: result)
			resultPanel.add(new Result(item));

		resultScroller = new JScrollPane(resultPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		resultScroller.setViewportView(resultPanel);
		resultScroller.setMaximumSize(new Dimension(800, 400));

		centerPanel = new JPanel();
		centerPanel.add(BorderLayout.NORTH, searchLabel);
		centerPanel.add(BorderLayout.NORTH, searchQuery);
		centerPanel.add(BorderLayout.NORTH, searchButton);
		centerPanel.setPreferredSize(new Dimension(800, centerPanel.getMinimumSize().height + 5));
		centerPanel.setMaximumSize(new Dimension(1200, centerPanel.getMinimumSize().height + 5));

		this.add(centerPanel);
		this.add(resultScroller);
	}

	public void updateResultPanel(boolean inputWasEmpty) {
		if (inputWasEmpty) {
			// this.remove(resultScroller);
			// this.add(BorderLayout.NORTH, errorMessage);
			// this.add(BorderLayout.SOUTH, resultScroller);
			resultScroller.getViewport().remove(resultPanel);
			resultScroller.getViewport().add(errorMessage);
			this.validate();
		} else {
			this.remove(errorMessage);
			if (resultPanel != null) {
				resultScroller.getViewport().remove(resultPanel);
			}
			resultPanel = new JPanel();
			resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS)); // results display vertically
			resultPanel.setBackground(Color.WHITE);
	
			updateFilterSelectionPanel();

			if (result.size() != 0) { // display new results
				for (Movie item: result)
					resultPanel.add(new Result(item));
			} else { // no results yielded from search
				resultPanel.add(new JLabel("No Results"));
				result = filterHandler.getFullMovieList();
			}

			resultScroller.getViewport().add(resultPanel);
			resultScroller.setViewportView(resultPanel);

			this.add(resultScroller);
			this.validate();
			this.repaint();
		}
	}

	// Updates the display of filters that the user has so far selected
	public void updateFilterSelectionPanel() {
		if(filterSelectionPanel != null) { this.remove(filterSelectionPanel); }
		filterSelectionPanel = new JPanel();
		filterSelectionPanel.setBackground(Color.WHITE);

		for (Filter filter : filterHandler.getFilterList()) {
			String filterType = filter.getFilterType().substring(3);

			// Fixes naming inconsistencies between filterList and filter buttons
			if (filterType.equals("ParentalRating")) {
				filterType = "Parental Rating";
			}
			if (filterType.equals("Date")) {
				filterType = "Year";
			}
			if (filterType.equals("Runtime")) {
				filterType = "Length";
			}
			if (filterType.equals("CriticalRating")) {
				filterType = "Rating";
			}

			JButton removeButton = new JButton("X");
			removeButton.setPreferredSize(new Dimension(50, 30));
			removeButton.setActionCommand(filter.getFilterType() + "/" + filter.getQuery());
			removeButton.addActionListener(new removeButtonActionListener());
			filterSelectionPanel.add(removeButton);

			JLabel label = new JLabel(filterType + ": " + filter.getQuery());
			label.setBorder(new CompoundBorder(label.getBorder(), new EmptyBorder(10, 10, 10, 10)));
			label.setOpaque(true);
			label.setBackground(new Color(255, 51, 51));
			label.setForeground(Color.WHITE);
			filterSelectionPanel.add(label);
		}
		
		filterSelectionPanel.validate();
		filterSelectionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, filterSelectionPanel.getMinimumSize().height));
		this.add(filterSelectionPanel);
		resultPanel.validate();

		// resultScroller.getViewport().add(resultPanel);
		// resultScroller.setViewportView(resultPanel);
	
		this.validate();
		this.repaint();
	}
	
	class removeButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			String[] filterParameters = event.getActionCommand().split("/");
			result = filterHandler.removeFromFilterList(filterParameters[0], filterParameters[1]);
			updateResultPanel(false);
		}
	}

	/**
	 * Subclass used to apply user filters
	 */
	class searchActionListener implements ActionListener {
		/**
		 * Acts on user pressing enter to apply filters
		 * 
		 * @param event
		 *            User generated event signaling to apply filters
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			String unCutFilter = searchLabel.getText();
			String filterInput = "get" + unCutFilter.substring(71, unCutFilter.length() - 2);
			String queryInput = searchQuery.getText();

			if (queryInput.isEmpty()) {
				updateResultPanel(true);
			} else {
				result = filterHandler.addToFilterList(filterInput, queryInput);
				updateResultPanel(false);
			}

		}

	}

	/**
	 * This action listener used to update the search-by label according to the
	 * selected filter
	 */
	class filterActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			String label = "Select one of the displayed filters to search for a movie according to "
					+ event.getActionCommand() + " :";
			searchLabel.setText(label);
		}
	}

	public String getSearch() {
		return "";
		// return String searchResult = searchQuery.getText();
	}

	public String getFilter() {
		return "";
		// return String filterResult = filter.getText();
	}

	public String getSort() {
		return "";
	}
}


