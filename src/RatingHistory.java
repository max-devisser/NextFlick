package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.io.*;

public class RatingHistory implements Serializable{
	private transient int currentRating;	//helper variable for action listener
	private HashMap<Movie, Integer> history;

	public RatingHistory(){
		history = new HashMap<Movie, Integer>();
	}

	public void displayRateFrame(Movie movie){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Please rate " + movie.getTitle() + " by selecting one of the following options"));
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
			history.put(movie, currentRating);
			serialize();
			System.out.println(this);
		}
	}

	public String toString(){
		String map = "";
		for(HashMap.Entry<Movie, Integer> entry: history.entrySet()){
			String title = entry.getKey().getTitle();
			String rating = entry.getValue().toString();
			map+= "\n" + title + ": " + rating;
		}
		return map;
	}
	/*
	//	Called evertime history is updated to preserve it on the disc
	*/
	public void serialize(){
		try{
			FileOutputStream fo = new FileOutputStream("History.ser");
			ObjectOutputStream os = new ObjectOutputStream(fo);
			os.writeObject(this);
			os.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public HashMap<Movie,Integer> getHistory(){
		return this.history;
	}
}
