package Enemies;

import Entity.Animation;

public class Phantom extends Enemy {

	private static Animation PHANTOM_MOVE;
	private static Animation PHANTOM_IDLE; 
	
	static {
		PHANTOM_IDLE = new Animation("res/monster/Phantom Knight/sprites/phantom-knight-idle-0", 4, 600);
		PHANTOM_MOVE = new Animation("res/monster/Phantom Knight/sprites/phantom-knight-run-0", 6, 600);
	}
	
	public Phantom(int xPos, int yPos) {
		super(xPos, yPos);
		this.height = 130;
		this.width = 110;
	}

	@Override
	protected Animation getMove() {
		return PHANTOM_MOVE;
	}

	@Override
	protected Animation getIdle() {
		return PHANTOM_IDLE;
	}

}
