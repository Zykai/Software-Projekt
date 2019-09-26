import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Menu {
	
public JPanel menu;
private Container startScreen;
private Container playScreen;
private JButton saves;
private JButton play;
private JButton options;
private JButton loadSaves;
private JButton newGame;

public Menu(){
	menu = new JPanel();
	startScreen = new Container();
	playScreen = new Container();
	play = new JButton("Play Game");
	options = new JButton("Options");
	newGame = new JButton("Create new Game");
	saves = new JButton("Load Game");
	
	
	menu.add(startScreen);
	startScreen.add(play);
	startScreen.add(options);
	menu.setVisible(true);
	menu.setSize(1000, 700);
	
	}
}
