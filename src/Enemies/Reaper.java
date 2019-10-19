package Enemies;

import Entity.Animation;

public class Reaper extends Enemy {

	private static Animation REAPER_MOVE;
	private static Animation REAPER_IDLE; 
	private static Animation REAPER_ATTACK;
	private static Animation REAPER_DIE;

	static {
		REAPER_IDLE = new Animation("res/monster/Reaper/Individual Sprites/reaper-idle-00", 4, 600);
		REAPER_MOVE = new Animation("res/monster/Reaper/Individual Sprites/reaper-move-00", 4, 600);
		REAPER_ATTACK = new Animation("res/monster/Reaper/Individual Sprites/reaper-attack-00", 5, 600);
		REAPER_DIE = new Animation("res/monster/Reaper/Individual Sprites/reaper-die-00", 10, 1000); // needs one more frame
	}
	
	public Reaper(int xPos, int yPos) {
		super(xPos, yPos);
		this.height = 130;
		this.width = 110;
		floating = true;
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

	@Override
	protected Animation getDie() {
		return REAPER_DIE;
	}

	@Override
	public double getHitX(){
		return xPosition+10;
	}

	@Override
	public double getHitY(){
		return yPosition + 15;
	}

	@Override
	public double getHitWidth(){
		return width-15;
	}

	@Override
	public double getHitHeight(){
		return height-40;
	}

}
