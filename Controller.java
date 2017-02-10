//Controller class
//Starts the program and manages interactions between the GUI and backend
//Barbara Korycki

public class Controller{
  //Lets the thread sleep
  public Controller(GUI g){
    GUI gui = g;
  }
  public static void sleep(){
        try{
            Thread.sleep(10);
        }
        catch (InterruptedException e){
        }
    }

  public void wait(){
    gui = getSearchGUI();
    while((gui.getSearch()).isEmpty()) || !gui.tabClick()){       //HomeGUI class needs a getSearch() method that returns a string
                                              //return empty string if user has not entered anything?
                                              //needs to stop user from searching an empty string
      Controller.sleep();
    }
  }

  public SearchGUI getSearchGUI(){
    return this.gui;
  }

  public void go(){
    SearchController searchController = new SearchController(this.getSearchGUI());
    while(!searchGUI.quit()){                      //genGUI quit() method checks if user quit
      wait();
      if(searchGUI.tabClick()){
        searchController.reset();       //need to clear search in case user comes back to search page
        //open new page
      }
      else{
        searchController.searchGo();
      }
    }
  }
  public static void main(String[] args){
    //Search page will open by default
    SearchGUI searchGui = new SearchGUI();
    Controller controller = new Controller(searchGUI);
    controller.go();
  }
}