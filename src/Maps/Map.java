package Maps;

import java.awt.Graphics;
import java.util.LinkedList;

import Enemies.Enemy;
import Player.Player;

public abstract class Map {

	public static int TILE_SIZE = 32;
	
	protected LinkedList<Enemy> activeEnemies;
	protected LinkedList<Enemy> dyingEnemies;
	protected int xSize, ySize;
	protected boolean[][] solidGrid;
	
	public Map(){
		activeEnemies = new LinkedList<Enemy>();
		dyingEnemies = new LinkedList<Enemy>();
	}
	public void draw(Graphics g, int xoffset, int yoffset) {
		for(int i = 0; i < activeEnemies.size(); i++){
			activeEnemies.get(i).draw(g, xoffset, yoffset);
		}
	}
	
	public abstract void update(float deltaTime, Player p);
	
	public LinkedList<Enemy> getEnemyList(){
		return this.activeEnemies;
	}
	
	public abstract double getStartingX();
	
	public abstract double getStartingY();
	
	public boolean isCorrectPosition(double x, double y, double radius) {
		if(x-radius <0 || x+radius > xSize || y-radius < 0 || y+radius > ySize) {
			return false;
		}
		return true;
	}
	
	public boolean isCorrectPosition(double x, double y, double width, double height) {
		return isCorrectPosition(x,y,width);
	}
}
