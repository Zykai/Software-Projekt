import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Menu extends JPanel{
	private Main main;
	private Menu menu;
	
	private JLabel optionsL;
	private JFrame frame;
	private JPanel startScreenP;
	private JPanel playScreenP;
	private JPanel optionScreenP;
	private JButton savesB;
	private JButton playB;
	private JButton optionsB;
	private JButton loadSavesB;
	private JButton newGameB;
	private JButton backB;
	private JButton exitB;
	private Image playButton, playButtonB;
	
	public Menu(final JFrame frame, final Main main){
		
		/*
		BufferedImage img = null;
		try {
			playButtonB = ImageIO.read(new File("playbutton.png"));
			playButton = playButtonB.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		*/
		
		
		this.main = main;
		optionsL = new JLabel("coming soon");
		
		final Menu self = menu;
		startScreenP = new JPanel();
		playScreenP = new JPanel();
		optionScreenP = new JPanel();
		playB = new JButton(/*new ImageIcon(playButton)*/"Play Game"); 
		optionsB = new JButton("Options");
		newGameB = new JButton("Create new Game");
		savesB = new JButton("Load Game");
		backB = new JButton("Back to menu");
		exitB = new JButton("Exit Game");
		
		//playB.setIcon((Icon) startButton);
		
		add(startScreenP);
		setVisible(true);
		
		
		//Startscreen
		startScreenP.setLayout(new GridLayout());
		startScreenP.add(playB);
		startScreenP.add(optionsB);
		startScreenP.add(exitB);
		
		
		//Optionscreen
		optionScreenP.setLayout(new GridLayout());
		optionScreenP.add(optionsL);
		optionScreenP.add(backB);
		
		
		
		playB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(self);
				frame.add(self.main);
				frame.validate();
			}
		});
		
		optionsB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(self);
				frame.add(optionScreenP);
				frame.validate();
			}
		});
		
		exitB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		backB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(self);
				System.out.println("asd");
				frame.add(self);
				frame.validate();
			}
		});
	}
}

