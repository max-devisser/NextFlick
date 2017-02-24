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
		header.add(rate);
		this.add(header);

		JLabel label1 = new JLabel("Release Date: " + movie.getDate() +"Director: " + movie.getDirector()+"Genre: " + movie.getGenre());
		JLabel label4 = new JLabel("Parental Rating: " + movie.getParentalRating());
		JLabel label5 = new JLabel("Release Date: " + movie.getDate());
		JLabel label6 = new JLabel("Release Date: " + movie.getDate());
		JLabel label7 = new JLabel("Release Date: " + movie.getDate());


		this.add(label1);
		this.add(label4);
		this.add(label5);
		this.add(label6);
		this.add(label7);


	}
}
