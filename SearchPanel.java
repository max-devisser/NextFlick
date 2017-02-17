import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel implements ActionListener
{
	private JLabel searchLabel;
	private JPanel filterOptions;
	private ButtonGroup buttons;
	private String[] filters = {"Title", "Year", "Genre", "Actors", "Director", "Parental Rating", "Length", "Language", "Country", "Rating"};
    public SearchPanel() {
		filterOptions = new JPanel();
		filterOptions.setLayout(new GridLayout(1, 4));
		buttons = new ButtonGroup();
		for(String filter: filters){
			JButton button = new JButton(filter);
			button.addActionListener(this);
			buttons.add(button);
			filterOptions.add(button);
		}
		// title.addActionListener(this);

		this.add(BorderLayout.NORTH, filterOptions);
		String filter = "filter";
		String[] movie = {"TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TESTTEST TEST TEST TEST TEST TEST TEST TEST"};

    	searchLabel = new JLabel("Search by " + filter + ":");
		JTextField searchQuery = new JTextField(40);
		JButton searchButton = new JButton("Enter");
		this.add(BorderLayout.CENTER, searchLabel);
		this.add(BorderLayout.CENTER, searchQuery);
		this.add(BorderLayout.CENTER, searchButton);

		JList list = new JList(movie);
		this.add(BorderLayout.SOUTH, list);
		//JComboBox<String> sortPreference = new JComboBox<String>(sortOptions) //need sortOptions string array
		//sortPreference.setSelectedIndex( ); //depends on size of sortOptions
		
	}

	public void actionPerformed(ActionEvent event){
		searchLabel.setText();
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
