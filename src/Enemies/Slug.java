package Enemies;

import Entity.Animation;

public class Slug extends Enemy {

	private static Animation SLUG_MOVE;
	private static Animation SLUG_IDLE; 
	private static Animation SLUG_ATTACK[]; 
	
	static {
		SLUG_IDLE = new Animation("res/monster/Slug/Individual Sprites/slug-idle-0", 4, 600);
		SLUG_MOVE = new Animation("res/monster/Slug/Individual Sprites/slug-move-0", 5, 600);
		SLUG_ATTACK = new Animation[2];
		SLUG_ATTACK[0] = new Animation("res/monster/Slug/Individual Sprites/slug-attack1-0", 6, 600);
		// Second attack is missing some frames due to inconsistent naming
		SLUG_ATTACK[1] = new Animation("res/monster/Slug/Individual Sprites/slug-attack2-0", 10, 600);
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

	@Override
	protected Animation getAttack() {
		return SLUG_ATTACK[(int)Math.round(Math.random())];
	}

}
