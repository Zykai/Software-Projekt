package Enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import Constants.Constants;
import Entity.Entity;
import Maps.Map;
import Player.Player;

public abstract class Enemy extends Entity{
	
	private Color enemyColor;
	protected int originalX, originalY;
	
	public double visionRange;
	public double attackRange;
	protected double timer;
	protected double timerEnd;
	
	public boolean active = false;
	
	private static Color backgroundColor = new Color(0.2f, 0.22f, 0.35f, 0.5f);
	
	public Enemy(int xPos, int yPos) {
		super();
		visionRange = 300;
		this.attackRange = 100;
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

	// Looks if enemies is in range for attack, if yes, attack, otherwise move closer
	public void updateActive(float deltaTime, Map map, Player player){
		super.update(deltaTime, map);
		if(this.state == Enemy.IDLE){
			if(Constants.getDistance(this.getCenterX(), this.getCenterY(), player.getCenterX(), player.getCenterY()) > this.attackRange * this.attackRange || ((player.getCenterX() > this.getCenterX() ^ this.direction == 1) && Math.abs(player.getCenterX()-this.getCenterX()) > 20)){
				this.moveDif((int)(player.getCenterX()-this.getCenterX()), (int)(player.getCenterY() - this.getCenterY()));
			} else {
				this.state = Enemy.ATTACK;
				this.currentAnimationDuration = 0;
				this.currentAnimation = this.getAttack();
			}
		}
		if(this.state == Enemy.MOVING){
			if(Constants.getDistance(this.xgoal, this.ygoal, player.getCenterX(), player.getCenterY()) > this.attackRange * this.attackRange){
				this.moveDif((int)(player.getCenterX()-this.getCenterX()), (int)(player.getCenterY() - this.getCenterY()));
			}
		}
		if (this.state == Enemy.ATTACK){
			if(this.currentAnimation.isFinished(this.currentAnimationDuration)){
				this.state = Enemy.IDLE;
				this.currentAnimationDuration = 0;
				this.currentAnimation = getIdle();
			}
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
