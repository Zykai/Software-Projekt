package Enemies;

import Constants.Constants;

public class EnemyGenerator {
	
	private double currentCapacity;
	
	public static final int GHOUL = 0;
	public static final int IMP = 1;
	public static final int UNDEAD = 2;
	public static final int SLUG = 3;
	public static final int REAPER = 4;
	public static final int PHANTOM = 5;
	
	
	public EnemyGenerator(int capacity) {
		this.currentCapacity = (double) capacity;
	}
	
	private double getCapacity(int type) {
		switch(type) {
		case GHOUL:
			return 0.5;
		case IMP:
			return 0.5;
		case UNDEAD:
			return 1;
		case SLUG:
			return 1;
		case REAPER:
			return 2;
		case PHANTOM:
			return 2.5;
		default:
			System.out.println("Illegal enemy type");
			System.exit(0);
			return 0;
		}
	}
	
	private int getEnemyCount(int type) {
		switch(type) {
		case GHOUL:
			return 3 + Constants.random(0, 10);
		case IMP:
			return 4 + Constants.random(0, 5);
		case UNDEAD:
			return 3 + Constants.random(0, 4);
		case SLUG:
			return 2 + Constants.random(0, 2);
		case REAPER:
			return 2 + Constants.random(0, 3);
		case PHANTOM:
			return (int) (1 + Math.sqrt(Constants.random(0, 4)));
		default:
			System.out.println("Illegal enemy type");
			System.exit(0);
			return 0;
		}
	}
	
	public EnemyGenInformation getNext() {
		// if enemy generation is used up
		if(currentCapacity < getCapacity(PHANTOM)) {
			return null;
		}
		
		int random = Constants.random(1, 100);
		int enemyType;
		if(random <= 24) {
			enemyType = GHOUL;
		} else if(random <= 48) {
			enemyType = IMP;
		} else if(random <= 60) {
			enemyType = UNDEAD;
		} else if(random <= 70) {
			enemyType = SLUG;
		} else if(random <= 90) {
			enemyType = REAPER;
		} else {
			enemyType = PHANTOM;
		}
		double enemyCapacity = this.getCapacity(enemyType);
		int enemyCount = this.getEnemyCount(enemyType);
		this.currentCapacity -= enemyCount * enemyCapacity;
		return new EnemyGenInformation(enemyType, enemyCount);
	}
	
	public Enemy fromType(int type, int x, int y) {
		switch(type) {
		case GHOUL:
			return new Ghoul(x,y);
		case IMP:
			return new Imp(x,y);
		case UNDEAD:
			return new Undead(x,y);
		case SLUG:
			return new Slug(x,y);
		case REAPER:
			return new Reaper(x,y);
		case PHANTOM:
			return new Phantom(x,y);
		default:
			System.out.println("Illegal enemy type");
			System.exit(0);
			return null;
		}
	}
}
