package Player;

import java.awt.Graphics;

import Entity.Animation;
import Maps.Map;

public class Hero extends Player {
	
	private static Animation PLAYER_IDLE[];
	private static Animation PLAYER_RUN;
	private static Animation PLAYER_ATTACK[];

	static {
		PLAYER_IDLE = new Animation[2];
		PLAYER_IDLE[0] = new Animation("res/hero/adventurer-idle-0", 4, 1000);
		PLAYER_IDLE[1] = new Animation("res/hero/adventurer-idle-2-0", 4, 1000);
		PLAYER_RUN = new Animation("res/hero/adventurer-run-0", 5, 750);
		PLAYER_ATTACK = new Animation[3];
		PLAYER_ATTACK[0] = new Animation("res/hero/adventurer-attack1-0", 5, 500);
		PLAYER_ATTACK[1] = new Animation("res/hero/adventurer-attack2-0", 6, 500);
		PLAYER_ATTACK[2] = new Animation("res/hero/adventurer-attack3-0", 6, 500);
	}

	private int attackIndex;

	public Hero() {
		super();
		attackIndex = 0;
		this.height = 100;
		this.width = 80;
		this.movespeed = 0.45f;
		this.currentAnimation = PLAYER_IDLE[0];
		this.currentAnimationDuration = 0.0;
		this.currentXP = 6;
		this.maxXP = 10;
	}
	
	public void update(float deltaTime, Map map) {
		super.update(deltaTime, map);
		if(this.state == Hero.ATTACK) {
			if(this.currentAnimation.isFinished(this.currentAnimationDuration)) {
				this.currentAnimationDuration = 0.0;
				this.currentAnimation = getIdle();
				this.state = Hero.IDLE;
			}
		}
	}
	
	@Override
	public void qAbility(int xMouse, int yMouse, Map map) {
		if(this.state != Hero.ATTACK) {
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
	
	public void draw(Graphics g, int xoffset, int yoffset) {
		if(this.direction>0) {
			g.drawImage(this.currentAnimation.getCurrentImage(this.currentAnimationDuration),(int) this.xPosition + xoffset, (int) this.yPosition + yoffset, (int)this.width, (int)this.height, null);	
		} else {
			g.drawImage(this.currentAnimation.getCurrentImage(this.currentAnimationDuration), xoffset + (int) this.xPosition + (int)this.width, (int)this.yPosition + yoffset, (int)-this.width, (int)this.height, null);
		}
		super.draw(g);
	}
}
