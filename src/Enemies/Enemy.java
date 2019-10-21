package Enemies;

import java.awt.Color;
import java.awt.Graphics;

import Constants.Constants;
import Entity.Entity;
import Maps.Map;
import Player.Player;

public abstract class Enemy extends Entity{
	
	protected int originalX, originalY;
	
	public double visionRange;
	public double attackRange;
	protected double timer;
	protected double timerEnd;
	
	public boolean active = false;
		
	public Enemy(int xPos, int yPos) {
		super();
		this.hpRegen = 1;
		this.maxHP = Constants.random(30, 50);;
		this.currentHP = maxHP;
		visionRange = 300;
		this.attackRange = this.getHitWidth();
		this.attackDamage = Constants.random(9, 14);
		timer = 0;
		timerEnd = 100;
		movespeed = Constants.random(30, 45);
		this.xPosition = xPos;
		this.originalX = xPos;
		this.yPosition = yPos;
		this.originalY = yPos;
		this.attackSpeed = 1.4;
		this.updateAttackSpeed();
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
			int newx = originalX + Constants.random(50, 200) * (Math.random() < 0.5 ? -1 : 1);
			int newy = originalY + Constants.random(50, 150) * (Math.random() < 0.5 ? -1 : 1);
			this.moveTo(newx, newy);			
		}
	}

	// Looks if enemies is in range for attack, if yes, attack, otherwise move closer
	public void updateActive(float deltaTime, Map map, Player player){
		super.update(deltaTime, map);
		int offset = (int) (player.getHitHeight() / 4);
		if(this.state == Enemy.IDLE){
			if(Constants.getDistance(this.getHitCenterX(), this.getHitCenterY(), player.getHitCenterX(), player.getHitCenterY()) > this.attackRange * this.attackRange || ((player.getHitCenterX() > this.getHitCenterX() ^ this.direction == 1) && Math.abs(player.getHitCenterX()-this.getHitCenterX()) > 20)){
				this.moveDif((int)(player.getHitCenterX()-this.getHitCenterX()), (int)(player.getHitCenterY() - this.getHitCenterY()) + Constants.random(-offset, offset), true);
			} else {
				this.state = Enemy.ATTACK;
				this.currentAnimationDuration = 0;
				this.currentAnimation = this.getAttack();
			}
		}
		if(this.state == Enemy.MOVING){
			if(Constants.getDistance(this.xgoal, this.ygoal, player.getHitCenterX(), player.getHitCenterY()) > this.attackRange * this.attackRange){
				this.moveDif((int)(player.getHitCenterX()-this.getHitCenterX()), (int)(player.getHitCenterY() - this.getHitCenterY()) + Constants.random(-offset, offset),true);
			}
		}
		if (this.state == Enemy.ATTACK){
			if(this.currentAnimation.isFinished(this.currentAnimationDuration)){
				this.state = Enemy.IDLE;
				this.currentAnimationDuration = 0;
				this.currentAnimation = getIdle();
				if(this.hitEntity(player)){
					this.applyDamage(player, 1.0);
				}
			}
		}
	}
	
	public void draw(Graphics g, int xoffset, int yoffset) {
		if(direction < 0) {
			g.drawImage(this.currentAnimation.getCurrentImage(this.currentAnimationDuration), (int)xPosition + xoffset, (int) yPosition + yoffset, (int)this.width, (int)this.height, null);	
		} else {
			g.drawImage(this.currentAnimation.getCurrentImage(this.currentAnimationDuration), (int) (xPosition + xoffset + this.width), (int) yPosition + yoffset, (int)-this.width, (int)this.height, null);
		}
		if(this.active){
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.GREEN);
		}
		g.fillRect((int)(this.getHitX()+xoffset), (int)(this.getHitY() + this.getHitHeight() + 15 + yoffset), (int)(this.getHitWidth() * this.currentHP / this.maxHP), 5);
		g.setColor(Color.BLACK);
		g.drawRect((int)(this.getHitX()+xoffset), (int)(this.getHitY() + this.getHitHeight() + 15 + yoffset), (int)this.getHitWidth(), 5);

	}
}
