package Maps;

import java.awt.Graphics;
import java.awt.List;
import java.util.LinkedList;

import Enemies.Enemy;
import Player.Player;

public abstract class Map {

	protected LinkedList<Enemy> enemyList;
	protected int xSize, ySize;
	public Map(){
		
	}
	public abstract void draw(Graphics g, int xoffset, int yoffset);
	
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
