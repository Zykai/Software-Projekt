package Enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Entity.Entity;
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
		System.out.println("ned ");
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if(!moving) {
			Random rand = new Random();
			int newx = rand.nextInt(300) + 350;
			int newy = rand.nextInt(250) + 250;
			this.moveTo(newx, newy);
			
		}
	}
	
	public void draw(Graphics g, int xoffset, int yoffset) {
		g.setColor(enemyColor);
		g.fillOval((int)xPosition+xoffset, (int) (yPosition+yoffset), 40, 40);
	}
}
