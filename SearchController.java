import java.util.HashMap;

public class SearchController{
  private String input;
  private String filter;
  private String sort;
  private HashMap collection;
  private GUI gui;

  public SearchController(GUI g){
    gui = g;
    input = gui.getSearch();
    filter = gui.getFilter();
    sort =  gui.getSort();
    collection = new HashMap();  
  }
  public void searchGo(){
    collection = SearchHandler.search(input, filter, sort);
    gui.setResults(collection);
  }
  public void reset(){
    input = "";
    filter = "";
    sort = "";
    collection = new HashMap();
  }
}
