package Entity;

public abstract class Entity {
	private int hp;
	protected float movespeed = 0.6f;
	
	protected double xPosition, yPosition;
	protected double width, height;
	// Variablen f�r die Bewegung
	double xdirection, ydirection;
	double xgoal, ygoal;
	double prevDistance;
	protected boolean moving;
	
	public void update(float deltaTime){
		if(moving) {
			xPosition += xdirection * deltaTime * movespeed;
			yPosition += ydirection * deltaTime * movespeed;
			// Distanz mit Satz des Pythagoars, Vermeiden von Wurzeln f�r bessere Performance
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
		// Quadrieren, da um sp�teres Wurzelziehen beim Distanz-berechnen zu vermeiden
		prevDistance *= prevDistance;
	}
}