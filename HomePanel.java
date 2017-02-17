import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel
{
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana",Font.BOLD,16));
        g.drawString("Home panel", 20, 20);
    }
}

