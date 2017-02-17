import javax.swing.*;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class SearchPanel extends JPanel implements ActionListener
{
	private JLabel searchLabel;
	private JPanel filterOptions;
	private ButtonGroup buttons;
	private String[] filters = {"Title", "Year", "Genre", "Actors", "Director", "Parental Rating", "Length", "Language", "Country", "Rating"};
    private JTextField searchQuery;
    private JList list;
    private JButton searchButton;
    private JButton result;

    public SearchPanel() {
		filterOptions = new JPanel();
		filterOptions.setLayout(new GridLayout(1, 4));
		buttons = new ButtonGroup();
		for(String filter: filters){
			JButton button = new JButton(filter);
			button.setActionCommand(filter);
			button.addActionListener(this);
			buttons.add(button);
			filterOptions.add(button);
		}
		// title.addActionListener(this);
		//"testing what"
		this.add(BorderLayout.NORTH, filterOptions);
		String filter = "filter";
		String[] movie = {"TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TESTTEST TEST TEST TEST TEST TEST TEST TEST"};

    	searchLabel = new JLabel("Search by " + filter + ":");
		searchQuery = new JTextField(40);
		searchButton = new JButton("Enter");
		searchButton.addActionListener(new searchActionListener());
		this.add(BorderLayout.CENTER, searchLabel);
		this.add(BorderLayout.CENTER, searchQuery);
		this.add(BorderLayout.CENTER, searchButton);
		list = new JList(movie);
		this.add(BorderLayout.SOUTH, list);
		//JComboBox<String> sortPreference = new JComboBox<String>(sortOptions) //need sortOptions string array
		//sortPreference.setSelectedIndex( ); //depends on size of sortOptions
		
	}
	class searchActionListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String filter = "";
    	
			//filter = buttons.getSelection().getActionCommand();
			String input = searchQuery.getText();
			HashMap<Integer, Movie> result;
			result = FilterHandler.searchParameter(new HashMap<Integer, Movie>(), "string1", "string2" );
			System.out.println(result);
			SearchPanel testPanel = new SearchPanel();
			//testPanel.addResults(result);
			this.result.addResults(result);
			System.out.println(input);
		}
	}
	public void addResults(HashMap<Integer, Movie> map){
		SearchPanel testPanel = new SearchPanel();
		System.out.println("this");
		this.add(map);

	}

	public void actionPerformed(ActionEvent event){
		String label = "Search by " + event.getActionCommand() +" :";
		searchLabel.setText(label);
	}

    public String getSearch() {
    	return "";
		//eturn String searchResult = searchQuery.getText();
    }

    public String getFilter() {
    	return "";
		//return String filterResult = filter.getText();	
    }

    public String getSort() {
    	return "";
	}
}
