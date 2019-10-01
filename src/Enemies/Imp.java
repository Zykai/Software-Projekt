package Enemies;

import Entity.Animation;

public class Imp extends Enemy {
	
	private static Animation IMP_IDLE;
	private static Animation IMP_MOVE;
	
	static {
		IMP_IDLE = new Animation("res/monster/Imp/sprites/imp-idle-0", 5, 600);
		IMP_MOVE = new Animation("res/monster/Imp/sprites/imp-move-0", 5, 600);
	}
	
	public Imp(int xPos, int yPos) {
		super(xPos, yPos);
		this.height = 70;
		this.width = 70;
	}

	@Override
	public Animation getIdle() {
		return IMP_IDLE;
	}

	@Override
	protected Animation getMove() {
		return IMP_MOVE;
	}
}
