public class SearchController extends Controller{

  //constructor
  public SearchController(SearchGUI g){
    SearchGUI gui = g;
    String input = gui.getSearch();
    String filter = gui.getFilter();
    String sort =  gui.getSort();
    MovieCollection collection = new MovieCollection();  //EMPTY CONTSTRUCTOR RETURNS ENTIRE DATABASE    
  }
  public void searchGo(){
    collection = SearchHandler.search(input, filter, sort);
    searchGUI.setResults(collection;)
  }
  public void reset(){
    this = new SearchController(this.getGUI())
  }
  public searchGUI getGUI(){
    return gui;
  }
}