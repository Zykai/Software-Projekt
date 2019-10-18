import java.awt.Graphics;
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

public class Options extends JPanel{
	
	private Menu menu;
	private JPanel panelBg, panel;
	private Image bgI, backI; //bgI = background Image
	private BufferedImage bgIB, backBI; // bgIB = background Image Buffered
	private JLabel tbd;
	private int BACK_BUTTON_X, BACK_BUTTON_Y;
	private JButton backM;
	
	public Options() {
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
		BACK_BUTTON_X = 370/2;
		BACK_BUTTON_Y = 130/2;
		backM = new JButton();
		backM.setOpaque(false);
		backM.setBorder(BorderFactory.createEmptyBorder());
		backM.setBounds((Constants.SCREEN_X - BACK_BUTTON_X) / 2, (Constants.SCREEN_Y - BACK_BUTTON_Y)/ 2, BACK_BUTTON_X, BACK_BUTTON_Y);
		try {
			backBI = ImageIO.read(new File("res/buttons/exit.png"));
	   		backI = backBI.getScaledInstance(BACK_BUTTON_X, BACK_BUTTON_Y, Image.SCALE_SMOOTH);
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



