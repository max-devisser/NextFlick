package src;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.*;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.*;
import src.movie_builder.TestCollectionBuilder;

public class SearchPanel extends JPanel {
	private JLabel searchLabel; // "Search by *filter type*: ""
	private JPanel filterOptions; // menu of filters to select from
	private JPanel filterSelectionPanel; // displays user's previous filters that they input
	private String[] filters = { "Title", "Year", "Genre", "Actors", "Director", "Parental Rating", "Length",
			"Language", "Country", "Rating" };
	private JTextField searchQuery;
	private JButton searchButton;
	private HashMap<Integer, Movie> result; // the list of results returned by FilterHandler
	private JPanel resultPanel; // what displays the results
	private JLabel errorMessage = new JLabel("Please enter input");
	private TestCollectionBuilder test;
	private FilterHandler filterHandler;

	/**
	 * Constructor for SearchPanel. Adds fields for filters and displaying of
	 * results
	 */
	public SearchPanel() {
		// this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //Might need this to be able to do scrolling
		filterOptions = new JPanel();
		filterOptions.setBackground(Color.WHITE);
		filterSelectionPanel = new JPanel();
		filterHandler = new FilterHandler();

		for (String filter : filters) {
			JButton button = new JButton(filter);
			button.setPreferredSize(new Dimension(120, 30));
			button.setActionCommand(filter); // allows us to be able to update search label based on filter selected
			button.addActionListener(new filterActionListener());
			filterOptions.add(button);
		}
		this.add(BorderLayout.NORTH, filterOptions);

		String filterSearchWord = "Title ";
		searchLabel = new JLabel("Select one of the displayed filters to search for a movie according to " 
									+ filterSearchWord + ":");
		searchQuery = new JTextField(40);
		searchButton = new JButton("Enter");
		searchButton.addActionListener(new searchActionListener());
		test = new TestCollectionBuilder(); // NORMALLY WILL BE INITIALIZED TO CONTAIN WHOLE DATABASE
		result = test.getTestCollection();
		resultPanel = new JPanel();

		this.add(BorderLayout.CENTER, searchLabel);
		this.add(BorderLayout.CENTER, searchQuery);
		this.add(BorderLayout.CENTER, searchButton);
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
				SearchPanel.this.remove(resultPanel);
				SearchPanel.this.add(BorderLayout.NORTH, errorMessage);
				SearchPanel.this.add(BorderLayout.SOUTH, resultPanel);
				SearchPanel.this.validate();
			} else {
				SearchPanel.this.remove(errorMessage);
				if (resultPanel != null) { // clear result panel
					SearchPanel.this.remove(resultPanel);
					SearchPanel.this.validate(); // probably not needed since we validate at the end of the function
				}
				resultPanel = new JPanel();
				resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS)); // results display vertically
				resultPanel.setBackground(Color.WHITE);

				result = filterHandler.addToFilterList(filterInput, queryInput);
				updateFilterSelection();

				if (!result.isEmpty()) { // display new results
					result.forEach((k, v) -> resultPanel.add(new Result(v)));
				} else { // no results yielded from search
					resultPanel.add(new JLabel("No Results"));
					result = test.getTestCollection();
				}
				
				SearchPanel.this.add(BorderLayout.SOUTH, resultPanel);
				SearchPanel.this.validate();
				SearchPanel.this.repaint();
			}
		}

		// Updates the display of filters that the user has so far selected
		public void updateFilterSelection() {
			System.out.println("!");
			SearchPanel.this.remove(filterSelectionPanel);
			filterSelectionPanel = new JPanel();
			filterSelectionPanel.setBackground(Color.WHITE);

			
			
			for (Filter filter : filterHandler.getFilterList()) {
				System.out.print(filter.getType() + ": " + filter.getQuery() + ", ");
				String filterType = filter.getType().substring(3);
				
				// Fixes naming inconsistencies between filterList and filter buttons
				if (filterType.equals("ParentalRating")) { filterType = "Parental Rating"; }
				if (filterType.equals("Date")) { filterType = "Year"; }
				if (filterType.equals("Runtime")) { filterType = "Length"; }
				if (filterType.equals("CriticalRating")) { filterType = "Rating"; }
				
				JButton removeButton = new JButton("X");
				removeButton.setPreferredSize(new Dimension(50, 30));
				removeButton.setActionCommand(filter.getType() + "/" + filter.getQuery());
				removeButton.addActionListener(new removeButtonActionListener());
				filterSelectionPanel.add(removeButton);
				
				JLabel label = new JLabel(filterType + ": " + filter.getQuery());
				label.setBorder(new CompoundBorder(label.getBorder(), new EmptyBorder(10, 10, 10, 10)));
				label.setOpaque(true);
				label.setBackground(new Color(255, 51, 51));
				label.setForeground(Color.WHITE);
				filterSelectionPanel.add(label);
			}
			System.out.println();
			filterSelectionPanel.validate();
			resultPanel.add(BorderLayout.SOUTH, filterSelectionPanel);
			resultPanel.validate();
			SearchPanel.this.repaint();
		}
		
		class removeButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent event) {
				String[] filterParameters = event.getActionCommand().split("/");
				result = filterHandler.removeFromFilterList(filterParameters[0], filterParameters[1]);
				updateFilterSelection();
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
}
