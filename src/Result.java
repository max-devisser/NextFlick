package src;
import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.*;

public class Result extends JPanel{
	private Movie movie;
	private JLabel title;
	JButton rate;
	public Result(Movie movie, int rating){
		movie = movie; // don't you mean this.movie = movie?
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setOpaque(true);
		this.setBackground(new Color(243,243,243));

		Border border = this.getBorder();
		Border margin = new LineBorder(Color.WHITE, 12);
		this.setBorder(new CompoundBorder(border, margin));

		JPanel header = new JPanel();
		JLabel title = new JLabel(movie.getTitle());
		title.setFont (title.getFont ().deriveFont (30.0f));		//make Title bigger
		header.add(title);
		rate = new JButton("Rate");
		header.add(rate);
		if(rating != 0 ){
			header.add(new JLabel("Current Rating: "+ rating));
		}
		this.add(header);

		JLabel label1 = new JLabel("Release Date: " + movie.getDate());
		JLabel label2 = new JLabel("Actors: " + movie.getActors());
		JLabel label3 = new JLabel("Director: " + movie.getDirector());
		JLabel label4 = new JLabel("Genre: " + movie.getGenre());
		JLabel label5 = new JLabel("Parental Rating: " + movie.getParentalRating());
		JLabel label6 = new JLabel("Length: " + movie.getRuntime() + " minutes");
		JLabel label7 = new JLabel("Critical Rating: " + movie.getCriticalRating());
		JLabel label8 = new JLabel("Country: " + movie.getCountry());

		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(label4);
		this.add(label5);
		this.add(label6);
		this.add(label7);
		this.add(label8);

		this.setPreferredSize(new Dimension(600, 200));
		this.setMaximumSize(new Dimension(800, 200));
		this.setMinimumSize(new Dimension(800, 200));
		this.setAlignmentX( Component.LEFT_ALIGNMENT );
	}

	public void addListener(SearchPanel.RateListener listener){
		rate.addActionListener(listener);
	}
}
