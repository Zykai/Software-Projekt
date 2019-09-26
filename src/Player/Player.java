package Player;

import java.awt.Color;
import java.awt.Graphics;
import Entity.Entity;;

public class Player extends Entity{

	private int level;
	
	private Color playerColor;
	
	
	
	public Player(){
		playerColor = new Color(1.0f, 0.5f, 0.2f);
		height = 30;
		width = 30;
	}
	
	
	
	public void draw(Graphics g){
		g.setColor(playerColor);
		g.fillOval((int)xPosition, (int)yPosition, 40, 40);
	}
	
}