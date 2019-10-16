import java.awt.BorderLayout;
import java.awt.Container;
<<<<<<< Updated upstream
=======
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
=======
import Constants.Constants;
import Player.Hero;
import Player.Player;
import Inventory.Inventory;

>>>>>>> Stashed changes
public class Creator extends JPanel{
	private Main main;
	private Menu menu;
	
	private JFrame frame;
<<<<<<< Updated upstream
	private JPanel panel;
	private JButton backM;
	private JButton standart;
	private JButton play;
=======
	private JPanel panel, panelBg, top, mid, bottom, character, statsM, statsH;
	private JButton exit, choseH, choseM, heroImg, mageImg;
	private JLabel hero, mage;
	private Image heroI, backI, bgI; //bgI = background Image
	private BufferedImage heroBI, backBI, bgIB;
	private JLabel hp, level, speed, attack;
	private int BUTTON_X, BUTTON_Y;
	private int chosenPlayer;
>>>>>>> Stashed changes
	
	public Creator(final JFrame frame, final Main main, final Menu menu) {
		
		this.main = main;
		this.menu = menu;
		final Creator self = this;
		panelBg = new JPanel();
		panel = new JPanel();
<<<<<<< Updated upstream
		backM = new JButton("Back to Menu");
		standart = new JButton("Standartcharakter");
		play = new JButton("Confirm character and play");
		add(panel);
		panel.setLayout(new BorderLayout());
		panel.add(backM, BorderLayout.PAGE_START);
		panel.add(play, BorderLayout.PAGE_END);
		
		frame.setVisible(true);
=======
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
		
		//Hintergrund
		try {
			bgIB = ImageIO.read(new File("res/background/char.png"));
	   		bgI = bgIB.getScaledInstance(Constants.SCREEN_X, Constants.SCREEN_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
		}
		panelBg.setOpaque(true);
		panel.setOpaque(false);
		top.setOpaque(false);
		mid.setOpaque(false);
		bottom.setOpaque(false);
		character.setOpaque(false);
		statsM.setOpaque(false);
		statsH.setOpaque(false);
		
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
>>>>>>> Stashed changes
		
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
<<<<<<< Updated upstream
				frame.remove(self);
				frame.add(self.menu);
				frame.validate();
=======
				FrameManager.currentScreen = FrameManager.Screen.Game;
				FrameManager.run();
>>>>>>> Stashed changes
			}
		});
		mid.add(heroImg);
		
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
				frame.remove(self);
				frame.add(self.main);
				frame.validate();
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
			});
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
	public void draw(Graphics g){
		
	}
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
