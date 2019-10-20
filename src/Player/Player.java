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
import Maps.Map;

public abstract class Player extends Entity{

	private Color playerColor;
	public Inventory inventory;		
	

	public Player(){
		playerColor = new Color(1.0f, 0.5f, 0.2f);
		xPosition = Constants.SCREEN_X / 2 + 100;
		yPosition = Constants.SCREEN_Y / 2 + 200;
		height = 30;
		width = 30;
		this.currentHP = 60;
		this.maxHP = 100;
		inventory = new Inventory(Constants.safeName, this);
		this.attackSpeed = 1.5;
		this.updateAttackSpeed();
	}
	

	
	public void draw(Graphics g){
		inventory.draw(g, this);
	}

	public void draw(Graphics g, int xoffset, int yoffset){
		super.draw(g, xoffset, yoffset);
		g.setColor(Color.BLACK);
		int xPos = 800;
		int yPos = 950;
		g.fillRect(xPos, yPos, 400, 50);
		g.setColor(Color.RED);
		g.fillRect(xPos+10, yPos+10, (int)(380 * this.currentHP / this.maxHP), 30);
		inventory.draw(g, this);
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
