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

public class SearchPanel extends JPanel{
	private JLabel searchLabel;		//"Search by *filter type*: ""
	private JPanel filterOptions;	//menu of filters to select from
	private JPanel filterSelectionPanel;	//displays user's previous filters that they inputted 
	private Map<String, String> filterSelections;
	private String[] filters = {"Title", "Year", "Genre", "Actors", "Director", "Parental Rating", "Length", "Language", "Country", "Rating"};
    private JTextField searchQuery;
    private JButton searchButton;
    private HashMap<Integer,Movie> result;	//the list of results returned by FilterHandler
    private JPanel resultPanel; //what displays the results
    private JLabel errorMessage = new JLabel("Please enter input");
    private TestCollectionBuilder test;

	/**
	 * Constructor for SearchPanel. Adds fields for filters and displaying of results
	 */
    public SearchPanel() {
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  //Might need this to be able to do scrolling
		filterOptions = new JPanel();		
		filterOptions.setBackground(Color.WHITE);
		filterSelectionPanel = new JPanel();
		filterSelections = new LinkedHashMap<String, String>();

		for (String filter : filters) {
			JButton button = new JButton(filter);
			button.setPreferredSize(new Dimension(120,30));
			button.setActionCommand(filter);					//allows us to be able to update search label based on filter selected
			button.addActionListener(new filterActionListener());	
			filterOptions.add(button);
		}
		this.add(BorderLayout.NORTH, filterOptions);

		String filterSearchWord = "Title ";
    	searchLabel = new JLabel("Select one of the displayed filters to search for a movie according to " + filterSearchWord + ":");
		searchQuery = new JTextField(40);
		searchButton = new JButton("Enter");
		searchButton.addActionListener(new searchActionListener());
		test = new TestCollectionBuilder(); //NORMALLY WILL BE INITIALIZED TO CONTAIN WHOLE DATABASE
		result = test.getTestCollection();
		resultPanel = new JPanel();

		this.add(BorderLayout.CENTER, searchLabel);
		this.add(BorderLayout.CENTER, searchQuery);
		this.add(BorderLayout.CENTER, searchButton);
	}	
	/**
	 * Subclass used to apply user filters
	 */
	class searchActionListener implements ActionListener{
		/**
		 * Acts on user pressing enter to apply filters
		 * 
		 * @param event User generated event signaling to apply filters
		 */
		@Override
		public void actionPerformed(ActionEvent event) {

			String unCutFilter = searchLabel.getText();
			String filterInput = "get" + unCutFilter.substring(71, unCutFilter.length()-2);
			String queryInput = searchQuery.getText();

			if(queryInput.isEmpty()){
				SearchPanel.this.remove(resultPanel);
				SearchPanel.this.add(BorderLayout.NORTH, errorMessage);
				SearchPanel.this.add(BorderLayout.SOUTH, resultPanel);
				SearchPanel.this.validate();
			}
			else{
				SearchPanel.this.remove(errorMessage);
				if(resultPanel != null){				//clear result panel
					SearchPanel.this.remove(resultPanel);
					SearchPanel.this.validate(); //probably not needed since we validate at the end of the function
				}
				resultPanel = new JPanel(); 
				resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));	//results display vertically
				resultPanel.setBackground(Color.WHITE);

				addFilterSelection(filterInput.substring(3), queryInput);
				// Ugly, fix later
				switch (filterInput.substring(3)) {
				case "Title" : 
				case "Director" :
				case "Language" :
				case "Country" :
				case "Genre" :
				case "Actors" :
					result = FilterHandler.searchParameter(result, filterInput, queryInput);
					break;
				case "Parental Rating" :
					result = FilterHandler.searchParameter(result, "getParentalRating", queryInput);
					break;
				case "Year" :
					result = FilterHandler.searchParameter(result, "getDate", queryInput);
					break;
				case "Length" :
					result = FilterHandler.searchParameter(result, "getRuntime", Integer.parseInt(queryInput));
					break;
				case "Rating" :
					result = FilterHandler.searchParameter(result, "getCriticalRating", Double.parseDouble(queryInput));
					break;
				}
				

				if (!result.isEmpty()) {				//display new results
					result.forEach((k,v) -> resultPanel.add(new Result(v)));
				}
				else{									//no results yielded from search
					resultPanel.add(new JLabel("No Results"));
					result = test.getTestCollection();
				}
				SearchPanel.this.add(BorderLayout.SOUTH, resultPanel);
				SearchPanel.this.validate();
				SearchPanel.this.repaint();
			}
		}
		//Updates the display of filters that the user has so far selected
		public void addFilterSelection(String filter, String input){
			SearchPanel.this.remove(filterSelectionPanel);
			filterSelectionPanel = new JPanel();
			filterSelectionPanel.setBackground(Color.WHITE);

			if(filterSelections.containsKey(filter)){				//if the user has already searched with that filter we need to start the database over
				//TestCollectionBuilder test = new TestCollectionBuilder();
				//result  = test.getTestCollection();		//NORMALLY WILL BE INITIALIZED TO CONTAIN WHOLE DATABASE
			}

			filterSelections.put(filter,input);
			for (String key: filterSelections.keySet()){
				String labelString = key + ": " + filterSelections.get(key);
				JLabel  label = new JLabel(labelString);
				Border border = label.getBorder();
				Border margin = new EmptyBorder(10,10,10,10);
				label.setBorder(new CompoundBorder(border, margin));
				label.setOpaque(true);
				label.setBackground(new Color(255, 51,51));
				label.setForeground(Color.WHITE);
				filterSelectionPanel.add(label);
			}
			filterSelectionPanel.validate();
			resultPanel.add(BorderLayout.SOUTH,filterSelectionPanel);
			resultPanel.validate();	
			
		}
	}

	/**
	* This action listener used to update the search-by label according to the selected filter
	*/
	class filterActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			String label = "Select one of the displayed filters to search for a movie according to " + event.getActionCommand() +" :";
			searchLabel.setText(label);
		}
	}
	public String getSearch() {
		return "";
		// eturn String searchResult = searchQuery.getText();
	}

	public String getFilter() {
		return "";
		// return String filterResult = filter.getText();
	}

	public String getSort() {
		return "";
	}
}
