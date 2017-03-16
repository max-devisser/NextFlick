package src;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SearchPanel extends RatePanel {

	// Filter panel displaying buttons to select filter
	private JPanel filterSelectionPanel;
	private JPanel filterRemovalPanel;
	private String[] filterOptions = { "Title", "Year", "Genre", "Actors", "Director", "Parental Rating", "Length",
			"Language", "Country", "Rating" };
	private ArrayList<String> currentFilters;

	// Search field panel
	private JPanel searchFieldPanel;
	private JLabel searchLabel;
	private JTextField searchQuery;
	private JButton searchButton;
	private ArrayList<String> searchQueries;

	// Sort menu panel and options
	private JPanel sortPanel;
	private JComboBox sortMenu;
	private JLabel sortLabel;
	private JButton sortOrder;
	private String currentSortOption;	
	private boolean sortDescending = true;
	

	// Result panel displaying filtered library
	private JScrollPane resultScrollPane;
	private JPanel resultPanel;
	private JLabel errorMessage = new JLabel("Please enter input");

	/**
	 * Constructor for SearchPanel. Adds fields for filters and displaying of
	 * results
	 */
	public SearchPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Filter panel initialization
			// adding filters
		filterSelectionPanel = new JPanel();
		filterSelectionPanel.setBackground(Color.WHITE);

		for (String currentFilter : filterOptions) {
			JButton filterButton = new JButton(currentFilter);
			filterButton.setActionCommand(currentFilter);
			filterButton.setPreferredSize(new Dimension(120, 30));
			filterButton.setActionCommand(currentFilter); // allows us to be able to update
												  		  // search label based on filter
														  // selected
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
		sortMenu = new JComboBox(sortOptions);
		sortMenu.addActionListener(new sortActionListener());

		sortPanel = new JPanel();
		sortLabel = new JLabel("Sort by");
		sortPanel.add(BorderLayout.NORTH, sortLabel);
		sortPanel.add(BorderLayout.NORTH, sortMenu);
		sortPanel.add(BorderLayout.NORTH, sortOrder);
		sortPanel.setMaximumSize(new Dimension(1100, sortPanel.getMinimumSize().height));
		// End sort initilization

		// Result panel initilization
		updateResultPanel(false);
		// End results initilization

		// Add everything to the JFrame
		this.add(filterSelectionPanel);
		this.add(filterRemovalPanel);
		this.add(searchFieldPanel);
		this.add(sortPanel);
		this.add(resultScrollPane);
	}	

	public void updateResultPanel(boolean inputEmpty) {
		currentSortOption = (String) sortMenu.getItemAt(sortMenu.getSelectedIndex());
		updateFilterRemovalPanel();
		
		if (inputEmpty) {
			resultScrollPane.getViewport().remove(resultPanel);
			resultScrollPane.getViewport().add(errorMessage);

		} else if (resultPanel == null) {
			resultPanel = createMovieListPanel(Controller.libraryFacade.getFullLibraryList(currentSortOption, sortDescending));
			resultScrollPane = new JScrollPane(resultPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			resultScrollPane.setViewportView(resultPanel);
			resultScrollPane.setMaximumSize(new Dimension(800, 400));
			resultScrollPane.getVerticalScrollBar().setUnitIncrement(30);

		} else if (currentFilters.isEmpty()) {
			resultScrollPane.getViewport().remove(resultPanel);
			resultPanel = createMovieListPanel(Controller.libraryFacade.getFullLibraryList(currentSortOption, sortDescending));
			resultScrollPane.getViewport().add(resultPanel);
			resultScrollPane.setViewportView(resultPanel);	

		} else {
			resultScrollPane.getViewport().remove(errorMessage);
			resultScrollPane.getViewport().remove(resultPanel);
			currentSortOption = (String) sortMenu.getItemAt(sortMenu.getSelectedIndex());
			resultPanel = createMovieListPanel(Controller.libraryFacade.getFilteredLibrary(currentFilters, searchQueries, 
																		   currentSortOption, sortDescending));
			resultScrollPane.getViewport().add(resultPanel);
			resultScrollPane.setViewportView(resultPanel);
		}

		resultScrollPane.validate();
		resultScrollPane.repaint();

		this.validate();
		this.repaint();
	}

	public void updateFilterRemovalPanel() {
		if(resultScrollPane!=null)
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
		if(resultScrollPane != null)
			this.add(resultScrollPane);
		this.validate();
		this.repaint();

	}

	public class addFilterActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			String label = "Search by " + event.getActionCommand() + ": ";
			searchLabel.setText(label);
		}
	}

	public class removeFilterActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			String[] filters = event.getActionCommand().split("/");
			currentFilters.remove(filters[0]);
			searchQueries.remove(filters[1]);	
			updateResultPanel(false);
		}
	}

	public class searchActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			String unCutFilter = searchLabel.getText();
			String filterInput = unCutFilter.substring(10, unCutFilter.length() - 2);
			String queryInput = searchQuery.getText();//.toLowerCase();

			if (queryInput.isEmpty()) {
				updateResultPanel(true);
			} else {
				if(filterInput.equals("Actors") || filterInput.equals("Genre")){
					for(String q: searchQueries){
						if(q.toLowerCase().equals(queryInput.toLowerCase())){
							searchQuery.setText("");
							return;
						}
					}
					currentFilters.add(filterInput);
					searchQueries.add(queryInput);
				}
				else if(currentFilters.contains(filterInput)){	//filter already searched, just need to update its query
					int filterIndex = currentFilters.indexOf(filterInput);
					searchQueries.set(filterIndex, queryInput);
				}
				else{
					currentFilters.add(filterInput);
					searchQueries.add(queryInput);
				}
				updateResultPanel(false);
			}
		}
	}

	class sortActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent event) {
			currentSortOption = (String) sortMenu.getItemAt(sortMenu.getSelectedIndex());
			updateResultPanel(false);
		}
	}

	class sortDescendingActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (sortDescending) {
				sortOrder.setLabel("in ascending order");
				sortDescending = false;
			} else {
				sortOrder.setLabel("in descending order");
				sortDescending = true;
			}

			currentSortOption = (String) sortMenu.getItemAt(sortMenu.getSelectedIndex());

			updateResultPanel(false);
		}
	}
}

