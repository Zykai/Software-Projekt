package Player;

import java.awt.Color;
import java.awt.Graphics;

import Constants.Constants;
import Entity.Animation;
import Maps.Map;

public class Hero extends Player {

	private Animation[] idle;
	private Animation[] attack;
	private Animation run;
	private Animation currentAnimation;
	private boolean attacking;
	
	public Hero() {
		super();
		attacking = false;
		this.height = 100;
		this.width = 80;
		this.movespeed = 0.45f;
		idle = new Animation[2];
		idle[0] = new Animation("res/hero/adventurer-idle-0", 4, 1000);
		idle[1] = new Animation("res/hero/adventurer-idle-2-0", 4, 1000);
		run = new Animation("res/hero/adventurer-run-0", 5, 750);
		attack = new Animation[3];
		attack[0] = new Animation("res/hero/adventurer-attack1-0", 	5, 400);
		attack[1] = new Animation("res/hero/adventurer-attack2-0", 	6, 400);
		attack[2] = new Animation("res/hero/adventurer-attack3-0", 	6, 400);
		this.currentAnimation = idle[0];
	}
	
	public void update(float deltaTime, Map map) {
		if(moving) {
			this.currentAnimation = run;
			double newX = xPosition + xdirection * deltaTime * movespeed;
			double newY = yPosition + ydirection * deltaTime * movespeed;
			int moves = 0;
			if(map.isCorrectPosition(newX, yPosition, this.width, this.height)) {
				xPosition = newX;
				moves++;
			}
			if(map.isCorrectPosition(xPosition, newY, this.width, this.height)) {
				yPosition = newY;
				moves++;
			} 
			if(moves == 0){
				moving = false;
				this.currentAnimation = this.idle[(int)Math.round(Math.random())];
				return;
			} 
			// Distanz mit Satz des Pythagoras, Vermeiden von Wurzeln für bessere Performance
			double distance = (xgoal - xPosition) * (xgoal - xPosition) + (ygoal - yPosition) * (ygoal - yPosition);
			// Erster Teil: Erreichen des Ziels, Zweiter Teil behebt einen Fehler, wenn man sich auf den aktuellen Punkt bewegt
			if(prevDistance < distance || Double.isNaN(distance)) {
				moving = false;
				this.currentAnimation = this.idle[(int)Math.round(Math.random())];
				return;
			}
			prevDistance = distance;
			this.currentAnimation.advance(deltaTime);
		} else if(attacking) {
			this.currentAnimation.advance(deltaTime);
			if(this.currentAnimation.isFinished()) {
				this.currentAnimation.reset();
				this.currentAnimation = this.idle[(int)Math.round(Math.random())];
				attacking = false;
			}
		}
		this.currentAnimation.advance(deltaTime);
	}
	
	@Override
	public void qAbility(int xMouse, int yMouse, Map map) {
		if(!attacking) {
			attacking = true;
			this.moving = false;
			this.currentAnimation.reset();
			this.currentAnimation = this.attack[(int) Math.round(Math.random()*2.0)];
			this.currentAnimation.reset();

		}
	}
	
	@Override
	public void wAbility(int xMouse, int yMouse, Map map) {
		this.xPosition = map.getStartingX();
		this.yPosition = map.getStartingY();
	}
	
	public void draw(Graphics g) {
		if(this.direction>0) {
			g.drawImage(this.currentAnimation.getCurrentImage(), (int)(Constants.SCREEN_X/2-width/2), (int)(Constants.SCREEN_Y/2-height/2), (int)this.width, (int)this.height, null);	
		} else {
			g.drawImage(this.currentAnimation.getCurrentImage(), (int)(Constants.SCREEN_X/2-width/2) + (int)this.width, (int)(Constants.SCREEN_Y/2-height/2), (int)-this.width, (int)this.height, null);
		}
		
	}
}
