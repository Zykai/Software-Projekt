package Maps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import Enemies.Enemy;

public class TestMap extends Map {

	private Color mapColor;
	
	public TestMap() {
		mapColor = new Color(0.5f, 0.5f, 0.5f);
		this.enemyList = new LinkedList<Enemy>();
		enemyList.add(new Enemy());
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(mapColor);
		g.fillRect(0, 0, 1000, 750);
		for(Iterator<Enemy> i = enemyList.iterator(); i.hasNext();) {
			Enemy e = i.next();
			e.draw(g);
		}
	}

	@Override
	public void update(float deltaTime) {
		for(Iterator<Enemy> i = enemyList.iterator(); i.hasNext();) {
			Enemy e = i.next();
			e.update(deltaTime);
		}
	}

}
