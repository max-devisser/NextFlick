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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.*;

public class Result extends JPanel{
	private Movie movie;
	private JLabel title;
	public Result(Movie movie){
		movie = movie;
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
		JButton rate = new JButton("Rate");
		rate.addActionListener(new RateListener(movie));
		header.add(rate);
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
	}

	class RateListener implements ActionListener{
		Movie movie;
		public RateListener(Movie m){
			movie = m;
		}
		public void actionPerformed(ActionEvent e){
			RatingFrame.displayRateFrame(movie);
		}
	}
}
