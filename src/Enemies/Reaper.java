package Enemies;

import Entity.Animation;

public class Reaper extends Enemy {

	private static Animation REAPER_MOVE;
	private static Animation REAPER_IDLE; 
	private static Animation REAPER_ATTACK;

	static {
		REAPER_IDLE = new Animation("res/monster/Reaper/Individual Sprites/reaper-idle-00", 4, 600);
		REAPER_MOVE = new Animation("res/monster/Reaper/Individual Sprites/reaper-move-00", 4, 600);
		REAPER_ATTACK = new Animation("res/monster/Reaper/Individual Sprites/reaper-attack-00", 5, 600);
	}
	
	public Reaper(int xPos, int yPos) {
		super(xPos, yPos);
		this.height = 130;
		this.width = 110;
	}

	@Override
	protected Animation getMove() {
		return REAPER_MOVE;
	}

	@Override
	protected Animation getIdle() {
		return REAPER_IDLE;
	}

	@Override
	protected Animation getAttack() {
		return REAPER_ATTACK;
	}

}
