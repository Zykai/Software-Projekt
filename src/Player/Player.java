package Player;

import java.awt.Graphics;

public class Player {

	private int hp;
	private int level;
	
	private double xPosition;
	private double yPosition;
	
	double xdirection;
	double ydirection;
	
	public void update(float deltaTime){
		xPosition += xdirection;
		yPosition += ydirection;
	}
	
	public void draw(Graphics g){
		g.drawOval((int)xPosition, (int)yPosition, 40, 40);
	}
	public void moveTo(int x, int y){
		double xdif = x - xPosition;
		System.out.println("XDIF: " + xdif);
		double ydif = y - yPosition;
		System.out.println("YDIF: " + ydif);
		//xPosition = x;
		//yPosition = y;
		double magnitude = Math.sqrt(xdif* xdif + ydif * ydif);
		System.out.println("M: " + magnitude);
		xdirection = xdif / magnitude;
		ydirection = ydif / magnitude;
		System.out.println(xdirection);
		System.out.println(ydirection);

	}
}