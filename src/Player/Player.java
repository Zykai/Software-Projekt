package Player;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.ListIterator;

import Constants.Constants;
import Enemies.Enemy;
import Entity.Entity;
import Maps.Map;;

public class Player extends Entity{

	private int level;
	private int hp; //warum in entity und nicht hier? Wenn wir das in Entity machen, sollte auch das Level rüber.
	private Color playerColor;
			
	
	
	public Player(){
		playerColor = new Color(1.0f, 0.5f, 0.2f);
		xPosition = Constants.SCREEN_X / 2 + 100;
		yPosition = Constants.SCREEN_Y / 2 + 200;
		height = 30;
		width = 30;
	}
	

	
	public void draw(Graphics g){
		g.setColor(getPlayerColor());
		g.fillOval((int)(Constants.SCREEN_X/2-width/2), (int)(Constants.SCREEN_Y/2-height/2), (int)width, (int)height);
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
	
	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}
	
	public void qAbility(int xMouse, int yMouse, Map map) {
		map.getEnemyList().add(new Enemy(xMouse, yMouse));
	}
	
	public void wAbility(int xMouse, int yMouse, Map map) {
		for(int i = 0; i < 10; i++) {
			map.getEnemyList().add(new Enemy(xMouse, yMouse));
		}
	}
	
	public void eAbility(int xMouse, int yMouse, Map map) {
		LinkedList<Enemy> enemyList = map.getEnemyList();
		ListIterator<Enemy> iter = enemyList.listIterator();
		while(iter.hasNext()){
		    Enemy current = iter.next();
		    double xDistance = current.getX() - xMouse;
		    double yDistance = current.getY() - yMouse;
		    if (xDistance * xDistance + yDistance * yDistance < 1600) {
		    	iter.remove();
		    }
		}
	}
	
	public void rAbility(int xMouse, int yMouse, Map map) {
	}
	
}
