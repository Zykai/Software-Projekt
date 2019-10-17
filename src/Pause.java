import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Player.Player;

public class Pause {
	
	private static Image pauseBackground;
	private JPanel pauseP;
	private JLabel pauseText;
	private JButton backToMenu;
	private JButton back;
	private JButton exitGame;
	
	static{
        try {
            //pauseBackground = ImageIO.read(new File("res/dsui/menu_only_bg.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
	public Pause(){
		pauseP = new JPanel();
		pauseText = new JLabel("Pause");
		backToMenu = new JButton("Back to Menu");
		back = new JButton("Back");
		exitGame = new JButton("Exit Game");
		
		backToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	FrameManager.currentScreen = FrameManager.Screen.Menu;
				FrameManager.run();
            }
        });
		
		exitGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		pauseP.add(pauseText);
		pauseP.add(backToMenu);
		pauseP.add(back);
		pauseP.add(exitGame);
		
		
	}

    
    public void draw(Graphics g){
    	g.drawRect(0,0,100, 100);
        //g.translate(XOFFSET, YOFFSET);

        //g.drawImage(pauseBackground, 0, 0, width, height, null);

        }

}
