import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
 
import javax.swing.JButton;
//from   w ww  .j  a v a  2  s  .c  o m
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Font;
 
// https://docs.oracle.com/javase/tutorial/2d/geometry/primitives.html
 
public class Main extends JPanel {
 
 private int frameNumber = 1;
 static private float deltaTime;
 private Image image;
 
 public Main(){
 	 super();
 	 BufferedImage img = null;
 	 try {
     	 img = ImageIO.read(new File("res/held.png"));
 	 } catch (IOException e) {
     	 System.out.println(e.getMessage());
 	 }
 	 image = img.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
 }
 
 public void update(float deltaTime){
    
 }
 
 public void paint(Graphics g) {
   g.setColor (Color.red);
   g.fillRect(0,0,500,500);
 
    g.setColor (Color.green);
    g.fillRect(150, 150 + (int) (50 * Math.sin(System.currentTimeMillis() / 1000.0)),200, 50);
 
    g.drawImage(image, 0, 0,null);
 
    float fps = (float) (1000.0/deltaTime);
    g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
    g.drawString(String.format("%.2f", fps),30, 30);
    Toolkit.getDefaultToolkit().sync();
 }
 
 public static void main(String[] args) {
   JFrame frame = new JFrame();
   JPanel main = new Main();
   frame.add(main);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setBounds(20,20, 1000,750);
   frame.setLocationRelativeTo(null);
   frame.setVisible(true);
   long time = System.currentTimeMillis();
   frame.remove(main);
   
   frame.add(main);
    while(true){
        try {
            Thread.sleep(16);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
        long newTime = System.currentTimeMillis();
        deltaTime = (float) (newTime - time);
        time = newTime;
        frame.repaint();
    }
 }
}
 

