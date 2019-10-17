import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import Constants.Constants;

public class Options extends JPanel{
	
	private Menu menu;
	private JPanel panelBg, panel;
	private Image bgI, backI; //bgI = background Image
	private BufferedImage bgIB, backBI; // bgIB = background Image Buffered
	private JLabel tbd;
	private int BUTTON_X, BUTTON_Y;
	private JButton backM;
	
	public Options() {
		final Options self = this;
		panel = new JPanel();
		panelBg = new JPanel();
		tbd = new JLabel("coming soon");
		add(panelBg);
		panelBg.add(panel);
		panel.add(tbd);
		
		//Hintergrund
		try {
			bgIB = ImageIO.read(new File("res/background/menu.png"));
	   		bgI = bgIB.getScaledInstance(Constants.SCREEN_X, Constants.SCREEN_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
			}
		panelBg.setOpaque(false);
		panel.setOpaque(false);
		//back Button
		BUTTON_X = 370/2;
		BUTTON_Y = 130/2;
		backM = new JButton();
		backM.setOpaque(false);
		backM.setBorder(BorderFactory.createEmptyBorder());
		backM.setBounds((Constants.SCREEN_X - BUTTON_X) / 2, (Constants.SCREEN_Y - BUTTON_Y)/ 2, BUTTON_X, BUTTON_Y);
		try {
			backBI = ImageIO.read(new File("res/exit.png"));
	   		backI = backBI.getScaledInstance(BUTTON_X, BUTTON_Y, Image.SCALE_SMOOTH);
		} catch (IOException e) {
				e.printStackTrace();
			}
		backM.setIcon(new ImageIcon(backI));
		backM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	FrameManager.currentScreen = FrameManager.Screen.Menu;
				FrameManager.run();
            }
        });
		panel.add(backM);
		panel.add(tbd);
		FrameManager.frame.validate();
	}
	public void paintComponent(Graphics g)
	{
		   
		super.paintComponent(g);
		g.drawImage(bgI, 0, 0, null);
	}
	
}



