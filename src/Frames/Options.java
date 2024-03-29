package Frames;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import Constants.Constants;
import Frames.FrameManager.Screen;

public class Options extends JPanel{
	
	private JPanel panelBg, panel;
	private Image bgI, backI; //bgI = background Image
	private BufferedImage bgIB, backBI; // bgIB = background Image Buffered
	private JLabel tbd;
	private JButton backB, changeSize;
	
	public Options() {
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
		panel.add(backB);
		changeSize = new JButton("Change Screen Size");
		changeSize.setFont(new Font("SERIF", Font.PLAIN, 25));
		changeSize.setForeground(Color.WHITE);
		changeSize.setHorizontalTextPosition(JButton.CENTER);
		changeSize.setVerticalTextPosition(JButton.CENTER);
		changeSize.setOpaque(false);
		changeSize.setContentAreaFilled(false);
		changeSize.setBorder(BorderFactory.createEmptyBorder());
		changeSize.setBounds((Constants.SCREEN_X - Constants.BUTTON_X) / 2, (Constants.SCREEN_Y - Constants.BUTTON_Y)/ 2,Constants.BUTTON_X, Constants.BUTTON_Y);
		changeSize.setIcon(new ImageIcon(backI));
		changeSize.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Constants.SCREEN_X == 1920){
					FrameManager.frame.setSize(1720, 880);
					Constants.SCREEN_X = 1720;
					Constants.SCREEN_Y = 880;
				} else {
					FrameManager.frame.setSize(1920, 1080);
					Constants.SCREEN_X = 1920;
					Constants.SCREEN_Y = 1080;
				}
				
			}
			
		});
		panel.add(changeSize);
		FrameManager.frame.validate();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(bgI, 0, 0, null);
	}
	
}



