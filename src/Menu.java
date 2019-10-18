import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Constants.Constants;

public class Menu extends JPanel{
	
	public static JPanel startScreenP;
	private JButton playB, optionsB, exitB, createB;
	private Image backgroundI, exitI, optionsI, playI;
	private BufferedImage backgroundIB, exitBI, optionsBI, playBI;
	private int EXIT_BUTTON_X, EXIT_BUTTON_Y, PLAY_BUTTON_X, PLAY_BUTTON_Y, OPTIONS_BUTTON_X, OPTIONS_BUTTON_Y;
	
	public Menu(final JFrame frame, final Main main) {		
		startScreenP = new JPanel();
		playB = new JButton("Play Game"); 
		optionsB = new JButton("Options");
		createB = new JButton("Create Character");
		
		add(startScreenP);
		setVisible(true);
		
		//Hintergrund
		try {
			backgroundIB = ImageIO.read(new File("res/background/startscreen.png"));
			   backgroundI = backgroundIB.getScaledInstance(Constants.SCREEN_X, Constants.SCREEN_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		startScreenP.setOpaque(false);
		
		
		//play Button
		PLAY_BUTTON_X = 370/2;
		PLAY_BUTTON_Y = 130/2;
		playB = new JButton();
		playB.setOpaque(false);
		playB.setBorder(BorderFactory.createEmptyBorder());
		playB.setBounds((Constants.SCREEN_X - PLAY_BUTTON_X) / 2, (Constants.SCREEN_Y - PLAY_BUTTON_Y)/ 2, PLAY_BUTTON_X, PLAY_BUTTON_Y);
		try {
			playBI = ImageIO.read(new File("res/buttons/play.png"));
			playI = playBI.getScaledInstance(PLAY_BUTTON_X, PLAY_BUTTON_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		playB.setIcon(new ImageIcon(playI));
		playB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameManager.currentScreen = FrameManager.Screen.Game;
				FrameManager.run();
			}
		});
		
		createB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameManager.currentScreen = FrameManager.Screen.Creator;
				FrameManager.run();
			}
		});
		
		//options Button
		OPTIONS_BUTTON_X = 370/2;
		OPTIONS_BUTTON_Y = 130/2;
		optionsB = new JButton();
		optionsB.setOpaque(false);
		optionsB.setBorder(BorderFactory.createEmptyBorder());
		optionsB.setBounds((Constants.SCREEN_X - OPTIONS_BUTTON_X) / 2, (Constants.SCREEN_Y - OPTIONS_BUTTON_Y)/ 2, OPTIONS_BUTTON_X, OPTIONS_BUTTON_Y);
		try {
			optionsBI = ImageIO.read(new File("res/buttons/options.png"));
			optionsI = optionsBI.getScaledInstance(OPTIONS_BUTTON_X, OPTIONS_BUTTON_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		optionsB.setIcon(new ImageIcon(optionsI));
		optionsB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameManager.currentScreen = FrameManager.Screen.Options;
				FrameManager.run();
			}
		});
		
		//exit Button
		EXIT_BUTTON_X = 370/2;
		EXIT_BUTTON_Y = 130/2;
		exitB = new JButton();
		exitB.setOpaque(false);
		exitB.setBorder(BorderFactory.createEmptyBorder());
		exitB.setBounds((Constants.SCREEN_X - EXIT_BUTTON_X) / 2, (Constants.SCREEN_Y - EXIT_BUTTON_Y)/ 2, EXIT_BUTTON_X, EXIT_BUTTON_Y);
		try {
			exitBI = ImageIO.read(new File("res/buttons/exit.png"));
	   		exitI = exitBI.getScaledInstance(EXIT_BUTTON_X, EXIT_BUTTON_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		exitB.setIcon(new ImageIcon(exitI));
		exitB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//Startscreen
		startScreenP.add(playB);
		startScreenP.add(createB);
		startScreenP.add(optionsB);
		startScreenP.add(exitB);
		
	}
	public void paintComponent(Graphics g)
	{
		   
		super.paintComponent(g);
		g.drawImage(backgroundI, 0, 0, null);
	}
}

