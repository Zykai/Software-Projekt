package Maps;

import java.awt.Graphics;
import java.awt.List;
import java.util.Iterator;
import java.util.LinkedList;

import Enemies.Enemy;
import Player.Player;

public abstract class Map {

	public static int TILE_SIZE = 64;
	
	protected LinkedList<Enemy> enemyList;
	protected int xSize, ySize;
	protected boolean[][] solidGrid;
	
	public Map(){
		enemyList = new LinkedList<Enemy>();
	}
	public void draw(Graphics g, int xoffset, int yoffset) {
		for(Iterator<Enemy> i = enemyList.iterator(); i.hasNext();) {
			Enemy e = i.next();
			e.draw(g, xoffset, yoffset);
		}
	}
	
	public abstract void update(float deltaTime);
	
	public LinkedList<Enemy> getEnemyList(){
		return this.enemyList;
	}
	
	public boolean isCorrectPosition(double x, double y, double radius) {
		if(x-radius <0 || x+radius > xSize || y-radius < 0 || y+radius > ySize) {
			return false;
		}
		return true;
	}
	
}
