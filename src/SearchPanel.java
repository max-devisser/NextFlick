
package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Panel for displaying filters, sorting, and its results
 */
public class SearchPanel extends RatePanel {

	// Filter panel displaying buttons to select filter
	private JPanel filterSelectionPanel;
	private JPanel filterRemovalPanel;
	private String[] filterOptions = { "Title", "Year", "Genre", "Actors", "Director", "Parental Rating", "Length" };
	private ArrayList<String> currentFilters;

	// Search field panel
	private JPanel searchFieldPanel;
	private JLabel searchLabel;
	private JTextField searchQuery;
	private JButton searchButton;
	private ArrayList<String> searchQueries;

	// Sort menu panel and options
	private JPanel sortPanel;
	private JComboBox<String> sortMenu;
	private JLabel sortLabel;
	private JButton sortOrder;
	private String currentSortOption;
	private boolean sortDescending = true;

	// Result panel displaying filtered library
	private JScrollPane resultScrollPane;
	private JPanel resultPanel;
	private JLabel errorMessage = new JLabel("Please enter input");
	private JLabel invalidInputMessage = new JLabel("Your input is invalid for this filter");
	private boolean isValidInput = true;

	/**
	 * Constructor for SearchPanel. Adds fields for filters and displaying of
	 * results
	 */
	public SearchPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Filter panel initialization adding filters
		filterSelectionPanel = new JPanel();
		filterSelectionPanel.setBackground(Color.WHITE);

		for (String currentFilter : filterOptions) {
			JButton filterButton = new JButton(currentFilter);
			filterButton.setActionCommand(currentFilter);
			filterButton.setPreferredSize(new Dimension(120, 30));
			filterButton.setActionCommand(currentFilter); 	// allows us to be able to update search label
															// based on filter selected
			filterButton.addActionListener(new addFilterActionListener());
			filterSelectionPanel.add(filterButton);
		}

		filterSelectionPanel.setMaximumSize(new Dimension(1000, filterSelectionPanel.getMinimumSize().height));

		currentFilters = new ArrayList<String>();
		searchQueries = new ArrayList<String>();

		// removing filters
		updateFilterRemovalPanel();
		// End filter initialization

		// Search field panel initialization
		String filterSearchWord = "Title ";
		searchLabel = new JLabel("Search by " + filterSearchWord + ":");
		searchQuery = new JTextField(40);
		searchQuery.setPreferredSize(new Dimension(1000, searchQuery.getMinimumSize().height));
		searchQuery.addKeyListener(new enterKeyListener());
		searchButton = new JButton("Enter");
		searchButton.addActionListener(new searchActionListener());

		searchFieldPanel = new JPanel();
		searchFieldPanel.add(BorderLayout.NORTH, searchLabel);
		searchFieldPanel.add(BorderLayout.NORTH, searchQuery);
		searchFieldPanel.add(BorderLayout.NORTH, searchButton);
		searchFieldPanel.setMaximumSize(new Dimension(1100, searchFieldPanel.getMinimumSize().height));
		// End search initialization

		// Sort panel initilization
		sortOrder = new JButton("in descending order");
		sortOrder.addActionListener(new sortDescendingActionListener());
		String[] sortOptions = { "Title", "Date", "Critical Rating", "Length" };
		sortMenu = new JComboBox<String>(sortOptions);
		sortMenu.addActionListener(new sortActionListener());

		sortPanel = new JPanel();
		sortLabel = new JLabel("Sort by");
		sortPanel.add(BorderLayout.NORTH, sortLabel);
		sortPanel.add(BorderLayout.NORTH, sortMenu);
		sortPanel.add(BorderLayout.NORTH, sortOrder);
		sortPanel.setMaximumSize(new Dimension(1100, sortPanel.getMinimumSize().height));
		// End sort initilization

		// Result panel initilization
		updateResultPanel();
		// End results initilization

		// Add everything to the JFrame
		this.add(filterSelectionPanel);
		this.add(filterRemovalPanel);
		this.add(searchFieldPanel);
		this.add(sortPanel);
		this.add(resultScrollPane);
	}

	/**
	 * Applys filters and sorting to the result list and displays the results
	 */
	public void updateResultPanel() {
		currentSortOption = (String) sortMenu.getItemAt(sortMenu.getSelectedIndex());
		updateFilterRemovalPanel();

		if (resultPanel == null) {
			resultPanel = createMovieListPanel(
					Controller.libraryApplication.getFullLibraryList(currentSortOption, sortDescending));
			resultScrollPane = new JScrollPane(resultPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			resultScrollPane.setViewportView(resultPanel);
			resultScrollPane.setMaximumSize(new Dimension(1000, 400));
			resultScrollPane.getVerticalScrollBar().setUnitIncrement(30);
		} else if (currentFilters.isEmpty()) {
			resultScrollPane.getViewport().remove(resultPanel);
			resultPanel = createMovieListPanel(
					Controller.libraryApplication.getFullLibraryList(currentSortOption, sortDescending));
			resultScrollPane.getViewport().add(resultPanel);
			resultScrollPane.setViewportView(resultPanel);
		} else {
			resultScrollPane.getViewport().remove(invalidInputMessage);
			resultScrollPane.getViewport().remove(errorMessage);
			isValidInput = true;
			resultScrollPane.getViewport().remove(resultPanel);
			currentSortOption = (String) sortMenu.getItemAt(sortMenu.getSelectedIndex());
			resultPanel = createMovieListPanel(Controller.libraryApplication.getFilteredLibrary(currentFilters,
					searchQueries, currentSortOption, sortDescending));
			resultScrollPane.getViewport().add(resultPanel);
			resultScrollPane.setViewportView(resultPanel);
		}

		resultScrollPane.validate();
		resultScrollPane.repaint();

		this.validate();
		this.repaint();
	}

	/**
	 * Displays currently selected filter-query pairs and buttons to remove them
	 */
	private void updateFilterRemovalPanel() {
		if (resultScrollPane != null)
			this.remove(resultScrollPane);
		if (filterRemovalPanel != null)
			this.remove(filterRemovalPanel);

		filterRemovalPanel = new JPanel();
		filterRemovalPanel.setBackground(Color.WHITE);

		int currentQueryIndex = 0;
		for (String filter : currentFilters) {
			JButton removeButton = new JButton("X");
			removeButton.setPreferredSize(new Dimension(50, 30));
			removeButton.setActionCommand(filter + "/" + searchQueries.get(currentQueryIndex));
			removeButton.addActionListener(new removeFilterActionListener());
			filterRemovalPanel.add(removeButton);

			JLabel filterLabel = new JLabel(filter + ": " + searchQueries.get(currentQueryIndex));
			filterLabel.setBorder(new CompoundBorder(filterLabel.getBorder(), new EmptyBorder(10, 10, 10, 10)));
			filterLabel.setOpaque(true);
			filterLabel.setBackground(new Color(255, 51, 51));
			filterLabel.setForeground(Color.WHITE);
			filterRemovalPanel.add(filterLabel);

			currentQueryIndex++;
		}

		filterRemovalPanel.validate();
		filterRemovalPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, filterRemovalPanel.getMinimumSize().height));

		this.add(filterRemovalPanel);
		if (resultScrollPane != null)
			this.add(resultScrollPane);
		this.validate();
		this.repaint();

	}

	/**
	 * ActionListener for the filter buttons
	 */
	private class addFilterActionListener implements ActionListener {

		/*
		 * (non-Javadoc) Updates label next to search field
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			String label = "Search by " + event.getActionCommand() + ": ";
			searchLabel.setText(label);
		}
	}

	/**
	 * Listener for filter removal button
	 */
	private class removeFilterActionListener implements ActionListener {

		/*
		 * (non-Javadoc) Removes the selected filter-query pair and updates the
		 * result panel
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			String[] filters = event.getActionCommand().split("/");
			currentFilters.remove(filters[0]);
			searchQueries.remove(filters[1]);
			updateResultPanel();
		}
	}

	/**
	 * Listener for the enter button
	 */
	private class searchActionListener implements ActionListener {

		/*
		 * (non-Javadoc) Calls searchPerformed
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			searchPerformed();
		}

	}

	/**
	 * Listener for the enter key
	 */
	private class enterKeyListener implements KeyListener {

		/*
		 * (non-Javadoc) Calls searchPerformed if the key pressed in the enter
		 * key
		 * 
		 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == 10) {
				searchPerformed();
			}
		}

		@Override
		public void keyReleased(KeyEvent event) {
		}

		@Override
		public void keyTyped(KeyEvent event) {
		}
	}

	/**
	 * Adds user input filter-query pair to the appropriate lists and updates
	 * the resultPanel
	 */
	private void searchPerformed() {
		String unCutFilter = searchLabel.getText();
		String filterInput = unCutFilter.substring(10, unCutFilter.length() - 2);
		String queryInput = searchQuery.getText();

		if (filterInput.equals("Length")) {
			try {
				Integer.parseInt(queryInput);
				isValidInput = true;
			} catch (NumberFormatException ex) {
				isValidInput = false;
			}
		}

		if (!isValidInput) {
			if (resultPanel != null) {
				resultScrollPane.getViewport().remove(resultPanel);
			}
			resultScrollPane.getViewport().add(invalidInputMessage);
			resultScrollPane.setViewportView(invalidInputMessage);
			resultScrollPane.validate();
			resultScrollPane.repaint();
			return;
		}

		if (!queryInput.isEmpty()) {
			if (filterInput.equals("Actors") || filterInput.equals("Genre")) {
				for (String q : searchQueries) {
					if (q.toLowerCase().equals(queryInput.toLowerCase())) {
						searchQuery.setText("");
						return;
					}
				}
				currentFilters.add(filterInput);
				searchQueries.add(queryInput);
			} else if (currentFilters.contains(filterInput)) { 	// filter already searched, just need to
																// update its query
				searchQueries.set(currentFilters.indexOf(filterInput), queryInput);
			} else {
				currentFilters.add(filterInput);
				searchQueries.add(queryInput);
			}
			updateResultPanel();
		}
	}

	/**
	 * Listener for the JComboBox for sorting
	 */
	class sortActionListener implements ActionListener {

		/*
		 * (non-Javadoc) Updates currentSortOption and updates the resultPanel
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			currentSortOption = (String) sortMenu.getItemAt(sortMenu.getSelectedIndex());
			updateResultPanel();
		}
	}

	/**
	 * Listener for ascending/descending selection button
	 */
	class sortDescendingActionListener implements ActionListener {

		/*
		 * (non-Javadoc) Updates text in button, updates currentSortOption, then
		 * updates resultPanel
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			if (sortDescending) {
				sortOrder.setText("in ascending order");
				sortDescending = false;
			} else {
				sortOrder.setText("in descending order");
				sortDescending = true;
			}

			currentSortOption = sortMenu.getItemAt(sortMenu.getSelectedIndex());

			updateResultPanel();
		}
	}
}
