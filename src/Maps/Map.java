package Maps;

import java.awt.Graphics;
import java.awt.List;
import java.util.LinkedList;

import Enemies.Enemy;

public abstract class Map {

	protected LinkedList<Enemy> enemyList;
	private int xSize, ySize;
	public Map(){
		
	}
	public abstract void draw(Graphics g);
	
	public abstract void update(float deltaTime);
	
	public LinkedList<Enemy> getEnemyList(){
		return this.enemyList;
	}
	
	
}
