import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class Pause {
	
	private static Image pauseBackground;
	private static int XOFFSET = 0, YOFFSET = 0;
	
	private int width, height;
	public static Font cursive = new Font("Yu Gothic", Font.ITALIC, 26);
    public static Font font = new Font("TimesRoman", Font.PLAIN, 26);
    public static Font bigFont = new Font("TimesRoman", Font.PLAIN, 52);
    public static Color textColor = new Color(204, 204, 204);
	
	static{
        try {
            pauseBackground = ImageIO.read(new File("res/dsui/menu_only_bg.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
	public Pause(){
		
		width = (int)(pauseBackground.getWidth(null) * 1.5);
        height = (int)(pauseBackground.getHeight(null) * 1.5);
		
		
		
	}

    
    public void draw(Graphics g){
        g.translate(XOFFSET, YOFFSET);

        g.drawImage(pauseBackground, 0, 0, width, height, null);
        g.setColor(textColor);
        g.setFont(bigFont);
        g.drawString("Pause", 600, 150);
        g.setFont(font);
        g.drawRect(560,180,200, 50);
        g.drawRect(560,250,200, 50);
        g.drawRect(560,320,200, 50);
        g.drawString("Continue", 600, 210);
        g.drawString("Save Game", 600, 280);
        g.drawString("Back To Menu ", 590, 350);
        }
}
