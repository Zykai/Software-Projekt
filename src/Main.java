import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
//from   w ww  .j  a v a  2  s  .c  o m
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Constants.Constants;
import Maps.Darkness;
import Maps.Map;
import Player.Mage;
import Player.Player;

// https://docs.oracle.com/javase/tutorial/2d/geometry/primitives.html

public class Main extends JPanel {

	private int frameNumber = 1;
	public float deltaTime;
	private Image image;
	private Player player;
	private Map map;
	private Pause pause;
	private boolean isPaused;

	private int xMouse, yMouse;

	public Main() {
		super();
		setBackground(Color.BLUE);		
		//player = getCharacter();
		player = new Mage();
		map = new Darkness();
		pause = new Pause();
		player.setX(map.getStartingX());
		player.setY(map.getStartingY());

		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isPaused) {
					if(e.getX() >= 560 && e.getX() <= 760 && e.getY() >= 180 && e.getY() <= 230){// Pause continue
						isPaused = !isPaused;
					}
					if(e.getX() >= 560 && e.getX() <= 760 && e.getY() >= 250 && e.getY() <= 300){// pause exit game
						System.exit(0);
					}
					if(e.getX() >= 560 && e.getX() <= 760 && e.getY() >= 320 && e.getY() <= 370){//pause back to menu
						FrameManager.currentScreen = FrameManager.Screen.Menu;
						FrameManager.run();
					}
				}
					
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (player.inventory.isVisible()) {
					player.inventory.startDrag(e.getX(), e.getY());
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (player.inventory.isVisible()) {
					player.inventory.endDrag(e.getX(), e.getY(), player);
				} else {
					player.moveDif(e.getX() - Constants.SCREEN_X / 2, e.getY() - Constants.SCREEN_Y / 2, true);
				}
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
				if (player.inventory.isVisible()) {
					player.inventory.continueDrag(e.getX(), e.getY());
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if (player.inventory.isVisible()) {
					player.inventory.hover(e.getX(), e.getY());
				}
				xMouse = (int) (player.getX() + e.getX() - Constants.SCREEN_X / 2 + player.getWidth()/2);
				yMouse = (int) (player.getY() + e.getY() - Constants.SCREEN_Y / 2 + player.getHeight()/2);
			}

		});
		this.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				player.inventory.scroll(e.getUnitsToScroll());
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
		createKeyBinding("I", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				player.inventory.setInventory();
			}
		});
		
		createKeyBinding("P", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isPaused = !isPaused;
			}
		});
		createKeyBinding("K", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				player.inventory.saveInventory("test");
			}
		});
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false),
				KeyEvent.VK_ESCAPE + "Pressed");
		this.getActionMap().put(KeyEvent.VK_ESCAPE + "Pressed", escapeAction());

	}

	private javax.swing.Action escapeAction() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// was passieren soll wenn escape gedrückt wird
				/**
				 * Frame.remove(Menu.playScreenP); //System.out.println("asd");
				 * Frame.add(Menu.startScreenP); validate();
				 **/
				FrameManager.currentScreen = FrameManager.Screen.Menu;
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				FrameManager.run();
			}
		};
	}

	private void createKeyBinding(String button, AbstractAction action) {
		InputMap iMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap aMap = this.getActionMap();
		iMap.put(KeyStroke.getKeyStroke(button), button + "action");
		aMap.put(button + "action", action);
	}
	
	public void updateGame(float deltaTime) {
		if(!isPaused){
			// update Spielerposition, animationen
			player.update(deltaTime, map);
			// update enemies etc...
			map.update(deltaTime, player);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//System.out.println("Pain Component");
	}

	public void paint(Graphics g) {
		//System.out.println("Repaint");
		super.paint(g);
		int xoffset = (int) (-player.getX() + Constants.SCREEN_X / 2 - player.getWidth() / 2);
		int yoffset = (int) (-player.getY() + Constants.SCREEN_Y / 2 - player.getHeight() / 2);
		// TODO: g.translate

		float fps = (float) (1000.0 / deltaTime);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		map.draw(g, xoffset, yoffset);
		player.draw(g, xoffset, yoffset);
		g.setColor(new Color(0.0f, 1.0f, 1.0f));
		g.drawString(String.format("%.2f", fps), 30, 30);
		if(isPaused){
			pause.draw(g);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		//Main main = new Main();
		// frame.add(main);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(20, 20, Constants.SCREEN_X, Constants.SCREEN_Y);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		FrameManager.frame = frame;
		FrameManager.currentScreen = FrameManager.Screen.Menu;
		FrameManager.run();
	}
	/*public Player getCharacter()
	{
		return Creator.getPlayer();
	}
	public void setCharacter(Player p)
	{
		player = p;
	}*/
}
 

