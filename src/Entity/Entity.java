package Entity;

import Maps.Map;

public abstract class Entity {
	private int hp;
	protected float movespeed = 0.6f;
	
	protected double xPosition, yPosition;
	protected double width, height;
	// Variablen für die Bewegung
	double xdirection, ydirection;
	double xgoal, ygoal;
	double prevDistance;
	protected boolean moving;
	
	public void update(float deltaTime, Map map){
		if(moving) {
			double newX = xPosition + xdirection * deltaTime * movespeed;
			double newY = yPosition + ydirection * deltaTime * movespeed;
			if(map.isCorrectPosition(newX, newY, this.width)) {
				xPosition = newX;
				yPosition = newY;
			} else {
				moving = false;
				return;
			}
			// Distanz mit Satz des Pythagoras, Vermeiden von Wurzeln für bessere Performance
			double distance = (xgoal - xPosition) * (xgoal - xPosition) + (ygoal - yPosition) * (ygoal - yPosition);
			// Erster Teil: Erreichen des Ziels, Zweiter Teil behebt einen Fehler, wenn man sich auf den aktuellen Punkt bewegt
			if(prevDistance < distance || Double.isNaN(distance)) {
				moving = false;
				xPosition = xgoal;
				yPosition = ygoal;
			}
			prevDistance = distance;
		}
	}
	
	public void moveTo(int x, int y){
		x -= width / 2;
		y -= height / 2;
		double xdif = x - xPosition;
		double ydif = y - yPosition;
		prevDistance = Math.sqrt(xdif* xdif + ydif * ydif);
		xdirection = xdif / prevDistance;
		ydirection = ydif / prevDistance;
		moving = true;
		xgoal = x;
		ygoal = y;
		// Quadrieren, um späteres Wurzelziehen beim Distanz-berechnen zu vermeiden
		prevDistance *= prevDistance;
	}
	
	public void moveDif(int xdif, int ydif){
		prevDistance = Math.sqrt(xdif* xdif + ydif * ydif);
		xdirection = xdif / prevDistance;
		ydirection = ydif / prevDistance;
		moving = true;
		xgoal = this.xPosition + xdif;
		ygoal = this.yPosition + ydif;
		// Quadrieren, um späteres Wurzelziehen beim Distanz-berechnen zu vermeiden
		prevDistance *= prevDistance;
	}
	
	public double getX() {
		return this.xPosition;
	}
	
	public double getY() {
		return this.yPosition;
	}
}
