package Enemies;

import Constants.Constants;

public class EnemyGenerator {
	
	private double currentCapacity;
	
	public final int RAT = 0;
	public final int WASP = 1;
	public final int MANDRAKE = 2;
	public final int SATYR = 3;
	public final int BANDIT = 4;
	public final int SHADE = 5;
	public final int SAMURAI = 6;
	
	
	public EnemyGenerator(int capacity) {
		this.currentCapacity = (double) capacity;
	}
	
	private double getCapacity(int type) {
		switch(type) {
		case RAT:
			return 0.5;
		case WASP:
			return 0.5;
		case MANDRAKE:
			return 1;
		case SATYR:
			return 1;
		case BANDIT:
			return 2;
		case SHADE:
			return 2.5;
		case SAMURAI:
			return 3;
		default:
			System.out.println("Illegal enemy type");
			System.exit(0);
			return 0;
		}
	}
	
	private int getEnemyCount(int type) {
		switch(type) {
		case RAT:
			return 3 + Constants.random(0, 10);
		case WASP:
			return 4 + Constants.random(0, 5);
		case MANDRAKE:
			return 3 + Constants.random(0, 4);
		case SATYR:
			return 2 + Constants.random(0, 2);
		case BANDIT:
			return 2 + Constants.random(0, 3);
		case SHADE:
			return 1 + Constants.random(0, 2);
		case SAMURAI:
			return (int) (1 + Math.sqrt(Constants.random(0, 4)));
		default:
			System.out.println("Illegal enemy type");
			System.exit(0);
			return 0;
		}
	}
	
	public EnemyGenInformation getNext() {
		// if enemy generation is used up
		if(currentCapacity < getCapacity(SAMURAI)) {
			return null;
		}
		
		int random = Constants.random(1, 100);
		int enemyType;
		if(random <= 16) {
			enemyType = RAT;
		} else if(random <= 32) {
			enemyType = WASP;
		} else if(random <= 48) {
			enemyType = MANDRAKE;
		} else if(random <= 64) {
			enemyType = SATYR;
		} else if(random <= 80) {
			enemyType = BANDIT;
		} else if(random <= 90) {
			enemyType = SHADE;
		} else {
			enemyType = SAMURAI;
		}
		double enemyCapacity = this.getCapacity(enemyType);
		int enemyCount = this.getEnemyCount(enemyType);
		this.currentCapacity -= enemyCount * enemyCapacity;
		return new EnemyGenInformation(enemyType, enemyCount);
	}
}
