package src;
import javax.swing.*;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.ArrayList;

public class SearchPanel extends JPanel{
	private JLabel searchLabel;
	private JPanel filterOptions;
	private ButtonGroup filterButtons;
	private JPanel filterSelections;
	private String[] filters = {"Title", "Year", "Genre", "Actors", "Director", "Parental Rating", "Length", "Language", "Country", "Rating"};
    private JTextField searchQuery;
    private JButton searchButton;
    private HashMap<Integer,Movie> result;
    private JLabel[] selectedFilters;
    private JPanel resultPanel;

	/**
	 * Constructor for SearchPanel. Adds fields for filters and displaying of results
	 */
    public SearchPanel() {
		filterOptions = new JPanel();			
		filterOptions.setLayout(new GridLayout(1, 4));
		filterButtons = new ButtonGroup();

		for (String filter : filters) {
			JButton button = new JButton(filter);
			button.setActionCommand(filter);
			button.addActionListener(new filterActionListener());
			filterButtons.add(button);
			filterOptions.add(button);
		}
		this.add(BorderLayout.NORTH, filterOptions);

		String filterSearchWord = "Title";
    	searchLabel = new JLabel("Search by " + filterSearchWord + ":");
		searchQuery = new JTextField(40);
		searchButton = new JButton("Enter");
		searchButton.addActionListener(new searchActionListener());
		result  = new HashMap<Integer,Movie>();					//NORMALLY WILL BE INITIALIZED TO CONTAIN WHOLE DATABASE
		
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
			String filterInput = "get" + unCutFilter.substring(10, unCutFilter.length()-2);
			String queryInput = searchQuery.getText();
			
			//HARD-CODED RESULT
			Movie m = new Movie(1);
			m.setTitle("Star Wars");
			result.put(1, m);
			Movie m2 = new Movie(2);
			m2.setTitle("Movie");
			result.put(2, m2);

			//result = FilterHandler.searchParameter(result, filterInput, queryInput);
			if(resultPanel != null){				//clear result panel
				SearchPanel.this.remove(resultPanel);
				SearchPanel.this.validate();
			}
			resultPanel = new JPanel(); 
			if (!result.isEmpty()) {				//display new results
				resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));	//results display vertically
				result.forEach((k,v) -> resultPanel.add(new JButton(v.toString())));
			}
			else{
				resultPanel.add(new JLabel("No Results"));
			}
			SearchPanel.this.add(BorderLayout.SOUTH, resultPanel);
			SearchPanel.this.validate();
		}
	}

	/**
	* This action listener used to update the search-by label according to the selected filter
	*/
	class filterActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			String label = "Search by " + event.getActionCommand() +" :";
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
