//Controller class

public class Controller{
  HomeGUI homeGUI;
  public void go(){
    homeGui = new HomeGUI();
    //check what user wants to do
    //Iteration 1: automatically open search page
    SearchGUI searchGui = new SearchGUI();
    searchGUI.go();
    searchGUI.quit();
  }
  public static void main(String[] args){
    Controller controller = new Controller();
    controller.go();
  }
}
