//Controller class

public class Controller{
  HomeGUI homeGUI;
  public void go(){
    homeGUI = new HomeGUI();
    //check what user wants to do
    //Iteration 1: automatically open search page
    SearchGUI searchGui = new SearchGUI();
    SearchController searchController = new SearchController();
    searchController.go(searchGUI);
    searchController.quit();
  }
  public static void main(String[] args){
    Controller controller = new Controller();
    controller.go();
  }
}
