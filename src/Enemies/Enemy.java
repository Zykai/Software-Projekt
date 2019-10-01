package Enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Constants.Constants;
import Entity.Entity;
import Maps.Map;
import Player.Player;

public abstract class Enemy extends Entity{
	
	private Color enemyColor;
	protected int originalX, originalY;
	
	protected double timer;
	protected double timerEnd;
	
	public Enemy(int xPos, int yPos) {
		super();
		timer = 0;
		timerEnd = Constants.random(0, 5000);
		movespeed = 0.35f;
		enemyColor = new Color(10, 100, 50);
		this.xPosition = xPos;
		this.originalX = xPos;
		this.yPosition = yPos;
		this.originalY = yPos;
	}
	
	@Override
	public void update(float deltaTime, Map map) {
		super.update(deltaTime, map);	
		if(state != Enemy.MOVING) {
			timer += deltaTime;
		}
		if(timer > timerEnd && state == Enemy.IDLE) {
			timer = 0;
			timerEnd = Constants.random(2000, 5000);
			int newx = originalX + Constants.random(-200, 200);
			int newy = originalY + Constants.random(-200, 200);
			this.moveTo(newx, newy);			
		}
	}
	
	public void draw(Graphics g, int xoffset, int yoffset) {
		if(direction < 0) {
			g.drawImage(this.currentAnimation.getCurrentImage(this.currentAnimationDuration), (int)xPosition + xoffset, (int) yPosition + yoffset, (int)this.width, (int)this.height, null);	
		} else {
			g.drawImage(this.currentAnimation.getCurrentImage(this.currentAnimationDuration), (int) (xPosition + xoffset + this.width), (int) yPosition + yoffset, (int)-this.width, (int)this.height, null);
		}
	}
}
