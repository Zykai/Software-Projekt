package Maps;

import java.awt.Graphics;
import java.awt.List;

public abstract class Map {

	private List enemyList;
	private int xSize, ySize;
	public Map(){
		
	}
	public abstract void draw(Graphics g);
	
	public abstract void update(float deltaTime);
	
	public List getEnemyList(){
		return this.enemyList;
	}
	
	
}
