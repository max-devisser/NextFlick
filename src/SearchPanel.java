package src;

import javax.swing.*;
import java.util.HashMap;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class SearchPanel extends JPanel {

	// Filter panel, filters, and handler
	private JPanel filterSelectionPanel; // displays user's previous filters that they input
	private JPanel filterOptionsPanel; // menu of filters to select from
	private String[] filters = { "Title", "Year", "Genre", "Actors", "Director", "Parental Rating", "Length",
			"Language", "Country", "Rating" };
	private FilterHandler filterHandler;

	// Result display panel and result list
	private JScrollPane resultScroller; // contains resultPanel
	private JPanel resultPanel; // what displays the results
	private ArrayList<Movie> result; // the list of results returned by FilterHandler
	private JLabel errorMessage = new JLabel("Please enter input");

	// Search field panel
	private JPanel searchOptionsPanel;
	private JLabel searchLabel; // "Search by *filter type*: ""
	private JTextField searchQuery;
	private JButton searchButton;

	// Sort menu panel and sort options
	private JPanel sortOptionsPanel;
	private JComboBox sortMenu;
	private JLabel sortLabel;
	private JButton sortType; // ascending or descending
	private boolean sortDescending = true;
	private String sortOptionSelected;

	private HomePanel homePanel;
	private RatingHistory ratingHistory;
	/**
	 * Constructor for SearchPanel. Adds fields for filters and displaying of
	 * results
	 */

	public SearchPanel(HomePanel homeAccess) {
		homePanel = homeAccess;
		ratingHistory = homePanel.getRatingHistory(); //fix later

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 

		// Filter panel initialization
		filterOptionsPanel = new JPanel();
		filterOptionsPanel.setBackground(Color.WHITE);
		filterSelectionPanel = new JPanel();
		filterHandler = new FilterHandler();

		for (String filter : filters) {
			JButton button = new JButton(filter);
			button.setPreferredSize(new Dimension(120, 30));
			button.setActionCommand(filter); // allows us to be able to update
												// search label based on filter
												// selected
			button.addActionListener(new filterActionListener());
			filterOptionsPanel.add(button);
		}

		filterSelectionPanel = new JPanel();
		filterSelectionPanel.setBackground(Color.WHITE);
		filterSelectionPanel.setBorder(new EmptyBorder(0, 0, 0, 5));
		filterSelectionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, filterSelectionPanel.getMinimumSize().height));

		filterHandler = new FilterHandler();
		filterOptionsPanel.setMaximumSize(new Dimension(1000, filterOptionsPanel.getMinimumSize().height));
		// End filter initialization


		// Search field panel initialization
		String filterSearchWord = "Title ";
		searchLabel = new JLabel(
				"Select one of the displayed filters to search for a movie according to " + filterSearchWord + ":");
		searchQuery = new JTextField(40);
		searchQuery.setPreferredSize(new Dimension(1000, searchQuery.getMinimumSize().height));
		searchButton = new JButton("Enter");
		searchButton.addActionListener(new searchActionListener());

		searchOptionsPanel = new JPanel();
		searchOptionsPanel.add(BorderLayout.NORTH, searchLabel);
		searchOptionsPanel.add(BorderLayout.NORTH, searchQuery);
		searchOptionsPanel.add(BorderLayout.NORTH, searchButton);
		searchOptionsPanel.setMaximumSize(new Dimension(1100, searchOptionsPanel.getMinimumSize().height));
		// End search initialization


		// Sort panel initilization
		sortType = new JButton("in descending order");
		sortType.addActionListener(new sortDescendingActionListener());
		String[] sortOptions = { "Title", "Date", "Critical Rating", "Length" };
		sortMenu = new JComboBox(sortOptions);
		sortMenu.addActionListener(new sortActionListener());

		sortOptionsPanel = new JPanel();
		sortLabel = new JLabel("Sort by");
		sortOptionsPanel.add(BorderLayout.NORTH, sortLabel);
		sortOptionsPanel.add(BorderLayout.NORTH, sortMenu);
		sortOptionsPanel.add(BorderLayout.NORTH, sortType);
		sortOptionsPanel.setMaximumSize(new Dimension(1100, searchOptionsPanel.getMinimumSize().height));
		// End sort initilization


		// Result panel initialization
		result = filterHandler.getFullMovieList(); // NORMALLY WILL BE INITIALIZED TO CONTAIN WHOLE DATABASE
		setUpResultPanel();
		// End result initialization

		
		// Add everything to the JFrame
		this.add(filterOptionsPanel);
		this.add(searchOptionsPanel);
		this.add(sortOptionsPanel);
		this.add(filterSelectionPanel);
		this.add(resultScroller);
	}

	public void updateResultPanel(boolean inputWasEmpty) {
		if (inputWasEmpty) {
			resultScroller.getViewport().remove(resultPanel);
			resultScroller.getViewport().add(errorMessage);
			this.validate();
		} else {
			resultScroller.getViewport().remove(errorMessage);
			if (resultPanel != null) {
				resultScroller.getViewport().remove(resultPanel);
			}
			resultPanel = new JPanel();
			resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS)); // results display vertically
			resultPanel.setBackground(Color.WHITE);
	
			updateFilterSelectionPanel();

			sortOptionSelected = (String) sortMenu.getItemAt(sortMenu.getSelectedIndex());
			result = SortHandler.chooseSortingMethod(result, sortOptionSelected, sortDescending);

			if (result.size() != 0) { // display new results
				for (Movie item: result){
					Result result;
					if(ratingHistory.containsKey(item)){			//if the movie has been rated
						result = new Result(item, ratingHistory.get(item));
					}
					else{
						result = new Result(item, 0);
					}
					result.addListener(new RateListener(item));
					resultPanel.add(result);
				}
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

	public void setUpResultPanel(){
		resultPanel = new JPanel();
		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS)); // results display vertically
		resultPanel.setBackground(Color.WHITE);

		sortOptionSelected = (String) sortMenu.getItemAt(sortMenu.getSelectedIndex());
		result = SortHandler.chooseSortingMethod(result, sortOptionSelected, sortDescending);
		
		for (Movie item: result) {
			Result ratingResult;
			if(ratingHistory.containsKey(item)){			//if the movie has been rated
				ratingResult = new Result(item, ratingHistory.get(item));
			}
			else{
				ratingResult = new Result(item, 0);
			}
			ratingResult.addListener(new RateListener(item));
			resultPanel.add(ratingResult);
		}

		resultScroller = new JScrollPane(resultPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		resultScroller.setViewportView(resultPanel);
		resultScroller.setMaximumSize(new Dimension(800, 400));
		resultScroller.getVerticalScrollBar().setUnitIncrement(30);
	}

	class RateListener implements ActionListener{
		private Movie movie;
		public RateListener(Movie m){
			movie = m;
		}
		@Override
		public void actionPerformed(ActionEvent e){
			ratingHistory.displayRateFrame(movie);
			SearchPanel.this.refresh();
			homePanel.refresh();
		}
	}

	// Updates the display of filters that the user has so far selected
	public void updateFilterSelectionPanel() {
		if(filterSelectionPanel != null) 
			this.remove(filterSelectionPanel); 

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

	public void refresh() {
		this.remove(resultScroller);
		setUpResultPanel();
		this.add(resultScroller);
	}

	class sortActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
				sortOptionSelected = (String) sortMenu.getItemAt(sortMenu.getSelectedIndex());
				result = SortHandler.chooseSortingMethod(result, sortOptionSelected, sortDescending);
				updateResultPanel(false);
			}
		}

	class sortDescendingActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (sortDescending) {
				sortType.setLabel("in ascending order");
				sortDescending = false;
			} else {
				sortType.setLabel("in descending order");
				sortDescending = true;
			}
			sortOptionSelected = (String) sortMenu.getItemAt(sortMenu.getSelectedIndex());
			result = SortHandler.chooseSortingMethod(result, sortOptionSelected, sortDescending);

			if(searchQuery.getText().isEmpty())
				updateResultPanel(true);
			else
				updateResultPanel(false);
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


