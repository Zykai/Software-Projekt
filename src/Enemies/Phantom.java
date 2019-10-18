package Enemies;

import Entity.Animation;

public class Phantom extends Enemy {

	private static Animation PHANTOM_MOVE;
	private static Animation PHANTOM_IDLE; 
	private static Animation PHANTOM_ATTACK;

	static {
		PHANTOM_IDLE = new Animation("res/monster/Phantom Knight/Individual Sprites/phantom-knight-idle-0", 4, 600);
		PHANTOM_MOVE = new Animation("res/monster/Phantom Knight/Individual Sprites/phantom-knight-run-0", 6, 600);
		PHANTOM_ATTACK = new Animation("res/monster/Phantom Knight/Individual Sprites/phantom-knight-attack1-0", 7, 600);
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

	@Override
	protected Animation getAttack() {
		return PHANTOM_ATTACK;
	}

	@Override
	public double getHitX(){
		return xPosition+5;
	}

	@Override
	public double getHitY(){
		return yPosition + 40;
	}

	@Override
	public double getHitWidth(){
		return width - 30;
	}

	@Override
	public double getHitHeight(){
		return height - 40;
	}

}
