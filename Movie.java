import java.util.ArrayList;

// Stores info on movies
// Have constructors to initialize data
// Have getters for all information
// String title, int year, String director, String genre, String[] actors, String parentalRating, String runtime, String language, 
// String country, double IMdBRating, String plot, String imageURL
import java.util.ArrayList;

public class Movie {
   private int key;
   private String title;
   private String date;
   private String director;
   private ArrayList<String> genre;
   private ArrayList<String> actors;
   private String parentalRating; // TODO: enum this shit
   private int runtime;
   private String language;
   private String country;
   private double criticalRating;
   private String plot;
   private String imageURL;
   
   /**
     * Creates a Movie object with default values for all variables, must provide key
     * Use this first, then use setters to alter variables
     * @param key The key for this movie
   **/
   public Movie(int key) {
      this.key = key;
      title = "";
      date = "";
      director = "";
      genre = new ArrayList<String>();
      actors = new ArrayList<String>();
      parentalRating = "";
      runtime = 0;
      language = "";
      country = "";
      criticalRating = 0.0;
      plot = "";
      imageURL = "";
   }
   
   /**
     * Create a movie object with all variables initialized in order, pulling from parameters
     * @param parameters List of all values to set variables of new movie object to in the order
     * title, year, director, genre, actors, parentalRating, runtime, language, country,
     * criticalRating, plot, imageURL
   **/
   public Movie(ArrayList<Object> parameters) {
        
   }
   
   public String toString()
   {
      return "Movie Title: " + title + ", Release Date: " + date + ", Director: " + director + ", Genre(s): " + genre + ", Actors: " + actors
        + ", Parental Rating: " + parentalRating + ", Runtime: " + runtime + " (minutes), Language: " + language + ", Country: " + country 
        + ", Critical Rating: " + ", Plot: " + plot;  
   }
   
   /**
     * @return The movie's key
   **/
   public int getKey() {
      return this.key;
   }
   
   /**
     * Sets the movie's key
     * @param key New key for the movie
   **/
   public void setKey(int key) {
      this.key = key;
   }
   
   /**
     * @return The movie's title
   **/
   public String getTitle() {
      return this.title;
   }
   
   /**
     * Sets the movie's title
     * @param title New title for the movie
   **/
   public void setTitle(String title) {
      this.title = title;
   }
   
   /**
     * @return The movie's date as a string
   **/
   public String getDate() {
      return this.date;
   }
   
   /**
     * Sets the movie's year
     * @param year New year for the movie
   **/
   public void setDate(String date) {
      this.date = date;
   }
   
   /**
     * @return The movie's director
   **/
   public String getDirector() {
      return this.director;
   }
   
   /**
     * Sets the movie's director
     * @param director New director for the movie
   **/
   public void setDirector(String director) {
      this.director = director;
   }
   
   /**
     * @return The movie's genre
   **/
   public ArrayList<String> getGenre() {
      return this.genre;
   }
   
   /**
     * Sets the movie's genre
     * @param genre New genre for the movie
   **/
   public void setGenre(ArrayList<String> genre) {
      this.genre = genre;
   }
   
   /**
     * @return The movie's actors as an ArrayList<String>
   **/
   public ArrayList<String> getActors() {
      return this.actors;
   }
   
   /**
     * Sets the movie's actors
     * @param actors New actors for the movie in an ArrayList<String>
   **/
   public void setActors(ArrayList<String> actors) {
      this.actors = actors;
   }
   
   /**
     * @return The movie's parentalRating
   **/
   public String getParentalRating() {
      return this.parentalRating;
   }
   
   /**
     * Sets the movie's parentalRating
     * @param parentalRating New parentalRating for the movie
   **/
   public void setParentalRating(String parentalRating) {
      this.parentalRating = parentalRating;
   }
   
   /**
     * @return The movie's runtime as an int in minutes
   **/
   public int getRuntime() {
      return this.runtime;
   }
   
   /**
     * Sets the movie's runtime
     * @param runtime New runtime for the movie
   **/
   public void setRuntime(int runtime) {
      this.runtime = runtime;
   }
   
   /**
     * @return The movie's language
   **/
   public String getLanguage() {
      return this.language;
   }
   
   /**
     * Sets the movie's language
     * @param language New language for the movie
   **/
   public void setLanguage(String language) {
      this.language = language;
   }
   
   /**
     * @return The movie's country
   **/
   public String getCountry() {
      return this.country;
   }
   
   /**
     * Sets the movie's country
     * @param country New country for the movie
   **/
   public void setCountry(String country) {
      this.country = country;
   }
   
   /**
     * @return The movie's rating out of 10 by critics as a double 
   **/
   public double getCriticalRating() {
      return this.criticalRating;
   }
   
   /**
     * Sets the movie's rating out of 10
     * @param criticalRating New criticalRating for the movie as a double
   **/
   public void setCriticalRating(double criticalRating) {
      this.criticalRating = criticalRating;
   }
   
   /**
     * @return The movie's plot synopsis
   **/
   public String getPlot() {
      return this.plot;
   }
   
   /**
     * Sets the movie's plot
     * @param plot New plot synopsis for the movie
   **/
   public void setPlot(String plot) {
      this.plot = plot;
   }
   
   /**
     * @return The movie's poster art URL
   **/
   public String getImageURL() {
      return this.imageURL;
   }
   
   /**
     * Sets the movie's poster art URL
     * @param imageURL New poster art URL for the movie
   **/
   public void setImageURL(String imageURL) {
      this.imageURL = imageURL;
   }
   
   /**
     * @return The hash code for this movie, which is the key
   **/
   @Override
   public int hashCode() {
      return key;
   }
   
   @Override
   public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Movie)) {
            return false;
        }
        Movie movie = (Movie) o;
        // Stub, make this more robust later
        return movie.title.equals(this.title);
   }
   
}
