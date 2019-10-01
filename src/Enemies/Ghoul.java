package Enemies;

import Entity.Animation;

public class Ghoul extends Enemy {

	private static Animation GHOUL_MOVE;
	private static Animation GHOUL_IDLE; 
	
	static {
		GHOUL_IDLE = new Animation("res/monster/Ghoul/sprites/ghoul-idle-0", 4, 600);
		GHOUL_MOVE = new Animation("res/monster/Ghoul/sprites/ghoul-run-0", 6, 600);
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

}
