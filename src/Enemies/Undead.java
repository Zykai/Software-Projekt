package Enemies;

import Entity.Animation;

public class Undead extends Enemy {

	private static Animation UNDEAD_IDLE;
	private static Animation UNDEAD_MOVE; 
	
	static {
		UNDEAD_IDLE = new Animation("res/monster/Undead Warrior/sprites/undead-warrior-idle-0", 4, 600);
		UNDEAD_MOVE = new Animation("res/monster/Undead Warrior/sprites/undead-warrior-run-0", 6, 600);
	}
	
	public Undead(int xPos, int yPos) {
		super(xPos, yPos);
		this.height = 90;
		this.width = 90;
		
	}

	@Override
	protected Animation getMove() {
		return UNDEAD_MOVE;
	}

	@Override
	protected Animation getIdle() {
		return UNDEAD_IDLE;
	}

}
