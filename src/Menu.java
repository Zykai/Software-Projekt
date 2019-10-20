import java.awt.Color;
import java.awt.Font;
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
	private Image backgroundI, exitI, optionsI, playI, createI;
	private BufferedImage backgroundIB, exitBI, optionsBI, playBI, createBI;
	
	public Menu(final JFrame frame, final Main main) {		
		//Hintergrund
		try {
			backgroundIB = ImageIO.read(new File("res/background/startscreen.png"));
			backgroundI = backgroundIB.getScaledInstance(Constants.SCREEN_X, Constants.SCREEN_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		//Create Button
		
		createB = new JButton("Create Character");
		createB.setFont(new Font("SERIF", Font.PLAIN, 25));
		createB.setForeground(Color.WHITE);
		createB.setHorizontalTextPosition(JButton.CENTER);
		createB.setVerticalTextPosition(JButton.CENTER);
		createB.setOpaque(false);
		createB.setContentAreaFilled(false);
		createB.setBorder(BorderFactory.createEmptyBorder());
		createB.setBounds((Constants.SCREEN_X - Constants.BUTTON_X) / 2, (Constants.SCREEN_Y - Constants.BUTTON_Y)/ 2, Constants.BUTTON_X, Constants.BUTTON_Y);
		try {
			createBI = ImageIO.read(new File("res/menu/MENU_kickCard.png"));
			createI = createBI.getScaledInstance(Constants.BUTTON_X, Constants.BUTTON_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		createB.setIcon(new ImageIcon(createI));
		createB.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				FrameManager.currentScreen = FrameManager.Screen.Creator;
				FrameManager.run();
			}
		});

		//Play Button
		playB = new JButton("Play");
		playB.setFont(new Font("SERIF", Font.PLAIN, 25));
		playB.setForeground(Color.WHITE);
		playB.setHorizontalTextPosition(JButton.CENTER);
		playB.setVerticalTextPosition(JButton.CENTER);
		playB.setOpaque(false);
		playB.setContentAreaFilled(false);
		playB.setBorder(BorderFactory.createEmptyBorder());

		playB.setBounds((Constants.SCREEN_X - Constants.BUTTON_X) / 2, (Constants.SCREEN_Y - Constants.BUTTON_Y)/ 2,Constants.BUTTON_X, Constants.BUTTON_Y);
		try {
			playBI = ImageIO.read(new File("res/menu/MENU_kickCard.png"));
			playI = playBI.getScaledInstance(Constants.BUTTON_X, Constants.BUTTON_Y, Image.SCALE_SMOOTH);
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
			
		//options Button
		optionsB = new JButton("Options");
		optionsB.setFont(new Font("SERIF", Font.PLAIN, 25));
		optionsB.setForeground(Color.WHITE);
		optionsB.setHorizontalTextPosition(JButton.CENTER);
		optionsB.setVerticalTextPosition(JButton.CENTER);
		optionsB.setOpaque(false);
		optionsB.setContentAreaFilled(false);		optionsB.setBorder(BorderFactory.createEmptyBorder());
		optionsB.setBounds((Constants.SCREEN_X - Constants.BUTTON_X) / 2, (Constants.SCREEN_Y - Constants.BUTTON_Y)/ 2,Constants.BUTTON_X, Constants.BUTTON_Y);
		try {
			optionsBI = ImageIO.read(new File("res/menu/MENU_kickCard.png"));
			optionsI = optionsBI.getScaledInstance(Constants.BUTTON_X, Constants.BUTTON_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		optionsB.setIcon(new ImageIcon(createI));
		optionsB.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				FrameManager.currentScreen = FrameManager.Screen.Options;
				FrameManager.run();
			}
		});
		
		//exit Button
		exitB = new JButton("Exit");
		exitB.setFont(new Font("SERIF", Font.PLAIN, 25));
		exitB.setForeground(Color.WHITE);
		exitB.setHorizontalTextPosition(JButton.CENTER);
		exitB.setVerticalTextPosition(JButton.CENTER);
		exitB.setOpaque(false);
		exitB.setContentAreaFilled(false);
		exitB.setBorder(BorderFactory.createEmptyBorder());
		exitB.setBounds((Constants.SCREEN_X - Constants.BUTTON_X) / 2, (Constants.SCREEN_Y - Constants.BUTTON_Y)/ 2,Constants.BUTTON_X, Constants.BUTTON_Y);
		try {
			exitBI = ImageIO.read(new File("res/menu/MENU_kickCard.png"));
			exitI = exitBI.getScaledInstance(Constants.BUTTON_X, Constants.BUTTON_Y, Image.SCALE_SMOOTH);
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
		startScreenP = new JPanel();
		startScreenP.setOpaque(false);
		add(startScreenP);
		startScreenP.add(playB);
		startScreenP.add(createB);
		startScreenP.add(optionsB);
		startScreenP.add(exitB);
		
		setVisible(true);
	}
	public void paintComponent(Graphics g)
	{
		   
		super.paintComponent(g);
		g.drawImage(backgroundI, 0, 0, null);
	}
	
}

