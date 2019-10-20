package Player;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import Enemies.Enemy;
import Entity.Animation;
import Maps.Map;

public class Mage extends Player {
	
	private static Animation PLAYER_IDLE[];
	private static Animation PLAYER_RUN;
	private static Animation PLAYER_ATTACK[];

	static {
		PLAYER_IDLE = new Animation[2];
		PLAYER_IDLE[0] = new Animation("res/monster/Necromancer/Individual Sprites/necromancer-idle-0", 4, 1000);
		PLAYER_IDLE[1] = new Animation("res/monster/Necromancer/Individual Sprites/necromancer-hurt-0", 3, 1000);
		PLAYER_RUN = new Animation("res/monster/Necromancer/Individual Sprites/necromancer-move-0", 6, 750);
		PLAYER_ATTACK = new Animation[3];
		PLAYER_ATTACK[0] = new Animation("res/monster/Necromancer/Individual Sprites/necromancer-attack-0", 5, 100);
		PLAYER_ATTACK[1] = new Animation("res/monster/Necromancer/Individual Sprites/necromancer-summon-0", 6, 100);
		PLAYER_ATTACK[2] = new Animation("res/monster/Necromancer/Individual Sprites/necromancer-summon-0", 6, 100);
	}

	private ArrayList<MageProjectile> projectiles;
	private int attackIndex;

	private int projectileXDir;
	private int projectileYDir;

	public Mage() {
		super();
		attackIndex = 0;
		this.height = 100;
		this.width = 80;
		this.movespeed = 45f;
		this.attackDamage = 10;
		this.critChance = 5;
		this.currentAnimation = PLAYER_IDLE[0];
		this.currentAnimationDuration = 0.0;
		this.currentXP = 6;
		this.maxXP = 10;
		projectiles = new ArrayList<MageProjectile>();
	}
	
	public void update(float deltaTime, Map map) {
		super.update(deltaTime, map);
		for(int i = 0; i < this.projectiles.size(); i++){
			if(this.projectiles.get(i).updateProjectile(deltaTime, map)){
				this.projectiles.remove(i);
				i--;
			}
		}
		if(this.state == Mage.ATTACK) {
			if(this.currentAnimation.isFinished(this.currentAnimationDuration)) {
				projectiles.add(new MageProjectile(this.getHitCenterX(), this.getHitCenterY(), projectileXDir, projectileYDir));
				this.currentAnimationDuration = 0.0;
				this.currentAnimation = getIdle();
				this.state = Mage.IDLE;
			}
		}
		LinkedList<Enemy> enemies = map.getEnemyList();
		for(int i = 0; i < projectiles.size(); i++){
			MageProjectile current = projectiles.get(i);
			for(int j = 0; j < enemies.size(); j++){
				Enemy currentEnemy = enemies.get(j);
				if(current.hitEntity(currentEnemy)){
					if(current.canDamage(currentEnemy)){
						this.applyDamage(currentEnemy, 1.2);
					}
				}
			}
		}
	}
	
	@Override
	public void qAbility(int xMouse, int yMouse, Map map) {
		if(this.state != Mage.ATTACK) {
			projectileXDir = xMouse;
			projectileYDir = yMouse;
			this.direction = xMouse < this.getHitCenterX() ? -1 : 1;
			this.state = Mage.ATTACK;
			this.currentAnimationDuration = 0.0;
			this.currentAnimation = this.getAttack();
			this.currentAnimationDuration = 0.0;
		}
	}
	
	@Override
	public void wAbility(int xMouse, int yMouse, Map map) {
		this.xPosition = map.getStartingX();
		this.yPosition = map.getStartingY();
	}
	
	@Override
	protected Animation getIdle() {
		return PLAYER_IDLE[(int)Math.round(Math.random())];
	}
	
	@Override
	protected Animation getMove() {
		return PLAYER_RUN;
	}

	@Override
	protected Animation getAttack(){
		return PLAYER_ATTACK[attackIndex++ % 3];
	}

	@Override
	protected Animation getDie(){
		return null;
	}
	
	public void draw(Graphics g, int xoffset, int yoffset) {
		if(direction < 0) {
			g.drawImage(this.currentAnimation.getCurrentImage(this.currentAnimationDuration), (int)(xPosition + xoffset), (int) yPosition + yoffset, (int)this.width, (int)this.height, null);	
		} else {
			g.drawImage(this.currentAnimation.getCurrentImage(this.currentAnimationDuration), (int) (xPosition + xoffset + this.width * 1.5), (int) yPosition + yoffset, (int)-this.width, (int)this.height, null);
		}
		for(int i = 0; i < this.projectiles.size(); i++){
			projectiles.get(i).draw(g, xoffset, yoffset);
		}
		super.draw(g, xoffset, yoffset);
	}

	@Override
	public double getHitX(){
		return xPosition + 30;
	}

	@Override
	public double getHitY(){
		return yPosition + 30;
	}

	@Override
	public double getHitWidth(){
		return width - 20;
	}

	@Override
	public double getHitHeight(){
		return height - 30;
	}
}
