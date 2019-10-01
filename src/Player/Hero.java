package Player;

import java.awt.Color;
import java.awt.Graphics;

import Constants.Constants;
import Entity.Animation;
import Maps.Map;

public class Hero extends Player {

	private static final int ATTACKING = 3;
	
	private static Animation PLAYER_IDLE[];
	private static Animation PLAYER_RUN;
	static {
		PLAYER_IDLE = new Animation[2];
		PLAYER_IDLE[0] = new Animation("res/hero/adventurer-idle-0", 4, 1000);
		PLAYER_IDLE[1] = new Animation("res/hero/adventurer-idle-2-0", 4, 1000);
		PLAYER_RUN = new Animation("res/hero/adventurer-run-0", 5, 750);
	}
	private Animation[] attack;
	private boolean attacking;
	
	public Hero() {
		super();
		attacking = false;
		this.height = 100;
		this.width = 80;
		this.movespeed = 0.45f;
		attack = new Animation[3];
		attack[0] = new Animation("res/hero/adventurer-attack1-0", 	5, 1000);
		attack[1] = new Animation("res/hero/adventurer-attack2-0", 	6, 1000);
		attack[2] = new Animation("res/hero/adventurer-attack3-0", 	6, 1000);
		this.currentAnimation = PLAYER_IDLE[0];
		this.currentAnimationDuration = 0.0;
	}
	
	public void update(float deltaTime, Map map) {
		super.update(deltaTime, map);
		if(attacking) {
			this.currentAnimationDuration += deltaTime;
			if(this.currentAnimation.isFinished(this.currentAnimationDuration)) {
				this.currentAnimationDuration = 0.0;
				this.currentAnimation = getIdle();
				attacking = false;
			}
		}
	}
	
	@Override
	public void qAbility(int xMouse, int yMouse, Map map) {
		if(!attacking) {
			attacking = true;
			this.state = Hero.ATTACKING;
			this.currentAnimationDuration = 0.0;
			this.currentAnimation = this.attack[(int) Math.round(Math.random()*2.0)];
			this.currentAnimationDuration = 0.0;

		}
	}
	
	@Override
	public void wAbility(int xMouse, int yMouse, Map map) {
		this.xPosition = map.getStartingX();
		this.yPosition = map.getStartingY();
	}
	
	@Override
	public Animation getIdle() {
		return PLAYER_IDLE[(int)Math.round(Math.random())];
	}
	
	@Override
	public Animation getMove() {
		return PLAYER_RUN;
	}
	
	public void draw(Graphics g) {
		if(this.direction>0) {
			g.drawImage(this.currentAnimation.getCurrentImage(this.currentAnimationDuration), (int)(Constants.SCREEN_X/2-width/2), (int)(Constants.SCREEN_Y/2-height/2), (int)this.width, (int)this.height, null);	
		} else {
			g.drawImage(this.currentAnimation.getCurrentImage(this.currentAnimationDuration), (int)(Constants.SCREEN_X/2-width/2) + (int)this.width, (int)(Constants.SCREEN_Y/2-height/2), (int)-this.width, (int)this.height, null);
		}
		
	}
}
