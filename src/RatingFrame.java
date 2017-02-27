package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RatingFrame{
	private static int currentRating;
	public static void displayRateFrame(Movie movie){
		JPanel panel = new JPanel();
		ButtonGroup group = new ButtonGroup();
		for (Integer i = 1; i <=5; i++){
			JRadioButton button = new JRadioButton(i.toString());
			button.setActionCommand(i.toString());
			button.addActionListener(e->currentRating= Integer.parseInt(e.getActionCommand()));
			group.add(button);
			panel.add(button);
		}
		Object[] options = { "OK", "CANCEL" };
		int action = JOptionPane.showOptionDialog(null, panel,
    					"Rate", JOptionPane.DEFAULT_OPTION,
    					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if(action ==0){
			Controller.updateRatingHistory(movie, currentRating);
		}
	}
}
