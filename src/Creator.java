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
import Player.Mage;
import Player.Hero;
import Player.Player;
import Inventory.Inventory;

public class Creator extends JPanel{
	
	private JFrame frame;
	private JPanel panel, panelBg, top, mid, bottom, character, statsM, statsH;
	private JButton exit, choseH, choseM, heroImg, mageImg;
	private JLabel hero, mage;
	private Image heroI, mageI, backI, bgI; //bgI = background Image
	private BufferedImage heroBI, mageBI, backBI, bgIB;
	private JLabel hp, level, speed, attack;
	private int BUTTON_X, BUTTON_Y;
	private Player player;
	private Player h, m;
	
	public Creator() {
		
		final Creator self = this;
		h = new Hero();
		m = new Mage();

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
		bottom.setLayout(new GridLayout(1,2));
		panel.setLayout(new BorderLayout());
		mid.add(character);
		/*
		//Hintergrund
		try {
			bgIB = ImageIO.read(new File("res/background/char.png"));
	   		bgI = bgIB.getScaledInstance(Constants.SCREEN_X, Constants.SCREEN_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		panelBg.setOpaque(true);
		*/
		panel.setOpaque(false);
		top.setOpaque(false);
		mid.setOpaque(false);
		bottom.setOpaque(false);
		character.setOpaque(false);
		statsM.setOpaque(false);
		statsH.setOpaque(false);
		
		//stats held
		hp = new JLabel("HP: " + Integer.toString(h.getHp()));
		level = new JLabel("Level: " + Integer.toString(h.getLevel()));
		speed = new JLabel("tbd");
		attack = new JLabel("tbd");
		statsH.setLayout(new GridLayout(5,1));
		statsH.add(new JLabel("Stats:"));
		statsH.add(hp);
		statsH.add(level);
		statsH.add(speed);
		statsH.add(attack);
		mid.add(statsH);
		statsH.setOpaque(true);
		
		//stats held
		hp = new JLabel("HP: " + Integer.toString(h.getHp()));
		level = new JLabel("Level: " + Integer.toString(h.getLevel()));
		speed = new JLabel("tbd");
		attack = new JLabel("tbd");
		statsH.setLayout(new GridLayout(5,1));
		statsH.add(new JLabel("Stats:"));
		statsH.add(hp);
		statsH.add(level);
		statsH.add(speed);
		statsH.add(attack);
		mid.add(statsH);
		statsH.setOpaque(true);
		
		
		
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

		
		//bild Hero
		heroImg = new JButton();
		heroImg.setOpaque(false);
		heroImg.setBorderPainted(false);
		heroImg.setFocusPainted(false);
		heroImg.setContentAreaFilled(false);
		heroImg.setBorder(BorderFactory.createEmptyBorder());
		try {
			heroBI = ImageIO.read(new File("res/hero/adventurer-idle-03.png"));
	   		heroI = heroBI.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		heroImg.setIcon(new ImageIcon(heroI));
		heroImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setPlayer(h);
				FrameManager.currentScreen = FrameManager.Screen.Game;
				FrameManager.run();
			}
		});
		mid.add(heroImg);
		mid.add(statsH);
		//bild mage
			mageImg = new JButton();
			mageImg.setOpaque(false);
			mageImg.setBorderPainted(false);
			mageImg.setFocusPainted(false);
			mageImg.setContentAreaFilled(false);
			mageImg.setBorder(BorderFactory.createEmptyBorder());
			try {
					mageBI = ImageIO.read(new File("res/monster/Necromancer/Individual Sprites/necromancer-idle-03.png"));
			   		mageI = mageBI.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			} catch (IOException e) {
					e.printStackTrace();
			}
			mageImg.setIcon(new ImageIcon(mageI));
			mageImg.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					FrameManager.currentScreen = FrameManager.Screen.Game;
					FrameManager.run();
				}
			});
			mid.add(mageImg);
		/*
		//choose Hero chooseH
		choseH = new JButton("Choose Hero");
		choseH.setOpaque(false);
		choseH.setContentAreaFilled(false);
		choseH.setBorderPainted(false);
		choseH.setBorder(BorderFactory.createEmptyBorder());
		choseH.setBounds((Constants.SCREEN_X - BUTTON_X) / 2, (Constants.SCREEN_Y - BUTTON_Y)/ 2, BUTTON_X, BUTTON_Y);
		bottom.add(choseH);
		chosenPlayer = 0;
		FrameManager.frame.validate();
		choseH.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameManager.currentScreen = FrameManager.Screen.Game;
				FrameManager.run();
			}
		});
		
		//choose Mage chooseM
		choseM = new JButton("Choose Mage");
		choseM.setOpaque(false);
		choseM.setContentAreaFilled(true);
		choseM.setBorderPainted(false);
		choseM.setOpaque(false);
		choseM.setBounds((Constants.SCREEN_X - BUTTON_X) / 2, (Constants.SCREEN_Y - BUTTON_Y)/ 2, BUTTON_X, BUTTON_Y);
		bottom.add(choseM);
		chosenPlayer = 1;
		FrameManager.frame.validate();
		choseM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameManager.currentScreen = FrameManager.Screen.Game;
				FrameManager.run();
				}
			}); */
		bottom.setOpaque(false);
		
		panel.setLayout(new BorderLayout());
		panel.add(top, BorderLayout.NORTH);
		panel.add(bottom, BorderLayout.SOUTH);
		panel.add(mid, BorderLayout.CENTER);
		updateUI();
	}
	public void paint(Graphics g)
	{
		   
		super.paint(g);
		g.drawImage(bgI, 0, 0, null);
	}
	public Player getPlayer()
	{
		return player;
	}
	public void setPlayer(Player p)
	{
		player = p;
	}
}
