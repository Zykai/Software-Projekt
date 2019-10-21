package Player;

import java.awt.Graphics;
import java.util.LinkedList;

import Constants.Constants;
import Enemies.Enemy;
import Entity.Animation;
import Entity.Entity;
import Maps.Map;

public class Hero extends Player {
	
	private static Animation PLAYER_IDLE[];
	private static Animation PLAYER_RUN;
	private static Animation PLAYER_ATTACK[];
	private static Animation PLAYER_DEATH;

	static {
		PLAYER_IDLE = new Animation[2];
		PLAYER_IDLE[0] = new Animation("res/hero/adventurer-idle-0", 4, 1000);
		PLAYER_IDLE[1] = new Animation("res/hero/adventurer-idle-2-0", 4, 1000);
		PLAYER_RUN = new Animation("res/hero/adventurer-run-0", 5, 750);
		PLAYER_ATTACK = new Animation[3];
		PLAYER_ATTACK[0] = new Animation("res/hero/adventurer-attack1-0", 5, 500);
		PLAYER_ATTACK[1] = new Animation("res/hero/adventurer-attack2-0", 6, 500);
		PLAYER_ATTACK[2] = new Animation("res/hero/adventurer-attack3-0", 6, 500);
		PLAYER_DEATH = new Animation("res/hero/adventurer-die-0", 7, 700);
	}

	private int attackIndex;

	public Hero() {
		super();
		attackIndex = 0;
		this.height = 100;
		this.width = 80;
		this.movespeed = 45f;
		this.attackDamage = 15;
		this.abilityPower = 0;
		this.critChance = 20;
		this.hpRegen = 2;
		this.maxHP = 200;
		this.currentHP = 100;
		this.currentAnimation = PLAYER_IDLE[0];
		this.currentAnimationDuration = 0.0;
		this.currentXP = 6;
		this.maxXP = 10;
		this.lifeSteal = 10;
		this.attackSpeed = 2.0;
		this.updateAttackSpeed();
	}
	
	public void updateAttackSpeed(){
		PLAYER_ATTACK[0].setDuration(1000 / this.attackSpeed);
		PLAYER_ATTACK[1].setDuration(1000 / this.attackSpeed);
		PLAYER_ATTACK[1].setDuration(1000 / this.attackSpeed);
	}

	public void update(float deltaTime, Map map) {
		super.update(deltaTime, map);
		if(this.state == Hero.ATTACK) {
			if(this.currentAnimation.isFinished(this.currentAnimationDuration)) {
				this.currentAnimationDuration = 0.0;
				this.currentAnimation = getIdle();
				this.state = Hero.IDLE;
				LinkedList<Enemy> enemies = map.getEnemyList();
				for(int i = 0; i < enemies.size(); i++){
					Enemy e = enemies.get(i);
					if(this.hitEntity(e)) {
						this.applyDamage(e, 1.0);
					}
				}
			}
		}
	}
	
	public void applyDamage(Entity e, double mul){
		double pureDamage = ((this.attackDamage + this.abilityPower / 2) * mul * (Constants.random(0,100) > this.critChance ? 1.5 : 1.0));
		int damage = (int) pureDamage * 100 / (100 + this.armor);
		int heal = this.lifeSteal * damage / 100;
		this.heal(heal);
		e.currentHP -= damage;
		e.currentHP = Math.max(0, e.currentHP);
	}

	@Override
	public void qAbility(int xMouse, int yMouse, Map map) {
		if(this.state != Hero.ATTACK) {
			this.direction = xMouse < this.getHitCenterX() ? -1 : 1;
			this.state = Hero.ATTACK;
			this.currentAnimationDuration = 0.0;
			this.currentAnimation = this.getAttack();
			this.currentAnimationDuration = 0.0;
		}
	}
	
	@Override
	public void wAbility(int xMouse, int yMouse, Map map) {
		this.xPosition = map.getStartingX();
		this.yPosition = map.getStartingY();
	}
	
	@Override
	protected Animation getIdle() {
		return PLAYER_IDLE[(int)Math.round(Math.random())];
	}
	
	@Override
	protected Animation getMove() {
		return PLAYER_RUN;
	}

	@Override
	protected Animation getAttack(){
		return PLAYER_ATTACK[attackIndex++ % 3];
	}

	@Override
	protected Animation getDie(){
		return PLAYER_DEATH;
	}

	@Override
	public double getHitX(){
		return xPosition+15;
	}

	@Override
	public double getHitY(){
		return yPosition + 15;
	}

	@Override
	public double getHitWidth(){
		return width-25;
	}

	@Override
	public double getHitHeight(){
		return height-15;
	}
	
	public void draw(Graphics g, int xoffset, int yoffset) {
		if(direction > 0) {
			g.drawImage(this.currentAnimation.getCurrentImage(this.currentAnimationDuration), (int)xPosition + xoffset, (int) yPosition + yoffset, (int)this.width, (int)this.height, null);	
		} else {
			g.drawImage(this.currentAnimation.getCurrentImage(this.currentAnimationDuration), (int) (xPosition + xoffset + this.width), (int) yPosition + yoffset, (int)-this.width, (int)this.height, null);
		}
		super.draw(g, xoffset, yoffset);
	}
}
