package Enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Entity.Entity;
import Maps.Map;
import Player.Player;

public class Enemy extends Entity{
	
	private Color enemyColor;
	
	public Enemy() {
		movespeed = 0.35f;
		enemyColor = new Color(10, 255, 255);
	}
	
	public Enemy(int xPos, int yPos) {
		movespeed = 0.35f;
		enemyColor = new Color(10, 100, 50);
		this.xPosition = xPos;
		this.yPosition = yPos;
	}
	
	public Enemy(int xPos, int yPos, int type) {
		movespeed = 0.35f;
		enemyColor = new Color(type * 30, type * 30, 0);
		this.xPosition = xPos;
		this.yPosition = yPos;
	}

	@Override
	public void update(float deltaTime, Map map) {
		super.update(deltaTime, map);
		if(!moving) {
			Random rand = new Random();
			int newx = rand.nextInt(300) + 350;
			int newy = rand.nextInt(250) + 250;
			this.moveTo(newx, newy);
			
		}
	}
	
	public void draw(Graphics g, int xoffset, int yoffset) {
		g.setColor(enemyColor);
		g.fillOval((int)xPosition * Map.TILE_SIZE + xoffset, (int) yPosition * Map.TILE_SIZE + yoffset, 40, 40);
	}
}
