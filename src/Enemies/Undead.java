package Enemies;

import Entity.Animation;

public class Undead extends Enemy {

	private static Animation UNDEAD_IDLE;
	private static Animation UNDEAD_MOVE; 
	private static Animation UNDEAD_ATTACK; 
	
	static {
		UNDEAD_IDLE = new Animation("res/monster/Undead Warrior/Individual Sprites/undead-warrior-idle-0", 4, 600);
		UNDEAD_MOVE = new Animation("res/monster/Undead Warrior/Individual Sprites/undead-warrior-run-0", 6, 600);
		UNDEAD_ATTACK = new Animation("res/monster/Undead Warrior/Individual Sprites/undead-warrior-attack-0", 5, 600);
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

	@Override
	protected Animation getAttack() {
		return UNDEAD_ATTACK;
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
	public double getHitY(){
		return yPosition + 10;
	}

	@Override
	public double getHitWidth(){
		return width - 40;
	}

	@Override
	public double getHitHeight(){
		return height;
	}

}
