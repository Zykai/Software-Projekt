import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Constants.Constants;
import Player.Hero;
import Player.Player;
import Inventory.Inventory;

public class Creator extends JPanel{
	
	private JFrame frame;
	private JPanel panel, panelBg, top, mid, bottom, character, statsM, statsH;
	private JButton exit, choseH, choseM;
	private JLabel hero;
	private Image hero1, backI, bgI; //bgI = background Image
	private BufferedImage hero1B, backBI, bgIB;
	private JLabel hp, level, speed, attack;
	private int BUTTON_X, BUTTON_Y;
	
	public Creator() {
		
		final Creator self = this;
		panelBg = new JPanel();
		panel = new JPanel();
		top = new JPanel();
		mid = new JPanel();
		bottom = new JPanel();
		character = new JPanel();
		exit = new JButton();
		statsM = new JPanel();
		statsH = new JPanel();
		add(panelBg);
		panelBg.add(panel);
		top.setLayout(new FlowLayout());
		mid.setLayout(new GridLayout(2,2));
		bottom.setLayout(new GridLayout(2,1));
		panel.setLayout(new BorderLayout());
		mid.add(character);
		
		//Hintergrund
		try {
			bgIB = ImageIO.read(new File("res/background/char.png"));
	   		bgI = bgIB.getScaledInstance(Constants.SCREEN_X, Constants.SCREEN_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		panelBg.setOpaque(false);
		
		// held
		Player p = new Hero();
		hp = new JLabel("HP: " + Integer.toString(p.getHp()));
		level = new JLabel("Level: " + Integer.toString(p.getLevel()));
		speed = new JLabel("tbd");
		attack = new JLabel("tbd");
		statsH.setLayout(new GridLayout(5,1));
		statsH.add(new JLabel("Stats:"));
		statsH.add(hp);
		statsH.add(level);
		statsH.add(speed);
		statsH.add(attack);
		
		//back Button
		BUTTON_X = 370/2;
		BUTTON_Y = 130/2;
		exit = new JButton();
		exit.setOpaque(true);
		exit.setBorder(BorderFactory.createEmptyBorder());
		exit.setBounds((Constants.SCREEN_X - BUTTON_X) / 2, (Constants.SCREEN_Y - BUTTON_Y)/ 2, BUTTON_X, BUTTON_Y);
		try {
			backBI = ImageIO.read(new File("res/exit.png"));
	   		backI = backBI.getScaledInstance(BUTTON_X, BUTTON_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
			}
		exit.setIcon(new ImageIcon(backI));
		exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	FrameManager.currentScreen = FrameManager.Screen.Menu;
				FrameManager.run();
            }
        });
		top.add(exit);
		FrameManager.frame.validate();
		
		//choose Hero chooseH
		choseH = new JButton("Choose Hero");
		choseH.setOpaque(false);
		choseH.setContentAreaFilled(false);
		choseH.setBorderPainted(false);
		choseH.setBorder(BorderFactory.createEmptyBorder());
		choseH.setBounds((Constants.SCREEN_X - BUTTON_X) / 2, (Constants.SCREEN_Y - BUTTON_Y)/ 2, BUTTON_X, BUTTON_Y);
		bottom.add(choseH);
		FrameManager.frame.validate();
		choseH.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameManager.currentScreen = FrameManager.Screen.Game;
				FrameManager.run();
			}
		});
		
		
		panel.setLayout(new BorderLayout());
		panel.add(top, BorderLayout.NORTH);
		panel.add(bottom, BorderLayout.SOUTH);
		panel.add(mid, BorderLayout.EAST);
		
	}
	public void paint(Graphics g)
	{
		   
		super.paint(g);
		g.drawImage(bgI, 0, 0, null);
	}
	public void draw(Graphics g){
		
	}
}
