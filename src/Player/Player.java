package Player;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.ListIterator;

import Constants.Constants;
import Enemies.Enemy;
import Enemies.Imp;
import Entity.Entity;
import Inventory.Inventory;
import Maps.Map;;

public abstract class Player extends Entity{

	private static int level;
	private static int hp; //warum in entity und nicht hier? Wenn wir das in Entity machen, sollte auch das Level r�ber.
	private Color playerColor;
	public Inventory inventory;		
	

	public Player(){
		playerColor = new Color(1.0f, 0.5f, 0.2f);
		xPosition = Constants.SCREEN_X / 2 + 100;
		yPosition = Constants.SCREEN_Y / 2 + 200;
		height = 30;
		width = 30;
		inventory = new Inventory();
	}
	

	
	public void draw(Graphics g){
		inventory.draw(g);
	}

	public void draw(Graphics g, int xoffset, int yoffset){
		g.setColor(getPlayerColor());
		g.fillRect((int) this.xPosition + xoffset, (int) this.yPosition + yoffset, (int)this.width, (int)this.height);
	}
	
	public static int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public static int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		Player.level = level;
	}

	public Color getPlayerColor() {
		return playerColor;
	}
	
	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}
	
	public void qAbility(int xMouse, int yMouse, Map map) {
		map.getEnemyList().add(new Imp(xMouse, yMouse));
	}
	
	public void wAbility(int xMouse, int yMouse, Map map) {
		for(int i = 0; i < 10; i++) {
			map.getEnemyList().add(new Imp(xMouse, yMouse));
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
