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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Creator extends JPanel{
	private Main main;
	private Menu menu;
	
	private JFrame frame;
	private JPanel panel;
	private JButton backM;
	private JButton standart;
	private JButton play;
	
	public Creator(final JFrame frame, final Main main, final Menu menu) {
		
		this.main = main;
		this.menu = menu;
		final Creator self = this;
		panel = new JPanel();
		backM = new JButton("Back to Menu");
		standart = new JButton("Standartcharakter");
		play = new JButton("Confirm character and play");
		add(panel);
		panel.setLayout(new BorderLayout());
		panel.add(backM, BorderLayout.PAGE_START);
		panel.add(play, BorderLayout.PAGE_END);
		
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