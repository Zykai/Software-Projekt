import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Menu extends JPanel{
private Main main;
private JLabel test;
private JFrame frame;
private JPanel startScreen;
private JPanel playScreen;
private JPanel optionScreen;
private JButton saves;
private JButton play;
private JButton options;
private JButton loadSaves;
private JButton newGame;

public Menu(final JFrame frame, Main main){
	this.main = main;
	test = new JLabel("test");
	
	final Menu self = this;
	startScreen = new JPanel();
	playScreen = new JPanel();
	optionScreen = new JPanel();
	play = new JButton("Play Game"); 
	options = new JButton("Options");
	newGame = new JButton("Create new Game");
	saves = new JButton("Load Game");
	
	
	add(startScreen);
	startScreen.setLayout(new GridLayout());
	startScreen.add(play);
	startScreen.add(options);
	setVisible(true);
	setSize(1000, 700);
	
	optionScreen.setLayout(new GridLayout());
	optionScreen.add(test);
	
	play.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.remove(self);
			frame.add(self.main);
			frame.validate();
		}
	});
	options.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.remove(self);
			frame.add(optionScreen);
		}
	});
	
	
	}
		

}

