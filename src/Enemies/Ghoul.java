package Enemies;

import Entity.Animation;

public class Ghoul extends Enemy {

	private static Animation GHOUL_MOVE;
	private static Animation GHOUL_IDLE; 
	private static Animation GHOUL_ATTACK; 
	private static Animation GHOUL_DIE; 

	static {
		GHOUL_IDLE = new Animation("res/monster/Ghoul/Individual Sprites/ghoul-idle-0", 4, 600);
		GHOUL_MOVE = new Animation("res/monster/Ghoul/Individual Sprites/ghoul-run-0", 6, 600);
		GHOUL_ATTACK = new Animation("res/monster/Ghoul/Individual Sprites/ghoul-attack-0", 5, 600);
		GHOUL_DIE = new Animation("res/monster/Ghoul/Individual Sprites/ghoul-die-0", 8, 800);
	}
	
	public Ghoul(int xPos, int yPos) {
		super(xPos, yPos);
		this.height = 40;
		this.width = 90;
	}

	@Override
	protected Animation getMove() {
		return GHOUL_MOVE;
	}

	@Override
	protected Animation getIdle() {
		return GHOUL_IDLE;
	}

	@Override
	protected Animation getAttack() {
		return GHOUL_ATTACK;
	}

	@Override
	protected Animation getDie() {
		return GHOUL_DIE;
	}

	@Override
	public double getHitX(){
		if(this.direction>0){
			return xPosition;
		} else {
			return xPosition+30;
		}
	}

	@Override
	public double getHitWidth(){
		return width - 30;
	}

}
