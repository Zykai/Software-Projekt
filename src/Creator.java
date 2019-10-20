import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
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
	private JPanel panel, character, top, mid, back, statsM, statsH;
	private JButton backB, heroImg, mageImg;
	private Image heroI, mageI, backI, bgI, statsI; //bgI = background Image
	private BufferedImage heroBI, mageBI, backBI, bgIB, statsBI;
	private JLabel hpH, hpM, levelH, levelM, speedH, speedM, attackH, attackM, info, typeH, typeM, attackSpeedH, attackSpeedM, regH, regM;
	private Player player;
	private Player h, m;
	
	public Creator() {
		
		final Creator self = this;
		h = new Hero();
		m = new Mage();

		panel = new JPanel();
		back = new JPanel();
		top = new JPanel();
		mid = new JPanel();
		backB = new JButton();
		statsM = new JPanel();
		statsH = new JPanel();
		character = new JPanel();
		add(panel);
		character.setLayout(new GridLayout(2,2));
		mid.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		
		//Hintergrund
		try {
			bgIB = ImageIO.read(new File("res/background/char.png"));
	   		bgI = bgIB.getScaledInstance(Constants.SCREEN_X, Constants.SCREEN_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		panel.setOpaque(false);
		mid.setOpaque(false);
		statsM.setOpaque(false);
		statsH.setOpaque(false);
		character.setOpaque(false);
		
		info = new JLabel("Please click on the character you want to play with!");
		info.setFont(new Font("SERIF", Font.PLAIN, 25));
		info.setForeground(Color.WHITE);
		top.add(info);
		mid.add(info, BorderLayout.NORTH);
		mid.add(character, BorderLayout.CENTER);
		
		//stats held
		typeH = new JLabel("	HERO	");
		typeH.setFont(new Font("SERIF", Font.BOLD, 15));
		typeH.setForeground(Color.WHITE);
		hpH = new JLabel("HP ❤: " + Integer.toString(h.currentHP));
		hpH.setFont(new Font("SERIF", Font.PLAIN, 15));
		hpH.setForeground(Color.WHITE);
		attackH = new JLabel("Attack ⚔: " + Integer.toString(h.attackDamage));
		attackH.setFont(new Font("SERIF", Font.PLAIN, 15));
		attackH.setForeground(Color.WHITE);
		attackSpeedH = new JLabel("Attack speed 🗡: " + Double.toString(h.attackSpeed));
		attackSpeedH.setFont(new Font("SERIF", Font.PLAIN, 15));
		attackSpeedH.setForeground(Color.WHITE);
		regH = new JLabel("Regeneration ➕: " + Integer.toString(h.hpRegen));
		regH.setFont(new Font("SERIF", Font.PLAIN, 15));
		regH.setForeground(Color.WHITE);
		levelH = new JLabel("Ablility Level 🌠 : " + Integer.toString(h.abilityPower));
		levelH.setFont(new Font("SERIF", Font.PLAIN, 15));
		levelH.setForeground(Color.WHITE);
		speedH = new JLabel("Movement speed 🏃: " + Float.toString(h.movespeed));
		speedH.setFont(new Font("SERIF", Font.PLAIN, 15));
		speedH.setForeground(Color.WHITE);
		
		statsH.setLayout(new GridLayout(7,1));
		statsH.add(typeH);
		statsH.add(hpH);
		statsH.add(attackH);
		statsH.add(attackSpeedH);
		statsH.add(regH);
		statsH.add(levelH);
		statsH.add(speedH);
		statsH.setOpaque(false);
		
		try {
			statsBI = ImageIO.read(new File("res/menu/MENU_PC_TextSelect.png"));
	   		statsI = statsBI.getScaledInstance(Constants.SCREEN_X/2, Constants.SCREEN_Y/2, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		//stats mage
		typeM = new JLabel("	MAGE	");
		typeM.setFont(new Font("SERIF", Font.BOLD, 15));
		typeM.setForeground(Color.WHITE);
		hpM = new JLabel("HP ❤: " + Integer.toString(m.currentHP));
		hpM.setFont(new Font("SERIF", Font.PLAIN, 15));
		hpM.setForeground(Color.WHITE);
		attackM = new JLabel("Attack ⚔: " + Integer.toString(m.attackDamage));
		attackM.setFont(new Font("SERIF", Font.PLAIN, 15));
		attackM.setForeground(Color.WHITE);
		attackSpeedM = new JLabel("Attack speed 🗡: " + Double.toString(h.attackSpeed));
		attackSpeedM.setFont(new Font("SERIF", Font.PLAIN, 15));
		attackSpeedM.setForeground(Color.WHITE);
		regM = new JLabel("Regeneration ➕: " + Integer.toString(m.hpRegen));
		regM.setFont(new Font("SERIF", Font.PLAIN, 15));
		regM.setForeground(Color.WHITE);
		levelM = new JLabel("Ablility Level 🌠: " + Integer.toString(m.abilityPower));
		levelM.setFont(new Font("SERIF", Font.PLAIN, 15));
		levelM.setForeground(Color.WHITE);
		speedM = new JLabel("Movement speed 🏃: " + Float.toString(m.movespeed));
		speedM.setFont(new Font("SERIF", Font.PLAIN, 15));
		speedM.setForeground(Color.WHITE);
		
		statsM.setLayout(new GridLayout(7,1));
		statsM.add(typeM);
		statsM.add(hpM);
		statsM.add(attackM);
		statsM.add(attackSpeedM);
		statsM.add(regM);
		statsM.add(levelM);
		statsM.add(speedM);
		statsM.setOpaque(false);
		
		//back Button
		backB = new JButton("Exit");
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
		back.setLayout(new GridLayout(2,1));
		back.add(backB);
		back.add(new JLabel(" "));
		back.setOpaque(false);

		
		//bild Hero
		heroImg = new JButton();
		heroImg.setOpaque(false);
		heroImg.setBorderPainted(false);
		heroImg.setFocusPainted(false);
		heroImg.setContentAreaFilled(false);
		heroImg.setBorder(BorderFactory.createEmptyBorder());
		try {
			heroBI = ImageIO.read(new File("res/hero/adventurer-idle-03.png"));
	   		heroI = heroBI.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		heroImg.setIcon(new ImageIcon(heroI));
		heroImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Constants.PLAYER = new Hero();
				FrameManager.currentScreen = FrameManager.Screen.Game;
				FrameManager.run();
			}
		});
		//bild mage
			mageImg = new JButton();
			mageImg.setOpaque(false);
			mageImg.setBorderPainted(false);
			mageImg.setFocusPainted(false);
			mageImg.setContentAreaFilled(false);
			mageImg.setBorder(BorderFactory.createEmptyBorder());
			try {
					mageBI = ImageIO.read(new File("res/monster/Necromancer/Individual Sprites/necromancer-idle-03.png"));
			   		mageI = mageBI.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			} catch (IOException e) {
					e.printStackTrace();
			}
			mageImg.setIcon(new ImageIcon(mageI));
			mageImg.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Constants.PLAYER = new Mage();
					FrameManager.currentScreen = FrameManager.Screen.Game;
					FrameManager.run();
				}
			});
			
			character.add(heroImg);
			character.add(mageImg);
			character.add(statsH);
			character.add(statsM);
		
		//anweisungen
		info = new JLabel(" Please Click on the Character you would like to play with");
		
		
		panel.setLayout(new BorderLayout());
		panel.add(back, BorderLayout.NORTH);
		panel.add(mid, BorderLayout.CENTER);
		updateUI();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(bgI, 0, 0, null);
		g.drawImage(statsI, Constants.SCREEN_X/4, mid.getY(), null);
	}
	public void draw(Graphics g)
	{
		g.setColor(Inventory.textColor);
		g.setFont(Inventory.font);
		g.drawString("Please click on the character you would like to play with", 40, 500);
	}
}
