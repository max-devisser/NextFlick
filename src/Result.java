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
		this.add(new JLabel(movie.toString()));

	}
}
