public class SearchController{
  SearchGUI gui;
  MovieCollection collection = new MovieCollection();     //EMPTY CONTSTRUCTOR RETURNS ENTIRE DATABASE
  //constructor
  public SearchController(){
  }
  public void go(SearchGUI g){
    gui = g;
    //call gui functions to set up gui
    //create a while loop to have this shit running until user clicks quit
    //wait for user input
    String input = "";                          // USER INPUT
    collection = SearchHandler.search(collection, input);
    
  }
  public void sort(){
    
  }
  public void quit(){
  
  }
}
