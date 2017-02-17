import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class GUI
{
    private JFrame frame;
    private JTabbedPane tabbedPane;
    HomePanel homePanel;
    SearchPanel searchPanel;

    public GUI() {
	   go();
    }

    public void go() {
        frame = new JFrame();
        tabbedPane = new JTabbedPane();
        homePanel = new HomePanel();
        searchPanel = new SearchPanel();

        tabbedPane.add("Home", homePanel);
        tabbedPane.add("Search", searchPanel);
        frame.getContentPane().add(tabbedPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    public void setResults(HashMap collection){

    }
    public String getSearch(){          //STUB
        return "";
    }
    public String getFilter(){          //STUB
        return "";

    }
    public String getSort(){          //STUB
        return "";
    }
    public boolean quit(){                  //STuB
        return false;                  
    }
    //return 0 if still on home panel
    //return 1 if on search panel
    public int tabClick(){              //STUB
        return 0;
    }
    // public static void main(String[] args){      //TESTING GUI
    //     GUI g = new GUI();
    // }
}
