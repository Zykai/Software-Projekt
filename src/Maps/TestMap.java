package Maps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import Enemies.Enemy;
import Player.Player;
import Constants.Constants;

public class TestMap extends Map {

	private Color mapColor;
	private Image img;
	
	public TestMap() {
		super();
		this.xSize = Constants.SCREEN_X;
		this.ySize = Constants.SCREEN_Y;
		mapColor = new Color(0.5f, 0.5f, 0.5f);
		enemyList.add(new Enemy());
	
		try {
			img = ImageIO.read(new File("res/test_dungeon.jpg"));
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void draw(Graphics g, int xoffset, int yoffset) {
		//g.setColor(mapColor);
		//g.fillRect(0, 0, 1000, 750);
		g.drawImage(img, xoffset, yoffset, Constants.SCREEN_X, Constants.SCREEN_Y, null);
		super.draw(g, xoffset, yoffset);
	}

	@Override
	public void update(float deltaTime) {
		for(Iterator<Enemy> i = enemyList.iterator(); i.hasNext();) {
			Enemy e = i.next();
			e.update(deltaTime, this);
		}
	}

}
