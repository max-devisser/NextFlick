package src;

import javax.swing.*;
import java.util.HashMap;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class RecPanel extends JPanel{
	private RatingHistory ratingHistory;
	private JScrollPane scroller;
	private JPanel recommendationPanel;

	public RecPanel(RatingHistory ratingHistory){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.ratingHistory = ratingHistory;		
		recommendationPanel = new JPanel();
		recommendationPanel.setLayout(new BoxLayout(recommendationPanel, BoxLayout.Y_AXIS)); // results display vertically
		recommendationPanel.setBackground(Color.WHITE);
		display();
	}
	public void display(){
		ArrayList<Movie> recommendations = MovieRecommender.recommend(ratingHistory);
		if(recommendations.isEmpty()){
			JLabel message = new JLabel("In order to view your recommendations, please first rate more movies.");
			recommendationPanel.add(message);
		}
		else{
			for(Movie movie: recommendations){
				Result result = new Result(movie);
				recommendationPanel.add(result);
			}
		}

		scroller = new JScrollPane(recommendationPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setViewportView(recommendationPanel);
		scroller.setMaximumSize(new Dimension(800, 600));
		this.add(scroller);
    }
    public void update(){
    	this.remove(scroller);
    	recommendationPanel = new JPanel();
		recommendationPanel.setLayout(new BoxLayout(recommendationPanel, BoxLayout.Y_AXIS)); // results display vertically
		recommendationPanel.setBackground(Color.WHITE);
		display();
    }
}