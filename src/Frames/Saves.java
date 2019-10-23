package Frames;

import java.awt.BorderLayout;
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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import Constants.Constants;
import Frames.FrameManager.Screen;

public class Saves extends JPanel{
	
	private JComboBox saveFiles;
	private JPanel panelBg, panel;
	private Image bgI, backI, playI; //bgI = background Image
	private BufferedImage bgIB, backBI, playBI; // bgIB = background Image Buffered
	private JButton backB, playB;
	
	public Saves() {
		panelBg = new JPanel();
		panel = new JPanel();
		
		add(panelBg);
		panel.setLayout(new GridLayout(2,1));
		panelBg.add(panel);
		
		//Hintergrund
		try {
			bgIB = ImageIO.read(new File("res/background/menu.png"));
	   		bgI = bgIB.getScaledInstance(Constants.SCREEN_X, Constants.SCREEN_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
			}
		panelBg.setOpaque(false);
		panel.setOpaque(false);		
		
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
		backB = new JButton("Back to Menu");
		backB.setFont(new Font("SERIF", Font.PLAIN, 25));
		backB.setForeground(Color.WHITE);
		backB.setHorizontalTextPosition(JButton.CENTER);
		backB.setVerticalTextPosition(JButton.CENTER);
		backB.setOpaque(false);
		backB.setContentAreaFilled(false);
		backB.setBorder(BorderFactory.createEmptyBorder());
		backB.setBounds((Constants.SCREEN_X - Constants.BUTTON_X) / 2, (Constants.SCREEN_Y - Constants.BUTTON_Y)/ 2,Constants.BUTTON_X, Constants.BUTTON_Y);
		try {
			backBI = ImageIO.read(new File("res/menu/MENU_kickCard.png"));
			backI = backBI.getScaledInstance(Constants.BUTTON_X, Constants.BUTTON_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		backB.setIcon(new ImageIcon(backI));
		backB.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				FrameManager.currentScreen = FrameManager.Screen.Menu;
				FrameManager.run();
			}
		});
		
		ArrayList<String> saves = new ArrayList<>();
		File saveFolder = new File("saves");
		for(File f : saveFolder.listFiles()){
			if(f.isDirectory()){
				saves.add(f.getName());
			}
		}
		//Saves
		saveFiles = new JComboBox<Object>(saves.toArray());
		saveFiles.setEditable(true);
		saveFiles.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Constants.safeName = saveFiles.getSelectedItem().toString();
			}
		});
		
		panel.add(playB);
		panel.add(backB);
		panel.add(saveFiles);
		FrameManager.frame.validate();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(bgI, 0, 0, null);
	}
	
}
