package src;

import javax.swing.*;
import java.util.HashMap;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import src.movie_builder.TestCollectionBuilder;

public class HomePanel extends JPanel
{
	private RatingHistory ratingHistory;
	private JButton clearButton;
	private JScrollPane scroller;
	private JPanel historyPanel;

	public HomePanel(RatingHistory ratingHistory){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.ratingHistory = ratingHistory;
		setUp();
	}
    public RatingHistory getRatingHistory(){
    	return ratingHistory;
    }
    public void refresh(){
    	this.remove(scroller);
    	setUp();
    }
    public void setUp(){
		historyPanel = new JPanel();
		historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS)); // results display vertically
		historyPanel.setBackground(Color.WHITE);
		HashMap<Movie,Integer> historyMap = ratingHistory.getHistory();
		for(HashMap.Entry<Movie, Integer> entry: historyMap.entrySet()){
			Movie movie = entry.getKey();
			Result result;
			if(ratingHistory.containsKey(movie)){			//if the movie has been rated
				result = new Result(entry.getKey(), ratingHistory.get(movie));
			}
			else{
				result = new Result(entry.getKey(), 0);
			}
			historyPanel.add(result);
			System.out.println("*");
		}

		scroller = new JScrollPane(historyPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setViewportView(historyPanel);
		scroller.setMaximumSize(new Dimension(800, 600));
		this.add(scroller);
    }
}

