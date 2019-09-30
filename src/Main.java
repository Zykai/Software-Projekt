import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
//from   w ww  .j  a v a  2  s  .c  o m
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Maps.Darkness;
import Maps.Map;
import Maps.TestMap;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

import Player.Hero;
import Player.Player;
import Constants.Constants;
import DungeonGenerator.RoomTree;
import Enemies.Enemy;

import java.awt.Font;
 
// https://docs.oracle.com/javase/tutorial/2d/geometry/primitives.html
 
public class Main extends JPanel {
 
	
	
	private int frameNumber = 1;
	static private float deltaTime;
	private Image image;
	private Player player;
	private Map map;
 
	private int xMouse, yMouse;
	
	public Main(){
		super();
		setBackground(Color.BLUE);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("res/held.png"));
		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		image = img.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		player = new Hero();
		map = new Darkness();
		player.setX(map.getStartingX());
		player.setY(map.getStartingY());
		
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				player.moveDif(e.getX()-Constants.SCREEN_X/2, e.getY()-Constants.SCREEN_Y/2);	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			} 
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				xMouse = (int) (player.getX() + e.getX()- Constants.SCREEN_X/2);
				yMouse = (int) (player.getY() + e.getY()- Constants.SCREEN_Y/2);
			}
			
		});
		
		createKeyBinding("Q", new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		      player.qAbility(xMouse, yMouse, map);
		    }
		});
		createKeyBinding("W", new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		      player.wAbility(xMouse, yMouse, map);
		    }
		});
		createKeyBinding("E", new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		      player.eAbility(xMouse, yMouse, map);
		    }
		});
		createKeyBinding("R", new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		      player.rAbility(xMouse, yMouse, map);
		    }
		});
	    
	}
	
	private void createKeyBinding(String button, AbstractAction action) {
		InputMap iMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap aMap = this.getActionMap();
	    iMap.put(KeyStroke.getKeyStroke(button), button+"action");
	    aMap.put(button+"action", action);
	}
 
	public void updateGame(float deltaTime){
		player.update(deltaTime, map);
		map.update(deltaTime);
	}
 
	public void paint(Graphics g) {
		int xoffset = (int)(-player.getX()+Constants.SCREEN_X / 2);
		int yoffset = (int)(-player.getY()+Constants.SCREEN_Y / 2);
		// TODO: g.translate
		
		float fps = (float) (1000.0/deltaTime);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString(String.format("%.2f", fps),30, 30);
		map.draw(g, xoffset, yoffset);
		player.draw(g);
		g.setColor(new Color(0.0f, 1.0f, 1.0f));
		Toolkit.getDefaultToolkit().sync();
	}
 
	public static void main(String[] args) {		
		JFrame frame = new JFrame();
		Main main = new Main();
		//frame.add(main);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(20,20, Constants.SCREEN_X,Constants.SCREEN_Y);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		long time = System.currentTimeMillis();
		//frame.remove(main);
		
		frame.add(new Menu(frame, main));
		//frame.add(main);
		frame.validate();
		while(true){
			try {
				Thread.sleep(16);
			}
			catch (Exception e) {
				//TODO: handle exception
			}
		long newTime = System.currentTimeMillis();
		deltaTime = (float) (newTime - time);
		time = newTime;
		main.updateGame(deltaTime);
		frame.repaint();
		}
	}
}
 

