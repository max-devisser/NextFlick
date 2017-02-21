import javax.swing.*;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class SearchPanel extends JPanel implements ActionListener {
	private JLabel searchLabel;
	private JPanel filterOptions;
	private ButtonGroup filterButtons;
	private String[] filters = {"Title", "Year", "Genre", "Actors", "Director", "Parental Rating", "Length", "Language", "Country", "Rating"};
    private JTextField searchQuery;
    private JButton searchButton;
    private HashMap<Integer,Movie> result;
    private JButton resultButton;
    private JLabel[] selectedFilters;

	/**
	 * Constructor for SearchPanel. Adds fields for filters and displaying of results
	 */
    public SearchPanel() {
		filterOptions = new JPanel();			
		filterOptions.setLayout(new GridLayout(1, 4));
		filterButtons = new ButtonGroup();
		resultButton = new JButton();

		for (String filter : filters) {
			JButton button = new JButton(filter);
			button.setActionCommand(filter);
			button.addActionListener(this);
			filterButtons.add(button);
			filterOptions.add(button);
		}
		this.add(BorderLayout.NORTH, filterOptions);	
		
		String filterSearchWord = "filter";
    	searchLabel = new JLabel("Search by " + filterSearchWord + ":");
		searchQuery = new JTextField(40);
		searchButton = new JButton("Enter");
		searchButton.addActionListener(new searchActionListener());
		result  = new HashMap<Integer,Movie>();					//NORMALLY WILL BE INITIALIZED TO CONTAIN WHOLE DATABASE

		this.add(BorderLayout.CENTER, searchLabel);
		this.add(BorderLayout.CENTER, searchQuery);
		this.add(BorderLayout.CENTER, searchButton);

		//JComboBox<String> sortPreference = new JComboBox<String>(sortOptions) //need sortOptions string array
		//sortPreference.setSelectedIndex( ); //depends on size of sortOptions
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


			result = FilterHandler.searchParameter(result, filterInput, queryInput);
			if (result.isEmpty()) {
				resultButton.setText("No results");
			} else {
				SearchPanel.this.add(BorderLayout.CENTER, resultButton);
				resultButton.setText(result.get(1).toString());
			}
		}
	}

	/**
	* This action listener used to update the search-by label according to the selected filter
	*/
	@Override
	public void actionPerformed(ActionEvent event){
		String label = "Search by " + event.getActionCommand() +" :";
		searchLabel.setText(label);
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
