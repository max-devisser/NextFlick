package src;
import javax.swing.*;
import java.awt.*;

public class Result extends JPanel{
	private Movie movie;
	private JLabel title;
	public Result(Movie movie){
		movie = movie;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setMinimumSize(new Dimension(Integer.MAX_VALUE, 300));	


		this.add(new JLabel(movie.toString()));
		this.add(new JLabel(movie.getDate()));

		this.setOpaque(true);
		this.setBackground(Color.BLUE);
		this.setForeground(Color.RED);

	}
}
