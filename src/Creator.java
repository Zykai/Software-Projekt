import java.awt.BorderLayout;
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
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Player.Hero;
import Player.Player;

public class Creator extends JPanel{
	private Main main;
	private Menu menu;
	
	private JFrame frame;
	private JPanel panel;
	private JButton backM;
	private JButton play;
	private JLabel hero;
	private Image hero1;
	private BufferedImage hero1B;
	private JPanel stats;
	private JLabel hp, level, speed, attack;
	public Creator(final JFrame frame, final Main main, final Menu menu) {
		
		this.main = main;
		this.menu = menu;
		final Creator self = this;
		panel = new JPanel();
		backM = new JButton("Back to Menu");
		stats = new JPanel();
		play = new JButton("Confirm character and play");
		add(panel);
		panel.setLayout(new BorderLayout());
		panel.add(backM, BorderLayout.NORTH);
		panel.add(play, BorderLayout.SOUTH);
		panel.add(stats, BorderLayout.EAST);
		
		Player p = new Hero();
		hp = new JLabel("HP: " + Integer.toString(p.getHp()));
		level = new JLabel("Level: " + Integer.toString(p.getLevel()));
		speed = new JLabel("tbd");
		attack = new JLabel("tbd");
		stats.setLayout(new GridLayout(5,1));
		stats.add(new JLabel("Stats:"));
		stats.add(hp);
		stats.add(level);
		stats.add(speed);
		stats.add(attack);
		try {
			hero1B = ImageIO.read(new File("res/hero/adventurer-idle-03.png"));
			hero1 = hero1B.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		hero = new JLabel(new ImageIcon(hero1));
		panel.add(hero, BorderLayout.CENTER);
		
		frame.setVisible(true);
		
		backM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(self);
				frame.add(self.menu);
				frame.validate();
			}
		});
		
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(self);
				frame.add(self.main);
				frame.validate();
			}
		});
	}
}