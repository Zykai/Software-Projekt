package Enemies;

import Entity.Animation;

public class Reaper extends Enemy {

	private static Animation REAPER_MOVE;
	private static Animation REAPER_IDLE; 
	
	static {
		REAPER_IDLE = new Animation("res/monster/Reaper/sprites/reaper-idle-00", 4, 600);
		REAPER_MOVE = new Animation("res/monster/Reaper/sprites/reaper-move-00", 4, 600);
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

}
