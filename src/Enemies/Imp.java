package Enemies;

import Entity.Animation;

public class Imp extends Enemy {
	
	private static Animation IMP_IDLE;
	private static Animation IMP_MOVE;
	private static Animation IMP_ATTACK;

	static {
		IMP_IDLE = new Animation("res/monster/Imp/Individual Sprites/imp-idle-0", 5, 600);
		IMP_MOVE = new Animation("res/monster/Imp/Individual Sprites/imp-move-0", 5, 600);
		IMP_ATTACK = new Animation("res/monster/Imp/Individual Sprites/imp-attack-0", 10, 600);
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

	@Override
	protected Animation getAttack() {
		return IMP_ATTACK;
	}

	@Override
	public double getHitX(){
		return xPosition+5;
	}

	@Override
	public double getHitY(){
		return yPosition+5;
	}

	@Override
	public double getHitWidth(){
		return width-10;
	}

	@Override
	public double getHitHeight(){
		return height-10;
	}

}
