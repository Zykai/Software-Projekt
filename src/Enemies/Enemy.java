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
	
	public void draw(Graphics g, Player p) {
		g.setColor(enemyColor);
		g.fillOval((int)(xPosition-p.getX()), (int)(yPosition-p.getY()), 40, 40);
	}
}
