package Enemies;

import Entity.Animation;

public class Slug extends Enemy {

	private static Animation SLUG_MOVE;
	private static Animation SLUG_IDLE; 
	
	static {
		SLUG_IDLE = new Animation("res/monster/Slug/sprites/slug-idle-0", 4, 600);
		SLUG_MOVE = new Animation("res/monster/Slug/sprites/slug-move-0", 5, 600);
	}
	
	public Slug(int xPos, int yPos) {
		super(xPos, yPos);
		this.height = 60;
		this.width = 60;
	}

	@Override
	protected Animation getMove() {
		return SLUG_MOVE;
	}

	@Override
	protected Animation getIdle() {
		return SLUG_IDLE;
	}

}
