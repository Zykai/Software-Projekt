package Player;
import java.awt.Color;
import java.awt.Graphics;

import Constants.Constants;
import Entity.Entity;;

public class Player extends Entity{

	private int level;
	private int hp; //warum in entity und nicht hier? Wenn wir das in Entity machen, sollte auch das Level rüber.
	private Color playerColor;
	
	
	
	public Player(){
		playerColor = new Color(1.0f, 0.5f, 0.2f);
		xPosition = 480;
		yPosition = 335;
		height = 30;
		width = 30;
	}
	

	
	public void draw(Graphics g){
		g.setColor(getPlayerColor());
		g.fillOval((int)(Constants.SCREEN_X/2-width), (int)(Constants.SCREEN_Y/2-height), 40, 40);
	}



	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	public double getX() {
		return this.xPosition;
	}
	
	public double getY() {
		return this.yPosition;
	}
	
	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}
	
}
