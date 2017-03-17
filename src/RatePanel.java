package src;

import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public abstract class RatePanel extends JPanel {

	public class MoviePanel extends JPanel {
		private Movie movie;
		private JLabel movieTitle;
		private JPanel titlePanel;
		private JLabel ratingLabel;
		private JLabel rating;
		JButton rateButton;
		JButton unRateButton;

		public MoviePanel(Movie movie) {
			this.movie = movie; 

			// Formatting
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.setOpaque(true);
			this.setBackground(new Color(243,243,243));
			Border border = this.getBorder();
			Border margin = new LineBorder(Color.WHITE, 12);
			this.setBorder(new CompoundBorder(border, margin));
			this.setPreferredSize(new Dimension(600, 200));
			this.setMaximumSize(new Dimension(800, 200));
			this.setMinimumSize(new Dimension(800, 200));
			this.setAlignmentX( Component.LEFT_ALIGNMENT );

			// Title formatting
			titlePanel = new JPanel();
			JLabel title = new JLabel(movie.getTitle());
			title.setFont(title.getFont().deriveFont(30.0f));	
			titlePanel.add(title);

			// Rating button and rating display
			ratingLabel = new JLabel("Your Rating:");
			rateButton = new JButton("Rate");
			titlePanel.add(rateButton);
			if (Controller.rateStorageApplication.getRating(movie) > 0) {
				rating = new JLabel(" " + Controller.rateStorageApplication.getRating(movie));
				titlePanel.add(ratingLabel);
				titlePanel.add(rating);
				unRateButton = new JButton("Delete Rating");
				titlePanel.add(unRateButton);
			}

			this.add(titlePanel);

			JLabel dateLabel = new JLabel("Release Date: " + movie.getDate());
			JLabel actorLabel = new JLabel("Actors: " + movie.getActors());
			JLabel directorLabel = new JLabel("Director: " + movie.getDirector());
			JLabel genreLabel = new JLabel("Genre: " + movie.getGenre());
			JLabel parentalLabel = new JLabel("Parental Rating: " + movie.getParentalRating());
			JLabel lengthLabel = new JLabel("Length: " + movie.getRuntime() + " minutes");
			JLabel critRatingLabel = new JLabel("Critical Rating: " + movie.getCriticalRating());
			JLabel countryLabel = new JLabel("Country: " + movie.getCountry());

			this.add(dateLabel);
			this.add(actorLabel);
			this.add(directorLabel);
			this.add(genreLabel);
			this.add(parentalLabel);
			this.add(lengthLabel);
			this.add(critRatingLabel);
			this.add(countryLabel);
		}

		public void addListener(RateActionListener ratingListener){
			rateButton.addActionListener(ratingListener);
		}
		public void addDeleteListener(UnRateActionListener ratingListener){
			if(unRateButton != null)
				unRateButton.addActionListener(ratingListener);
		}

		public Movie getMovie() {
			return movie;
		}

		public void updateRating() {
			if (rating != null) {
				titlePanel.remove(ratingLabel);
				titlePanel.remove(rating);
			}	
			if(unRateButton != null){
				titlePanel.remove(unRateButton);
			}
			if(Controller.rateStorageApplication.getRating(movie) != 0){
				rating = new JLabel(" " + Controller.rateStorageApplication.getRating(movie));
				unRateButton = new JButton("Delete Rating");
				titlePanel.add(ratingLabel);
				titlePanel.add(rating);
				addDeleteListener(new UnRateActionListener(this));
				titlePanel.add(unRateButton);

			}
		}
	}

	public JPanel createMovieListPanel(ArrayList<Movie> movieList) {
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS)); // results display vertically
		resultPanel.setBackground(Color.WHITE);
		
		for (Movie currentMovie : movieList) {
			MoviePanel moviePanel = new MoviePanel(currentMovie);
			moviePanel.addListener(new RateActionListener(moviePanel));
			moviePanel.addDeleteListener(new UnRateActionListener(moviePanel));
			resultPanel.add(moviePanel);
		}

		if (movieList.isEmpty())
			resultPanel.add(new JLabel("No results"));

		return resultPanel;
	}

	public class RateActionListener implements ActionListener {
		private Movie movie;
		private MoviePanel moviePanel;
		private int currentRating;

		public RateActionListener(MoviePanel ratedMovie) {
			movie = ratedMovie.getMovie();
			moviePanel = ratedMovie;
		}

		public void displayRateFrame() {
			JPanel ratingPanel = new JPanel();
			ratingPanel.add(new JLabel("Please rate " + movie.getTitle() + " by selecting one of the following options"));
			
			ButtonGroup ratingButtons = new ButtonGroup();
			for (Integer i = 1; i <=5; i++){
				JRadioButton rateButton = new JRadioButton(i.toString());
				rateButton.setActionCommand(i.toString());
				rateButton.addActionListener(e -> currentRating = Integer.parseInt(e.getActionCommand()));
				ratingButtons.add(rateButton);
				ratingPanel.add(rateButton);
			}

			Object[] options = { "OK", "CANCEL" };
			int action = JOptionPane.showOptionDialog(null, ratingPanel,
	    					"Rate", JOptionPane.DEFAULT_OPTION,
	    					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			if (action == 0)
				Controller.rateStorageApplication.rateMovie(movie, currentRating);
		}

		@Override
		public void actionPerformed(ActionEvent e){
			displayRateFrame();
			moviePanel.updateRating();
		}
	}
	public class UnRateActionListener implements ActionListener {
		private Movie movie;
		private MoviePanel moviePanel;

		public UnRateActionListener(MoviePanel ratedMovie) {
			movie = ratedMovie.getMovie();
			moviePanel = ratedMovie;
		}
		@Override
		public void actionPerformed(ActionEvent e){
			Controller.rateStorageApplication.unRateMovie(movie);
			moviePanel.updateRating();		
			if((RatePanel.this) instanceof HistoryPanel)
				((HistoryPanel)RatePanel.this).updateResultPanel();	
		}
	}
}

