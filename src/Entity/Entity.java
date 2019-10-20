package Entity;

import java.awt.Color;
import java.awt.Graphics;

import Constants.Constants;
import Maps.Map;

public abstract class Entity {
	
	public static final int IDLE = 0;
	public static final int MOVING = 1;
	public static final int ATTACK = 2;
	
	public int level;
	public int currentXP;
	public int xpBoost;
	public int critChance;
	public int hpRegen;
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
	private boolean stop;
	
	protected int direction;
	
	// Animations
	protected Animation currentAnimation;
	protected double currentAnimationDuration;
	
	protected int state;
	
	protected boolean floating;

	public Entity() {
		floating = false;
		this.currentAnimationDuration = 0.0;
		this.currentAnimation = getIdle();
	}
	
	// Update position and animation state
	public void update(float deltaTime, Map map){
		this.currentAnimationDuration += deltaTime;
		if(state == MOVING) {
			this.currentAnimation = getMove();
			double newX = xPosition + xdirection * deltaTime * movespeed;
			double newY = yPosition + ydirection * deltaTime * movespeed;
			int moves = 0;
			if(map.isCorrectPosition(newX, yPosition, this.width, this.height) || floating) {
				xPosition = newX;
				moves++;
			}
			if(map.isCorrectPosition(xPosition, newY, this.width, this.height) || floating) {
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
			if((prevDistance < distance || Double.isNaN(distance)) && stop) {
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

	protected abstract Animation getDie();

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
		stop = true;
	}
	
	public void moveDif(int xdif, int ydif, boolean stop){
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
		this.stop = stop;
	}
	
	public void applyDamage(Entity e, double mul){
		// Calculate damage
		double pureDamage = (this.attackDamage * mul * (Constants.random(0,100) > this.critChance ? 1.5 : 1.0));
		int damage = (int) pureDamage * 100 / (100 + this.armor);
		// Apply lifesteal
		int heal = this.lifeSteal * damage / 100;
		this.heal(heal);
		// Apply damage
		e.currentHP -= damage;
	}

	public void heal(int amount){
		this.currentHP += amount;
		this.currentHP = Math.min(this.currentHP, this.maxHP);
	}
	
	private boolean interSectPoint(Entity e, double x, double y){
		double ex = e.getHitX();
		double ey = e.getHitY();
		double ew = e.getHitWidth();
		double eh = e.getHitHeight();
		if(ex < x && x < ex + ew &&  y > ey && y < ey + eh){
			return true;
		}
		return false;
	}

	public boolean intersectEntity(Entity e){
		double ex = this.getHitX();
		double ey = this.getHitY();
		double ew = this.getHitWidth();
		double eh = this.getHitHeight();
		return this.interSectPoint(e, ex, ey) || this.interSectPoint(e, ex+ew, ey) || this.interSectPoint(e, ex, ey+eh) || this.interSectPoint(e, ex+ew, ey+eh);
	}

	public boolean hitEntity(Entity e){
		if(this.intersectEntity(e) || e.intersectEntity(this)){
			// Check if facing towards the other entity
			if(this.direction > 0){
				return this.getHitX() + this.getHitWidth() / 2 <= e.getHitX() + e.getHitWidth() / 2;
			} else {
				return this.getHitX() + this.getHitWidth() / 2 >= e.getHitX() + e.getHitWidth() / 2;	
			}
		} 
		return false;
	}

	public void setDead(){
		this.currentAnimation = getDie();
		this.currentAnimationDuration = 0;
		this.state = Entity.IDLE;
	}

	public boolean updateDead(float deltaTime){
		if(this.currentAnimation.isFinished(this.currentAnimationDuration)){
			return true;
		} else {
			this.currentAnimationDuration += deltaTime;
			return false;
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
	
	public void draw(Graphics g, int xoffset, int yoffset){
		g.drawRect((int)(this.getHitX() + xoffset), (int)(this.getHitY() + yoffset),(int) this.getHitWidth(), (int)this.getHitHeight());
	}

	public double getHitX(){
		return xPosition;
	}

	public double getHitY(){
		return yPosition;
	}

	public double getHitWidth(){
		return width;
	}

	public double getHitHeight(){
		return height;
	}

	public double getHitCenterX(){
		return this.getHitX() + this.getHitWidth() / 2;
	}

	public double getHitCenterY(){
		return this.getHitY() + this.getHitHeight() / 2;
	}
	
	int x = (int)xPosition;
	int y = (int)yPosition;
	public void drawHealth(Graphics g, int x, int y) {
		g.setColor(Color.red);
		g.drawRect(x, y, 100, 20);
	}
}
