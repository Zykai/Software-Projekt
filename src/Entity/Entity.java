package Entity;

import Enemies.Enemy;
import Enemies.Imp;
import Maps.Map;

public abstract class Entity {
	
	public static final int IDLE = 0;
	public static final int MOVING = 1;
	public static final int ATTACK = 2;
	
	public int level;
	public int currentXP;
	public int maxXP;
	public int currentHP;
	public int maxHP;
	public int armor;
	public int attackDamage;
	public int abilityPower;
	public int lifeSteal;
	public int coolDownReduction;
	public double attackSpeed;
	public float movespeed = 0.6f;
	
	protected double xPosition, yPosition;
	protected double width, height;
	// Variablen f�r die Bewegung
	protected double xdirection, ydirection;
	protected double xgoal;
	protected double ygoal;
	protected double prevDistance;
	
	protected int direction;
	
	// Animations
	protected Animation currentAnimation;
	protected double currentAnimationDuration;
	
	protected int state;
	
	public Entity() {
		this.currentAnimationDuration = 0.0;
		this.currentAnimation = getIdle();
	}
	
	public void update(float deltaTime, Map map){
		this.currentAnimationDuration += deltaTime;
		if(state == MOVING) {
			this.currentAnimation = getMove();
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
				state = IDLE;
				this.currentAnimation = this.getIdle();
				return;
			} 
			// Distanz mit Satz des Pythagoras, Vermeiden von Wurzeln f�r bessere Performance
			double distance = (xgoal - xPosition) * (xgoal - xPosition) + (ygoal - yPosition) * (ygoal - yPosition);
			// Erster Teil: Erreichen des Ziels, Zweiter Teil behebt einen Fehler, wenn man sich auf den aktuellen Punkt bewegt
			if(prevDistance < distance || Double.isNaN(distance)) {
				state = IDLE;
				this.currentAnimation = this.getIdle();
				return;
			}
			prevDistance = distance;
		}
	}
	
	protected abstract Animation getMove();

	protected abstract Animation getIdle();
	
	protected abstract Animation getAttack();

	public void moveTo(int x, int y){
		x -= width / 2;
		y -= height / 2;
		double xdif = x - getCenterX();
		double ydif = y - getCenterY();
		prevDistance = Math.sqrt(xdif* xdif + ydif * ydif);
		xdirection = xdif / prevDistance;
		ydirection = ydif / prevDistance;
		state = MOVING;
		xgoal = x;
		ygoal = y;
		// Quadrieren, um sp�teres Wurzelziehen beim Distanz-berechnen zu vermeiden
		prevDistance *= prevDistance;
		if (xdirection > 0) {
			this.direction = 1;
		} else {
			this.direction = -1;
		}
	}
	
	public void moveDif(int xdif, int ydif){
		prevDistance = Math.sqrt(xdif* xdif + ydif * ydif);
		xdirection = xdif / prevDistance;
		ydirection = ydif / prevDistance;
		state = MOVING;
		xgoal = this.xPosition + xdif;
		ygoal = this.yPosition + ydif;
		// Quadrieren, um sp�teres Wurzelziehen beim Distanz-berechnen zu vermeiden
		prevDistance *= prevDistance;
		if (xdirection > 0) {
			this.direction = 1;
		} else {
			this.direction = -1;
		}
	}
	
	public int getHp() {
		return currentHP;
	}

	public void setHp(int hp) {
		this.currentHP = hp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getX() {
		return this.xPosition;
	}
	
	public double getY() {
		return this.yPosition;
	}
	
	public double getCenterX(){
		return this.xPosition + this.width * 0.5;
	}

	public double getCenterY(){
		return this.yPosition + this.height * 0.5;
	}

	public double getWidth() {
		return this.width;
	}
	
	public double getHeight() {
		return this.height;
	}

	public void setX(double x) {
		this.xPosition = x;
	}
	
	public void setY(double y) {
		this.yPosition = y;
	}
}
